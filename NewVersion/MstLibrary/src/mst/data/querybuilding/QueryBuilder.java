/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import mst.data.connection.DriverTypes;
import static mst.data.connection.DriverTypes.*;
import mst.data.connection.Parameter;
import static mst.data.querybuilding.QueryTypes.*;

/**
 *
 * @author Mustafa SACLI
 */
public class QueryBuilder implements IQueryBuilder, IQueryTypes {

    private DriverTypes _connectionType = null;
    private QueryTypes _queryType;
    private IBaseBO _baseBO;
    private String _query = "";
    private ArrayList<Parameter> params = null;
    private String queryFormat = "";

    public QueryBuilder() throws Throwable {
        this(null, null, null);
    }

    public QueryBuilder(IBaseBO baseBO, QueryTypes queryType) throws Throwable {
        this(baseBO, queryType, DriverTypes.ApacheDerby);
    }

    public QueryBuilder(IBaseBO baseBO, QueryTypes queryType, DriverTypes connectionType) throws Throwable {
        this.params = new ArrayList<Parameter>();
        _baseBO = baseBO;
        _queryType = queryType;
        _connectionType = connectionType;
        params = getAllParameters();
        _query = getAllQuery();
    }

    /**
     * @return the _queryType
     */
    @Override
    public QueryTypes getQueryType() {
        return _queryType;
    }

    /**
     * @param queryType the _queryType to set
     */
    @Override
    public void setQueryType(QueryTypes queryType) {
        this._queryType = queryType;
    }

    /**
     * @return the _connectionType
     */
    @Override
    public DriverTypes getConnectionType() {
        return _connectionType;
    }

    /**
     * @param connectionType the _connectionType to set
     */
    @Override
    public void setConnectionType(DriverTypes connectionType) {
        this._connectionType = connectionType;
    }

    @Override
    public Parameter[] getParameters() {
        if (params == null || params.isEmpty()) {
            return null;
        } else {
            Parameter[] parameters = new Parameter[params.size()];
            parameters = params.toArray(parameters);
            /*for (int i = 0; i < parameters.length; i++) {
             parameters[i] = params.get(i);
             }*/
            return parameters;
        }
    }

    private String Queryformat() {
        String retQueryfomat = "";
        switch (_queryType) {
            case Select:
                retQueryfomat = "Select * From %s;";
                break;
            case InsertAndGetId:
            case Insert:
                retQueryfomat = "Insert Into %s(%s) Values (%s);";
                break;

            case Delete:
                retQueryfomat = "Delete From %s Where %s;";
                break;

            case Update:
                retQueryfomat = "Update %s Set %s Where %s;";
                break;

            case SelectWhere:
                retQueryfomat = "Select * From %s Where %s;";
                break;
        }

        return retQueryfomat;
    }

    /**
     *
     * @return Query of Given table object with given parameter and querytype.
     */
    @Override
    public String getQuery() {
        return _query;
    }

    private ArrayList<Parameter> getAllParameters() throws Throwable {
        try {
            ArrayList<Parameter> parameters = new ArrayList<Parameter>();;
            ArrayList<String> colList;
            String col;
            Field f;
            int index;
            switch (_queryType) {
                case Select:
                    return null;
                /**
                 * case Insert and InsertAndGetId
                 */
                case InsertAndGetId:
                case Insert:
                    colList = _baseBO.getColumnChangeList();
                    colList.remove(_baseBO.getIdColumn());
                    index = 1;
                    for (int i = 0; i < colList.size(); i++) {
                        col = colList.get(i);
                        if (!col.matches(_baseBO.getIdColumn())) {
                            f = _baseBO.getClass().getDeclaredField(String.format("_%s", col));
                            f.setAccessible(true);
                            Object val = f.get(_baseBO);
                            f.setAccessible(false);
                            parameters.add(new Parameter(index++, val));
                        }
                    }
                    return parameters;
                /**
                 * case Update
                 */
                case Update:
                    index = 1;
                    colList = _baseBO.getColumnChangeList();

                    for (int i = 0; i < colList.size(); i++) {
                        col = colList.get(i);
                        if (!col.matches(_baseBO.getIdColumn())) {
                            f = _baseBO.getClass().getDeclaredField(String.format("_%s", col));
                            f.setAccessible(true);
                            Object val = f.get(_baseBO);
                            f.setAccessible(false);
                            parameters.add(new Parameter(index++, val));
                        }
                    }
                    f = _baseBO.getClass().getDeclaredField(String.format("_%s", _baseBO.getIdColumn()));
                    f.setAccessible(true);
                    Object idVal = f.get(_baseBO);
                    f.setAccessible(false);
                    parameters.add(new Parameter(index, idVal));
                    return parameters;

                /**
                 * case Delete
                 */
                case Delete:
                    f = _baseBO.getClass().getDeclaredField(String.format("_%s", _baseBO.getIdColumn()));
                    f.setAccessible(true);
                    Object idValDel = f.get(_baseBO);
                    f.setAccessible(false);
                    parameters.add(new Parameter(1, idValDel));
                    return parameters;


                case SelectWhere:
                    colList = _baseBO.getColumnChangeList();
                    for (int i = 0; i < colList.size(); i++) {
                        col = colList.get(i);
                        f = _baseBO.getClass().getDeclaredField(String.format("_%s", col));
                        f.setAccessible(true);
                        Object val = f.get(_baseBO);
                        f.setAccessible(false);
                        parameters.add(new Parameter(i + 1, val));
                    }
                    return parameters;

            }
        } catch (Throwable t) {
            throw t;
        }
        return null;
    }

    private String getAllQuery() {
        queryFormat = Queryformat();
        String query = "";
        switch (_queryType) {

            case Select:
                query = String.format(queryFormat, _baseBO.getTable());
                break;

            case Insert:
                query = String.format(queryFormat, _baseBO.getTable(), getColumnParts(), getWhere_Id_ParamPart());
                break;

            case Update:
                query = String.format(queryFormat, _baseBO.getTable(), getColumnParts(), getWhere_Id_ParamPart());
                break;

            case Delete:
                query = String.format(queryFormat, _baseBO.getTable(), getWhere_Id_ParamPart());
                break;

            case InsertAndGetId:
                query = String.format(queryFormat, _baseBO.getTable(), getColumnParts(), getWhere_Id_ParamPart());
                query = query.concat(getIdentity());
                break;

            case SelectWhere:
                query = String.format(queryFormat, _baseBO.getTable(), getWhere_Id_ParamPart());
                break;
        }
        return query;
    }

    private String getColumnParts() {
        StringBuffer strBuffer = new StringBuffer();
        String cols = "";
        switch (_queryType) {
            case SelectWhere:
            case Select:
            case Delete:
                return "";

            case InsertAndGetId:
            case Insert:
                for (String col : _baseBO.getColumnChangeList()) {
                    if (!col.matches(_baseBO.getIdColumn())) {
                        strBuffer.append(String.format("%s,", col));
                    }
                }
                cols = strBuffer.toString();
                cols = cols.length() > 0 ? cols.substring(0, cols.length() - 1) : "";
                return cols;

            case Update:
                for (String col : _baseBO.getColumnChangeList()) {
                    if (!col.matches(_baseBO.getIdColumn())) {
                        strBuffer.append(String.format("%s=?,", col));
                    }
                }
                cols = strBuffer.toString();
                cols = cols.length() > 0 ? cols.substring(0, cols.length() - 1) : "";
                return cols;
        }
        return null;
    }

    private String getWhere_Id_ParamPart() {
        StringBuffer strBuffer = new StringBuffer();
        String cols = "";
        switch (_queryType) {
            case Select:
                return "";

            case SelectWhere:
                for (String col : _baseBO.getColumnChangeList()) {
                    strBuffer.append(String.format("%s=? And ", col));
                }
                cols = strBuffer.toString();
                cols = cols.length() > 0 ? cols.substring(0, cols.length() - 5) : "";
                return cols;

            case Delete:
            case Update:
                return String.format("%s=?", _baseBO.getIdColumn());

            case InsertAndGetId:
            case Insert:
                for (String col : _baseBO.getColumnChangeList()) {
                    if (!col.matches(_baseBO.getIdColumn())) {
                        strBuffer.append("?,");
                    }
                }
                cols = strBuffer.toString();
                cols = cols.length() > 0 ? cols.substring(0, cols.length() - 1) : "";
                return cols;

        }
        return null;
    }

    private String getIdentity() {

        switch (_connectionType) {
            case MsSQL:
            case MsSQL_Jtds:
            case MsSqlCe:
            case Access:
                return String.format(" SELECT IDENT_CURRENT('%s');", _baseBO.getTable());

            case Oracle:
                return " @@IDENTITY;";
                
            default:
                return "";
        }
    }
}

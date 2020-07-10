package mst.data.manager;

import java.sql.ResultSet;
import java.util.List;
import mst.data.connection.*;
import mst.data.querybuilding.IBaseBO;
import mst.data.querybuilding.QueryBuilder;
import mst.data.querybuilding.QueryTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 */
public class Manager implements IManager, IDbOperations, AutoCloseable {

    protected DriverTypes _ConnType;
    protected String _ConnectionUrl = "";
    protected String _user = null;
    protected String _password = null;

    public Manager() {
        this(null, "");
    }

    /**
     *
     * @param connectionType
     */
    public Manager(DriverTypes connectionType) {
        this(connectionType, "");
    }

    /**
     * @param connectionType Type of Database will be connected.
     * @param connectionUrl Database Connection Url
     *
     *
     */
    public Manager(DriverTypes connectionType, String connectionUrl) {
        _ConnType = connectionType;
        _ConnectionUrl = connectionUrl;
    }

    public Manager(DriverTypes ConnectionType, String connectionUrl,
            String user, String password) {
        _ConnType = ConnectionType;
        _ConnectionUrl = connectionUrl;
        _user = user;
        _password = password;
    }

    @Override
    public DbConnection createDbConnection() {
        if (_user == null || _user.length() == 0) {
            return new DbConnection(_ConnType, _ConnectionUrl);
        } else {
            return new DbConnection(_ConnType, _ConnectionUrl, _user, _password);
        }
    }

    /**
     * @return returns Connection Url.
     *
     */
    @Override
    public String getConnectionUrl() {
        return _ConnectionUrl;
    }

    /**
     * @param connectionUrl Connection Url
     *
     */
    @Override
    public void setConnectionUrl(String connectionUrl) {
        _ConnectionUrl = connectionUrl;
    }

    /**
     * @return the _ConnectionType
     */
    @Override
    public DriverTypes getConnectionType() {
        return _ConnType;
    }

    /**
     * @param ConnectionType the _ConnectionType to set
     */
    @Override
    public void setConnectionType(DriverTypes ConnectionType) {
        this._ConnType = ConnectionType;
    }

    @Override
    public QueryBuilder createQueryBuilder(IBaseBO baseBO, QueryTypes queryType) throws Throwable {
        return new QueryBuilder(baseBO, queryType, _ConnType);
    }

    @Override
    public int Insert(IBaseBO baseBO) throws Throwable {
        try {
            int retInt;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.Insert);
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public int InsertAndGetId(IBaseBO baseBO) throws Throwable {
        try {
            int retInt;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.Insert);
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public int Update(IBaseBO baseBO) throws Throwable {
        try {
            int retInt;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.Update);
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public int Delete(IBaseBO baseBO) throws Throwable {
        try {
            int retInt;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.Delete);
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet Select(IBaseBO baseBO) throws Throwable {
        try {
            ResultSet retRS;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.Select);
            try (DbConnection dbConn = createDbConnection()) {
                retRS = dbConn.getResultSetOfQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retRS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet SelectWhere(IBaseBO baseBO) throws Throwable {
        try {
            ResultSet retRS;
            QueryBuilder queryBuilder = createQueryBuilder(baseBO, QueryTypes.SelectWhere);
            try (DbConnection dbConn = createDbConnection()) {
                retRS = dbConn.getResultSetOfQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retRS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public void close() throws Exception {
        Runtime.getRuntime().gc();
    }

    @Override
    protected void finalize() throws Throwable {
        Runtime.getRuntime().gc();
        super.finalize();
    }

    @Override
    public Integer executeUpdateQuery(String query, Parameter... paramCollection) throws Throwable {
        try {
            int retInt;
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(query, paramCollection);
            }
            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Integer executeUpdateQuery(String query) throws Throwable {
        try {
            int retInt;
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateProcedure(query);
            }
            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Integer executeUpdateProcedure(String procedure, Parameter... paramCollection) throws Throwable {
        try {
            int retInt;
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateProcedure(procedure, paramCollection);
            }
            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Integer executeUpdateProcedure(String procedure) throws Throwable {
        try {
            int retInt;
            try (DbConnection dbConn = createDbConnection()) {
                retInt = dbConn.executeUpdateProcedure(procedure);
            }
            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Object[] executeScalarQuery(String query, Parameter... paramCollection) throws Throwable {
        try {
            Object[] objs;
            try (DbConnection dbConn = createDbConnection()) {
                objs = dbConn.executeScalarQuery(query, paramCollection);
            }
            return objs;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Object[] executeScalarQuery(String query) throws Throwable {
        try {
            Object[] objs;
            try (DbConnection dbConn = createDbConnection()) {
                objs = dbConn.executeScalarQuery(query);
            }
            return objs;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Object[] executeScalarProcedure(String procedure, Parameter... paramCollection) throws Throwable {
        try {
            Object[] objs;
            try (DbConnection dbConn = createDbConnection()) {
                objs = dbConn.executeScalarProcedure(procedure, paramCollection);
            }
            return objs;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public Object[] executeScalarProcedure(String procedure) throws Throwable {
        try {
            Object[] objs;
            try (DbConnection dbConn = createDbConnection()) {
                objs = dbConn.executeScalarProcedure(procedure);
            }
            return objs;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet getResultSetOfQuery(String query, Parameter... paramCollection) throws Throwable {
        try {
            ResultSet rS;
            try (DbConnection dbConn = createDbConnection()) {
                rS = dbConn.getResultSetOfQuery(query, paramCollection);
            }
            return rS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet getResultSetOfQuery(String query) throws Throwable {
        try {
            ResultSet rS;
            try (DbConnection dbConn = createDbConnection()) {
                rS = dbConn.getResultSetOfQuery(query);
            }
            return rS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet getResultSetOfProcedure(String procedure, Parameter... paramCollection) throws Throwable {
        try {
            ResultSet rS;
            try (DbConnection dbConn = createDbConnection()) {
                rS = dbConn.getResultSetOfProcedure(procedure, paramCollection);
            }
            return rS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public ResultSet getResultSetOfProcedure(String procedure) throws Throwable {
        try {
            ResultSet rS;
            try (DbConnection dbConn = createDbConnection()) {
                rS = dbConn.getResultSetOfProcedure(procedure);
            }
            return rS;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public List<String> getCatalogs() throws Throwable {
        try {
            List<String> allCatalogs;
            try (DbConnection dbConn = createDbConnection()) {
                allCatalogs = dbConn.getCatalogs();
            }
            return allCatalogs;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public List<String> getTables() throws Throwable {
        try {
            List<String> allTables;
            try (DbConnection dbConn = createDbConnection()) {
                allTables = dbConn.getTables();
            }
            return allTables;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public boolean executeQuery(String query, Parameter... parameters) throws Throwable {
        try {
            boolean retBool;
            try (DbConnection dbConn = createDbConnection()) {
                retBool = dbConn.executeQuery(query, parameters);
            }
            return retBool;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public boolean executeQuery(String query) throws Throwable {
        try {
            boolean retBool;
            try (DbConnection dbConn = createDbConnection()) {
                retBool = dbConn.executeQuery(query);
            }
            return retBool;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public boolean executeProcedure(String procedure, Parameter... parameters) throws Throwable {
        try {
            boolean retBool;
            try (DbConnection dbConn = createDbConnection()) {
                retBool = dbConn.executeProcedure(procedure, parameters);
            }
            return retBool;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public boolean executeProcedure(String procedure) throws Throwable {
        try {
            boolean retBool;
            try (DbConnection dbConn = createDbConnection()) {
                retBool = dbConn.executeProcedure(procedure);
            }
            return retBool;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public int[] executeBatchQuery(String query, Parameter[][] parameters) throws Throwable {
        try {
            int[] retArray;
            try (DbConnection dbConn = createDbConnection()) {
                retArray = dbConn.executeBatchQuery(query, parameters);
            }
            return retArray;
        } catch (Throwable t) {
            throw t;
        }
    }
}

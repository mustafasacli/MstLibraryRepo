/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import java.sql.ResultSet;
import mst.data.connection.DriverTypes;
import mst.data.connection.DbConnection;
import mst.data.manager.IManager;
import mst.data.manager.Manager;

/**
 *
 * @author Krkt
 */
public class AbstractBaseDL implements IBaseDL {

    @Override
    public IManager getManager() {
        return new Manager(DriverTypes.MsSQL, "", "sa", "123123");
    }

    @Override
    public int Insert(IBaseBO baseBO) throws Throwable {
        try {
            int retInt;
            QueryBuilder queryBuilder = getManager().createQueryBuilder(baseBO, QueryTypes.Insert);
            try (DbConnection dbConn = getManager().createDbConnection()) {
                retInt = dbConn.executeUpdateQuery(queryBuilder.getQuery(), queryBuilder.getParameters());
            }

            return retInt;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public int InsertAndGetId(IBaseBO baseBO) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Update(IBaseBO baseBO) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int Delete(IBaseBO baseBO) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet Select(IBaseBO baseBO) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet SelectWhere(IBaseBO baseBO) throws Throwable {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

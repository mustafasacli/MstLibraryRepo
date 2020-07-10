package mst.data.connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mst.data.querybuilding.IBaseOperations;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 */
public interface IDbConnection extends IDbOperations {

    void beginTransaction() throws SQLException;

    void commitTransaction() throws SQLException;

    void rollbackTranction() throws SQLException;

    void closeConnection() throws SQLException;

    ConnectionTypes getConnectionType();

    void setConnectionType(ConnectionTypes ConnType);

    Connection createConnection() throws Throwable;    
    
    void setExternalDriver(String externalDriver) throws Exception;
}

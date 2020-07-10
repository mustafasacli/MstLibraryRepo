package mst.data.connection;

import java.sql.Connection;
import java.sql.SQLException;

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

    DriverTypes getConnectionType();

    void setConnectionType(DriverTypes ConnType);

    Connection createConnection() throws Throwable;    
    
    void setExternalDriver(String externalDriver) throws Exception;
}

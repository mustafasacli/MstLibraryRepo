package mst.data.manager;

import mst.data.connection.DriverTypes;
import mst.data.connection.DbConnection;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 *
 */
public final class SQLiteManager extends Manager {
   
    public SQLiteManager() {
        super(DriverTypes.SQLite);
    }
    
    public SQLiteManager(String connectionUrl) {
        super(DriverTypes.SQLite, connectionUrl);
    }

    public SQLiteManager(String connectionUrl, String user, String password) {
        super(DriverTypes.SQLite, connectionUrl, user, password);
    }
    
}

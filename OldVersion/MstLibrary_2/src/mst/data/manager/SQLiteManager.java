package mst.data.manager;

import mst.data.connection.ConnectionTypes;
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
        super(ConnectionTypes.SQLite);
    }
    
    public SQLiteManager(String connectionUrl) {
        super(ConnectionTypes.SQLite, connectionUrl);
    }

    public SQLiteManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.SQLite, connectionUrl, user, password);
    }
    
}

package mst.data.manager;

import mst.data.connection.ConnectionTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 *
 */
public final class MsSqlManager extends Manager {

    public MsSqlManager() {
        super(ConnectionTypes.MsSQL);
    }

    public MsSqlManager(String connectionUrl) {
        super(ConnectionTypes.MsSQL, connectionUrl);
    }

    public MsSqlManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.MsSQL, connectionUrl, user, password);
    }
}

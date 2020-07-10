package mst.data.manager;

import mst.data.connection.DriverTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 *
 */
public final class MsSqlManager extends Manager {

    public MsSqlManager() {
        super(DriverTypes.MsSQL);
    }

    public MsSqlManager(String connectionUrl) {
        super(DriverTypes.MsSQL, connectionUrl);
    }

    public MsSqlManager(String connectionUrl, String user, String password) {
        super(DriverTypes.MsSQL, connectionUrl, user, password);
    }
}

package mst.data.manager;

import mst.data.connection.DriverTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 *
 */
public final class OracleManager extends Manager {

   
    public OracleManager() {
        super(DriverTypes.Oracle);
    }

    /**
     * @param connectionUrl Connection Url.
     */
    public OracleManager(String connectionUrl) {
        super(DriverTypes.Oracle, connectionUrl);
    }

    public OracleManager(String connectionUrl, String user, String password) {
        super(DriverTypes.Oracle, connectionUrl, user, password);
    }
}

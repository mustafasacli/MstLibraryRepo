package mst.data.manager;

import mst.data.connection.ConnectionTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 *
 */
public final class OracleManager extends Manager {

   
    public OracleManager() {
        super(ConnectionTypes.Oracle);
    }

    /**
     * @param connectionUrl Connection Url.
     */
    public OracleManager(String connectionUrl) {
        super(ConnectionTypes.Oracle, connectionUrl);
    }

    public OracleManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.Oracle, connectionUrl, user, password);
    }
}

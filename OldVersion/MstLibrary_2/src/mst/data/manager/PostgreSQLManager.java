/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.manager;

import mst.data.connection.ConnectionTypes;

/**
 *
 * @author Krkt
 */
public final class PostgreSQLManager extends Manager {

    public PostgreSQLManager() {
        super(ConnectionTypes.PostgreSQL);
    }

    public PostgreSQLManager(String url) {
        super(ConnectionTypes.PostgreSQL, url);
    }

    public PostgreSQLManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.PostgreSQL, connectionUrl, user, password);
    }
}

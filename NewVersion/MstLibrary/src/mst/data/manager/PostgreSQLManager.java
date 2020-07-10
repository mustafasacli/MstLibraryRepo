/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.manager;

import mst.data.connection.DriverTypes;

/**
 *
 * @author Krkt
 */
public final class PostgreSQLManager extends Manager {

    public PostgreSQLManager() {
        super(DriverTypes.PostgreSQL);
    }

    public PostgreSQLManager(String url) {
        super(DriverTypes.PostgreSQL, url);
    }

    public PostgreSQLManager(String connectionUrl, String user, String password) {
        super(DriverTypes.PostgreSQL, connectionUrl, user, password);
    }
}

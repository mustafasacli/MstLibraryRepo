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
public final class MySQLManager extends Manager {

    public MySQLManager() {
        super(ConnectionTypes.MySQL);
    }

    public MySQLManager(String connectionUrl) {
        super(ConnectionTypes.MySQL, connectionUrl);
    }

    public MySQLManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.MySQL, connectionUrl, user, password);
    }
}

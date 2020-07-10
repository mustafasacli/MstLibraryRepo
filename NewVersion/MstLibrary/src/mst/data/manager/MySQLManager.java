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
public final class MySQLManager extends Manager {

    public MySQLManager() {
        super(DriverTypes.MySQL);
    }

    public MySQLManager(String connectionUrl) {
        super(DriverTypes.MySQL, connectionUrl);
    }

    public MySQLManager(String connectionUrl, String user, String password) {
        super(DriverTypes.MySQL, connectionUrl, user, password);
    }
}

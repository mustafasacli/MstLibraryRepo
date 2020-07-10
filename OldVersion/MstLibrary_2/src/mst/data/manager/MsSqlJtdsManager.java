/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.manager;

import mst.data.connection.ConnectionTypes;

/**
 *
 * @author eww
 */
public final class MsSqlJtdsManager extends Manager {

    public MsSqlJtdsManager() {
        super(ConnectionTypes.MsSQL_Jtds);
    }

    public MsSqlJtdsManager(String connectionUrl) {
        super(ConnectionTypes.MsSQL_Jtds, connectionUrl);
    }

    public MsSqlJtdsManager(String connectionUrl, String user, String password) {
        super(ConnectionTypes.MsSQL_Jtds, connectionUrl, user, password);
    }
    
}

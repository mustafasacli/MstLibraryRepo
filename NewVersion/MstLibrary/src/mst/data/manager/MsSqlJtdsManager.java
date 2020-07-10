/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.manager;

import mst.data.connection.DriverTypes;

/**
 *
 * @author eww
 */
public final class MsSqlJtdsManager extends Manager {

    public MsSqlJtdsManager() {
        super(DriverTypes.MsSQL_Jtds);
    }

    public MsSqlJtdsManager(String connectionUrl) {
        super(DriverTypes.MsSQL_Jtds, connectionUrl);
    }

    public MsSqlJtdsManager(String connectionUrl, String user, String password) {
        super(DriverTypes.MsSQL_Jtds, connectionUrl, user, password);
    }
    
}

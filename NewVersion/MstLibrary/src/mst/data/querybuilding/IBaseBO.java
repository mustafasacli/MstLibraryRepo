/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import java.util.ArrayList;

/**
 *
 * @author Mustafa SACLI
 */
public interface IBaseBO {

    String getTable();

    String getIdColumn();
    
    /*
     int Insert();

     int InsertAndGetId();

     int Delete();

     int Update();
     */

    ArrayList<String> getColumnChangeList();

    void addChangeList(String column);
}

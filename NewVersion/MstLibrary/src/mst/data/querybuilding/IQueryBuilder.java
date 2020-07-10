/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import mst.data.connection.DriverTypes;
import mst.data.connection.Parameter;

/**
 *
 * @author Mustafa SACLI
 */
public interface IQueryBuilder {
    
    QueryTypes getQueryType();
    
     void setQueryType(QueryTypes queryType);
     
     DriverTypes getConnectionType();
     
     void setConnectionType(DriverTypes connectionType);
     
     Parameter[] getParameters();
     
     String getQuery();
}

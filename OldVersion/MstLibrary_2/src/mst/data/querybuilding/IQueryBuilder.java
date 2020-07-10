/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import mst.data.connection.ConnectionTypes;
import mst.data.connection.Parameter;

/**
 *
 * @author Mustafa SACLI
 */
public interface IQueryBuilder {
    
    QueryTypes getQueryType();
    
     void setQueryType(QueryTypes queryType);
     
     ConnectionTypes getConnectionType();
     
     void setConnectionType(ConnectionTypes connectionType);
     
     Parameter[] getParameters();
     
     String getQuery();
}

package mst.data.manager;

import mst.data.connection.ConnectionTypes;
import mst.data.connection.DbConnection;
import mst.data.querybuilding.IBaseBO;
import mst.data.querybuilding.IBaseOperations;
import mst.data.querybuilding.QueryBuilder;
import mst.data.querybuilding.QueryTypes;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 */
public interface IManager extends IBaseOperations {

    QueryBuilder createQueryBuilder(IBaseBO baseBO, QueryTypes queryType) throws Throwable;

    DbConnection createDbConnection();

    String getConnectionUrl();

    void setConnectionUrl(String connectionUrl);

    ConnectionTypes getConnectionType();
    
    void setConnectionType(ConnectionTypes ConnectionType);
  
}

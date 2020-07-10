/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

/**
 *
 * @author eww
 */
public enum QueryTypes {

    Select(IQueryTypes.QUERY_TYPE_SELECT),
    Insert(IQueryTypes.QUERY_TYPE_INSERT),
    Update(IQueryTypes.QUERY_TYPE_UPDATE),
    Delete(IQueryTypes.QUERY_TYPE_DELETE),
    InsertAndGetId(IQueryTypes.QUERY_TYPE_INSERT_AND_GETID),
    SelectWhere(IQueryTypes.QUERY_TYPE_SELECT_WHERE);
    private int _querytype = 0;

    private QueryTypes(int queryType) {
        _querytype = queryType;
    }

}

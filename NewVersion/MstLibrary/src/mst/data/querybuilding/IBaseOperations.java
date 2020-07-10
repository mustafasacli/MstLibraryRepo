/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import java.sql.ResultSet;

/**
 *
 * @author Mustafa SACLI
 */
public interface IBaseOperations {
    
    int Insert(IBaseBO baseBO) throws Throwable;

    int InsertAndGetId(IBaseBO baseBO) throws Throwable;

    int Update(IBaseBO baseBO) throws Throwable;

    int Delete(IBaseBO baseBO) throws Throwable;
    
    ResultSet Select(IBaseBO baseBO) throws Throwable;
    
    ResultSet SelectWhere(IBaseBO baseBO) throws Throwable;
    
}

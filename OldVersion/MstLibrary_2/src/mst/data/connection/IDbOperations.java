/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.connection;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Krkt
 */
public interface IDbOperations {
    
       Integer executeUpdateQuery(String query,
            Parameter... paramCollection) throws Throwable;

    Integer executeUpdateQuery(String query) throws Throwable;

    Integer executeUpdateProcedure(String procedure,
            Parameter... paramCollection) throws Throwable;

    Integer executeUpdateProcedure(String procedure) throws Throwable;

    Object[] executeScalarQuery(String query,
            Parameter... paramCollection) throws Throwable;

    Object[] executeScalarQuery(String query) throws Throwable;

    Object[] executeScalarProcedure(String procedure,
            Parameter... paramCollection) throws Throwable;

    Object[] executeScalarProcedure(String procedure) throws Throwable;

    ResultSet getResultSetOfQuery(String query,
            Parameter... paramCollection) throws Throwable;

    ResultSet getResultSetOfQuery(String query) throws Throwable;

    ResultSet getResultSetOfProcedure(String query,
            Parameter... paramCollection) throws Throwable;

    ResultSet getResultSetOfProcedure(String procedure) throws Throwable;

    List<String> getCatalogs() throws Throwable;

    List<String> getTables() throws Throwable;

    boolean executeQuery(String query,
            Parameter... parameters) throws Throwable;

    boolean executeQuery(String query) throws Throwable;

    boolean executeProcedure(String procedure,
            Parameter... parameters) throws Throwable;

    boolean executeProcedure(String procedure) throws Throwable;

    /**
     * for int[] exceuteBatch(...)
     */
    int[] executeBatchQuery(String query, Parameter[][] parameters) throws Throwable;

}

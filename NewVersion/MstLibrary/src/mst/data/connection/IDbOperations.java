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

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    Integer executeUpdateQuery(String query,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param query
     * @return
     * @throws Throwable
     */
    Integer executeUpdateQuery(String query) throws Throwable;

    /**
     *
     * @param procedure
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    Integer executeUpdateProcedure(String procedure,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param procedure
     * @return
     * @throws Throwable
     */
    Integer executeUpdateProcedure(String procedure) throws Throwable;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    Object[] executeScalarQuery(String query,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param query
     * @return
     * @throws Throwable
     */
    Object[] executeScalarQuery(String query) throws Throwable;

    /**
     *
     * @param procedure
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    Object[] executeScalarProcedure(String procedure,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param procedure
     * @return
     * @throws Throwable
     */
    Object[] executeScalarProcedure(String procedure) throws Throwable;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    ResultSet getResultSetOfQuery(String query,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param query
     * @return
     * @throws Throwable
     */
    ResultSet getResultSetOfQuery(String query) throws Throwable;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Throwable
     */
    ResultSet getResultSetOfProcedure(String query,
            Parameter... paramCollection) throws Throwable;

    /**
     *
     * @param procedure
     * @return
     * @throws Throwable
     */
    ResultSet getResultSetOfProcedure(String procedure) throws Throwable;

    /**
     *
     * @return @throws Throwable
     */
    List<String> getCatalogs() throws Throwable;

    /**
     *
     * @return @throws Throwable
     */
    List<String> getTables() throws Throwable;

    /**
     *
     * @param query
     * @param parameters
     * @return
     * @throws Throwable
     */
    boolean executeQuery(String query,
            Parameter... parameters) throws Throwable;

    /**
     *
     * @param query
     * @return
     * @throws Throwable
     */
    boolean executeQuery(String query) throws Throwable;

    /**
     *
     * @param procedure
     * @param parameters
     * @return
     * @throws Throwable
     */
    boolean executeProcedure(String procedure,
            Parameter... parameters) throws Throwable;

    /**
     *
     * @param procedure
     * @return
     * @throws Throwable
     */
    boolean executeProcedure(String procedure) throws Throwable;

    /**
     * for int[] exceuteBatch(...)
     *
     * @param query
     * @param parameters
     * @return
     * @throws java.lang.Throwable
     */
    int[] executeBatchQuery(String query, Parameter[][] parameters) throws Throwable;

}

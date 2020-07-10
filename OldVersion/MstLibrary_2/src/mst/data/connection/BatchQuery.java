/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author eww
 */
public class BatchQuery {

    private String _query = "";
    private List<Parameter> _paramsList = new ArrayList<Parameter>();

  
    public BatchQuery(String query, Parameter... parameters) {
        _query = query;
        if(parameters!=null){
            _paramsList.addAll(Arrays.asList(parameters));
        }
    }

    /**
     * @return the _query
     */
    public String getQuery() {
        return _query;
    }

    /**
     * @param query the _query to set
     */
    public void setQuery(String query) {
        this._query = query;
    }

    /**
     * @return the _paramsList
     */
    public List<Parameter> getParamsList() {
        return _paramsList;
    }

    /**
     * @param paramsList the _paramsList to set
     */
    public void setParamsList(List<Parameter> paramsList) {
        this._paramsList = paramsList;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mst.data.querybuilding;

import java.util.ArrayList;

/**
 *
 * @author Krkt
 */
public abstract class AbstractBaseBO implements IBaseBO {

    protected ArrayList<String> changeList = new ArrayList<String>();

    /**
     *
     * @return
     */
    @Override
    public String getTable() {
        throw new UnsupportedOperationException("Table Name must be defined.");
    }

    @Override
    public String getIdColumn() {
        throw new UnsupportedOperationException("Id Column Name must be defined.");
    }

    @Override
    public ArrayList<String> getColumnChangeList() {
        return changeList;
    }

    @Override
    public void addChangeList(String column) {
        if (!changeList.contains(column)) {
            changeList.add(column);
        }
    }
}

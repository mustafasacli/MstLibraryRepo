package mst.data.connection;

public interface IParameter {

    Integer getIndex();

    void setIndex(Integer Index);

    Object getValue();

    void setValue(Object value);

    Integer getParameterDirection();

    void setParameterDirection(Integer parameterDirection);
}

package mst.data.connection;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 */
public class Parameter implements IParameter, IParameterDirection {

    private Integer _Index = 1;
    private Object _Value = null;
    private Integer _parameterDirection = 1;

    public Parameter() {
        this(0, null, Parameter.PARAMETER_DIRECTION_IN);
    }

    public Parameter(int index, Object value,
            Integer parameterDirection) {
        _Index = index;
        _Value = value;
        _parameterDirection = parameterDirection;
    }

    public Parameter(int index, Object value) {
        this(index, value, Parameter.PARAMETER_DIRECTION_IN);
    }

    /**
     * @return Returns Index Of Parameter.
     *
     *
     */
    @Override
    public Integer getIndex() {
        return _Index;
    }

    /**
     * @param index Index Of Parameter.
     *
     *
     */
    @Override
    public void setIndex(Integer index) {
        _Index = index > 0 ? index : 1;
    }

    /**
     * @return Returns Value Of Parameter.
     *
     *
     */
    @Override
    public Object getValue() {
        return _Value;
    }

    /**
     * @param value Value Of Parameter.
     *
     *
     */
    @Override
    public void setValue(Object value) {
        _Value = value;
    }

    /**
     * @return the _parameterDirection
     */
    @Override
    public Integer getParameterDirection() {
        return _parameterDirection;
    }

    /**
     * @param parameterDirection the _parameterDirection to set
     */
    @Override
    public void setParameterDirection(Integer parameterDirection) {
        this._parameterDirection =
                parameterDirection == PARAMETER_DIRECTION_IN || parameterDirection == PARAMETER_DIRECTION_OUT
                ? parameterDirection : PARAMETER_DIRECTION_IN;
    }

    public static Parameter createParam(int index, Object value) {
        return new Parameter(index, value);
    }

    public static Parameter createParam(int index, Object value, int parameterDirection) {
        return new Parameter(index, value, parameterDirection);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Runtime.getRuntime().gc();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Parameter) {
            Parameter p = (Parameter) obj;
            return p.getIndex() == _Index;
        } else {
            return false;
        }
    }
}

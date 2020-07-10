package mst.data.connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Mustafa SACLI
 *
 * @since 1.7
 *
 */
public final class DbConnection
        implements IDbConnection, AutoCloseable {

    private boolean _autoCommit;
    private String DRIVER = "";
    private ConnectionTypes _ConnectionType;
    private String _ConnectionUrl = "";
    private Properties _props = null;
    private String _user = "";
    private String _pass = "";
    private int ctorType = 0;
    private Connection _Conn = null;

    public DbConnection(ConnectionTypes ConnType, String ConnectionUrl) {
        _ConnectionType = ConnType;
        _ConnectionUrl = ConnectionUrl;
        DRIVER = _ConnectionType.getDriverOfConnectionType();
        ctorType = 1;
    }

    public DbConnection(ConnectionTypes ConnType, String ConnectionUrl, String user, String password) {
        _ConnectionType = ConnType;
        _ConnectionUrl = ConnectionUrl;
        _user = user;
        _pass = password;
        DRIVER = _ConnectionType.getDriverOfConnectionType();
        ctorType = 2;
    }

    public DbConnection(ConnectionTypes ConnType, String ConnectionUrl, Properties props) {
        _ConnectionType = ConnType;
        _ConnectionUrl = ConnectionUrl;
        DRIVER = _ConnectionType.getDriverOfConnectionType();
        _props = props;
        ctorType = 3;
    }

    @Override
    public ConnectionTypes getConnectionType() {
        return _ConnectionType;
    }

    @Override
    public void setConnectionType(ConnectionTypes ConnType) {
        _ConnectionType = ConnType;
    }

    @Override
    public Connection createConnection() throws Throwable {
        try {
            Driver drv = (Driver) Class.forName(DRIVER).newInstance();
            DriverManager.registerDriver(drv);

            switch (ctorType) {
                case 1: // URL.
                    return DriverManager.getConnection(_ConnectionUrl);
                case 2: // URL, user and pass.
                    return DriverManager.getConnection(_ConnectionUrl, _user, _pass);
                case 3: // URL and Properties.
                    return DriverManager.getConnection(_ConnectionUrl, _props);
                default:
                    throw new Exception("Unknown Construction Type Of Connection.");
            }
        } catch (Throwable exc) {
            throw exc;
        }
    }

    public static DbConnection createDbConn(ConnectionTypes ConnType, String ConnectionUrl) {
        return new DbConnection(ConnType, ConnectionUrl);
    }

    public static DbConnection createDbConn(ConnectionTypes ConnType, String ConnectionUrl, String user, String password) {
        return new DbConnection(ConnType, ConnectionUrl, user, password);
    }

    public static DbConnection createDbConn(ConnectionTypes ConnType, String ConnectionUrl, Properties props) {
        return new DbConnection(ConnType, ConnectionUrl, props);
    }

    /**
     * Aşağıdaki metotlar IDbConnection arayüzüne eklenen yeni metotlar içindir.
     */
    @Override
    public boolean executeQuery(String query, Parameter... parameters) throws Throwable {
        try {
            boolean retBool = false;
            _Conn = createConnection();
            beginTransaction();

            try (PreparedStatement prepared = _Conn.prepareStatement(query)) {
                if (parameters != null) {
                    for (Parameter param : parameters) {
                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            prepared.setObject(param.getIndex(), param.getValue());
                        }
                    }

                }
                retBool = prepared.execute();
            }

            commitTransaction();
            closeConnection();
            return retBool;

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public boolean executeQuery(String query) throws Throwable {
        try {
            boolean retBool;
            retBool = false;
            _Conn = createConnection();
            beginTransaction();

            try (Statement _statement = _Conn.createStatement()) {
                retBool = _statement.execute(query);
            }

            commitTransaction();
            closeConnection();
            // closing _Conn Connetion
            return retBool;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public boolean executeProcedure(String procedure, Parameter... parameters) throws Throwable {
        try {
            boolean retBool;
            retBool = false;
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(procedure)) {

                if (parameters != null) {
                    for (Parameter param : parameters) {
                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            callable.setObject(param.getIndex(), param.getValue());
                        }
                    }
                }

                retBool = callable.execute();
                callable.clearParameters();
            }
            commitTransaction();
            closeConnection();

            return retBool;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public boolean executeProcedure(String procedure) throws Throwable {
        try {
            boolean retBool = false;

            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(procedure)) {

                retBool = callable.execute();
            }

            commitTransaction();
            closeConnection();
            return retBool;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Integer executeUpdateQuery(String query, Parameter... parameters) throws Throwable {
        try {
            int retInt = -1;
            _Conn = createConnection();
            beginTransaction();

            try (PreparedStatement prepared = _Conn.prepareStatement(query)) {

                if (parameters != null) {
                    for (Parameter param : parameters) {
                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            prepared.setObject(param.getIndex(), param.getValue());
                        }
                    }
                }
                retInt = prepared.executeUpdate();
                prepared.clearParameters();
                prepared.clearWarnings();
            }

            commitTransaction();
            closeConnection();
            return retInt;

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Integer executeUpdateQuery(String query) throws Throwable {
        try {
            int retInt;
            _Conn = createConnection();

            beginTransaction();
            try (Statement _statement = _Conn.createStatement()) {
                retInt = _statement.executeUpdate(query);
            }

            commitTransaction();
            closeConnection();

            return retInt;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Integer executeUpdateProcedure(String procedure, Parameter... parameters) throws Throwable {
        try {
            int retInt;
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(procedure)) {
                if (parameters != null) {
                    for (Parameter param : parameters) {
                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            callable.setObject(param.getIndex(), param.getValue());
                        }
                    }
                }

                retInt = callable.executeUpdate();
            }

            commitTransaction();
            closeConnection();

            return retInt;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Integer executeUpdateProcedure(String procedure) throws Throwable {
        try {
            int retInt;
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(procedure)) {
                retInt = callable.executeUpdate();
            }
            commitTransaction();
            closeConnection();

            return retInt;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Object[] executeScalarQuery(String query, Parameter... parameters) throws Throwable {
        try {
            List<Object> retObjList = new ArrayList<Object>();
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(query)) {

                if (parameters != null) {

                    for (Parameter param : parameters) {
                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_OUT) {
                            callable.registerOutParameter(param.getIndex(),
                                    java.sql.Types.JAVA_OBJECT);
                        } else {
                            callable.setObject(param.getIndex(), param.getValue());
                        }
                    }

                    callable.executeUpdate();

                    for (Parameter param : parameters) {

                        if (param.getParameterDirection() == Parameter.PARAMETER_DIRECTION_OUT) {
                            retObjList.add(callable.getObject(param.getIndex()));
                        }
                    }

                } else {

                    try (ResultSet rs = callable.executeQuery()) {
                        while (rs.next()) {
                            int colInt = rs.getMetaData().getColumnCount();
                            for (int i = 0; i < colInt; i++) {
                                retObjList.add(rs.getObject(i + 1));
                            }
                            rs.close();
                            break;
                        }
                    }
                }
            }

            commitTransaction();
            closeConnection();

            return retObjList.toArray();

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Object[] executeScalarQuery(String query) throws Throwable {
        try {
            List<Object> objList = new ArrayList<Object>();
            _Conn = createConnection();
            beginTransaction();

            try (Statement _statement = _Conn.createStatement()) {

                ResultSet rS = _statement.executeQuery(query);
                int cols = rS.getMetaData().getColumnCount();
                while (rS.next()) {
                    for (int i = 0; i < cols; i++) {
                        objList.add(rS.getObject(i + 1));
                    }
                    break;
                }
            }

            commitTransaction();
            closeConnection();
            return objList.toArray();

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public Object[] executeScalarProcedure(String procedure, Parameter... parameters) throws Throwable {
        try {
            List<Object> objList = new ArrayList<Object>();
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(procedure)) {

                if (parameters != null) {

                    for (Parameter p : parameters) {

                        if (p.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            callable.setObject(p.getIndex(), p.getValue());
                        } else {
                            callable.registerOutParameter(p.getIndex(),
                                    java.sql.Types.JAVA_OBJECT);
                        }
                    }

                    callable.executeUpdate();

                    for (Parameter p : parameters) {
                        if (p.getParameterDirection() == Parameter.PARAMETER_DIRECTION_OUT) {
                            objList.add(callable.getObject(p.getIndex()));
                        }
                    }

                } else {
                    ResultSet rS = callable.executeQuery();
                    int cols = rS.getMetaData().getColumnCount();
                    while (rS.next()) {
                        for (int i = 0; i < cols; i++) {
                            objList.add(rS.getObject(i + 1));
                        }
                        rS.close();
                        break;
                    }
                }
            }

            commitTransaction();
            closeConnection();

            return objList.toArray();

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;

        }
    }

    @Override
    public Object[] executeScalarProcedure(String procedure) throws Throwable {
        try {
            return executeScalarQuery(procedure, null);
        } catch (Throwable exc) {
            throw exc;
        }
    }

    @Override
    public ResultSet getResultSetOfQuery(String query, Parameter... parameters) throws Throwable {
        try {
            ResultSet resultSet = null;
            _Conn = createConnection();
            beginTransaction();

            try (PreparedStatement prepared = _Conn.prepareStatement(query)) {

                if (parameters != null) {
                    for (Parameter p : parameters) {
                        if (p.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            prepared.setObject(p.getIndex(), p.getValue());
                        }
                    }
                }

                resultSet = prepared.executeQuery();
            }
            commitTransaction();
            closeConnection();

            return resultSet;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public ResultSet getResultSetOfQuery(String query) throws Throwable {
        try {
            ResultSet resultSet = null;
            _Conn = createConnection();
            beginTransaction();

            try (Statement _statement = _Conn.createStatement()) {

                resultSet = _statement.executeQuery(query);
            }

            commitTransaction();
            closeConnection();

            return resultSet;

        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public ResultSet getResultSetOfProcedure(String query, Parameter... parameters) throws Throwable {
        try {
            ResultSet rS = null;
            _Conn = createConnection();
            beginTransaction();

            try (CallableStatement callable = _Conn.prepareCall(query)) {
                if (parameters != null) {
                    for (Parameter p : parameters) {
                        if (p.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                            callable.setObject(p.getIndex(), p.getValue());
                        }
                    }
                }
                rS = callable.executeQuery();
            }

            commitTransaction();
            closeConnection();

            return rS;
        } catch (Throwable exc) {
            rollbackTranction();
            closeConnection();
            throw exc;
        }
    }

    @Override
    public ResultSet getResultSetOfProcedure(String procedure) throws Throwable {
        try {
            return getResultSetOfProcedure(procedure, null);
        } catch (Throwable exc) {
            throw exc;
        }
    }

    @Override
    public void close() throws Exception {
        try {
            closeConnection();
            Runtime.getRuntime().gc();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> getCatalogs() throws Throwable {
        try {
            List<String> dbS = new ArrayList<String>();
            try (Connection _Conn = createConnection()) {

                try (ResultSet rS = _Conn.getMetaData().getCatalogs()) {

                    while (rS.next()) {
                        dbS.add(rS.getString(1));
                    }
                }
            }
            return dbS;
        } catch (Throwable exc) {
            throw exc;

        }
    }

    @Override
    public List<String> getTables() throws Throwable {
        try {
            List<String> tables = new ArrayList<String>();
            try (Connection _Conn = createConnection()) {
                DatabaseMetaData dbmd = _Conn.getMetaData();

                // Specify the type of object; in this case we want tables
                String[] types = {"TABLE"};

                try (ResultSet resultSet = dbmd.getTables(null, null, "%", types)) {

                    while (resultSet.next()) {
                        // Get the table name
                        tables.add(resultSet.getString(3));

                        /*
                         // Get the table's catalog and schema names (if any)
                         String tableCatalog = resultSet.getString(1);
                         String tableSchema = resultSet.getString(2);
                         */
                    }
                }
            }
            return tables;
        } catch (Throwable exc) {
            throw exc;
        }
    }

    @Override
    public int[] executeBatchQuery(String query, Parameter[][] parameters) throws Throwable {
        int[] retIntArray = null;
        try {
            if (parameters == null || parameters.length == 0) {
                throw new Throwable("Parameters list can not be null or empty.");
            }

            try (Connection Conn = createConnection()) {
                try (PreparedStatement prep = Conn.prepareStatement(query)) {
                    for (int i = 0; i < parameters.length; i++) {
                        for (int j = 0; j < parameters[i].length; j++) {
                            Parameter p = parameters[i][j];
                            if (p != null) {
                                if (p.getParameterDirection() == Parameter.PARAMETER_DIRECTION_IN) {
                                    prep.setObject(p.getIndex(), p.getValue());
                                }
                            }
                        }
                        prep.addBatch();
                    }
                    retIntArray = prep.executeBatch();
                }
            }
            return retIntArray;
        } catch (Throwable t) {
            throw t;
        }
    }

    @Override
    public void setExternalDriver(String externalDriver) throws Exception {
        if (_ConnectionType == ConnectionTypes.External) {
            DRIVER = externalDriver;
        } else {
            throw new Exception("Connectionn Type must be External");
        }
    }

    @Override
    public void beginTransaction() throws SQLException {
        try {
            if (_Conn != null) {
                if (!_Conn.isClosed()) {
                    _Conn.setAutoCommit(false);
                }
            }
        } catch (Exception exc) {
            throw exc;
        }
    }

    @Override
    public void commitTransaction() throws SQLException {
        try {
            if (_Conn != null) {
                if (!_Conn.isClosed()) {
                    _Conn.commit();
                    _Conn.setAutoCommit(true);
                }
            }
        } catch (Exception exc) {
            throw exc;
        }
    }

    @Override
    public void rollbackTranction() throws SQLException {
        try {
            if (_Conn != null) {
                if (!_Conn.isClosed()) {
                    _Conn.rollback();
                    _Conn.setAutoCommit(true);
                }
            }
        } catch (Exception exc) {
            throw exc;
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        try {
            if (_Conn != null) {
                if (!_Conn.isClosed()) {
                    _Conn.close();
                }
            }
        } catch (Exception exc) {
            throw exc;
        }
    }
}

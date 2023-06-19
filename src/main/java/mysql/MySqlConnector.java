package mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 * The MySqlConnector class provides a convenient way to establish a connection between a Java project and a MySQL database.
 * It encapsulates various functionalities related to database connection, login, database management, table creation, and data manipulation.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class MySqlConnector {

    /**
     * It is used to store the database connection object.
     */
    private Connection connection;

    /**
     * It is used to store prepared statements, which are used to execute parameterized MySQL queries.
     */
    private PreparedStatement preparedStatement;

    /**
     * The connection string specifies the protocol (jdbc:mysql://),<br>
     * the host (127.0.0.1 - localhost),<br>
     * and the port (3306) where the MySQL database is running.
     */
    private String JDBCConnectionString = "jdbc:mysql://127.0.0.1:3306/";

    /**
     * autoReconnect=true specifies that the JDBC driver should automatically reconnect to the database if the connection is lost,
     * and useSSL=false disables the use of SSL for the connection.
     */
    private String autoReconnect = "?autoReconnect=true&useSSL=false";

    /**
     * Storing the MySQL current database name.
     */
    private String database = null;

    /**
     * Storing the MySQL current table name.
     */
    private String table = null;

    /**
     * Storing the MySQL username.
     */
    private String user = null;

    /**
     * Storing the MySQL password.
     */
    private String password = null;

    /**
     * Default constructor.
     * <b>Automatic connection is not possible, the MySQL parameters must be specified separately:</b>
     * <ul>
     *     <li>{@link mysql.MySqlConnector#setDatabase(String)}</li>
     *     <li>{@link mysql.MySqlConnector#setTable(String)}</li>
     *     <li>{@link mysql.MySqlConnector#setUser(String)}</li>
     *     <li>{@link mysql.MySqlConnector#setPassword(String)}</li>
     * </ul>
     */
    public MySqlConnector() {}

    /**
     * Default Login constructor.
     * <b>Automatic connection is not possible, the MySQL parameters must be specified separately:</b>
     * <ul>
     *     <li>{@link mysql.MySqlConnector#setDatabase(String)}</li>
     *     <li>{@link mysql.MySqlConnector#setTable(String)}</li>
     * </ul>
     * @param       user MySQL username.
     * @param       password MySQL password.
     * @see         mysql.MySqlConnector#setUser(String)
     * @see         mysql.MySqlConnector#setPassword(String)
     */
    public MySqlConnector(String user, String password) {
        setUser(user);
        setPassword(password);
    }

    /**
     * Login constructor.
     * All parameters must be specified to connect to MySQL.
     * @param       user MySQL username.
     * @param       password MySQL password.
     * @param       database MySQL current database name.
     * @param       table MySQL current table name.
     * @see         mysql.MySqlConnector#setUser(String)
     * @see         mysql.MySqlConnector#setPassword(String)
     * @see         mysql.MySqlConnector#setDatabase(String)
     * @see         mysql.MySqlConnector#setTable(String)
     */
    public MySqlConnector(String user, String password, String database, String table) {
        setUser(user);
        setPassword(password);
        setDatabase(database);
        setTable(table);
    }

    /**
     * This method is a getter for the connection variable. It returns the current Connection object.
     * @return      the connection to MySQL.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * This method is a getter for the preparedStatement variable. It returns the current PreparedStatement object.
     * @return      the statement to MySQL.
     */
    public PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    /**
     * This method is a getter for the JDBCConnectionString variable. It returns the current JDBC connection string.
     * @return      the connection String to MySQL.
     */
    public String getJDBCConnectionString() {
        return JDBCConnectionString;
    }

    /**
     * This method is a getter for the autoReconnect variable. It returns the current auto-reconnect parameter string.
     * @return      the current auto-reconnect parameter.
     */
    public String getAutoReconnect() {
        return autoReconnect;
    }

    /**
     * This method is a getter for the database variable. It returns the current database name.
     * @return      the current database name.
     */
    public String getDatabase() {
        return database;
    }

    /**
     * This method is a getter for the table variable. It returns the current table name.
     * @return      the current table name.
     */
    public String getTable() {
        return table;
    }

    /**
     * This method is a setter for the JDBCConnectionString variable.
     * It allows the JDBC connection string to be updated by providing a new value.
     * @param       JDBCConnectionString to be updated.
     */
    public void setJDBCConnectionString(String JDBCConnectionString) {
        this.JDBCConnectionString = JDBCConnectionString;
    }

    /**
     * This method is a setter for the autoReconnect variable.
     * It allows the auto-reconnect parameter string to be updated by providing a new value.
     * @param       autoReconnect to be updated.
     */
    public void setAutoReconnect(String autoReconnect) {
        this.autoReconnect = autoReconnect;
    }

    /**
     * This method is a setter for the database variable.
     * It allows the database name to be updated by providing a new value.
     * @param       database to be updated.
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * This method is a setter for the table variable.
     * It allows the table name to be updated by providing a new value.
     * If the provided table name is the same as the current database name,
     * it appends "_table" to the table name to avoid conflicts.
     * @param       table to be updated.
     */
    public void setTable(String table) {
        if (!table.equals(database)) {
            this.table = table;
        } else {
            setTable(table + "_table");
        }
    }

    /**
     * This method is a setter for the user variable. It allows the username to be updated by providing a new value.
     * @param       user to be updated.
     * @throws      IllegalArgumentException If the provided value is null, empty, or contains only whitespace, it throws an.
     */
    public void setUser(String user)
    throws IllegalArgumentException {
        if ((user != null) && (!user.isBlank()) && (!user.isEmpty())) {
            this.user = user;
        } else {
            throw new IllegalArgumentException("mysqlUser argument is not correct!");
        }
    }

    /**
     * This method is a setter for the password variable. It allows the password to be updated by providing a new value.
     * @param       password to be updated.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@code mysqlLogIn} that is used to log in to a MySQL database.
     * It takes two parameters: {@code mysqlUser} (the username) and {@code mysqlPassword} (the password).<br><br>
     * This method attempts to log in to the MySQL database by setting the username and password using the provided parameters
     * and then calling the {@code mysqlConnection}() method.
     * <ul>
     *     <li>The method receives the {@code mysqlUser} and {@code mysqlPassword} as input parameters.</li>
     *     <li>It calls the {@code setUser} method, passing the {@code mysqlUser} parameter, to set the username.</li>
     *     <li>It calls the setPassword method, passing the {@code mysqlPassword} parameter, to set the password.</li>
     *     <li>It calls the mysqlConnection method, which is presumably responsible for establishing the database connection.</li>
     * </ul>
     * By encapsulating the login logic within this method, the code ensures that the username and password are set correctly
     * and that any exceptions thrown during the login process are handled appropriately.
     * @param       mysqlUser the username.
     * @param       mysqlPassword the password.
     * @throws      SQLException if there is an error during the login process.
     * @throws      IllegalArgumentException if the user and/or password is incorrect.
     * @see         mysql.MySqlConnector#setUser(String)
     * @see         mysql.MySqlConnector#setPassword(String)
     * @see         mysql.MySqlConnector#mysqlConnection()
     */
    public void mysqlLogIn(String mysqlUser, String mysqlPassword)
    throws SQLException, IllegalArgumentException {
        try {
            setUser(mysqlUser);
            setPassword(mysqlPassword);
            mysqlConnection();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * {@code mysqlLogIn} method that includes additional functionality.
     * This method is used to log in to a MySQL database,
     * and if the login fails, it attempts
     * to repair the database by creating it and creating a table with the specified {@code columns} and {@code datatype}.
     * <ul>
     *     <li>The method receives the {@code mysqlUser} and {@code mysqlPassword} as input parameters.</li>
     *     <li>It calls the {@code setUser} method, passing the {@code mysqlUser} parameter, to set the username.</li>
     *     <li>It calls the {@code setPassword} method, passing the {@code mysqlPassword} parameter, to set the password.</li>
     *     <li>It calls the {@code mysqlConnection} method to establish a connection to the MySQL database.</li>
     *     <li>If the connection fails and the {@code repair} flag is set to {@code true}, it proceeds with the repair process.</li>
     *     <li>Within the repair process, it calls the {@code mysqlConnection} method,
     *     passing the {@code column} and {@code datatype} arrays and setting the repair flag to {@code true}.</li>
     *     <li>If the repair process encounters an exception during the connection or repair process, it is caught,
     *     and a new {@code SQLException} with the same error message is thrown.</li>
     *     <li>If the connection fails and the {@code repair} flag is set to {@code false},
     *     it throws an {@code SQLException} with the original error message.</li>
     * </ul>
     * method provides a mechanism to handle failed login attempts and automatically repair the database if requested.
     * If the repair process encounters any exceptions, they are appropriately propagated with informative error messages.
     * @param       mysqlUser the username.
     * @param       mysqlPassword the password.
     * @param       column if the connection is not successful, the connection is tried based on these values, in a table,
     *              if repair is true
     * @param       datatype if the connection is not successful, it is tried based on these variable types, in a table,
     *              if repair is true
     * @param       repair if true try to fix the connection by creating the database and table.
     * @throws      SQLException if there is an error during the login process or the repair process.
     * @see         mysql.MySqlConnector#setUser(String) 
     * @see         mysql.MySqlConnector#setPassword(String) 
     * @see         mysql.MySqlConnector#mysqlConnection() 
     * @see         mysql.MySqlConnector#mysqlConnection(String[], String[], boolean) 
     */
    public void mysqlLogIn(String mysqlUser, String mysqlPassword, String[] column, String[] datatype, boolean repair)
    throws SQLException {
        try {
            setUser(mysqlUser);
            setPassword(mysqlPassword);
            mysqlConnection();
        } catch (SQLException e) {
            if (repair) {
                mysqlConnection(column, datatype, true);
            } else {
                throw new SQLException(e.getMessage());
            }
        }
    }

    /**
     * {@code mysqlConnection} that establishes a connection to a MySQL database.
     * It utilizes the {@link java.sql.DriverManager DriverManager} class from JDBC to create the connection object.<br><br>
     * This method attempts to establish a connection to the MySQL database using the provided connection string, database name, username, and password.
     * It utilizes the DriverManager.{@link java.sql.DriverManager#getConnection(String) getConnection()} method to create
     * a {@link java.sql.Connection Connection} object and assigns it to the connection variable.
     * <ul>
     *     <li>The method concatenates the JDBC connection string, the database name,
     *     and the auto-reconnect parameter to form the complete connection URL.</li>
     *     <li>It calls the {@code DriverManager.getConnection()} method, passing
     *     the connection URL, username, and password, to establish a connection to the MySQL database.</li>
     *     <li>The resulting {@code Connection} object is assigned to the {@code connection} variable.</li>
     *     <li>If an {@code SQLException} occurs during the connection process,
     *     it is caught, and a new exception with the same error message is thrown.</li>
     * </ul>
     * By encapsulating the connection logic within this method, the code ensures that the connection is established correctly
     * and any exceptions thrown during the process are handled appropriately.
     * @throws      SQLException if there is an error during the connection process.
     */
    public void mysqlConnection()
    throws SQLException {
        try {
            connection = DriverManager.getConnection((JDBCConnectionString + database + autoReconnect), user, password);
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * {@code mysqlConnection} method that includes additional functionality.
     * This method establishes a connection to a MySQL database and, if the connection fails,
     * attempts to repair the database by creating it and creating a table with the specified {@code columns} and {@code datatype}.
     * <ul>
     *     <li>The method attempts to establish a connection to the MySQL database using the provided connection string,
     *     database name, username, and password, similar to the previous version.</li>
     *     <li>If the connection fails and the {@code repair} flag is set to {@code true}, it proceeds with the repair process.</li>
     *     <li>Within the repair process, it sets the database and table names using the current values.</li>
     *     <li>It calls the {@code mysqlCreateDatabase} method to create the database.</li>
     *     <li>It calls the mysqlCreateTable method, passing the column and datatype arrays,
     *     to create a table with the specified {@code columns} and {@code datatype}.</li>
     *     <li>If an {@code IllegalArgumentException} occurs during the repair process, it is caught,
     *     and a new exception with the same error message is thrown.</li>
     *     <li>If an {@code SQLException} occurs during the repair process, it is caught, and a new exception with the same error message is thrown.</li>
     *     <li>If the connection fails and the {@code repair} flag is set to {@code false},
     *     it throws an {@code SQLException} with the original error message.</li>
     * </ul>
     * method provides a mechanism to handle failed database connections and automatically repair the database if requested.
     * If the repair process encounters any exceptions,
     * they are appropriately propagated with informative error messages.
     * @param       column if the connection is not successful, the connection is tried based on these values, in a table,
     *              if repair is true
     * @param       datatype if the connection is not successful, it is tried based on these variable types, in a table,
     *              if repair is true
     * @param       repair if true try to fix the connection by creating the database and table.
     * @throws      SQLException if there is an error during the connection process or the repair process.
     * @throws      IllegalArgumentException if there is an invalid argument.
     * @see         mysql.MySqlConnector#setDatabase(String) 
     * @see         mysql.MySqlConnector#setTable(String) 
     * @see         mysql.MySqlConnector#mysqlCreateDatabase() 
     * @see         mysql.MySqlConnector#mysqlCreateTable(String[], String[]) 
     */
    public void mysqlConnection(String[] column, String[] datatype, boolean repair)
    throws SQLException, IllegalArgumentException {
        try {
            connection = DriverManager.getConnection((JDBCConnectionString + database + autoReconnect), user, password);
        } catch (SQLException e) {
            if (repair) {
                try {
                    setDatabase(database);
                    setTable(table);
                    mysqlCreateDatabase();
                    mysqlCreateTable(column, datatype);
                } catch (IllegalArgumentException e2) {
                    throw new IllegalArgumentException(e2.getMessage());
                } catch (SQLException e2) {
                    throw new SQLException(e2.getMessage());
                }
            } else {
                throw new SQLException(e.getMessage());
            }
        }
    }

    /**
     * {@code mysqlDisconnection} that is used to disconnect from the MySQL database.
     * It closes the connection by calling the close method on the {@link java.sql.Connection Connection} object.<br><br>
     * This method attempts to close the connection to the MySQL database by calling
     * the {@link Connection#close() close()} method on the {@code connection} object.
     * The close method is responsible for releasing any resources associated with the connection.
     * <ul>
     *     <li>The method calls the {@code close} method on the connection object
     *     to close the {@code connection} to the MySQL database.</li>
     *     <li>If an exception of type Exception occurs during the disconnection process, it is caught,
     *     and a new {@code SQLException} with the same error message is thrown.</li>
     * </ul>
     * By encapsulating the disconnection logic within this method, the code ensures that the connection
     * is closed properly and any exceptions thrown during the disconnection process are handled appropriately.
     * @throws      SQLException if there is an error during the disconnection process.
     */
    public void mysqlDisconnection()
    throws SQLException {
        try {
            connection.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * {@code mysqlCreateDatabase} that is used to create a MySQL database.
     * It executes an SQL query to create the database if it doesn't already exist.
     * <ul>
     *     <li>It constructs an SQL statement to create a database using the
     *     <pre> CREATE DATABASE IF NOT EXISTS </pre>
     *     syntax. The {@code database} variable is used to specify the name of the database to be created.</li>
     *     <li>It establishes a new connection to the MySQL server
     *     by calling {@link java.sql.DriverManager DriverManager}.{@link java.sql.DriverManager#getConnection(String) getConnection()},
     *     passing the JDBC connection string without the specific database name
     *     but with the auto-reconnect option, and the username and password.</li>
     *     <li>It creates a {@link java.sql.PreparedStatement PreparedStatement} object
     *     by calling {@code connection}.{@link java.sql.Connection#prepareStatement(String) prepareStatement()}
     *     and passing the SQL statement for creating the database.</li>
     *     <li>It executes the SQL statement to create the database
     *     by calling {@code preparedStatement}.{@link java.sql.PreparedStatement#executeUpdate() executeUpdate()}.</li>
     *     <li>It clears the parameters of the prepared statement
     *     by calling {@code preparedStatement}.{@link java.sql.PreparedStatement#clearParameters() clearParameters()}
     *     to release any resources associated with it.</li>
     *     <li>It closes the connection by calling {@code connection}.{@link Connection#close() close()}.</li>
     *     <li>If an exception of type {@code Exception} occurs during the database creation process,
     *     it is caught, and a new {@code SQLException} with the same error message is thrown.</li>
     * </ul>
     * By encapsulating the database creation logic within this method,
     * the code ensures that the database is created or verified to exist,
     * and any exceptions thrown during the process are handled appropriately.
     * @throws      SQLException if there is an error during the database creation process.
     */
    public void mysqlCreateDatabase()
    throws SQLException {
        String mysqlInstructions = "CREATE DATABASE IF NOT EXISTS " + database;
        try {
            connection = DriverManager.getConnection((JDBCConnectionString + autoReconnect), user, password);
            preparedStatement = connection.prepareStatement(mysqlInstructions);
            preparedStatement.executeUpdate();
            preparedStatement.clearParameters();
            connection.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }
    }

    /**
     * {@code mysqlCreateTable} that is used to create a table in a MySQL database.
     * It takes two arrays, {@code column} and {@code datatype},
     * representing the column names and corresponding data types for the table.
     * <ul>
     *     <li>It constructs an SQL statement to create a table using the
     *     <pre>CREATE TABLE IF NOT EXISTS</pre>
     *     syntax. The {@code table} variable is used to specify the name of the table to be created.</li>
     *     <li>It checks if the sizes of the {@code column} and {@code datatype} arrays are equal and greater than or equal to <i>1</i>.
     *     If they are not, it throws an {@code IllegalArgumentException} with an appropriate error message.</li>
     *     <li>If the sizes of the arrays are valid, it iterates over
     *     the elements of the {@code column} and {@code datatype} arrays and appends them to the SQL statement.
     *     Each column and datatype pair is separated by a comma.
     *     The data type is converted to uppercase.</li>
     *     <li>After the loop, it removes the trailing comma and space from the SQL statement and closes it with a closing parenthesis.</li>
     *     <li>It establishes a new connection to the MySQL server
     *     by calling {@link java.sql.DriverManager DriverManager}.{@link java.sql.DriverManager#getConnection(String) getConnection()},
     *     passing the JDBC connection string with the specific database name and auto-reconnect option, and the username and password.</li>
     *     <li>It creates a PreparedStatement object
     *     by calling {@code connection}.{@link java.sql.Connection#prepareStatement(String) prepareStatement()}
     *     and passing the SQL statement for creating the table.</li>
     *     <li>It executes the SQL statement to create the table
     *     by calling {@code preparedStatement}.{@link java.sql.PreparedStatement#executeUpdate() executeUpdate()}.</li>
     *     <li>It clears the parameters of the prepared statement
     *     by calling {@code preparedStatement}.{@link java.sql.PreparedStatement#clearParameters() clearParameters()}
     *     to release any resources associated with it.</li>
     *     <li>If an exception of type {@code Exception} occurs during the table creation process, it is caught,
     *     and a new {@code SQLException} with the same error message is thrown.</li>
     *     <li>If the sizes of the {@code column} and {@code datatype} arrays are not equal,
     *     it throws an {@code IllegalArgumentException} with an error message indicating the mismatch.</li>
     *     <li>If the sizes of the arrays are valid but less than <i>1</i>,
     *     it throws an {@code IllegalArgumentException} with a generic error message stating that the array sizes do not match.</li>
     * </ul>
     * By encapsulating the table creation logic within this method, the code ensures that the table is created or verified to exist,
     * and any exceptions thrown during the process are handled appropriately.
     * @param       column if the connection is not successful, the connection is tried based on these values, in a table.
     * @param       datatype if the connection is not successful, it is tried based on these variable types, in a table.
     * @throws      SQLException if there is an error during the database creation process.
     * @throws      IllegalArgumentException if the input arrays are not valid.
     */
    public void mysqlCreateTable(String[] column, String[] datatype)
    throws SQLException, IllegalArgumentException {
        String mysqlInstructions = "CREATE TABLE IF NOT EXISTS " + table + "(";
        if ((column.length == datatype.length) && (column.length >= 1)) {
            for (int i = 0; i < column.length; i++) {
                mysqlInstructions += column[i] + " " + datatype[i].toUpperCase() + ", ";
            }
            mysqlInstructions = mysqlInstructions.substring(0, (mysqlInstructions.length() - 2)) + ")";
            try {
                connection = DriverManager.getConnection((JDBCConnectionString + database + autoReconnect), user, password);
                preparedStatement = connection.prepareStatement(mysqlInstructions);
                preparedStatement.executeUpdate();
                preparedStatement.clearParameters();
            } catch (Exception e) {
                throw new SQLException(e.getMessage());
            }
        } else if (column.length != datatype.length) {
            throw new IllegalArgumentException("Not equals column " + column.length + " " + ((Math.min(column.length, datatype.length) == column.length) ? "<" : ">") + " datatype " + datatype.length);
        } else {
            throw new IllegalArgumentException("Argument array size does not match!");
        }
    }
}
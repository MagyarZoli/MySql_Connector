# MySql Connector

## Description
Connection between a Java project and a MySQL database.

## Features
The `MySqlConnector` class provides a convenient way to establish a connection between a `Java project` and a `MySQL` database.
It encapsulates various functionalities related to database connection, login, database management, table creation, and data manipulation.

## Example

### Class Members
  - `connection`: Stores the connection object representing the connection to the MySQL database.
  - `preparedStatement`: Stores the prepared statement object used for executing SQL queries.
  - `JDBCConnectionString`: Represents the JDBC connection string for the MySQL database.
  - `autoReconnect`: Specifies the auto-reconnect option for the database connection.
  - `database`: Stores the name of the currently selected database.
  - `table`: Stores the name of the currently selected table.
  - `user`: Stores the MySQL user for authentication.
  - `password`: Stores the password for the MySQL user.

### Constructors
  - The `MySqlConnector` class provides a default constructor and additional constructors that allow you to initialize the class members as needed.

### Connection Management
  - `getConnection()`: Returns the established connection object.
  - `getPreparedStatement()`: Returns the prepared statement object.
  - `getJDBCConnectionString()`: Returns the JDBC connection string.
  - `getAutoReconnect()`: Returns the auto-reconnect option.
  - `getDatabase()`: Returns the name of the current database.
  - `getTable()`: Returns the name of the current table.
  - `setJDBCConnectionString(String JDBCConnectionString)`: Sets the JDBC connection string.
  - `setAutoReconnect(String autoReconnect)`: Sets the auto-reconnect option.
  - `setDatabase(String database)`: Sets the name of the current database.
  - `setTable(String table)`: Sets the name of the current table.

### Database Connection
  - `mysqlConnection()`: Establishes a connection to the MySQL database using the provided connection parameters.
  - `mysqlLogIn(String mysqlUser, String mysqlPassword)`: Attempts to log in to the MySQL database using the specified user credentials.
  - `mysqlConnection(String[] column, String[] datatype, boolean repair)`: Establishes a connection to the MySQL database and provides an option to repair the connection if it fails.

### Database Management
  - `mysqlCreateDatabase()`: Creates a database if it does not already exist.
  - `mysqlCreateTable(String[] column, String[] datatype)`: Creates a table in the current database with the specified columns and data types.

### Authors
Magyar Zoltán

### Contact
magyarz95@gmail.com
package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * {@code MySqlOperation} It is a generic interface with a type parameter {@code T}.
 * It contains operations related to MySQL, which are partly developed and partly made up of methods to be implemented.
 * @param       <T> to determine the desired type, in order to upload and query data.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface MySqlOperation<T> {

    /**
     * {@code mysqlGet} method executes the provided MySQL instruction by preparing a statement using
     * the {@code connection} object and the {@code mysqlInstructions} parameter.
     * <ul>
     *     <li>It then executes the prepared statement using {@link java.sql.PreparedStatement#executeQuery() executeQuery()} method,
     *     which returns a {@link java.sql.ResultSet ResultSet}.</li>
     *     <li>The method iterates over the rows in the ResultSet and retrieves
     *     the values for each column using {@code resultSet}.{@link java.sql.ResultSet#getString(String) getString()} method.
     *     It stores the column values in a {@code List<String>} named {@code resultList}.</li>
     *     <li>For each row, it adds the {@code resultList} as a string array to another {@code List<String[]>} named {@code lists}.
     *     This creates a list of string arrays, where each array represents a row of data from the {@code ResultSet}.</li>
     *     <li>After iterating over all rows, the method closes the {@code ResultSet},
     *     clears the parameters of the prepared statement,
     *     and returns the lists as a two-dimensional array of strings.</li>
     * </ul>
     * {@code mysqlGet} method provides a convenient way to execute a parameterized MySQL query
     * and retrieve the results as a two-dimensional array of strings.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @return      Values read from MySql stored in a two-dimensional String array.
     * @throws      SQLException if it runs into some other MySql error while running.
     * @see         mysql.MySqlConnector#connection
     * @see         mysql.MySqlConnector#preparedStatement
     */
    default public String[][] mysqlGet(String mysqlInstructions)
    throws SQLException {
        List<String> resultList = new ArrayList<>();
        List<String[]> lists = new ArrayList<>();
        MySqlConnector.preparedStatement = MySqlConnector.connection.prepareStatement(mysqlInstructions);
        ResultSet resultSet = MySqlConnector.preparedStatement.executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                resultList.add(resultSet.getString(resultSet.getMetaData().getColumnName((i + 1))));
            }
            lists.add(resultList.toArray(new String[0]));
            resultList.clear();
        }
        resultSet.close();
        MySqlConnector.preparedStatement.clearParameters();
        return lists.toArray(new String[0][]);
    }

    /**
     * {@code mysqlGetToCollection}, returns the result of the MySQL query as a collection of string arrays.
     * <ul>
     *     <li>The method initializes a new collection, {@code resultCollection}, of type {@code Collection<String>},
     *     which will store the values of each column in a row.</li>
     *     <li>It also initializes a new collection, {@code collections}, of type {@code Collection<String[]>},
     *     which will store the string arrays representing each row of data.</li>
     *     <li>The method prepares a statement using the provided {@code mysqlInstructions} and the {@code connection} object.</li>
     *     <li>It executes the prepared statement by calling {@link java.sql.PreparedStatement#executeQuery() executeQuery()},
     *     which returns a {@link java.sql.ResultSet ResultSet} containing the result of the query.</li>
     *     <li>The method iterates over each row in the {@code ResultSet} using {@code resultSet}.{@link java.sql.ResultSet#next() next()}.
     *     For each row, it iterates over the columns using the {@code for} loop.</li>
     *     <li>Inside the inner loop, it retrieves the value of each column using
     *     {@code resultSet}.{@link java.sql.ResultSet#getString(String) getString()} and adds it to the {@code resultCollection}.</li>
     *     <li>Once all columns in a row are added to the {@code resultCollection},
     *     it converts the collection to a string array using
     *     {@code resultCollection}.{@link java.util.Collection#toArray(Object[]) toArray}{@code (new String[0])}
     *     and adds it to the {@code collections} collection.</li>
     *     <li>After adding a row to {@code collections}, it clears the {@code resultCollection} to prepare for the next row.</li>
     *     <li>Once all rows have been processed, the method closes the {@code ResultSet},
     *     clears the parameters of the prepared statement,
     *     and returns the {@code collections} collection containing all rows as string arrays.</li>
     * </ul>
     * {@code mysqlGetToCollection} method provides a way to retrieve the result of a MySQL query as a collection of string arrays, where each string array represents a row of data.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @return      the result of the MySQL query as a collection of string arrays.
     * @throws      SQLException if it runs into some other MySql error while running.
     * @see         mysql.MySqlConnector#connection
     * @see         mysql.MySqlConnector#preparedStatement
     */
    default public Collection<String[]> mysqlGetToCollection(String mysqlInstructions)
    throws SQLException {
        Collection<String> resultCollection = new ArrayList<>();
        Collection<String[]> collections = new ArrayList<>();
        MySqlConnector.preparedStatement = MySqlConnector.connection.prepareStatement(mysqlInstructions);
        ResultSet resultSet = MySqlConnector.preparedStatement.executeQuery();
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                resultCollection.add(resultSet.getString(resultSet.getMetaData().getColumnName((i + 1))));
            }
            collections.add(resultCollection.toArray(new String[0]));
            resultCollection.clear();
        }
        resultSet.close();
        MySqlConnector.preparedStatement.clearParameters();
        return collections;
    }

    /**
     * {@code mysqlGetToGenericCollection}, is declared to return a collection of a generic type {@code T}.
     * <ul>
     *     <li>The method is defined to take a {@code mysqlInstructions} parameter,
     *     which represents the MySQL query that will be executed.
     *     It throws a {@code SQLException} in case of any database-related errors.</li>
     * </ul>
     * To make use of the generic type {@code T} within the method, you would need to specify the type parameter when calling
     * the method or implement the method with appropriate logic to handle the generic type.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @return      the result of the MySQL query as a collection of a generic type {@code T}.
     * @throws      SQLException if it runs into some other MySql error while running.
     */
    public Collection<T> mysqlGetToGenericCollection(String mysqlInstructions)
    throws SQLException;

    /**
     * {@code mysqlSetVarargs} that is used to execute a parameterized MySQL update statement with variable arguments.
     * <ul>
     *     <li>The method takes two parameters:
     *     {@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and update, which is a variable number of arguments (varargs) represented as an array of objects.</li>
     *     <li>The method prepares a statement using the provided {@code mysqlInstructions} and the {@code connection} object.</li>
     *     <li>It iterates over the {@code update} array using a {@code for} loop.</li>
     *     <li>Inside the loop, it sets the object at index {@code (i + 1)} in the prepared statement
     *     using the {@link java.sql.PreparedStatement#setObject(int, Object) setObject} method.
     *     The index {@code (i + 1)} is used because JDBC uses <i>1</i>-based indexing for parameter placeholders.</li>
     *     <li>After setting all the parameters, the method executes the update statement
     *     using {@link PreparedStatement#executeUpdate() executeUpdate()} method,
     *     which returns the number of affected rows (if applicable).</li>
     *     <li>Finally, the method clears the parameters of the prepared statement
     *     using {@link PreparedStatement#clearParameters() clearParameters()} to prepare it for future use.</li>
     * </ul>
     * {@code mysqlSetVarargs} method provides a way to execute a parameterized MySQL update statement with a flexible number of arguments.
     * The method sets the provided arguments as parameters in the prepared statement and then executes the update statement against the MySQL database.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     * @see         mysql.MySqlConnector#connection
     * @see         mysql.MySqlConnector#preparedStatement
     */
    default public void mysqlSetVarargs(String mysqlInstructions, Object... update)
    throws SQLException {
        MySqlConnector.preparedStatement = MySqlConnector.connection.prepareStatement(mysqlInstructions);
        for (int i = 0; i < update.length; i++) {
            MySqlConnector.preparedStatement.setObject((i + 1), update[i]);
        }
        MySqlConnector.preparedStatement.executeUpdate();
        MySqlConnector.preparedStatement.clearParameters();
    }

    /**
     * {@code mysqlSet} that is used to execute a parameterized MySQL update statement with a fixed-size array of objects.
     * <ul>
     *     <li>The method takes two parameters:
     *     {@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and {@code update}, which is an array of objects containing the values to be set as parameters in the statement.</li>
     *     <li>The method prepares a statement using the provided {@code mysqlInstructions} and the {@code connection} object.</li>
     *     <li>It iterates over the {@code update} array using a {@code for} loop.</li>
     *     <li>Inside the loop, it sets the object at index {@code (i + 1)} in the prepared statement
     *     using the {@link java.sql.PreparedStatement#setObject(int, Object) setObject} method.
     *     The index {@code (i + 1)} is used because JDBC uses <i>1</i>-based indexing for parameter placeholders.</li>
     *     <li>After setting all the parameters, the method executes the update statement
     *     using the {@link PreparedStatement#executeUpdate() executeUpdate()} method,
     *     which returns the number of affected rows (if applicable).</li>
     *     <li>Finally, the method clears the parameters of the prepared statement
     *     using {@link PreparedStatement#clearParameters() clearParameters()} to prepare it for future use.</li>
     * </ul>
     * {@code mysqlSet} method provides a way to execute a parameterized MySQL update statement with a fixed-size array of objects.
     * The method sets the provided values as parameters in
     * the prepared statement and then executes the update statement against the MySQL database.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     * @see         mysql.MySqlConnector#connection
     * @see         mysql.MySqlConnector#preparedStatement
     */
    default public void mysqlSet(String mysqlInstructions, Object[] update)
    throws SQLException {
        MySqlConnector.preparedStatement = MySqlConnector.connection.prepareStatement(mysqlInstructions);
        for (int i = 0; i < update.length; i++) {
            MySqlConnector.preparedStatement.setObject((i + 1), update[i]);
        }
        MySqlConnector.preparedStatement.executeUpdate();
        MySqlConnector.preparedStatement.clearParameters();
    }

    /**
     * {@code mysqlSet} that is used to execute a parameterized MySQL update statement with a {@code List} of objects as the input.
     * <ul>
     *     <li>The method takes two parameters:
     *     {@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and {@code update}, which is a {@code List} of objects containing the values to be set as parameters in the statement.</li>
     *     <li>The method prepares a statement using the provided {@code mysqlInstructions} and the {@code connection} object.</li>
     *     <li>It iterates over the {@code update} list using a {@code for} loop and retrieves each object
     *     using the {@link java.util.List#get(int) get} method.</li>
     *     <li>Inside the loop, it sets the object at index {@code (i + 1)} in the prepared statement
     *     using the {@link java.sql.PreparedStatement#setObject(int, Object) setObject} method.
     *     The index {@code (i + 1)} is used because JDBC uses <i>1</i>-based indexing for parameter placeholders.</li>
     *     <li>After setting all the parameters, the method executes the update statement
     *     using the {@link PreparedStatement#executeUpdate() executeUpdate()} method,
     *     which returns the number of affected rows (if applicable).</li>
     *     <li>Finally, the method clears the parameters of the prepared statement
     *     using {@link PreparedStatement#clearParameters() clearParameters()} to prepare it for future use.</li>
     * </ul>
     * {@code mysqlSet} method provides a way to execute a parameterized MySQL update statement with a {@code List} of objects as input.
     * The method sets the provided values from the list as parameters in the prepared statement
     * and then executes the update statement against the MySQL database.
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     * @see         mysql.MySqlConnector#connection
     * @see         mysql.MySqlConnector#preparedStatement
     */
    default public void mysqlSet(String mysqlInstructions, List<Object> update)
    throws SQLException {
        MySqlConnector.preparedStatement = MySqlConnector.connection.prepareStatement(mysqlInstructions);
        for (int i = 0; i < update.size(); i++) {
            MySqlConnector.preparedStatement.setObject((i + 1), update.get(i));
        }
        MySqlConnector.preparedStatement.executeUpdate();
        MySqlConnector.preparedStatement.clearParameters();
    }

    /**
     * {@code mysqlSetToGeneric} that are used to execute parameterized MySQL update statements with generic input.
     * <ul>
     *     <li>{@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and {@code update}, which is a single object of generic type {@code T} that contains the values to be set as parameters in the statement.
     *     The method executes the update statement using the provided {@code mysqlInstructions} and the values from the {@code update} object.</li>
     * </ul>
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     */
    public void mysqlSetToGeneric(String mysqlInstructions, T update)
    throws SQLException;

    /**
     * {@code mysqlSetToGeneric} that are used to execute parameterized MySQL update statements with generic input.
     * <ul>
     *     <li>{@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and {@code update}, which is an array of objects of generic type {@code T}.
     *     The method iterates over the update array and sets the values as parameters in the prepared statement.
     *     Then it executes the update statement using the provided {@code mysqlInstructions}.</li>
     * </ul>
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     */
    public void mysqlSetToGeneric(String mysqlInstructions, T[] update)
    throws SQLException;

    /**
     * {@code mysqlSetToGeneric} that are used to execute parameterized MySQL update statements with generic input.
     * <ul>
     *     <li>{@code mysqlInstructions}, which represents the parameterized MySQL update statement,
     *     and {@code update}, which is a {@code Collection} (such as {@code List} or {@code Set}) of objects of generic type {@code T}.
     *     The method iterates over the update collection and sets the values as parameters in the prepared statement.
     *     Then it executes the update statement using the provided {@code mysqlInstructions}.</li>
     * </ul>
     * @param       mysqlInstructions MySql instruction, text string containing instructions.
     * @param       update the created statement and then executes the update statement against the MySQL database.
     * @throws      SQLException if it runs into some other MySql error while running.
     */
    public void mysqlSetToGeneric(String mysqlInstructions, Collection<T> update)
    throws SQLException;
}
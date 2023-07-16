package mysql;

import java.sql.SQLException;
import java.util.Collection;

/**
 * {@code MySqlOperation} It is a generic interface with a type parameter {@code T}.
 * It contains operations related to MySQL, which are partly developed and partly made up of methods to be implemented.
 * @param       <T> to determine the desired type, in order to upload and query data.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public interface MySqlOperation<T> {

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
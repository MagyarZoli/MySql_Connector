import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import mysql.MySqlConnector;
import mysql.MySqlOperation;

/**
 * Testing the MySqlOperationTest class in JUnit 5 & Mockito.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class MySqlOperationTest {
    
    private static MySqlConnector mysql;
    private static MySqlOperation<String> mysqlOperation;
    private static String username;
    private static String password;
    private static String database = "database1_db";
    private static String table = "table1_t";
    private String instruction;
    private Collection<String> collection;

    @BeforeAll
    @DisplayName(value = "Connection to the database.")
    @SuppressWarnings("unchecked")
    static void setUpBeforeClass() {
        mysql = new MySqlConnector();
        username = Prop.getUsername();
        password = Prop.getPassword();
        mysql.setUser(username);
        mysql.setPassword(password);
        mysql.setDatabase(database);
        mysql.setTable(table);
        Assertions.assertDoesNotThrow(() -> mysql.mysqlConnection());
        mysqlOperation = Mockito.mock(MySqlOperation.class);
    }

    @Test
    @DisplayName(value = "SELECT * FROM <table> LIMIT 300; to Generic collection type")
    void mysqlGetToGenericCollectionTest() {
        instruction = "SELECT * FROM " + table + " LIMIT 300";
        List<String> data = List.of("data1", "data2", "data3");
        Collection<String> realCollection = Mockito.spy(data);
        Assertions.assertDoesNotThrow(() -> Mockito.when(mysqlOperation.mysqlGetToGenericCollection(instruction)).thenReturn(realCollection));
        Assertions.assertDoesNotThrow(() -> collection = mysqlOperation.mysqlGetToGenericCollection(instruction));
        Assertions.assertEquals(data, collection);
        instruction = "SELECT * FROM invalidTable_t LIMIT 300";
        Assertions.assertDoesNotThrow(() -> Mockito.doThrow(new SQLException("Invalid data provided")).when(mysqlOperation).mysqlGetToGenericCollection(instruction));
        Assertions.assertThrows(SQLException.class, () -> collection = mysqlOperation.mysqlGetToGenericCollection(instruction));
    }

    @Test
    @DisplayName(value = "INSERT INTO <table> VALUE (<update>); to Generic Type")
    void mysqlSetToGenericTest() {
        instruction = "INSERT INTO " + table + " VALUE ?";
        String data = "data", invalidData = "invalidData";
        Assertions.assertDoesNotThrow(() -> mysqlOperation.mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.verify(mysqlOperation).mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.doThrow(new SQLException("Invalid data provided")).when(mysqlOperation).mysqlSetToGeneric(instruction, invalidData));
        Assertions.assertThrows(SQLException.class, () -> mysqlOperation.mysqlSetToGeneric(instruction, invalidData));
    }

    @Test
    @DisplayName(value = "INSERT INTO <table> VALUE (<update>); to Generic Array Type")
    void mysqlSetToGenericArrayTest() {
        instruction = "INSERT INTO " + table + " VALUE (?, ?, ?)";
        String[] data = new String[]{"data1", "data2", "data3"};
        String[] invalidData = new String[]{"invalidData1", "invalidData2", "invalidData3"};
        Assertions.assertDoesNotThrow(() -> mysqlOperation.mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.verify(mysqlOperation).mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.doThrow(new SQLException("Invalid data provided")).when(mysqlOperation).mysqlSetToGeneric(instruction, invalidData));
        Assertions.assertThrows(SQLException.class, () -> mysqlOperation.mysqlSetToGeneric(instruction, invalidData));
    }

    @Test
    @DisplayName(value = "INSERT INTO <table> VALUE (<update>); to Generic Collection Type")
    void mysqlSetToGenericCollectionTest() {
        instruction = "INSERT INTO " + table + " VALUE (?, ?, ?)";
        Collection<String> data = List.of("data1", "data2", "data3");
        Collection<String> invalidData = List.of("invalidData1", "invalidData2", "invalidData3");
        Assertions.assertDoesNotThrow(() -> mysqlOperation.mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.verify(mysqlOperation).mysqlSetToGeneric(instruction, data));
        Assertions.assertDoesNotThrow(() -> Mockito.doThrow(new SQLException("Invalid data provided")).when(mysqlOperation).mysqlSetToGeneric(instruction, invalidData));
        Assertions.assertThrows(SQLException.class, () -> mysqlOperation.mysqlSetToGeneric(instruction, invalidData));
    }

    @AfterAll
    @DisplayName(value = "Disconnection to the database.")
    static void testDownAfterClass() {
        Assertions.assertDoesNotThrow(() -> mysql.mysqlDisconnection());
    }
}
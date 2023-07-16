import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import mysql.MySqlConnector;

/**
 * Testing the MySqlConnector class in JUnit 5.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MySqlConnectorTest {
    
    private static MySqlConnector mysql;
    private static String username;
    private static String password;
    private static String database = "database1_db";
    private static String table = "table1_t";
    private static String view = "view1_v";
    private static DateTimeFormatter formatter;
    private String instruction;
    private List<String[]> select;

    @BeforeAll
    @DisplayName(value = "Connection to the database.")
    static void setUpBeforeClass() {
        mysql = new MySqlConnector();
        username = Prop.getUsername();
        password = Prop.getPassword();
        mysql.setUser(username);
        mysql.setPassword(password);
        mysql.setDatabase(database);
        mysql.setTable(table);
        Assertions.assertDoesNotThrow(() -> mysql.mysqlConnection());
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @BeforeEach
    void setUp() {
        mysql.setDatabase(database);
        mysql.setTable(table);
    }

    @Test
    @DisplayName(value = "Examination of entering the table name.")
    void setTableTest() {
        mysql.setTable("table2_t");
        Assertions.assertEquals("table2_t", mysql.getTable());
        mysql.setTable(database);
        Assertions.assertEquals("database1_db_table", mysql.getTable());
    }

    @Test
    @DisplayName(value = "Examination of entering the user name.")
    void setUserTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> mysql.setUser(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> mysql.setUser(""));
        Assertions.assertThrows(IllegalArgumentException.class, () -> mysql.setUser(" "));
        Assertions.assertDoesNotThrow(() -> mysql.setUser("username"));
    }

    @Test
    @Tag("mysql")
    @DisplayName(value = "Checking login.")
    void mysqlLogInTest() {
        Assertions.assertDoesNotThrow(() -> mysql.mysqlDisconnection());
        Assertions.assertThrows(IllegalArgumentException.class, () -> mysql.mysqlLogIn(null, null));
        Assertions.assertThrows(SQLException.class, () -> mysql.mysqlLogIn("username", "password"));
        Assertions.assertDoesNotThrow(() -> mysql.mysqlLogIn(Prop.getUsername(), Prop.getPassword()));
    }

    @ParameterizedTest
    @ValueSource(strings = "database2_db")
    @DisplayName(value = "CREATE DATABASE <database>;")
    void mysqlCreateDatabaseTest(String database) {
        mysql.setDatabase(database);
        Assertions.assertDoesNotThrow(() -> mysql.mysqlCreateDatabase());
    }

    @Test
    @DisplayName(value = "CREATE TABLE <table> (<column> <datatype>);")
    void mysqlCreateTableTest() {
        String[] column = new String[]{"String_t2", "Integer_t2"};
        String[] datatype = new String[]{"VARCHAR(256)", "INT NOT NULL"};
        mysql.setTable("table3_t");
        Assertions.assertDoesNotThrow(() -> mysql.mysqlCreateTable(column, datatype));
    }

    @ParameterizedTest
    @ValueSource(strings = {"aaa", "bbb", "ccc"})
    @DisplayName(value = "INSERT INTO <table> VALUE (<update>); setVarargs")
    void mysqlSetVarargsTest(String firstUpdate) {
        instruction = "INSERT INTO " + table + " VALUE (?, ?, ?, ?)";
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSetVarargs(instruction, firstUpdate, 99, true, LocalDateTime.now()));
    }

    @ParameterizedTest
    @CsvSource(value = {
        "ddd;10;true;2022-07-06 12:00:00",
        "eee;11;false;2022-07-05 13:00:01",
        "fff;12;false;2022-06-06 14:01:00"
    }, delimiter = ';')
    @DisplayName(value = "INSER INTO <table> VALUE (<update>); set")
    void mysqlSetTest(String csv0, String csv1, String csv2, String csv3) {
        Object[] csvUpload = new Object[]{
            csv0,
            Integer.parseInt(csv1),
            Boolean.parseBoolean(csv2),
            LocalDateTime.parse(csv3, formatter)
        };
        instruction = "INSERT INTO " + table + " VALUE (?, ?, ?, ?)";
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction, csvUpload));
    }

    @Test
    @DisplayName(value = "SELECT * FROM <table> LIMIT 300;")
    void mysqlGetToCollectionTest() {
        select = new ArrayList<>();
        instruction = "SELECT * FROM " + table + " LIMIT 300";
        Assertions.assertDoesNotThrow(() -> select = mysql.mysqlGetToCollection(instruction));
        Assertions.assertNotNull(select.get(0));
    }

    @Test
    @DisplayName(value = "CREATE VIEW <view> AS SELECT <column> FROM <table>;")
    void mysqlviewTest() {
        select = new LinkedList<>();
        instruction = "CREATE VIEW " + view + " AS SELECT String_t, DateTime_t FROM " + table;
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction));
        instruction = "SELECT * FROM " + view + " LIMIT 300";
        Assertions.assertDoesNotThrow(() -> select = mysql.mysqlGetToCollection(instruction));
        Assertions.assertNotNull(select.get(0));
    }

    @Test
    @DisplayName(value = "ALTER TABLE <table> ADD COLUMN <column> <datatype>;")
    void mysqlalterTableTest() {
        select = new LinkedList<>();
        instruction = "ALTER TABLE " + table + " ADD COLUMN Integer2_t INT NOT NULL DEFAULT 0";
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction));
        instruction = "SELECT * FROM " + table + " LIMIT 300";
        Assertions.assertDoesNotThrow(() -> select = mysql.mysqlGetToCollection(instruction));
        Assertions.assertNotNull(select.get(0));
    }

    @Test
    @DisplayName(value = "UPDATE <table> SET <column> = <condition> WHERE <column> = <condition>;")
    void mysqlSetUpdateTest() {
        Object[] csv = new Object[]{"yyy", "55", "eee"};
        instruction = "UPDATE " + table + " SET String_t = ?, Integer_t = ? WHERE String_t = ?";
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction, csv));
    }

    @ParameterizedTest
    @ValueSource(strings = "fff")
    @DisplayName(value = "DELETE FROM <table> WHERE <column> = <condition>")
    void mysqlSetTest(String column) {
        Object[] csv = new Object[]{column};
        instruction = "DELETE FROM " + table + " WHERE String_t = ?";
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction, csv));
    }

    @Test
    @DisplayName(value = "DROP VIEW <view>;")
    void mysqlDropViewTest() {
        instruction = "DROP VIEW " + view;
        Assertions.assertDoesNotThrow(() -> mysql.mysqlSet(instruction));
    }

    @Test
    @DisplayName(value = "DROP TABLE <table>;")
    void mysqlDropTableTest() {
        mysql.setTable("table3_t");
        Assertions.assertDoesNotThrow(() -> mysql.mysqlDropTable());
    }

    @ParameterizedTest
    @ValueSource(strings = "database2_db")
    @DisplayName(value = "DROP DATABASE <database>;")
    void mysqlDropDatabaseTest(String database) {
        mysql.setDatabase("drop_db");
        Assertions.assertThrows(SQLException.class, () -> mysql.mysqlDropDatabase());
        mysqlCreateDatabaseTest(database);
        Assertions.assertDoesNotThrow(() -> mysql.mysqlDropDatabase());
    }

    @AfterAll
    @DisplayName(value = "Disconnection to the database.")
    static void testDownAfterClass() {
        Assertions.assertDoesNotThrow(() -> mysql.mysqlDisconnection());
    }
}
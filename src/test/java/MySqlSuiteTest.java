import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName(value = "MySqlOperation Test.")
@SelectClasses(MySqlConnectorTest.class)
public class MySqlSuiteTest {}
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * To securely store sensitive information like usernames and passwords in a Java program, you can use a configuration file that is ignored by Git using a <b>".gitignore"</b> file.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class Prop {
    
    /**
     * username and password storage.
     */
    private static String username, password;

    /**
     * private Constructor.
     */
    private Prop() {}

    /**
     * username read from <b>"config.properties"</b> file.
     * @return      username.
     * @see         Prop#propertiesWrite()
     */
    public static String getUsername() {
        propertiesWrite();
        return username;
    }

    /**
     * password read from <b>"config.properties"</b> file.
     * @return      password.
     * @see         Prop#propertiesWrite()
     */
    public static String getPassword() {
        propertiesWrite();
        return password;
    }

    /**
     * {@code propertiesWrite} It reads property values from a file called <b>"config.properties"</b> and assigns them to the variables {@code username} and {@code password}.
     * <ul>
     *      <li>Create a new {@link java.lang.Properties Properties} object. This creates a new instance of the {@code Properties} class, which is used to store key-value pairs.</li>
     *      <li>Use a {@code try-with-resources} statement to open a {@link java.io.FileInputStream FileInputStream} and load the properties: 
     *      he {@code try-with-resources} statement is used to automatically close the {@code FileInputStream} after it's used. It opens a file stream for <b>"config.properties"</b> and
     *      loads the properties from the file into the {@code Properties} object. If any {@link java.io.IOException IOException} occurs during this process,
     *      it will be caught and its stack trace will be printed.</li>
     *      <li>The {@link java.util.Properties#getProperty(String) getProperty()} method is used to retrieve the value associated with a specific key in the {@code Properties} object. 
     *      In this case, it retrieves the values for the keys "username" and "password" and assigns them to the variables {@code username} and {@code password}, respectively.</li>
     * </ul>
     * {@code propertiesWrite} this method is used to read property values from a file and store them in variables. It assumes that the <b>"config.properties"</b> file exists 
     * and contains the necessary properties.
     */
    private static void propertiesWrite() {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
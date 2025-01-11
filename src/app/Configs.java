package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configs {
    private static final String PROPERTIES_FILE = "database.properties";

    protected static String dbHost;
    protected static String dbPort;
    protected static String dbUser;
    protected static String dbPass;
    protected static String dbName;

    static {
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(PROPERTIES_FILE);
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error reading properties file: " + e.getMessage());
            e.printStackTrace();
        }
        dbHost = properties.getProperty("dbHost");
        dbPort = properties.getProperty("dbPort");
        dbUser = properties.getProperty("dbUser");
        dbPass = properties.getProperty("dbPass");
        dbName = properties.getProperty("dbName");
    }
}

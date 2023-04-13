package test.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Provides access to the database
 */
public class Database {
    private final Logger logger = LogManager.getLogger(this.getClass());
    //create an object of the class database
    private static Database instance = new Database();

    private Properties properties;
    private Connection connection;

    //private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            logger.error("Database.loadProperties()... Cannot load the properties file.", ioe);
        } catch (Exception e) {
            logger.error("Database.loadProperties()..." + e);
        }
    }

    //get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws Exception {
        if (connection != null)
            return;
        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Database.connect()...Error: MySQL driver not found." + e);
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection." + e);
            }
        }
        connection = null;
    }

    /**
     * Run the sql.
     *
     * @param sqlFile the sql file to be read and executed line by line
     */
    public void runSQL(String sqlFile) {

        Statement stmt = null;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(sqlFile);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect();
            stmt = connection.createStatement();
            String sql = "";
            while (br.ready())
            {
                char inputValue = (char)br.read();
                if(inputValue == ';')
                {
                    stmt.executeUpdate(sql);
                    sql = "";
                }
                else
                    sql += inputValue;
            }
        } catch (SQLException se) {
            logger.error(se);
        } catch (Exception e) {
            logger.error(e);
        } finally {
            disconnect();
        }

    }
}

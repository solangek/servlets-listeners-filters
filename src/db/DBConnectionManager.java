package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * a very basic incomplete DB class to demonstrate database connectivity
 * for a simple database project using SQLite or mySQL server
 */
public class DBConnectionManager {

    private String dbURL, driver;
    private Connection con;

    public DBConnectionManager(String driver, String url) {
        this.driver = driver;
        this.dbURL = url;

        try {
            // load the driver
            Class.forName(driver);
        } catch (Exception e) {
            System.out.println("Failed to load the driver: " + driver);
        }

        connect();
    }

    /**
     * connect to the database
     */
    private void connect() {
        try {
            con = DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            System.out.println("Failed to establish a DB connection: " + dbURL );
        }
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        if (!isConnected()) {
            connect();
        }
        return this.con;
    }

    public void closeConnection() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            // debugging info
            //e.printStackTrace();
        }
    }

    private boolean isConnected() {
        try {
            if (!con.isClosed() || con != null) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}

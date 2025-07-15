package com.movietime.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseConnection {
//34.47.170.75
//    private static String url = "jdbc:mysql://localhost:3306/movietime_db";
//    private static String username = "root";
//    private static String password = "Mukesh";

    // In DatabaseConnection.java

    // 1. Use the Public IP from the GCP "Overview" page
    private static final String DB_HOST = "34.47.170.75";

    // 2. The database name you just created
    private static final String DB_NAME = "movietime_db";

    // 3. The username is 'root'
    private static final String DB_USERNAME = "root";

    // 4. The password you created for the 'root' user
    private static final String DB_PASSWORD = "movietime-db";

    private static final String JDBC_URL = "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME + "?useSSL=true";
    private static Connection connection = null;

    private DatabaseConnection() {

    }
    public static Connection getConnection() {
        if ( connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL,DB_USERNAME,DB_PASSWORD);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

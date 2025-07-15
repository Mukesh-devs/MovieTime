package com.movietime.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class DatabaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/movietime_db";
    private static String username = "root";
    private static String password = "Mukesh";

    private static Connection connection = null;

    private DatabaseConnection() {

    }
    public static Connection getConnection() {
        if ( connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url,username,password);
            }
            catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

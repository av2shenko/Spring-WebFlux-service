package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBConnectivity {
    private static final String url = "jdbc:postgresql://localhost:5432/";
    private static final String driver = "org.postgresql.Driver";
    private static final String login = "postgres";
    private static final String password = "postgres";

    public void ConnectDB() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE demodb");
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
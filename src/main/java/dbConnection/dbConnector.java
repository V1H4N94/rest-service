/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbConnection;

import java.sql.*;

/**
 *
 * @author User
 */
public class dbConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cmu";
    private static final String USER = "root";
    private static final String PASS = "";
    
    private static dbConnector instance;
    private Connection connection;
    
    private dbConnector() {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException("Failed to register MySQL driver.", e);
        }
    }
    
    public static dbConnector getInstance() {
        if (instance == null) {
            instance = new dbConnector();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            }
            return connection;
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
            throw e;
        }
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DBUtilsAdmin {
    static final String DB_URL = "jdbc:mysql://localhost:3306/cmu";
    static final String USER = "root";
    static final String PASS = "";
    
    public Admin getAdmin(int id) throws SQLException {
        Admin admin = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String query = "SELECT * FROM admins WHERE id = ?";
            
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setInt(1, id);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        admin = new Admin();
                        admin.setId(rs.getInt("id"));
                        admin.setUser(rs.getString("username"));
                        admin.setPass(rs.getString("password"));
                        admin.setName(rs.getString("fName"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw e;
        }
        return admin;
    }
    
    public List<Admin> getAdmins() {
        List<Admin> admins = new ArrayList<>();
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                 Statement stmt = conn.createStatement(); 
                 ResultSet rs = stmt.executeQuery("SELECT * FROM admins")) {
                
                while (rs.next()) {
                    Admin adm = new Admin();
                    adm.setId(rs.getInt("id"));
                    adm.setUser(rs.getString("username"));
                    adm.setPass(rs.getString("password"));
                    adm.setName(rs.getString("fName"));
                    admins.add(adm);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admins;
    }
    
    public boolean addAdmin(Admin adm) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                 Statement stmt = conn.createStatement()) {
                
                String query = "INSERT INTO admins (id, username, password, fName) VALUES (" +
                             adm.getId() + ", '" + 
                             adm.getUser() + "', '" + 
                             adm.getPass() + "', '" + 
                             adm.getName() + "')";
                             
                stmt.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateAdmin(Admin adm) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                 Statement stmt = conn.createStatement()) {
                
                String query = "UPDATE admins SET " +
                             "username = '" + adm.getUser() + "', " +
                             "password = '" + adm.getPass() + "', " +
                             "fName = '" + adm.getName() + "' " +
                             "WHERE id = " + adm.getId();
                             
                stmt.executeUpdate(query);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteAdmin(int id) {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                 Statement stmt = conn.createStatement()) {
                
                stmt.executeUpdate("DELETE FROM admins WHERE id = " + id);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

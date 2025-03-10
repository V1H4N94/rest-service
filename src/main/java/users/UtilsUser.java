/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnector;

/**
 *
 * @author User
 */
public class UtilsUser {
    private dbConnector db;
    
    public UtilsUser() {
        this.db = dbConnector.getInstance();
    }
    
    public User getUser(int id) throws SQLException {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE id = ?";
            
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setInt(1, id);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        user = new User();
                        user.setId(rs.getInt("id"));
                        user.setName(rs.getString("fullName"));
                        user.setPass(rs.getString("pass"));
                        user.setEmail(rs.getString("email"));
                        user.setTel(rs.getString("tel"));
                        user.setNic(rs.getString("identity"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw e;
        }
        return user;
    }
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            try (Connection conn = db.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
                
                while (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("fullName"));
                    user.setPass(rs.getString("pass"));
                    user.setEmail(rs.getString("email"));
                    user.setTel(rs.getString("tel"));
                    user.setNic(rs.getString("identity"));
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public boolean addUser(User user) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO users (id, fullName, pass, email, tel, identity) VALUES (?, ?, ?, ?, ?, ?)")) {
                
                pstmt.setInt(1, user.getId());
                pstmt.setString(2, user.getName());
                pstmt.setString(3, user.getPass());
                pstmt.setString(4, user.getEmail());
                pstmt.setString(5, user.getTel());
                pstmt.setString(6, user.getNic());
                
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateUser(User user) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE users SET fullName = ?, pass = ?, email = ?, tel = ?, identity = ? WHERE id = ?")) {
                
                pstmt.setString(1, user.getName());
                pstmt.setString(2, user.getPass());
                pstmt.setString(3, user.getEmail());
                pstmt.setString(4, user.getTel());
                pstmt.setString(5, user.getNic());
                pstmt.setInt(6, user.getId());
                
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteUser(int id) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
                
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
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

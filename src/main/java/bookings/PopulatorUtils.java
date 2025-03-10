/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookings;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnector;

/**
 *
 * @author User
 */
public class PopulatorUtils {
    private dbConnector db;
    
    public PopulatorUtils() {
        this.db = dbConnector.getInstance();
    }
    
    public populator getPackage(int id) throws SQLException {
        populator packageObj = null;
        try {
            String query = "SELECT * FROM packages WHERE package_id = ?";
            
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setInt(1, id);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        packageObj = new populator();
                        packageObj.setId(rs.getInt("package_id"));
                        packageObj.setName(rs.getString("package_name"));
                        packageObj.setDescription(rs.getString("description"));
                        packageObj.setPrice(rs.getDouble("price"));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw e;
        }
        return packageObj;
    }
    
    public List<populator> getPackages() {
        List<populator> packages = new ArrayList<>();
        try {
            try (Connection conn = db.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM packages")) {
                
                while (rs.next()) {
                    populator packageObj = new populator();
                    packageObj.setId(rs.getInt("package_id"));
                    packageObj.setName(rs.getString("package_name"));
                    packageObj.setDescription(rs.getString("description"));
                    packageObj.setPrice(rs.getDouble("price"));
                    packages.add(packageObj);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return packages;
    }
    
    public boolean addPackage(populator packageObj) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO packages (package_name, description, price) VALUES (?, ?, ?)")) {
                
                pstmt.setString(1, packageObj.getName());
                pstmt.setString(2, packageObj.getDescription());
                pstmt.setDouble(3, packageObj.getPrice());
                
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
    
    public boolean updatePackage(populator packageObj) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE packages SET package_name = ?, description = ?, price = ? WHERE package_id = ?")) {
                
                pstmt.setString(1, packageObj.getName());
                pstmt.setString(2, packageObj.getDescription());
                pstmt.setDouble(3, packageObj.getPrice());
                pstmt.setInt(4, packageObj.getId());
                
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
    
    public boolean deletePackage(int id) {
        try {
            try (Connection conn = db.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement("DELETE FROM packages WHERE package_id = ?")) {
                
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

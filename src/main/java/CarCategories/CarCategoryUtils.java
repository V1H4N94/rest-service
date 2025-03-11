/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CarCategories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnector;

/**
 *
 * @author User
 */
public class CarCategoryUtils {
    private dbConnector db;
    
    public CarCategoryUtils() {
        this.db = dbConnector.getInstance();
    }
    
    public CarCategories getCarCategory(int id) throws SQLException {
        CarCategories category = null;
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM vehicletypes WHERE vehicle_type_id = ?")) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    category = new CarCategories(
                        rs.getInt("vehicle_type_id"),
                        rs.getString("vehicle_cat"),
                        rs.getString("description")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw e;
        }
        return category;
    }
    
    public List<CarCategories> getCarCategories() {
        List<CarCategories> categories = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM vehicletypes")) {
            
            while (rs.next()) {
                categories.add(new CarCategories(
                    rs.getInt("vehicle_type_id"),
                    rs.getString("vehicle_cat"),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public boolean addCarCategory(CarCategories category) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO vehicletypes (vehicle_type_id, vehicle_cat, description) VALUES (?, ?, ?)");) {
            
            pstmt.setInt(1, category.getVehicleTypeId());
            pstmt.setString(2, category.getVehicleCat());
            pstmt.setString(3, category.getDescription());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCarCategory(CarCategories category) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE vehicletypes SET vehicle_cat = ?, description = ? WHERE vehicle_type_id = ?")) {
            
            pstmt.setString(1, category.getVehicleCat());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getVehicleTypeId());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCarCategory(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM vehicletypes WHERE vehicle_type_id = ?")) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

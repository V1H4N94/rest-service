/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cars;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import dbConnection.dbConnector;

/**
 *
 * @author User
 */
public class CarUtils {
    private dbConnector db;
    
    public CarUtils() {
        this.db = dbConnector.getInstance();
    }
    
    public Car getCar(int id) throws SQLException {
        Car car = null;
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM cars WHERE id = ?")) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car(
                        rs.getInt("id"),
                        rs.getString("license"),
                        rs.getString("owner"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("category")
                    );
                }
            }
        }
        return car;
    }
    
    public List<Car> getCars() {
        List<Car> cars = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM cars")) {
            
            while (rs.next()) {
                cars.add(new Car(
                    rs.getInt("id"),
                    rs.getString("license"),
                    rs.getString("owner"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getString("category")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }
    
    public boolean addCar(Car car) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO cars (license, owner, brand, model, category) VALUES (?, ?, ?, ?, ?)")) {
            
            pstmt.setString(1, car.getLicense());
            pstmt.setString(2, car.getOwner());
            pstmt.setString(3, car.getBrand());
            pstmt.setString(4, car.getModel());
            pstmt.setString(5, car.getCategory());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCar(Car car) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE cars SET license = ?, owner = ?, brand = ?, model = ?, category = ? WHERE id = ?")) {
            
            pstmt.setString(1, car.getLicense());
            pstmt.setString(2, car.getOwner());
            pstmt.setString(3, car.getBrand());
            pstmt.setString(4, car.getModel());
            pstmt.setString(5, car.getCategory());
            pstmt.setInt(6, car.getId());
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteCar(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM cars WHERE id = ?")) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

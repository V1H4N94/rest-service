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
public class bookingRequestUtils {
    private dbConnector db;

    public bookingRequestUtils() {
        this.db = dbConnector.getInstance();
    }

    public bookingRequest getRequest(int id) throws SQLException {
        bookingRequest request = null;
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM requests WHERE id = ?")) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    request = new bookingRequest(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("package"),
                        rs.getString("date"),
                        rs.getString("location"),
                        rs.getString("car_type")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            throw e;
        }
        return request;
    }

    public List<bookingRequest> getAllRequests() {
        List<bookingRequest> requests = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM requests")) {

            while (rs.next()) {
                requests.add(new bookingRequest(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("package"),
                    rs.getString("date"),
                    rs.getString("location"),
                    rs.getString("car_type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public boolean addRequest(bookingRequest request) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO requests (email, package, date, location, car_type) VALUES (?, ?, ?, ?, ?)")) {

            pstmt.setString(1, request.getEmail());
            pstmt.setString(2, request.getPackageType());
            pstmt.setString(3, request.getDate());
            pstmt.setString(4, request.getLocation());
            pstmt.setString(5, request.getCarType());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateRequest(bookingRequest request) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE requests SET email = ?, package = ?, date = ?, location = ?, car_type = ? WHERE id = ?")) {

            pstmt.setString(1, request.getEmail());
            pstmt.setString(2, request.getPackageType());
            pstmt.setString(3, request.getDate());
            pstmt.setString(4, request.getLocation());
            pstmt.setString(5, request.getCarType());
            pstmt.setInt(6, request.getId());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRequest(int id) {
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM requests WHERE id = ?")) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

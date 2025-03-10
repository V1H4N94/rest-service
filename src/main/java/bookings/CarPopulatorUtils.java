package bookings;

import dbConnection.dbConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

/**
 *
 * @author User
 */
public class CarPopulatorUtils {
    private dbConnector db;
    
    public CarPopulatorUtils() {
        this.db = dbConnector.getInstance();
    }
    
    /**
     * Get all vehicle types for a specific package
     * @param packageId The package ID
     * @return List of vehicle types associated with the package
     * @throws SQLException if a database error occurs
     */
    public List<VehicleType> getVehiclesByPackage(int packageId) throws SQLException {
        List<VehicleType> vehicleTypes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT vt.vehicle_type_id, vt.vehicle_name, vt.description " +
                          "FROM VehicleTypes vt " +
                          "JOIN PackageVehicles pv ON vt.vehicle_type_id = pv.vehicle_type_id " +
                          "WHERE pv.package_id = ?";
            
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, packageId);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                VehicleType vehicleType = new VehicleType();
                vehicleType.setVehicleTypeId(rs.getInt("vehicle_type_id"));
                vehicleType.setVehicleName(rs.getString("vehicle_name"));
                vehicleType.setDescription(rs.getString("description"));
                vehicleTypes.add(vehicleType);
            }
        } catch (SQLException e) {
            System.err.println("Database error in getVehiclesByPackage: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                // Don't close the connection here, as it's managed by dbConnector
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        
        return vehicleTypes;
    }
    
    /**
     * Get all package-vehicle relationships
     * @return List of all package-vehicle relationships
     */
    public List<CarPopulator> getAllPackageVehicles() {
        List<CarPopulator> packageVehicles = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT pv.package_id, pv.vehicle_type_id " +
                          "FROM PackageVehicles pv";
            
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                CarPopulator packageVehicle = new CarPopulator();
                packageVehicle.setPackageId(rs.getInt("package_id"));
                packageVehicle.setVehicleType(rs.getInt("vehicle_type_id"));
                packageVehicles.add(packageVehicle);
            }
        } catch (SQLException e) {
            System.err.println("Database error in getAllPackageVehicles: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                // Don't close the connection here, as it's managed by dbConnector
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        
        return packageVehicles;
    }
    
    /**
     * Get all packages and their associated vehicles in a format suitable for frontend dropdowns
     * @return JSON string containing all packages and vehicles
     * @throws SQLException if a database error occurs
     */
    public String getAllPackagesAndVehicles() throws SQLException {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> packages = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // First, get all packages
            conn = db.getConnection();
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            
            String packageQuery = "SELECT package_id, package_name, description FROM Packages";
            pstmt = conn.prepareStatement(packageQuery);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> packageInfo = new HashMap<>();
                int packageId = rs.getInt("package_id");
                packageInfo.put("id", packageId);
                packageInfo.put("name", rs.getString("package_name"));
                packageInfo.put("description", rs.getString("description"));
                
                // Get vehicles for this package
                List<Map<String, Object>> vehicles = getVehiclesForPackageAsMap(packageId, conn);
                packageInfo.put("vehicles", vehicles);
                
                packages.add(packageInfo);
            }
            
            result.put("packages", packages);
            
            // Also include a flat list of all vehicle types
            List<Map<String, Object>> allVehicles = getAllVehicleTypesAsMap(conn);
            result.put("allVehicles", allVehicles);
            
            Gson gson = new Gson();
            return gson.toJson(result);
            
        } catch (SQLException e) {
            System.err.println("Database error in getAllPackagesAndVehicles: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                // Don't close the connection here, as it's managed by dbConnector
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    /**
     * Helper method to get vehicles for a specific package as a Map
     */
    private List<Map<String, Object>> getVehiclesForPackageAsMap(int packageId, Connection conn) throws SQLException {
        List<Map<String, Object>> vehicles = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT vt.vehicle_type_id, vt.vehicle_name, vt.description " +
                          "FROM VehicleTypes vt " +
                          "JOIN PackageVehicles pv ON vt.vehicle_type_id = pv.vehicle_type_id " +
                          "WHERE pv.package_id = ?";
            
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, packageId);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> vehicle = new HashMap<>();
                vehicle.put("id", rs.getInt("vehicle_type_id"));
                vehicle.put("name", rs.getString("vehicle_name"));
                vehicle.put("description", rs.getString("description"));
                vehicles.add(vehicle);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        
        return vehicles;
    }
    
    /**
     * Helper method to get all vehicle types as a Map
     */
    private List<Map<String, Object>> getAllVehicleTypesAsMap(Connection conn) throws SQLException {
        List<Map<String, Object>> vehicles = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            String query = "SELECT vehicle_type_id, vehicle_name, description FROM VehicleTypes";
            
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Map<String, Object> vehicle = new HashMap<>();
                vehicle.put("id", rs.getInt("vehicle_type_id"));
                vehicle.put("name", rs.getString("vehicle_name"));
                vehicle.put("description", rs.getString("description"));
                vehicles.add(vehicle);
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        
        return vehicles;
    }
}
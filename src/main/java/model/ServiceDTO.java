package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.log.Log;
import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;

public class ServiceDTO {
    
    private static final String INSERT_SERVICE_SQL = "INSERT INTO service (name, description, price) VALUES (?, ?, ?)";
    private static final String SELECT_ALL_SERVICES = "SELECT * FROM service";
    private static final String SELECT_SERVICE_BY_ID = "SELECT * FROM service WHERE service_id = ?";
    private static final String UPDATE_SERVICE_SQL = "UPDATE service SET name=?, description=?, price=? WHERE service_id=?";
    private static final String DELETE_SERVICE_SQL = "DELETE FROM service WHERE service_id=?";

    protected static Connection getConnection() throws SQLException {
        return DBUtil.getConnection();
    }

    public boolean addService(String name, String description, BigDecimal price) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SERVICE_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SERVICES);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int service_id = rs.getInt("service_id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");

                Service service = new Service(service_id, name, description, price);
                services.add(service);
            }
        } catch (SQLException e) {
            // Handle the exception properly
            e.printStackTrace();
            System.out.print("An error occurred while retrieving all services: " + e.getMessage());
        }
        return services;
    }

    public Service getServiceById(int serviceId) {
        Service service = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SERVICE_BY_ID)) {
            preparedStatement.setInt(1, serviceId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("service_id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    BigDecimal price = resultSet.getBigDecimal("price");
                    service = new Service(id, name, description, price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    public boolean updateService(int serviceId, String name, String description, BigDecimal price) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.setInt(4, serviceId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteService(int serviceId) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SERVICE_SQL)) {
            preparedStatement.setInt(1, serviceId);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void updateService(Service service) throws SQLException {
        
        try(Connection connection=getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SERVICE_SQL)) {
            preparedStatement.setString(1, service.getName());
            preparedStatement.setString(2, service.getDescription());
            preparedStatement.setBigDecimal(3, service.getPrice());
            preparedStatement.setInt(4, service.getService_id());

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the update was successful
            if (rowsAffected == 0) {
                throw new SQLException("Failed to update service with ID: " + service.getService_id());
            }
        } 
        }
    }


/**
 * Utility class for interacting with the MySQL Database 'flightreservation'.
 * This class provides methods to establish connections, add customers and bookings,
 * and retrieve customers and bookings from the database.
 * 
 * @author Jose Antoinne Templonuevo
 * @version 1.0
 */
package com.mycompany.flightreservationredux;


import java.sql.*;
import java.util.ArrayList;


public class db {
    private static final String URL = "jdbc:mysql://localhost/flight reservation";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    /**
     * Establishes a connection to the MySQL database.
     *
     * @return Connection object representing the connection to the database.
     * @throws SQLException if a database access error occurs.
     */

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    /**
     * Adds a new customer to the 'customers' table.
     *
     * @param customer The Customer object to be added to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customers (name) VALUES (?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    /**
     * Retrieves all customers from the 'customers' table.
     *
     * @return An ArrayList of Customer objects representing all customers in the database.
     */
    public static ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("customerId");
                String name = resultSet.getString("name");
                customers.add(new Customer(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }


    /**
     * Adds a new booking to the 'bookings' table.
     *
     * @param booking The Booking object to be added to the database.
     * @throws SQLException if a database access error occurs.
     */
    public static void addBooking(Booking booking) throws SQLException {
        String query = "INSERT INTO bookings(customerId, day, time, country, city) VALUES (?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, booking.getCustomerId());
            preparedStatement.setString(2, booking.getDay());
            preparedStatement.setString(3, booking.getTime());
            preparedStatement.setString(4, booking.getCountry());
            preparedStatement.setString(5, booking.getCity());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setId(generatedKeys.getInt(1));
                }
            }
        }
    }


    /**
     * Retrieves all bookings from the 'bookings' table.
     *
     * @return An ArrayList of Booking objects representing all bookings in the database.
     */
    public static ArrayList<Booking> getBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int customerId = resultSet.getInt("customerId");
                String day = resultSet.getString("day");
                String time = resultSet.getString("time");
                String country = resultSet.getString("country");
                String city = resultSet.getString("city");
                bookings.add(new Booking(id, customerId, day, time, country, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }


    public static void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM customers WHERE customerId = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteBooking(int id) throws SQLException {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
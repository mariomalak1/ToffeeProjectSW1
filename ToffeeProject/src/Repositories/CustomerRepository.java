package Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.Cart;
import Models.Customer;

public class CustomerRepository {
    private final Connection connection;

    public CustomerRepository() {
        this.connection = DatabaseInitializer.getConnection();
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (UserID, LoyaltyPoints) " +
                "VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customer.getID());
            statement.setInt(2, customer.getLoyaltyPoints());
            statement.executeUpdate();
        }
    }

    public void deleteCustomer(int customerId) throws SQLException {
        String query = "DELETE FROM Customer WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
            UserRepository userRepository = new UserRepository();
            userRepository.deleteUser(customerId);
        }
    }

    public Customer getCustomerById(int UserId) throws SQLException {
        String query = "SELECT * FROM Customer WHERE UserID = ?";
        Customer customer = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, UserId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    customer = extractCustomerFromResultSet(resultSet);
                }
            }
        }

        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Customer customer = extractCustomerFromResultSet(resultSet);
                customers.add(customer);
            }
        }

        return customers;
    }

    public List<Cart> getAllCartsForCustomer(int customerID){
        CartRepository cartRepository = new CartRepository();
        try {
            return cartRepository.getCartsByCustomerID(customerID);
        }catch (SQLException ig){
            return new ArrayList<>();
        }
    }

    public List<Cart> getAllUnFinishedCartsForCustomer(int customerID){
        CartRepository cartRepository = new CartRepository();
        try {
            return cartRepository.getUnfinishedCartsByCustomerId(customerID);
        }catch (SQLException ig){
            return new ArrayList<>();
        }
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE Customers SET LoyaltyPoints WHERE UserID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            UserRepository userRepository = new UserRepository();

            userRepository.updateUser(customer);
            statement.setInt(1, customer.getLoyaltyPoints());
            statement.setInt(2, customer.getID());
            statement.executeUpdate();
        }
    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int UserId = resultSet.getInt("UserID");
        int loyaltyPoints = resultSet.getInt("LoyaltyPoints");

        Models.User user = new UserRepository().getUserById(UserId);


        Customer customer = new Customer(user, loyaltyPoints);
        customer.setID(UserId);
        customer.setActive(user.isActive());
        customer.setLoyaltyPoints(loyaltyPoints);

        return customer;
    }
}

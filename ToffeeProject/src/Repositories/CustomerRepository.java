package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Customer;

public class CustomerRepository {
    private final Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO Customer (Name, Email, Password, PhoneNumber, Admin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getPhoneNumber());
            statement.setBoolean(5, customer.isAdmin());
            statement.executeUpdate();
        }
    }

    public Customer getCustomerById(int id) throws SQLException {
        String query = "SELECT * FROM Customer WHERE ID = ?";
        Customer customer = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                customer = mapCustomer(resultSet);
            }
        }

        return customer;
    }

    public List<Customer> getAllCustomers() throws SQLException {
        String query = "SELECT * FROM Customer";
        List<Customer> customers = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Customer customer = mapCustomer(resultSet);
                customers.add(customer);
            }
        }

        return customers;
    }

    public void updateCustomer(Customer customer) throws SQLException {
        String query = "UPDATE Customer SET Name = ?, Email = ?, Password = ?, PhoneNumber = ?, Admin = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getEmail());
            statement.setString(3, customer.getPassword());
            statement.setString(4, customer.getPhoneNumber());
            statement.setBoolean(5, customer.isAdmin());
            statement.setInt(6, customer.getID());
            statement.executeUpdate();
        }
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        String query = "DELETE FROM Customer WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customer.getID());
            statement.executeUpdate();
        }
    }

    private Customer mapCustomer(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        String password = resultSet.getString("Password");
        String phoneNumber = resultSet.getString("PhoneNumber");
        boolean admin = resultSet.getBoolean("Admin");
        return new Customer(id, name, email, password, phoneNumber, admin);
    }
}

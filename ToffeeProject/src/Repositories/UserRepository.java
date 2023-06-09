package Repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Models.User;

public class UserRepository {
    private final Connection connection;

    public UserRepository() {
        this.connection = DatabaseInitializer.getConnection();
    }

    public User addUser(User user) throws SQLException {
        String query = "INSERT INTO User (Name, Email, Password, PhoneNumber, Active, IsAdmin) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.setBoolean(5, user.isActive());
            statement.setBoolean(6, user.isAdmin());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    user.setID(generatedID);
                } else {
                    throw new SQLException("Adding user failed, no ID obtained.");
                }
            }
        }

        return user;
    }


    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM User WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        }
    }

    public User getUserById(int userId) throws SQLException {
        String query = "SELECT * FROM User WHERE ID = ?";
        User user = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
            }
        }

        return user;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE User SET Name = ?, Email = ?, Password = ?, PhoneNumber = ?, Admin = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getPhoneNumber());
            statement.setBoolean(5, user.isAdmin());
            statement.setInt(6, user.getID());
            statement.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT * FROM User";
        List<User> users = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
        }

        return users;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        String email = resultSet.getString("Email");
        String password = resultSet.getString("Password");
        String phoneNumber = resultSet.getString("PhoneNumber");
        boolean active = resultSet.getBoolean("Active");
        boolean isAdmin = resultSet.getBoolean("IsAdmin");

        User user = new User(name, email, password, phoneNumber, isAdmin);
        user.setID(id);
        user.setActive(active);

        return user;
    }
}

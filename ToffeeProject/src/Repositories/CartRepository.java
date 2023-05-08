package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Cart;

public class CartRepository {
    private final Connection connection;

    public CartRepository() {
        this.connection = DatabaseInitializer.getConnection();
    }

    public void addCart(Cart cart) throws SQLException {
        String query = "INSERT INTO Cart (CustomerID) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cart.getCustomerID());
            statement.executeUpdate();
        }
    }

    public Cart getCartById(int id) throws SQLException {
        String query = "SELECT * FROM Cart WHERE ID = ?";
        Cart cart = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                cart = mapCart(resultSet);
            }
        }

        return cart;
    }

    public List<Cart> getAllCarts() throws SQLException {
        String query = "SELECT * FROM Cart";
        List<Cart> carts = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Cart cart = mapCart(resultSet);
                carts.add(cart);
            }
        }

        return carts;
    }

    public void updateCart(Cart cart) throws SQLException {
        String query = "UPDATE Cart SET CustomerID = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cart.getCustomerID());
            statement.setInt(2, cart.getID());
            statement.executeUpdate();
        }
    }

    public void deleteCart(Cart cart) throws SQLException {
        String query = "DELETE FROM Cart WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cart.getID());
            statement.executeUpdate();
        }
    }

    private Cart mapCart(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        int customerID = resultSet.getInt("CustomerID");
        return new Cart(customerID);
    }
}

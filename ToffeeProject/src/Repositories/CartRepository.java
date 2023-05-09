package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Controllers.OrderController;
import Models.Cart;
import Models.Order;


public class CartRepository {
    private static final String TABLE_NAME = "Cart";
    private final Connection conn;

    public CartRepository() {
        this.conn = DatabaseInitializer.getConnection();
    }

    public Cart createCart(Cart cart) throws SQLException {
        String sql = "INSERT INTO " + TABLE_NAME + " (CustomerID, Finished) VALUES (?, ?)";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, cart.getCustomerID());
            statement.setBoolean(2, cart.isFinished());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    cart.setID(generatedID);
                } else {
                    throw new SQLException("Adding Cart failed, no ID obtained.");
                }
            }
        }
        return cart;
    }

    public List<Cart> getCartsByCustomerID(int customerID) throws SQLException {
        List<Cart> carts = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE CustomerID = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, customerID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = mapCart(resultSet);
                    carts.add(cart);
                }
            }
        }

        return carts;
    }

    public List<Cart> getUnfinishedCartsByCustomerId(int customerID) throws SQLException {
        List<Cart> unfinishedCarts = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE CustomerID = ? AND Finished = 0";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, customerID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = mapCart(resultSet);
                    unfinishedCarts.add(cart);
                }
            }
        }

        return unfinishedCarts;
    }

    public List<Cart> getAllUnfinishedCarts() throws SQLException {
        List<Cart> unfinishedCarts = new ArrayList<>();
        String sql = "SELECT * FROM Cart WHERE Finished = 0";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = mapCart(resultSet);
                    unfinishedCarts.add(cart);
                }
            }
        }

        return unfinishedCarts;
    }

    public List<Cart> getAlCarts() throws SQLException {
        List<Cart> Carts = new ArrayList<>();
        String sql = "SELECT * FROM Cart";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Cart cart = mapCart(resultSet);
                    Carts.add(cart);
                }
            }
        }
        return Carts;
    }

    public Cart getCartByID(int cartID) throws SQLException {
        String sql = "SELECT * FROM Cart WHERE ID = ?";
        Cart cart = null;

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, cartID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cart = mapCart(resultSet);
                    List<Order> ordersOfCart = new OrderController().getAllOrdersInCart(cart.getID());
                    cart.setOrders(ordersOfCart);
                }
            }
        }
        return cart;
    }

    public void updateCart(Cart cart) throws SQLException {
        String sql = "UPDATE " + TABLE_NAME + " SET Finished = ? WHERE ID = ?";

        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setBoolean(1, cart.isFinished());
            statement.setInt(2, cart.getID());

            statement.executeUpdate();
        }
    }

    public void deleteCart(Cart cart) throws SQLException {
        String query = "DELETE FROM Cart WHERE ID = ?";

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, cart.getID());
            statement.executeUpdate();
        }
    }

    private Cart mapCart(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        int customerID = resultSet.getInt("CustomerID");
        boolean finished = resultSet.getBoolean("Finished");
        Cart cart = new Cart(customerID);
        cart.setID(id);
        cart.setFinished(finished);
        return cart;
    }
}

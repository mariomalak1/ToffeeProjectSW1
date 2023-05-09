package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Order;

public class OrderRepository {
    private final Connection connection;

    public OrderRepository() {
        this.connection = DatabaseInitializer.getConnection();
    }

    public Order addOrder(Order order) throws SQLException {
        String query = "INSERT INTO Orders (CandyID, Quantity, CartID) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getCandyId());
            statement.setInt(2, order.getQuantity());
            statement.setInt(3, order.getCartID());
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Adding user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedID = generatedKeys.getInt(1);
                    order.setID(generatedID);
                } else {
                    throw new SQLException("Adding user failed, no ID obtained.");
                }
            }
        }
        return order;
    }

    public Order getOrderById(int id) throws SQLException {
        String query = "SELECT * FROM Orders WHERE ID = ?";
        Order order = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                order = mapOrder(resultSet);
            }
        }

        return order;
    }

    public List<Order> getOrdersByCartId(int cartId) throws SQLException {
        String query = "SELECT * FROM Orders WHERE CartID = ?";
        List<Order> orders = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, cartId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Order order = mapOrder(resultSet);
                orders.add(order);
            }
        }

        return orders;
    }

    public void updateOrder(Order order) throws SQLException {
        String query = "UPDATE Orders SET CandyID = ?, Quantity = ?, CartID = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getCandyId());
            statement.setInt(2, order.getQuantity());
            statement.setInt(3, order.getCartID());
            statement.setInt(4, order.getId());
            statement.executeUpdate();
        }
    }

    public void deleteOrder(Order order) throws SQLException {
        String query = "DELETE FROM Orders WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.executeUpdate();
        }
    }

    private Order mapOrder(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        int candyId = resultSet.getInt("CandyID");
        int quantity = resultSet.getInt("Quantity");
        int cartId = resultSet.getInt("CartID");
        Order order = new Order(candyId, quantity, cartId);
        order.setID(id);
        return order;
    }
}

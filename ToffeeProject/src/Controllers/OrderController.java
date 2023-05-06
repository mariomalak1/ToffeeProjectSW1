package Controllers;

import Repositories.OrderRepository;
import java.sql.Connection;
import java.sql.SQLException;
import Models.Order;
import Models.*;
import Repositories.*;

public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(Connection connection) {
        orderRepository = new OrderRepository(connection);
    }

    public void addOrder(int id, int candyID, int quantity, int cartID) {
        try {
            Order order = new Order(candyID, quantity, candyID);
            orderRepository.addOrder(order);
            System.out.println("Order added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add order: " + e.getMessage());
        }
    }

    public void getOrderById(int orderId) {
        try {
            Order order = orderRepository.getOrderById(orderId);
            if (order != null) {
                System.out.println("Order details: " + order);
            } else {
                System.out.println("Order not found.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve order: " + e.getMessage());
        }
    }

    public void updateOrder(Order order) {
        try {
            orderRepository.updateOrder(order);
            System.out.println("Order updated successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to update order: " + e.getMessage());
        }
    }

    public void deleteOrder(Order order) {
        try {
            orderRepository.deleteOrder(order);
            System.out.println("Order deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to delete order: " + e.getMessage());
        }
    }

}
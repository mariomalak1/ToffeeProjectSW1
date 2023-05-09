package Controllers;

import Repositories.OrderRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Order;
import Models.*;
import Repositories.*;

public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController() {
        orderRepository = new OrderRepository();
    }

    public Order addOrder(int id, int candyID, int quantity, int customerID) {
        try {
            Customer customer = new CustomerController().getCustomerByID(customerID);
            if (customer == null){
                System.out.println("There's Error Happen..");
                return null;
            }
            Cart CustomerCart = customer.getCurrentCart();

            Order order = new Order(candyID, quantity, CustomerCart.getID());
            orderRepository.addOrder(order);

            if (CustomerCart.isFinished()){
                CartController cartController = new CartController();
                CustomerCart = cartController.createCart(customerID);
                CustomerCart.addOrder(order);
            }else{
                CustomerCart.addOrder(order);
            }
            return order;
        } catch (SQLException e) {
            System.out.println("Failed to add order: " + e.getMessage());
        }
        return null;
    }

    public Order getOrderById(int orderId) {
        try {
            Order order = orderRepository.getOrderById(orderId);
            if (order != null) {
                return order;
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve order: " + e.getMessage());
        }
        return null;
    }

    public Order updateOrder(Order order) {
        try {
            orderRepository.updateOrder(order);
            return order;
        } catch (SQLException e) {
            System.out.println("Failed to update order: " + e.getMessage());
        }
        return null;
    }

    public boolean deleteOrder(Order order) {
        try {
            orderRepository.deleteOrder(order);
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to delete order: " + e.getMessage());
        }
        return false;
    }

    public List<Order> getAllOrdersInCart(int cartId){
        try {
            return orderRepository.getOrdersByCartId(cartId);
        }
        catch(SQLException e){
            System.out.println("Failed To Get All Orders: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
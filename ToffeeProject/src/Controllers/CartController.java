package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Customer;
import Models.Order;
import Repositories.CartRepository;
import Models.Cart;
import Repositories.OrderRepository;

public class CartController {
    private final CartRepository cartRepository;

    public CartController() {
        this.cartRepository = new CartRepository();
    }

    public Cart createCart(int customerId) {
        try {
            Cart cart = new Cart(customerId);
            cart = cartRepository.createCart(cart);
            return cart;
        } catch (Exception e) {
            System.out.println("Failed to create cart: " + e.getMessage());
        }
        return null;
    }

    public Cart getCartById(int cartId) {
        try {
            Cart cart = cartRepository.getCartByID(cartId);
            if (cart != null) {
                List<Order> orders = new OrderRepository().getOrdersByCartId(cartId);
                cart.setOrders(orders);
                return cart;
                // Add more information about the cart as needed
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve cart: " + e.getMessage());
        }
        return null;
    }

    public void updateCartCustomerId(Cart cart, Customer customer) {
        try {
            if (cart != null) {
                cart.setCustomerID(customer.getID());
                cartRepository.updateCart(cart);
            } else {
                System.out.println("Cart not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update cart: " + e.getMessage());
        }
    }

    public void deleteCart(int cartId) {
        try {
            Cart cart = cartRepository.getCartByID(cartId);
            if (cart != null) {
                cartRepository.deleteCart(cart);
                System.out.println("Cart deleted successfully.");
            } else {
                System.out.println("Cart not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete cart: " + e.getMessage());
        }
    }

    public List<Cart> getAllCarts() {
        try {
            List<Cart> carts = cartRepository.getAlCarts();
            if (!carts.isEmpty()){
                return carts;
            }
        } catch (Exception e) {
            System.out.println("Failed to get carts: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    // return the cart that not finished
    public Cart getCurrentCart(int customerID){
        Cart cart = null;
        try {
            List<Cart> allCarts = cartRepository.getUnfinishedCartsByCustomerId(customerID);
            if (allCarts.isEmpty()){
                cart = createCart(customerID);
            }else {
                cart = allCarts.get(allCarts.size() - 1);
                List<Order> ordersOfCart = new OrderController().getAllOrdersInCart(cart.getID());
                cart.setOrders(ordersOfCart);
            }
            return cart;
        }
        catch (SQLException e){
            System.out.println("Error Happen Suddenly" + e.getMessage());
        }
        return cart;
    }

    public List<Cart> getAllFinishedCarts(){
        try {
            List<Cart> carts = cartRepository.getAllFinishedCarts();
            if (!carts.isEmpty()){
                return carts;
            }
        } catch (Exception e) {
            System.out.println("Failed to get carts: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}

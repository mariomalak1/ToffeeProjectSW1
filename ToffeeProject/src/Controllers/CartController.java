package Controllers;

import java.sql.Connection;
import java.util.List;
import Repositories.CartRepository;
import Models.Cart;

public class CartController {
    private final CartRepository cartRepository;

    public CartController(Connection connection) {
        this.cartRepository = new CartRepository(connection);
    }

    public void createCart(int customerId) {
        try {
            Cart cart = new Cart(customerId);
            cartRepository.addCart(cart);
            System.out.println("Cart created successfully. ID: " + cart.getID());
        } catch (Exception e) {
            System.out.println("Failed to create cart: " + e.getMessage());
        }
    }

    public void getCartById(int cartId) {
        try {
            Cart cart = cartRepository.getCartById(cartId);
            if (cart != null) {
                System.out.println("Cart ID: " + cart.getID());
                System.out.println("Customer ID: " + cart.getCustomerID());
                // Add more information about the cart as needed
            } else {
                System.out.println("Cart not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve cart: " + e.getMessage());
        }
    }

    public void updateCartCustomerId(int cartId, int newCustomerId) {
        try {
            Cart cart = cartRepository.getCartById(cartId);
            if (cart != null) {
                cart.setCustomerID(newCustomerId);
                cartRepository.updateCart(cart);
                System.out.println("Cart updated successfully.");
            } else {
                System.out.println("Cart not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update cart: " + e.getMessage());
        }
    }

    public void deleteCart(int cartId) {
        try {
            Cart cart = cartRepository.getCartById(cartId);
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

    public void getAllCarts() {
        try {
            List<Cart> carts = cartRepository.getAllCarts();
            if (!carts.isEmpty()) {
                System.out.println("All Carts:");
                for (Cart cart : carts) {
                    System.out.println("Cart ID: " + cart.getID());
                    System.out.println("Customer ID: " + cart.getCustomerID());
                    // Add more information about the cart as needed
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No carts found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to get carts: " + e.getMessage());
        }
    }
}

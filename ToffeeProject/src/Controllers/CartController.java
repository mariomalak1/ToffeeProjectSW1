package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            cartRepository.createCart(cart);
            return cart;
        } catch (Exception e) {
            System.out.println("Failed to create cart: " + e.getMessage());
        }
        return null;
    }

    public Cart getCartById(int cartId) {
        try {
            Cart cart = cartRepository.getCartByID(cartId);
            List<Order> orders = new OrderRepository().getOrdersByCartId(cartId);
            cart.setOrders(orders);
            if (cart != null) {
                return cart;
                // Add more information about the cart as needed
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve cart: " + e.getMessage());
        }
        return null;
    }

    public void updateCartCustomerId(int cartId, int newCustomerId) {
        try {
            Cart cart = cartRepository.getCartByID(cartId);
            if (cart != null) {
                cart.setCustomerID(newCustomerId);
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
            }
        }
        catch (SQLException e){
            System.out.println("Error Happen Suddenly" + e.getMessage());
        }
        return cart;
    }

    public List<Cart> getAllUnFinishedCarts(){
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
}

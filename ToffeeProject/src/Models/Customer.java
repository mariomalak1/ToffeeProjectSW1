package Models;
import java.util.*;
public class Customer extends User {
    private int loyaltyPoints;
    private List<Cart> Carts;
    // he must be user before he become customer
    private int userID;

    public Customer(String name, String email, String password, String phoneNumber, boolean admin) {
        super(name, email, password, phoneNumber, admin);
        loyaltyPoints = 0;
        Carts = new ArrayList<>();
    }

    private void setLoyaltyPoints(int points){
        loyaltyPoints = points;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    private void addLoyaltyPoints(int points){
        loyaltyPoints += points;
    }

    public List<Cart> getCarts() {
        return Carts;
    }

    public void setCarts(List<Cart> carts) {
        Carts = carts;
    }

    public void removeCarts(){
        Carts.clear();
    }

    public Cart getCart(int id){
        for (Cart cart: Carts) {
            if (cart.getID() == id){
                return cart;
            }
        }
        return null;
    }

    public void removeCart(Cart cart){
        Carts.remove(cart);
    }
}

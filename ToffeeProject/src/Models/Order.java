package Models;
public class Order {
    private int id;
    private int candyId;
    private int quantity;
    private int cartId;
    private static int LastId = 0;

    public Order(int candyID, int quantity, int cartID){
        LastId++;
        this.id = LastId;
        this.candyId = candyID;
        this.quantity = quantity;
        this.cartId = cartID;
    }
    public void setCandyId (int candyID  ) {
        this.candyId = candyID;
    }
    public void setQuantity(int quantity ) {
        this.quantity = quantity;
    }
    public void setCartID(int cartID ) {
        this.cartId =cartID;
    }
    public int getId() {
        return id;
    }
    public int getCartID() {
        return cartId;
    }
    public int getQuantity() {
        return quantity;
    }

    public int getCandyId() {
        return candyId;
    }

    public void setID(int id){
        this.id = id;
    }
}

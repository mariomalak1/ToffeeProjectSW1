package Models;
public class order {
    private int id;
    private int candyId;
    private int quantity;
    private int cartId;

    order(int candyID,int quantity,int cartID){
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
   public int  getID() {
         return id;
   }
    public int getCartID() {
         return cartId;
    }
    public int getQuantity() {
         return quantity;
    }


}

package Models;
import java.util.*;
public class Customer extends Models.User {
    private int customerID;
    private int loyaltyPoints;
     private Cart customerCart = new Cart(customerID);
    private int CartID;
    private Vector<Models.Order> history;
     private Models.Candy[] items;

    public int getCustomerID() {
    return customerID;
    }
    public Models.Candy searchItem( String itemNameOrBrand) {
        for (int i = 0; i < items.length; i++) {
            if (itemNameOrBrand == items[i].getName()) {
                return items[i];
            }
        }
        System.out.println("not found");
        return null;
    }
   public void ViewAllItems(){
        for (int i = 0; i< items.length;i++) {
            System.out.println(items[i].getCategoryID());
            System.out.println(items[i]. getPrice());
            System.out.println(items[i]. getID());
            System.out.println(items[i]. getUnit());
            System.out.println(items[i]. getDescription());
            System.out.println(items[i]. getImagePath());
            System.out.println(items[i]. getLoyaltyPoints());
        }
        }



    public void makeOrder(int candyID,int quantity,int cartID){
       Models.Order newOrder = new Models.Order(candyID,quantity,cartID);
        customerCart.addOrder(newOrder);
       history.add(newOrder);
    }


    public void viewOrderHistory(){
        for (int i = 0; i<history.size();i++){
            System.out.println(history.get(i).getID());
            System.out.println(history.get(i).getQuantity());
            System.out.println(history.get(i).getCartID());
        }

    }
     private void addLoyaltyPoints(int points ){
        loyaltyPoints += points;
    }

   public void setPhoneNumber(String phoneNumber  ){
        this.phoneNumber = phoneNumber;
   }

     public char[] sendOTP(String phoneNumber ){
        System.out.println("Generating OTP using random() : ");
        System.out.print("You OTP is : ");

        // Using numeric values
        String numbers = phoneNumber;

        // Using random method
        Random rndm_method = new Random();

         char[] otp = new char[6];

         for (int i = 0; i < 6; i++)
         {
             // Use of charAt() method : to get character value
             // Use of nextInt() as it is scanning the value as int
             otp[i] =
                     numbers.charAt(rndm_method.nextInt(numbers.length()));
         }
         return otp;
    }
    }

















package Views;

import Controllers.CandyController;
import Controllers.CartController;
import Controllers.CustomerController;
import Controllers.OrderController;
import Models.Customer;
import java.util.Scanner;

public class OrderView {
    public static void createOrder(Models.User user){
        Scanner scanner = new Scanner(System.in);
        int candyID = 0;
        int quantity = 0;

        System.out.println("Welcome In Create Order Page");

        System.out.println("Enter Candy ID You Want To Buy: ");
        String CandyID = scanner.nextLine();
        if (MainView.isNumeric(CandyID)){
            candyID = Integer.parseInt(CandyID);
        }else{
            System.out.println("Please Enter Valid Candy ID, Try Again");
            createOrder(user);
        }

        // check that this id in candies or not
        Models.Candy candy = new CandyController().getCandyDetails(candyID);
        if (candy == null){
            System.out.println("This ID Doesn't Found, Please Enter Valid One");
            createOrder(user);
        }
        else{
            System.out.println("Enter How Much Quantity You Want From This Candy : ");
            String quantityString = scanner.nextLine();
            if (MainView.isNumeric(quantityString)){
                quantity = Integer.parseInt(quantityString);
            }else{
                System.out.println("Please Enter Valid Quantity, Try Again");
                createOrder(user);
            }
        }

        // create the order successfully
        Customer customer = new CustomerController().getCustomerByID(user.getID());
        if (customer != null) {
            OrderController orderController = new OrderController();
            Models.Order order = orderController.addOrder(candyID, quantity, customer.getID());
            Models.Cart cart =  new CartController().getCartById(order.getCartID());

            cart.addOrder(order);
            System.out.println("Order Created Successfully");
        }
        else{
            System.out.println("Sorry You Don't Login Yet, PLease Try Again");
        }
    }
}

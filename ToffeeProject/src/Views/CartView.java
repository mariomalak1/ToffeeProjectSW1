package Views;

import Controllers.CandyController;
import Controllers.CartController;
import Controllers.CustomerController;
import Models.Cart;
import Models.Customer;

import java.util.List;
import java.util.Scanner;

public class CartView {

    public static void checkOut(Models.User user){
        Customer customer = new CustomerController().getCustomerByID(user.getID());
        if (customer == null){
            System.out.println("Error Happen");
        }else{
            Cart currentCart = new CartController().getCurrentCart(customer.getID());
            if (currentCart == null){
                System.out.println("Error Happen");
            }else{
                System.out.println("Welcome In Check Out Page");
                // if the cart is finished, he must create new order to make new cart
                if (currentCart.getOrders().isEmpty() || currentCart.isFinished()){
                    System.out.println("No Orders Create Yet, Create Order First");
                }
                else {
                    System.out.println("This Your Orders Created : ");
                    for (Models.Order order : currentCart.getOrders()) {
                        Models.Candy candy = new CandyController().getCandyDetails(order.getCandyId());
                        if (candy != null) {
                            System.out.println("Candy : " + candy.getName() + " - Quantity : "
                                    + order.getQuantity() + " - Candy Price : " + candy.getPrice()
                                    + " - Costs : " + order.getQuantity() * candy.getPrice());
                            System.out.println("--------------------------");
                        }
                    }
                    System.out.println("Total Cost : " + currentCart.getTotalCostOfCart());
                    checkOutResponse(user);
                }
            }
        }
    }

    private static void checkOutResponse(Models.User user){
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1- To Confirm Order Page");
            System.out.println("2- Main Menu");
            System.out.println("This All Orders You Order Them : ");
            String stringResponse = scanner.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                checkOutResponse(user);
            } else {
                System.out.println("Please Enter Valid Response, Try Again");
            }
            if (stringResponse.equals("2")) {
                UserView.ViewCustomerPage(user);
                break;
            } else if (stringResponse.equals("1")) {
                ConfirmOrder(user);
                break;
            } else {
                System.out.println("Enter Valid Response");
            }
        }
    }

    public static void ConfirmOrder(Models.User user){
        System.out.println("Order Confirmed Successfully");
        Customer customer = new CustomerController().getCustomerByID(user.getID());
        if (customer == null){
            System.out.println("Error Happen Suddenly");
        }else{
            CartController cartController = new CartController();
            Cart cart = cartController.getCurrentCart(customer.getID());
            cart.setFinished(true);
            cartController.updateCartCustomerId(cart.getID(), customer.getID());
        }
    }

    // Admin View
    public static void allCartsNotFinished(Models.User user){
        if (user.isAdmin()){
            CartController cartController = new CartController();
            List<Cart> carts = cartController.getAllUnFinishedCarts();
            if (carts.isEmpty()){
                System.out.println("No Orders Created Yet");
            }
            else{
                for (Cart cart: carts) {
                    System.out.println("-------------------------------");
                    Customer customer = new CustomerController().getCustomerByID(cart.getCustomerID());
                    System.out.println("ID : " + cart.getID());
                    System.out.println("Customer Name : " + customer.getName());
                    System.out.println("Total Money Of This Order : " + cart.getTotalCostOfCart());
                }
            }
        }
    }
}

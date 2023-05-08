package Views;

import java.util.Scanner;
import Controllers.CustomerController;
import Models.Customer;

public class User {
    public static void registrationForCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Registration page");

        System.out.println("Enter Your Name : ");
        String name = scanner.nextLine();

        System.out.println("Enter Your Email : ");
        String email = scanner.nextLine();

        System.out.println("Enter Your password : ");
        String password = scanner.nextLine();

        System.out.println("Enter Your Phone Number : ");
        String phone = scanner.nextLine();

        CustomerController customerController = new CustomerController();

        if (!customerController.createCustomer(name, email, password, phone, false)){
            System.out.println("Sorry For This Error");
            registrationForCustomer();
        }
    }
}


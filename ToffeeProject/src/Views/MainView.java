package Views;

import java.util.Scanner;
import Repositories.DatabaseInitializer;
public class MainView {

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private static void redirectInput(int response){
        if (response == 5){
            System.exit(0);
        }
        else if(response == 1){
            User.registrationForCustomer();
        }

        else{
            System.out.println("Enter Valid Response");
        }
    }

    public static void runApplication(){
        int response;
        while (true) {
            System.out.println("Hello in Toffee Project");
            System.out.println("1- Registration");
            System.out.println("2- Login");
            System.out.println("3- Display all Products");
            System.out.println("4- Checkout");
            System.out.println("5- Exit");
            System.out.println("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                redirectInput(response);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }
}

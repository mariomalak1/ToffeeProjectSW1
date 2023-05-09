package Views;

import java.util.Scanner;
import Controllers.UserController;

public class UserView {

    // user views
    private static void createUserForRegistration(boolean admin){
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

        UserController customerController = new UserController();

        if (!customerController.createCustomer(name, email, password, phone, admin)){
            System.out.println("Sorry For This Error");
            createUserForRegistration(admin);
        }
    }

    public static void registrationForAdmin(Models.User user){
        createUserForRegistration(user.isAdmin());
    }

    public static void registrationForCustomer(){
        createUserForRegistration(false);
    }

    public static Models.User Login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Login Page");

        System.out.println("Enter Your ID : ");
        String id = scanner.nextLine();
        int int_id = 0;
        try {
            int_id = Integer.parseInt(id);
        }
        catch (NullPointerException ignored){
            System.out.println("Please Enter Valid ID");
            Login();
        }

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        UserController userController = new UserController();
        Models.User user = userController.getCustomerById(int_id);

        if (user != null){
            user.setActive(true);
            if (user.getPassword().equals(password)){
                System.out.println(user.getName() +" You Login Successfully");
                if (user.isAdmin()){
                    ViewAdminPage(user);
                }else{
                    ViewCustomerPage(user);
                }
            }else{
                System.out.println("You Enter Incorrect Password, Try Again");
            }
        }else{
            System.out.println("You Are Not In The System Or You Enter A non Valid ID");
        }
        return null;
    }

    public static void Logout(Models.User user){
        user.setActive(false);
        System.out.println("You Logout");
        MainView.runApplication();
    }

    // Customer Page
    public static void ViewCustomerPage(Models.User user){
        int response;
        while (true) {
            System.out.println("1- All Candies");
            System.out.println("2- Check Out");
            System.out.println("3- Logout");
            System.out.println("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                CustomerRedirectInput(response, user);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private static void CustomerRedirectInput(int response, Models.User user){
        if (response == 3){
            Logout(user);
        }
        else if (response == 1){
            CandyView.listAllCandies(user);
        }
        else if (response == 2){
            CartView.checkOut(user);
        }
        else{
            System.out.println("Enter Valid Response");
        }
    }

    // Admin Page
    public static void ViewAdminPage(Models.User user){
        int response;
        System.out.println("Welcome Admin Page");
        while (true) {
            System.out.println("1- Registration For New Admin");
            System.out.println("2- All Candies");
            System.out.println("3- All Orders");
            System.out.println("4- Logout");
            System.out.println("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                AdminRedirectInput(response, user);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private static void AdminRedirectInput(int response, Models.User user){
        if (response == 4){
            Logout(user);
        }
        else if(response == 1){
            UserView.registrationForAdmin(user);
        }
        else if (response == 2){
            CandyView.listAllCandies(user);
        }
        else if (response == 3){
            CartView.allCarts(user);
        }
        else{
            System.out.println("Enter Valid Response");
        }
    }
}


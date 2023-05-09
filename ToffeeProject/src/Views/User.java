package Views;

import java.util.Scanner;
import Controllers.UserController;


public class User {

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

    public static void registrationForAdmin(){
        createUserForRegistration(true);
    }

    public static void registrationForCustomer(){
        createUserForRegistration(false);
    }

    public static void Login(){
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
            if (user.getPassword().equals(password)){
                System.out.println(user.getName() +" You Login Successfully");
                if (user.isAdmin()){
                    ViewAdminPage();
                }else{
                    ViewCustomerPage();
                }
            }else{
                System.out.println("You Enter Incorrect Password, Try Again");
            }
        }else{
            System.out.println("You Are Not In The System Or You Enter A non Valid ID");
        }
    }

    public static void Logout(){
        System.out.println("You Logout");
        MainView.runApplication();
    }


    // Customer Page
    public static void ViewCustomerPage(){
        int response;
        while (true) {
            System.out.println("1- Display All Product To Pay");
            System.out.println("2- Check Out");
            System.out.println("3- Logout");
            System.out.println("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                CustomerRedirectInput(response);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private static void CustomerRedirectInput(int response){
        if (response == 3){
            Logout();
        }
        else{
            System.out.println("Enter Valid Response");
        }
    }

    // Admin Page
    public static void ViewAdminPage(){
        int response;
        System.out.println("Welcome Admin Page");
        while (true) {
            System.out.println("1- Registration For New Admin");
            System.out.println("2- Display all Products");
            System.out.println("3- Create New Product");
            System.out.println("4- Logout");
            System.out.println("What's Your Response : ");
            Scanner sc = new Scanner(System.in);
            String stringResponse = sc.nextLine();
            if (MainView.isNumeric(stringResponse)) {
                response = Integer.parseInt(stringResponse);
                AdminRedirectInput(response);
            } else {
                System.out.println("Please enter valid Response");
            }
        }
    }

    private static void AdminRedirectInput(int response){
        if (response == 4){
            Logout();
        }
        else if(response == 1){
            User.registrationForAdmin();
        }
        else{
            System.out.println("Enter Valid Response");
        }
    }
}


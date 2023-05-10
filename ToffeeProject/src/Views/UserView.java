package Views;

import java.util.Scanner;

import Controllers.CustomerController;
import Controllers.UserController;
import Models.User;

public class UserView {

    // user views
    private static User createUserForRegistration(boolean admin){
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

        User user = customerController.createUser(name, email, password, phone, admin);

        if (user == null){
            System.out.println("Sorry For This Error");
            createUserForRegistration(admin);
            return null;
        }else {
            System.out.println("Registration Done Successfully, You Can Login Now");
            System.out.println("You ID : " + user.getID() + " Remember It As You Will Login With It");
            return user;
        }
    }

    public static void registrationForAdmin(Models.User user){
        createUserForRegistration(user.isAdmin());
    }

    public static void registrationForCustomer(){
        User user = createUserForRegistration(false);
        if (user == null){
            System.out.println("Error Happen, Try To Register Agian");
        }else {
            CustomerController customerController = new CustomerController();
            customerController.createCustomer(user, 0);
        }
    }

    public static Models.User Login(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Login Page");

        System.out.println("Enter Your ID : ");
        String id = scanner.nextLine();
        int int_id;
        try {
            int_id = Integer.parseInt(id);
        }
        catch (NumberFormatException e){
            System.out.println("Please Enter Valid ID");
            return null;
        }

        System.out.println("Enter Your Password : ");
        String password = scanner.nextLine();

        UserController userController = new UserController();
        Models.User user = userController.getUserById(int_id);

        if (user != null){
            if (user.getPassword().equals(password)){
                user.setActive(true);
                System.out.println(user.getName() +" You Login Successfully");
                user = new UserController().updateUser(user.getID(), user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.isAdmin(), user.isActive());
                if (user.isAdmin()){
                    ViewAdminPage(user);
                }else{
                    ViewCustomerPage(user);
                }
                return user;
            }else{
                System.out.println("You Enter Incorrect Password, Try Again");
                return null;
            }
        }else{
            System.out.println("You Are Not In The System Or You Enter A non Valid ID");
        }
        return null;
    }

    public static void Logout(Models.User user){
        user.setActive(false);
        new UserController().updateUser(user.getID(), user.getName(), user.getEmail(), user.getPassword(), user.getPhoneNumber(), user.isAdmin(), user.isActive());
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
            CartView.allCartsNotFinished(user);
        }
        else{
            System.out.println("Enter Valid Response");
        }
    }
}


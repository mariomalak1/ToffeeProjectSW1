package Views;
import Controllers.CandyController;
import Controllers.UnitController;
import Controllers.UserController;
import Models.Candy;
import Models.Unit;

import java.util.Scanner;

public class CandyView {
    // to admin and customer

    private static void redirectInputForListCandies(int response, boolean adminUser){
        if (response == 3){
            if (adminUser){
                UserView.ViewAdminPage();
            }else{
                System.out.println("Enter Valid Response");
            }
        }

        else if(response == 1){
            if (adminUser){
                createNewCandy();
            }else {
                OrderView.createOrder();
            }
        }

        else if (response == 2){
            if (adminUser){
                deleteCandy();
            }else{
                UserView.ViewCustomerPage();
            }
        }

        else{
            System.out.println("Enter Valid Response");
        }
    }

    public static void listAllCandies(boolean adminUser){
        Scanner scanner = new Scanner(System.in);

        if (adminUser){
            System.out.println("1- Add New Candy");
            System.out.println("2- Delete Specific Candy With ID");
            System.out.println("3- Main Page");
        }else{
            System.out.println("1- Create an Order");
            System.out.println("2- Main Page");
        }


        CandyController candyController = new CandyController();
        candyController.getAllCandies();

        String stringResponse = scanner.nextLine();
        try{
            int response = Integer.parseInt(stringResponse);
            redirectInputForListCandies(response, adminUser);
        }
        catch (NullPointerException ignore){
            System.out.println("Please Enter Valid Response");
        }
    }

    // Admin Views
    public static void createNewCandy(){
        CandyController candyController = new CandyController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Create New Candy page");

        System.out.println("Enter Candy Name : ");
        String candyName = scanner.nextLine();

        System.out.println("Enter Candy Price : ");
        String candyPriceString = scanner.nextLine();

        double candyPrice = 0;
        if (MainView.isNumeric(candyPriceString)){
            candyPrice = Double.parseDouble(candyPriceString);
        }

        if (!candyController.createCandy(candyName, candyPrice, "Piece", 1, 0, 1, "", "")) {
            System.out.println("Sorry For This Error, Try Again");
        }
    }

    public static void deleteCandy(){
        CandyController candyController = new CandyController();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome in Create New Candy page");

        System.out.println("Enter Candy ID : ");
        String candyIDString = scanner.nextLine();


        int candyID = 0;
        if (MainView.isNumeric(candyIDString)){
            candyID = Integer.parseInt(candyIDString);
        }
        Candy candy = candyController.getCandyDetails(candyID);

        if (candy != null){
            candyController.deleteCandy(candyID);
        }
    }
}

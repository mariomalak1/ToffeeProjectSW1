package Controllers;
import java.util.List;

import Models.Candy;
import Models.Unit;
import Repositories.CandyRepository;

public class CandyController {
    private final CandyRepository candyRepository;

    public CandyController() {
        candyRepository = new CandyRepository();
    }

    public boolean createCandy(String name, double price, String unitName, int categoryID, int loyaltyPoints, int adminIDCreatedBy, String description, String imagePath) {
        try {
            UnitController unitController = new UnitController();
            Unit unit_ = unitController.createUnit(unitName);

            Candy candy = new Candy(name, price, unit_, categoryID, loyaltyPoints, adminIDCreatedBy, description, imagePath);
            candyRepository.addCandy(candy);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to create candy: " + e.getMessage());
        }
        return false;
    }

    public void updateCandy(int candyID, String name, double price, String unitName, int categoryID, int loyaltyPoints, int adminIDCreatedBy, String description, String imagePath) {
        try {
            Candy candy = candyRepository.getCandyById(candyID);
            if (candy != null) {
                UnitController unitController = new UnitController();
                Unit unit_ = unitController.createUnit(unitName);

                candy.setName(name);
                candy.setPrice(price);
                candy.setUnit(unit_);
                candy.setCategoryID(categoryID);
                candy.setLoyaltyPoints(loyaltyPoints);
                candy.setAdminIDCreatedBY(adminIDCreatedBy);
                candy.setDescription(description);
                candy.setImagePath(imagePath);
                System.out.println("Candy updated successfully.");
            } else {
                System.out.println("Candy not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update candy: " + e.getMessage());
        }
    }

    public void deleteCandy(int candyID) {
        try {
            Candy candy = candyRepository.getCandyById(candyID);
            if (candy != null) {
                candyRepository.deleteCandy(candy.getID());
                System.out.println("Candy deleted successfully.");
            } else {
                System.out.println("Candy not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete candy: " + e.getMessage());
        }
    }

    public Models.Candy getCandyDetails(int candyID) {
        try {
            Candy candy = candyRepository.getCandyById(candyID);
            if (candy != null) {
                return candy;
            }
        } catch (Exception e) {
            System.out.println("Failed to get candy details: " + e.getMessage());
        }
        return null;
    }

    public void getAllCandies() {
        try {
            List<Candy> candies = candyRepository.getAllCandies();
            if (!candies.isEmpty()) {
                System.out.println("All Candies:");
                for (Candy candy : candies) {
                    System.out.println("ID: " + candy.getID());
                    System.out.println("Name: " + candy.getName());
                    System.out.println("Price: " + candy.getPrice());
                    System.out.println("Unit: " + candy.getUnit().getUnitName());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No candies found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to get candies: " + e.getMessage());
        }
    }
}
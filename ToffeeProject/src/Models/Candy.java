package Models;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Candy{
    private int ID;
    private String Name;
    private double Price;
    private Unit Unit;
    private int CategoryID;
    private int LoyaltyPoints;
    private int AdminIDCreatedBY;
    private DateTimeException TimeCreatedAt;
    private String Description;
    private String ImagePath;
    private static int LastID = 0;

    public Candy(int id, String name, double price, Models.Unit unit, int categoryId, int loyaltyPoints, int adminIdCreatedBY, LocalDateTime timeCreatedAt, String description, String imagePath) {
        ID = LastID;
        this.Name = name;
        this.Price = price;
        this.CategoryID = categoryId;
        this.LoyaltyPoints = loyaltyPoints;
        this.AdminIDCreatedBY = adminIdCreatedBY;
        this.Description = description;
        this.ImagePath = imagePath;
    }

    public void setCategoryID(int categoryID) {
        this.CategoryID = categoryID;
    }

    public int getCategoryID() {
        return this.CategoryID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getLoyaltyPoints() {
        return LoyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        LoyaltyPoints = loyaltyPoints;
    }

    public int getAdminIDCreatedBY() {
        return AdminIDCreatedBY;
    }

    public void setAdminIDCreatedBY(int adminIDCreatedBY) {
        AdminIDCreatedBY = adminIDCreatedBY;
    }

    public String getTimeCreatedAt() {
        return TimeCreatedAt.toString();
    }

    public void setTimeCreatedAt(DateTimeException timeCreatedAt) {
        TimeCreatedAt = timeCreatedAt;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public Unit getUnit() {
        return Unit;
    }

    public void setUnit(Unit unit) {
        this.Unit = unit;
    }
}

package Models;
import java.time.DateTimeException;

class Unit{
    private int ID;
    public String unitName;
    private static int LastID = 0;
    Unit(String name){
        LastID++;
        ID = LastID;
        unitName = name;
    }
}

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

    Candy(String name, float price, String unitName, int Category, int loyaltyPoints, int IDCreatedBy, String description, String image){
        LastID++;
        ID = LastID;
        this.Name = name;
        this.Price = price;
        this.CategoryID = Category;
        this.LoyaltyPoints = loyaltyPoints;
        this.AdminIDCreatedBY = IDCreatedBy;
        this.Description = description;
        this.ImagePath = image;
        Models.Unit unit = new Unit(unitName);
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

    public DateTimeException getTimeCreatedAt() {
        return TimeCreatedAt;
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

    public String getUnit() {
        return Unit.unitName;
    }

    public void setUnit(String unitName) {
        Models.Unit unit = new Unit(unitName);
    }
}

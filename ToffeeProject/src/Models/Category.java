package Models;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private int ID;
    private String Name;
    private List<Candy> Candies;
    private static int LastID = 0;

    public Category(String name){
        LastID++;
        this.ID = LastID;
        this.Name = name;
        Candies = new ArrayList<Candy>();
    }

    public String getName(){
        return Name;
    }

    public int getId(){
        return ID;
    }

    public void setName(String name){
        this.Name = name;
    }

    public List<Candy> candiesCategory() {
        return Candies;
    }

    public void addCandy(Candy candy){
        Candies.add(candy);
    }

    public void removeCandy(Candy candy){
        Candies.remove(candy);
    }

    public Candy getCandy(int id){
        for (Candy candy: this.Candies) {
            if (candy.getID() == id){
                return candy;
            }
        }
        return null;
    }
}

package Models;
public class category {
    private int id;
     String name ;
     Models.Candy[] candyCategory;
    category(int id, String name){
        this.id = id;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
}

package Models;

public class Unit {
    private int ID;
    public String unitName;
    private static int LastID = 0;

    public Unit(String name) {
        LastID++;
        ID = LastID;
        unitName = name;
    }

    public int getID() {
        return ID;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setID(int id){
        this.ID = id;
    }
}

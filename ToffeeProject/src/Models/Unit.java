package Models;

public class Unit {
    private final int ID;
    public String unitName;
    private static int LastID = 0;

    public Unit(int id, String name) {
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
}

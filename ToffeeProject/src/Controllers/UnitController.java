package Controllers;

import java.sql.Connection;
import java.util.List;
import Repositories.UnitRepository;
import Models.Unit;

public class UnitController {
    private final UnitRepository unitRepository;

    public UnitController() {
        this.unitRepository = new UnitRepository();
    }

    public Unit createUnit(String unitName) {
        try {
            for (Unit unit : unitRepository.getAllUnits()) {
                if (unit.getUnitName().equals(unitName)){
                    return null;
                }
            }
            Unit unit = new Unit(unitName);
            unitRepository.addUnit(unit);
            System.out.println("Unit created successfully. ID: " + unit.getID());
            return unit;
        } catch (Exception e) {
            System.out.println("Failed to create unit: " + e.getMessage());
        }
        return null;
    }

    public void getUnitByID(int unitID) {
        try {
            Unit unit = unitRepository.getUnitById(unitID);
            if (unit != null) {
                System.out.println("Unit ID: " + unit.getID());
                System.out.println("Unit Name: " + unit.getUnitName());
            } else {
                System.out.println("Unit not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to retrieve unit: " + e.getMessage());
        }
    }

    public void updateUnitName(int unitID, String newUnitName) {
        try {
            Unit unit = unitRepository.getUnitById(unitID);
            if (unit != null) {
                unit.setUnitName(newUnitName);
                unitRepository.updateUnit(unit);
                System.out.println("Unit updated successfully.");
            } else {
                System.out.println("Unit not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to update unit: " + e.getMessage());
        }
    }

    public void deleteUnit(int unitID) {
        try {
            Unit unit = unitRepository.getUnitById(unitID);
            if (unit != null) {
                unitRepository.deleteUnit(unit);
                System.out.println("Unit deleted successfully.");
            } else {
                System.out.println("Unit not found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to delete unit: " + e.getMessage());
        }
    }

    public void getAllUnits() {
        try {
            List<Unit> units = unitRepository.getAllUnits();
            if (!units.isEmpty()) {
                System.out.println("All Units:");
                for (Unit unit : units) {
                    System.out.println("ID: " + unit.getID());
                    System.out.println("Unit Name: " + unit.getUnitName());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("No units found.");
            }
        } catch (Exception e) {
            System.out.println("Failed to get units: " + e.getMessage());
        }
    }
}

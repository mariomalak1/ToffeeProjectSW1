package Repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.Unit;

public class UnitRepository {
    private final Connection connection;

    public UnitRepository() {
        this.connection = DatabaseInitializer.getConnection();
    }

    public void addUnit(Unit unit) throws SQLException {
        String query = "INSERT INTO Unit (unitName) VALUES (?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, unit.getUnitName());
            statement.executeUpdate();
        }
    }

    public Unit getUnitById(int id) throws SQLException {
        String query = "SELECT * FROM Unit WHERE ID = ?";
        Unit unit = null;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                unit = mapUnit(resultSet);
            }
        }

        return unit;
    }

    public List<Unit> getAllUnits() throws SQLException {
        String query = "SELECT * FROM Unit";
        List<Unit> units = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Unit unit = mapUnit(resultSet);
                units.add(unit);
            }
        }

        return units;
    }

    public void updateUnit(Unit unit) throws SQLException {
        String query = "UPDATE Unit SET unitName = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, unit.getUnitName());
            statement.setInt(2, unit.getID());
            statement.executeUpdate();
        }
    }

    public void deleteUnit(Unit unit) throws SQLException {
        String query = "DELETE FROM Unit WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, unit.getID());
            statement.executeUpdate();
        }
    }

    private Unit mapUnit(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String unitName = resultSet.getString("unitName");
        Unit unit = new Unit(unitName);
        unit.setID(id);
        return unit;
    }
}

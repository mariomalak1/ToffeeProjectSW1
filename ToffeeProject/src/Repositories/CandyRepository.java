package Repositories;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import Models.Candy;
import Models.Unit;

public class CandyRepository {
    private final Connection connection;

    public CandyRepository(Connection connection) {
        this.connection = connection;
    }

    public void addCandy(Candy candy) throws SQLException {
        String sql = "INSERT INTO Candy (Name, Price, UnitID, CategoryID, LoyaltyPoints, AdminIDCreatedBY, TimeCreatedAt, Description, ImagePath) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candy.getName());
            statement.setDouble(2, candy.getPrice());
            statement.setInt(3, candy.getUnit().getID());
            statement.setInt(4, candy.getCategoryID());
            statement.setInt(5, candy.getLoyaltyPoints());
            statement.setInt(6, candy.getAdminIDCreatedBY());
            statement.setTimestamp(7, Timestamp.valueOf(candy.getTimeCreatedAt()));
            statement.setString(8, candy.getDescription());
            statement.setString(9, candy.getImagePath());

            statement.executeUpdate();
        }
    }

    public Candy getCandyById(int id) throws SQLException {
        String sql = "SELECT * FROM Candy WHERE ID = ?";
        Candy candy = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    candy = mapCandy(resultSet);
                }
            }
        }

        return candy;
    }

    public List<Candy> getAllCandies() throws SQLException {
        String sql = "SELECT * FROM Candy";
        List<Candy> candies = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Candy candy = mapCandy(resultSet);
                candies.add(candy);
            }
        }

        return candies;
    }

    public void updateCandy(Candy candy) throws SQLException {
        String sql = "UPDATE Candy SET Name = ?, Price = ?, UnitID = ?, CategoryID = ?, LoyaltyPoints = ?, " +
                "AdminIDCreatedBY = ?, TimeCreatedAt = ?, Description = ?, ImagePath = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, candy.getName());
            statement.setDouble(2, candy.getPrice());
            statement.setInt(3, candy.getUnit().getID());
            statement.setInt(4, candy.getCategoryID());
            statement.setInt(5, candy.getLoyaltyPoints());
            statement.setInt(6, candy.getAdminIDCreatedBY());
            statement.setTimestamp(7, Timestamp.valueOf(candy.getTimeCreatedAt()));
            statement.setString(8, candy.getDescription());
            statement.setString(9, candy.getImagePath());
            statement.setInt(10, candy.getID());

            statement.executeUpdate();
        }
    }

    public void deleteCandy(int id) throws SQLException {
        String sql = "DELETE FROM Candy WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Candy mapCandy(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("ID");
        String name = resultSet.getString("Name");
        double price = resultSet.getDouble("Price");
        int unitId = resultSet.getInt("UnitID");
        int categoryId = resultSet.getInt("CategoryID");
        int loyaltyPoints = resultSet.getInt("LoyaltyPoints");
        int adminIdCreatedBY = resultSet.getInt("AdminIDCreatedBY");
        LocalDateTime timeCreatedAt = resultSet.getTimestamp("TimeCreatedAt").toLocalDateTime();
        String description = resultSet.getString("Description");
        String imagePath = resultSet.getString("ImagePath");

        // Assuming you have a UnitRepository to retrieve the Unit object
        UnitRepository unitRepository = new UnitRepository(connection);
        Unit unit = unitRepository.getUnitById(unitId);

        return new Candy(id, name, price, unit, categoryId, loyaltyPoints, adminIdCreatedBY, timeCreatedAt, description, imagePath);
    }
}


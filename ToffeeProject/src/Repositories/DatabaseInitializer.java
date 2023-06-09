package Repositories;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String DB_PATH = "jdbc:sqlite:/D:\\FCAI-CU\\Second Year\\Second Semester\\Software\\Assignments\\Assignment 3\\ToffeeProjectSW1\\ToffeeProject\\ToffeeDataBase.db";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_PATH);
            createTables(conn);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error Happen");
            e.printStackTrace();
        }
    }

    private static void createTables(Connection conn) throws SQLException {
        createUnitTable(conn);
        createCandyTable(conn);
        createCategoryTable(conn);
        createUserTable(conn);
        createCustomerTable(conn);
        createCartTable(conn);
        createOrderTable(conn);
        createVoucherTable(conn);
    }

    private static void createUnitTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Unit (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "unitName TEXT NOT NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createCandyTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Candy (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Price REAL NOT NULL," +
                "LoyaltyPoints INTEGER NOT NULL," +
                "Description TEXT," +
                "ImagePath TEXT," +
                "UnitID INTEGER," +
                "CategoryID INTEGER," +
                "AdminIDCreatedBY INTEGER" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createCartTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Cart (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CustomerID INTEGER NOT NULL," +
                "Finished Bool NOT NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createCategoryTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Category (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createCustomerTable(Connection conn) throws SQLException{
        String sql = "CREATE TABLE IF NOT EXISTS Customer (" +
                "UserID INTEGER NOT NULL," +
                "LoyaltyPoints INTEGER NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createUserTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS User (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT NOT NULL," +
                "Email TEXT NOT NULL," +
                "Password TEXT NOT NULL," +
                "PhoneNumber TEXT NOT NULL," +
                "Active INTEGER NULL," +
                "IsAdmin INTEGER NOT NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createOrderTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Orders (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "CandyID INTEGER NOT NULL," +
                "Quantity INTEGER NOT NULL," +
                "CartID INTEGER NOT NULL," +
                "FOREIGN KEY (CandyID) REFERENCES Candy(ID)," +
                "FOREIGN KEY (CartID) REFERENCES Cart(ID)" +
                ")";
        executeStatement(conn, sql);
    }

    private static void createVoucherTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Voucher (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Discount REAL NOT NULL," +
                "TypeOfDiscount TEXT NOT NULL," +
                "Code TEXT NOT NULL" +
                ")";
        executeStatement(conn, sql);
    }

    private static void executeStatement(Connection conn, String sql) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_PATH);
        } catch (SQLException e) {
            System.out.println("Error Happen");
            e.printStackTrace();
        }
        return null;
    }
}


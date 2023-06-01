import java.sql.*;

public class DatabaseHandler {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/mysql";
    private static final String DATABASE_USER = "oanhn2";
    private static final String DATABASE_PASSWORD = "Nhokginrin1998";

    public void createTables() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();

            String createSellersTable = "CREATE TABLE IF NOT EXISTS Sellers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(191) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(191) UNIQUE NOT NULL," +
                    "location VARCHAR(255) NOT NULL," +
                    "seller_rating FLOAT DEFAULT 0)";

            String createBuyersTable = "CREATE TABLE IF NOT EXISTS Buyers (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(191) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "email VARCHAR(191) UNIQUE NOT NULL," +
                    "location VARCHAR(255) NOT NULL)";

            String createItemsTable = "CREATE TABLE IF NOT EXISTS Items (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "brand VARCHAR(255) NOT NULL," +
                    "item_name VARCHAR(255) NOT NULL," +
                    "size VARCHAR(255) NOT NULL," +
                    "color VARCHAR(255) NOT NULL," +
                    "cond ENUM('NEW', 'USED', 'COLLECTIBLE') NOT NULL," +
                    "price DECIMAL(10, 2) NOT NULL," +
                    "seller_id INT NOT NULL," +
                    "FOREIGN KEY (seller_id) REFERENCES Sellers(id) ON DELETE CASCADE," +
                    "item_image VARCHAR(191)," +
                    "original_price DECIMAL(10, 2) NOT NULL," +
                    "is_sold BOOLEAN DEFAULT 0)";

            String createFavoritesTable = "CREATE TABLE IF NOT EXISTS Favorites (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "buyer_id INT NOT NULL," +
                    "item_id INT NOT NULL," +
                    "FOREIGN KEY (buyer_id) REFERENCES Buyers(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (item_id) REFERENCES Items(id) ON DELETE CASCADE)";

            String createTransactionsTable = "CREATE TABLE IF NOT EXISTS Transactions (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "buyer_id INT NOT NULL," +
                    "item_id INT NOT NULL," +
                    "FOREIGN KEY (buyer_id) REFERENCES Buyers(id) ON DELETE CASCADE," +
                    "FOREIGN KEY (item_id) REFERENCES Items(id) ON DELETE CASCADE," +
                    "transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";

            statement.execute(createSellersTable);
            statement.execute(createBuyersTable);
            statement.execute(createItemsTable);
            statement.execute(createFavoritesTable);
            statement.execute(createTransactionsTable);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSeller(String username, String password, String email, String location, float seller_rating) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Sellers (username, password, email, location, seller_rating) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, location);
            preparedStatement.setFloat(5, seller_rating);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBuyer(String username, String password, String email, String location) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Sellers (username, password, email, location) VALUES (?, ?, ?, ?)")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, location);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addItem(String brand, String item_name, String size, String color, String cond, double price, int seller_id, String item_image, double original_price, boolean is_sold) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Items (brand, item_name, size, color, cond, price, seller_id, item_image, original_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, brand);
            preparedStatement.setString(2, item_name);
            preparedStatement.setString(3, size);
            preparedStatement.setString(4, color);
            preparedStatement.setString(5, cond);
            preparedStatement.setDouble(6, price);
            preparedStatement.setInt(7, seller_id);
            preparedStatement.setString(8, item_image);
            preparedStatement.setDouble(9, original_price);
            preparedStatement.setBoolean(10, is_sold);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFavorite(int buyer_id, int item_id) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Favorites (buyer_id, item_id) VALUES (?, ?)")) {

            preparedStatement.setInt(1, buyer_id);
            preparedStatement.setInt(2, item_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTransaction(int buyer_id, int item_id) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Transactions (buyer_id, item_id) VALUES (?, ?)")) {

            preparedStatement.setInt(1, buyer_id);
            preparedStatement.setInt(2, item_id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    // Change credentials here if you used root, etc.
    private static final String URL  = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "student_app";      // or "root"
    private static final String PASS = "StrongPass123!";   // or your root password

    public static Connection getConnection() throws SQLException {
        // JDBC 4 autoloads the MySQL driver, no Class.forName needed
        return DriverManager.getConnection(URL, USER, PASS);
    }

    /** Create table if missing */
    public static void init() {
        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "email VARCHAR(100) UNIQUE," +
                "course VARCHAR(100)" +
                ")";
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            System.err.println("DB init failed: " + e.getMessage());
        }
    }
}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionTest_q1 {

    // Replace with your Oracle DB details
    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    public static void main(String[] args) {

        // try-with-resources ensures connection closes automatically
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Connection successful");

            // Example operation (optional)
            conn.createStatement().execute("SELECT 1 FROM dual");

        } catch (SQLException e) {

            System.out.println("Exception: SQLException - " + e.getMessage());

        } finally {
            System.out.println("Connection closed");
        }
    }
}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FinallyDBExample_q4{

    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Connection successful");

            // Simulate operation
            conn.createStatement().execute("SELECT 1 FROM dual");

        } catch (SQLException e) {

            System.out.println("Exception: DatabaseConnectionException - Could not connect to the database");
            System.out.println("Log: [ERROR] DatabaseConnectionException occurred.");

        } finally {

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing connection");
            }

            System.out.println("Log: [INFO] Database connection closed successfully.");
        }
    }
}
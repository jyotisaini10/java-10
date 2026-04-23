import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 class DatabaseConnectionException extends Exception {

    public DatabaseConnectionException(String message) {
        super(message);
    }

    public DatabaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class CustomDBConnection_q2 {

    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    public static void main(String[] args) {

        try {
            connectToDatabase();
        } catch (DatabaseConnectionException e) {
            System.out.println("Exception: DatabaseConnectionException - " + e.getMessage());
        } finally {
            System.out.println("Connection closed");
        }
    }

    public static void connectToDatabase() throws DatabaseConnectionException {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Connection successful");

            // Simulate DB operation
            conn.createStatement().execute("SELECT 1 FROM dual");

        } catch (SQLException e) {

            if (e.getMessage().contains("Io exception") || e.getMessage().contains("The Network Adapter")) {
                throw new DatabaseConnectionException("Could not connect to the database", e);
            } else {
                throw new DatabaseConnectionException("An error occurred during the database operation", e);
            }
        }
    }
}
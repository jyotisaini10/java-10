import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingDBConnection_q3 {

    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    private static final Logger logger = Logger.getLogger(LoggingDBConnection.class.getName());

    public static void main(String[] args) {

        setupLogger();

        try {
            connectToDatabase();
        } catch (DatabaseConnectionException e) {
            System.out.println("Exception: DatabaseConnectionException - " + e.getMessage());
        } finally {
            System.out.println("Connection closed");
        }
    }

    public static void setupLogger() {
        try {
            FileHandler fh = new FileHandler("error.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) {
            System.out.println("Logger setup failed");
        }
    }

    public static void connectToDatabase() throws DatabaseConnectionException {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Connection successful");

            // Simulate operation
            conn.createStatement().execute("SELECT 1 FROM dual");

        } catch (SQLException e) {

            logger.severe("ERROR: " + e.getMessage());

            if (e.getMessage().contains("Io exception")) {
                throw new DatabaseConnectionException("Could not connect to the database", e);
            } else {
                throw new DatabaseConnectionException("An error occurred during the database operation", e);
            }
        }
    }
}
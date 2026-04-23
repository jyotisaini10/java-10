import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CustomOperationExceptionExample_q6 {

    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = getConnection();
            executeQuery(conn);

            System.out.println("Operation successful");

        } catch (DatabaseConnectionException e) {
            System.out.println("Exception: DatabaseConnectionException - " + e.getMessage());

        } catch (DatabaseOperationException e) {
            System.out.println("Exception: DatabaseOperationException - " + e.getMessage());

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {}
            System.out.println("Connection closed");
        }
    }

    static Connection getConnection() throws DatabaseConnectionException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Could not connect to the database");
        }
    }

    static void executeQuery(Connection conn) throws DatabaseOperationException {
        try {
            conn.createStatement().execute("SELECT * FROM dual");
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred during the database operation");
        }
    }
}
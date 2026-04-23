import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String msg) {
        super(msg);
    }
}

class DatabaseOperationException extends Exception {
    public DatabaseOperationException(String msg) {
        super(msg);
    }
}


public class CheckedExceptionExample_q5 {

    static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    static final String USER = "system";
    static final String PASSWORD = "oracle";

    public static void main(String[] args) {

        Connection conn = null;

        try {
            conn = connect();
            performOperation(conn);

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

    static Connection connect() throws DatabaseConnectionException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Could not connect to the database");
        }
    }

    static void performOperation(Connection conn) throws DatabaseOperationException {
        try {
            conn.createStatement().execute("SELECT 1 FROM dual");
        } catch (SQLException e) {
            throw new DatabaseOperationException("An error occurred during the database operation");
        }
    }
}
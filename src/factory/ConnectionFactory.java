package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/lp", "root", "a26b25d2301");

        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}

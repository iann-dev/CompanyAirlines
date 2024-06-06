import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost/airlines";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            System.out.println("Conectado ao database.");
        } catch (SQLException e) {
            System.out.println("Falha ao tentar conectar com o database");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Desconectado ao database");
            } catch (SQLException e) {
                System.out.println("Falha ao tentar desconectar com o database");
                e.printStackTrace();
            }
        }
    }
}

package aula01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection getConnection() {
        String banco = System.getenv("tipoBanco");
        try {
            return DriverManager.getConnection(String.format("jdbc:%s://localhost:3306/banco", banco), "usuario", "senha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

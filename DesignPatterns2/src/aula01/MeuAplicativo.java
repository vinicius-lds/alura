package aula01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MeuAplicativo {

    public static void main(String[] args) throws SQLException {
        Connection conexao =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "usuario", "senha");

        PreparedStatement ps = conexao.prepareStatement("select * from ...");
        // codigo continua aqui
    }
}

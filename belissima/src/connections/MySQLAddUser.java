package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLAddUser {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/lojaderoupas";
        String username = "root";
        String password = "root";

        String sql = "INSERT INTO usuarios (id_usuario, nome_usuario, login_usuario, senha_usuario, cpf_usuario, endereco_usuario, numero_usuario, acesso) VALUES (3, 'teste', 'teste', 'teste', 2, 'Palmares-PE', 1, 1)";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            int rowsInserted = statement.executeUpdate(sql);
            System.out.println("Registros inseridos: " + rowsInserted);

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

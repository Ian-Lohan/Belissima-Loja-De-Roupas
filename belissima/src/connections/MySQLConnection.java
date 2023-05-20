package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    public static void main(String[] args) {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/lojaderoupas";
        String username = "root";
        String password = "root";

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Conexão bem-sucedida!");

            // Faça outras operações no banco de dados aqui...

        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar conexão: " + e.getMessage());
                }
            }
        }
    }
}

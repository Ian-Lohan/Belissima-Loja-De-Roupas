package connections;

import java.sql.*;

public class Conexao {
	String driver = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lojaderoupas";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection ConectarBanco() {
        Connection conexao = null;

        try {
            conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexao;
    }
}

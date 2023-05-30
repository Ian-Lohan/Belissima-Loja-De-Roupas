package connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements Conexao{
	
	private final String usuario = "root";
	private final String senha = "root";
	private final String url = "jdbc:mysql://localhost:3306/lojaderoupas";
	private Connection conectar;

	public Connection conectarBanco() throws SQLException {
		if (conectar == null) {
			conectar = DriverManager.getConnection(url, usuario, senha);
		}
		return conectar;
	}
}

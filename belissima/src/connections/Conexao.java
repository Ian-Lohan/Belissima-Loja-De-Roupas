package connections;

import java.sql.Connection;
import java.sql.SQLException;

public interface Conexao {

	public Connection conectarBanco() throws SQLException;
}

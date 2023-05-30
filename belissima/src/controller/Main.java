package controller;

import connections.Conexao;
import connections.ConexaoMySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
	    Conexao conexao = new ConexaoMySQL();
	    String sql = "SELECT * FROM usuarios";
	    try {
	        Connection connection = conexao.conectarBanco();
	        System.out.println(connection);

	        PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            System.out.println(result.getString("nome_usuario"));
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
	    }
	}

}

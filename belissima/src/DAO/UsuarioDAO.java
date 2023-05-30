package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome, login, senha, cpf, endereco, numero, acesso) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getLogin());
        statement.setString(3, usuario.getSenha());
        statement.setInt(4, usuario.getCpf());
        statement.setString(5, usuario.getEndereco());
        statement.setInt(6, usuario.getNumero());
        statement.setInt(7, usuario.getAcesso());
        statement.executeUpdate();
        statement.close();
    }

    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome = ?, login = ?, senha = ?, cpf = ?, endereco = ?, numero = ?, acesso = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, usuario.getNome());
        statement.setString(2, usuario.getLogin());
        statement.setString(3, usuario.getSenha());
        statement.setInt(4, usuario.getCpf());
        statement.setString(5, usuario.getEndereco());
        statement.setInt(6, usuario.getNumero());
        statement.setInt(7, usuario.getAcesso());
        statement.setInt(8, usuario.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void excluirUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public Usuario obterUsuarioPorId(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setCpf(resultSet.getInt("cpf"));
            usuario.setEndereco(resultSet.getString("endereco"));
            usuario.setNumero(resultSet.getInt("numero"));
            usuario.setAcesso(resultSet.getInt("acesso"));
            return usuario;
        }

        return null;
    }

    public List<Usuario> obterUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setCpf(resultSet.getInt("cpf"));
            usuario.setEndereco(resultSet.getString("endereco"));
            usuario.setNumero(resultSet.getInt("numero"));
            usuario.setAcesso(resultSet.getInt("acesso"));
            usuarios.add(usuario);
        }

        return usuarios;
    }
}

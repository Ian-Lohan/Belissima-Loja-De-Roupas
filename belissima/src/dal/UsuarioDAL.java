package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Usuario;

public class UsuarioDAL {
    private Connection connection;

    public UsuarioDAL(Connection connection) {
        this.connection = connection;
    }

    public void adicionarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuarios (nome_usuario, login_usuario, senha_usuario, cpf_usuario, endereco_usuario, numero_usuario, acesso) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public void editarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE usuarios SET nome_usuario = ?, login_usuario = ?, senha_usuario = ?, cpf_usuario = ?, endereco_usuario = ?, numero_usuario = ?, acesso = ? WHERE id_usuario = ?";
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

    public void deletarUsuario(int id) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public List<Usuario> obterUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id_usuario"));
            usuario.setNome(resultSet.getString("nome_usuario"));
            usuario.setLogin(resultSet.getString("login_usuario"));
            usuario.setSenha(resultSet.getString("senha_usuario"));
            usuario.setCpf(resultSet.getInt("cpf_usuario"));
            usuario.setEndereco(resultSet.getString("endereco_usuario"));
            usuario.setNumero(resultSet.getInt("numero_usuario"));
            usuario.setAcesso(resultSet.getInt("acesso"));
            usuarios.add(usuario);
        }

        return usuarios;
    }
    
    public Usuario obterUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Usuario usuario = null;
        if (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id_usuario"));
            usuario.setNome(resultSet.getString("nome_usuario"));
            usuario.setLogin(resultSet.getString("login_usuario"));
            usuario.setSenha(resultSet.getString("senha_usuario"));
            usuario.setCpf(resultSet.getInt("cpf_usuario"));
            usuario.setEndereco(resultSet.getString("endereco_usuario"));
            usuario.setNumero(resultSet.getInt("numero_usuario"));
            usuario.setAcesso(resultSet.getInt("acesso"));
        }

        return usuario;
    }

}

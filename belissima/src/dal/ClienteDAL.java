package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.Cliente;

public class ClienteDAL {
    private Connection connection;

    public ClienteDAL(Connection connection) {
        this.connection = connection;
    }

    public void adicionarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome_cliente, cpf_cliente, numero_cliente, endereco_cliente) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setInt(2, cliente.getCpf());
        statement.setInt(3, cliente.getNumero());
        statement.setString(4, cliente.getEndereco());
        statement.executeUpdate();
        statement.close();
    }

    public void editarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome_cliente = ?, cpf_cliente = ?, numero_cliente = ?, endereco_cliente = ? WHERE id_cliente = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setInt(2, cliente.getCpf());
        statement.setInt(3, cliente.getNumero());
        statement.setString(4, cliente.getEndereco());
        statement.setInt(5, cliente.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void deletarCliente(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id_cliente = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public List<Cliente> obterClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(resultSet.getInt("id_cliente"));
            cliente.setNome(resultSet.getString("nome_cliente"));
            cliente.setCpf(resultSet.getInt("cpf_cliente"));
            cliente.setNumero(resultSet.getInt("numero_cliente"));
            cliente.setEndereco(resultSet.getString("endereco_cliente"));
            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente obterCliente(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        Cliente cliente = null;
        if (resultSet.next()) {
            cliente = new Cliente();
            cliente.setId(resultSet.getInt("id_cliente"));
            cliente.setNome(resultSet.getString("nome_cliente"));
            cliente.setCpf(resultSet.getInt("cpf_cliente"));
            cliente.setNumero(resultSet.getInt("numero_cliente"));
            cliente.setEndereco(resultSet.getString("endereco_cliente"));
        }

        return cliente;
    }
}

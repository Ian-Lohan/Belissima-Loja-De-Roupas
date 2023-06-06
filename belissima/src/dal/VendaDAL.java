package dal;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dao.Cliente;
import dao.Usuario;
import dao.Venda;

public class VendaDAL {
    private Connection connection;

    public VendaDAL(Connection connection) {
        this.connection = connection;
    }

    public List<Venda> obterVendas() {
        List<Venda> vendas = new ArrayList<>();
        String query = "SELECT id_venda, data_venda, pagamento_venda, total_venda, id_cliente, id_usuario FROM vendas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id_venda");
                LocalDateTime data = rs.getTimestamp("data_venda").toLocalDateTime();
                String pagamento = rs.getString("pagamento_venda");
                int total = rs.getInt("total_venda");
                int idCliente = rs.getInt("id_cliente");
                int idUsuario = rs.getInt("id_usuario");
                Venda venda = new Venda(id, data, pagamento, total, idCliente, idUsuario);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    public Venda obterVendaPorId(int vendaId) {
        Venda venda = null;
        String query = "SELECT id_venda, data_venda, pagamento_venda, total_venda, id_cliente, id_usuario FROM vendas WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, vendaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id_venda");
                    LocalDateTime data = rs.getTimestamp("data_venda").toLocalDateTime();
                    String pagamento = rs.getString("pagamento_venda");
                    int total = rs.getInt("total_venda");
                    int idCliente = rs.getInt("id_cliente");
                    int idUsuario = rs.getInt("id_usuario");
                    venda = new Venda(id, data, pagamento, total, idCliente, idUsuario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venda;
    }
    
    public void adicionarVenda(Venda venda) {
        String query = "INSERT INTO vendas (data_venda, pagamento_venda, total_venda, id_cliente, id_usuario) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(venda.getData()));
            stmt.setString(2, venda.getPagamento());
            stmt.setInt(3, venda.getTotal());
            stmt.setInt(4, venda.getIdCliente());
            stmt.setInt(5, venda.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarVenda(Venda venda) {
        String query = "UPDATE vendas SET data_venda = ?, pagamento_venda = ?, total_venda = ?, id_cliente = ?, id_usuario = ? WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setTimestamp(1, java.sql.Timestamp.valueOf(venda.getData()));
            stmt.setString(2, venda.getPagamento());
            stmt.setInt(3, venda.getTotal());
            stmt.setInt(4, venda.getIdCliente());
            stmt.setInt(5, venda.getIdUsuario());
            stmt.setInt(6, venda.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletarVenda(int vendaId) {
        String query = "DELETE FROM vendas WHERE id_venda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, vendaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Venda> obterVendasPorPeriodo(LocalDateTime primeiroDiaMes, LocalDateTime ultimoDiaMes) {
        List<Venda> vendas = new ArrayList<>();

        try {
            String query = "SELECT id, data_hora, id_cliente, id_usuario FROM vendas WHERE data_hora BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, primeiroDiaMes);
            preparedStatement.setObject(2, ultimoDiaMes);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                LocalDateTime dataHora = resultSet.getObject("data_hora", LocalDateTime.class);
                int idCliente = resultSet.getInt("id_cliente");
                int idUsuario = resultSet.getInt("id_usuario");

                ClienteDAL clienteDAL = new ClienteDAL(connection);
                Cliente cliente = clienteDAL.obterCliente(idCliente);

                UsuarioDAL usuarioDAL = new UsuarioDAL(connection);
                Usuario usuario = usuarioDAL.obterUsuario(idUsuario);

                Venda venda = new Venda();
                vendas.add(venda);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vendas;
    }
    
}

package dal;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.Conexao;
import dao.DetalhesVenda;
import dao.Produto;
import dao.Venda;

public class DetalhesVendaDAL {
    private Connection connection;

    public DetalhesVendaDAL() {
        connection = Conexao.ConectarBanco();
    }

    public void inserirDetalhesVenda(DetalhesVenda detalhesVenda) {
        try {
            String query = "INSERT INTO detalhesvendas (id_venda, id_produto, quantidade, precounitario, subtotal) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, detalhesVenda.getVenda().getId());
            preparedStatement.setInt(2, detalhesVenda.getProduto().getId());
            preparedStatement.setInt(3, detalhesVenda.getQuantidade());
            preparedStatement.setBigDecimal(4, detalhesVenda.getPrecounitario());
            preparedStatement.setBigDecimal(5, detalhesVenda.getSubtotal());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DetalhesVenda> obterDetalhesVenda() {
        List<DetalhesVenda> detalhesVendas = new ArrayList<>();

        try {
            String query = "SELECT id_venda, id_produto, quantidade, precounitario, subtotal FROM detalhesvendas";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idVenda = resultSet.getInt("id_venda");
                int idProduto = resultSet.getInt("id_produto");
                int quantidade = resultSet.getInt("quantidade");
                BigDecimal precoUnitario = resultSet.getBigDecimal("precounitario");
                BigDecimal subtotal = resultSet.getBigDecimal("subtotal");

                // Obter a venda e o produto associados pelos IDs
                VendaDAL vendaDAL = new VendaDAL(connection);
                Venda venda = vendaDAL.obterVendaPorId(idVenda);

                ProdutoDAL produtoDAL = new ProdutoDAL(connection);
                Produto produto = produtoDAL.obterProduto(idProduto);

                DetalhesVenda detalhesVenda = new DetalhesVenda(venda, produto, quantidade, precoUnitario, subtotal);
                detalhesVendas.add(detalhesVenda);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalhesVendas;
    }

    public void atualizarDetalhesVenda(DetalhesVenda detalhesVenda) {
        try {
            String query = "UPDATE detalhesvendas SET id_venda = ?, id_produto = ?, quantidade = ?, precounitario = ?, subtotal = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, detalhesVenda.getVenda().getId());
            preparedStatement.setInt(2, detalhesVenda.getProduto().getId());
            preparedStatement.setInt(3, detalhesVenda.getQuantidade());
            preparedStatement.setBigDecimal(4, detalhesVenda.getPrecounitario());
            preparedStatement.setBigDecimal(5, detalhesVenda.getSubtotal());

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirDetalhesVenda(int id) {
        try {
            String query = "DELETE FROM detalhesvendas WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

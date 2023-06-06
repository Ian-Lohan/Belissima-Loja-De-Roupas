package dal;

import dao.Produto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAL {
    private Connection connection;

    public ProdutoDAL(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> obterProdutos() throws SQLException {
        List<Produto> produtos = new ArrayList<>();

        String query = "SELECT * FROM produtos";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id_produto");
            String nome = resultSet.getString("nome_produto");
            String tipo = resultSet.getString("tipo_produto");
            String cor = resultSet.getString("cor_produto");
            BigDecimal preco = resultSet.getBigDecimal("preco_produto");
            int estoque = resultSet.getInt("estoque_produto");
            String detalhe = resultSet.getString("detalhe_produto");

            Produto produto = new Produto(id, nome, tipo, cor, preco, estoque, detalhe);
            produtos.add(produto);
        }

        resultSet.close();
        statement.close();

        return produtos;
    }

    public Produto obterProduto(int id) throws SQLException {
        String query = "SELECT * FROM produtos WHERE id_produto = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Produto produto = null;

        if (resultSet.next()) {
            String nome = resultSet.getString("nome_produto");
            String tipo = resultSet.getString("tipo_produto");
            String cor = resultSet.getString("cor_produto");
            BigDecimal preco = resultSet.getBigDecimal("preco_produto");
            int estoque = resultSet.getInt("estoque_produto");
            String detalhe = resultSet.getString("detalhe_produto");

            produto = new Produto(id, nome, tipo, cor, preco, estoque, detalhe);
        }

        resultSet.close();
        preparedStatement.close();

        return produto;
    }

    public void adicionarProduto(Produto produto) throws SQLException {
        String query = "INSERT INTO produtos (nome_produto, tipo_produto, cor_produto, preco_produto, estoque_produto, detalhe_produto) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setString(2, produto.getTipo());
        preparedStatement.setString(3, produto.getCor());
        preparedStatement.setBigDecimal(4, produto.getPreco());
        preparedStatement.setInt(5, produto.getEstoque());
        preparedStatement.setString(6, produto.getDetalhe());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        String query = "UPDATE produtos SET nome_produto = ?, tipo_produto = ?, cor_produto = ?, preco_produto = ?, estoque_produto = ?, detalhe_produto = ? WHERE id_produto = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setString(2, produto.getTipo());
        preparedStatement.setString(3, produto.getCor());
        preparedStatement.setBigDecimal(4, produto.getPreco());
        preparedStatement.setInt(5, produto.getEstoque());
        preparedStatement.setString(6, produto.getDetalhe());
        preparedStatement.setInt(7, produto.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deletarProduto(int id) throws SQLException {
        String query = "DELETE FROM produtos WHERE id_produto = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}

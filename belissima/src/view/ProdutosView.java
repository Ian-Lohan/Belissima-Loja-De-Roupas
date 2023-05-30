package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProdutosView extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProdutosView frame = new ProdutosView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProdutosView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setTitle("Belissima - Produtos");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Obter os dados da tabela 'produtos' do banco de dados
        String[][] data = getProdutosData();
        String[] columns = {"ID", "Nome", "Tipo", "Cor", "Preço", "Estoque", "Detalhe"};

        // Configurar os dados na tabela
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Método para obter os dados da tabela 'produtos' do banco de dados
    private String[][] getProdutosData() {
        String[][] data = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lojaderoupas", "root", "root");

            // Criar a declaração SQL
            String query = "SELECT id_produto, nome_produto, tipo_produto, cor_produto, preco_produto, estoque_produto, detalhe_produto FROM produtos";

            // Criar o objeto Statement
            statement = connection.createStatement();

            // Executar a consulta SQL
            resultSet = statement.executeQuery(query);

            // Obter o número de linhas no resultSet
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Criar a matriz de dados com o tamanho adequado
            data = new String[rowCount][7];

            // Preencher a matriz de dados com os valores do resultSet
            int index = 0;
            while (resultSet.next()) {
                data[index][0] = String.valueOf(resultSet.getInt("id_produto"));
                data[index][1] = resultSet.getString("nome_produto");
                data[index][2] = resultSet.getString("tipo_produto");
                data[index][3] = resultSet.getString("cor_produto");
                data[index][4] = String.valueOf(resultSet.getDouble("preco_produto"));
                data[index][5] = String.valueOf(resultSet.getInt("estoque_produto"));
                data[index][6] = resultSet.getString("detalhe_produto");
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechar os objetos ResultSet, Statement e Connection
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return data;
    }
}

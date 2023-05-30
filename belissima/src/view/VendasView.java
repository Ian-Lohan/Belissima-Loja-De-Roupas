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

public class VendasView extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VendasView frame = new VendasView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public VendasView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setTitle("Belissima - Vendas");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Obter os dados da tabela 'vendas' do banco de dados
        String[][] data = getVendasData();
        String[] columns = {"ID", "Data", "Pagamento", "Total", "ID Cliente", "ID Funcionário"};

        // Configurar os dados na tabela
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Método para obter os dados da tabela 'vendas' do banco de dados
    private String[][] getVendasData() {
        String[][] data = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lojaderoupas", "root", "root");

            // Criar a declaração SQL
            String query = "SELECT id_venda, data_venda, pagamento_venda, total_venda, id_cliente, id_funcionario FROM vendas";

            // Criar o objeto Statement
            statement = connection.createStatement();

            // Executar a consulta SQL
            resultSet = statement.executeQuery(query);

            // Obter o número de linhas no resultSet
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Criar a matriz de dados com o tamanho adequado
            data = new String[rowCount][6];

            // Preencher a matriz de dados com os valores do resultSet
            int index = 0;
            while (resultSet.next()) {
                data[index][0] = String.valueOf(resultSet.getInt("id_venda"));
                data[index][1] = resultSet.getTimestamp("data_venda").toString();
                data[index][2] = resultSet.getString("pagamento_venda");
                data[index][3] = String.valueOf(resultSet.getInt("total_venda"));
                data[index][4] = String.valueOf(resultSet.getInt("id_cliente"));
                data[index][5] = String.valueOf(resultSet.getInt("id_funcionario"));
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

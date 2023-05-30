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

public class ClientesView extends JFrame {

    private JPanel contentPane;
    private JTable table;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientesView frame = new ClientesView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClientesView() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450);
        setTitle("Belissima - Clientes");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Obter os dados da tabela 'clientes' do banco de dados
        String[][] data = getClientesData();
        String[] columns = {"ID", "Nome", "CPF", "Número", "Endereço"};

        // Configurar os dados na tabela
        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // Método para obter os dados da tabela 'clientes' do banco de dados
    private String[][] getClientesData() {
        String[][] data = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Estabelecer a conexão com o banco de dados
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lojaderoupas", "root", "root");

            // Criar a declaração SQL
            String query = "SELECT id_cliente, nome_cliente, cpf_cliente, numero_cliente, endereco_cliente FROM clientes";

            // Criar o objeto Statement
            statement = connection.createStatement();

            // Executar a consulta SQL
            resultSet = statement.executeQuery(query);

            // Obter o número de linhas no resultSet
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // Criar a matriz de dados com o tamanho adequado
            data = new String[rowCount][5];

            // Preencher a matriz de dados com os valores do resultSet
            int index = 0;
            while (resultSet.next()) {
                data[index][0] = String.valueOf(resultSet.getInt("id_cliente"));
                data[index][1] = resultSet.getString("nome_cliente");
                data[index][2] = String.valueOf(resultSet.getInt("cpf_cliente"));
                data[index][3] = String.valueOf(resultSet.getInt("numero_cliente"));
                data[index][4] = resultSet.getString("endereco_cliente");
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

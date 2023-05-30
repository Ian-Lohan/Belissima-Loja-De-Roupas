package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class UsuariosView extends JFrame {
    private JTable table;

    public UsuariosView() {
        setTitle("Tabela de Usuários");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose(); // Fecha a janela atual
            }
        });
        setSize(800, 400);
        setLocationRelativeTo(null);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            // Conexão com o banco de dados
            String url = "jdbc:mysql://localhost/lojaderoupas";
            String user = "root";
            String password = "root";
            Connection conn = DriverManager.getConnection(url, user, password);

            // Consulta SQL para obter os dados da tabela 'usuarios'
            String query = "SELECT * FROM usuarios";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Criar o modelo de tabela e preencher os dados
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Nome");
            model.addColumn("Login");
            model.addColumn("Senha");
            model.addColumn("CPF");
            model.addColumn("Endereço");
            model.addColumn("Número");
            model.addColumn("Acesso");

            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome_usuario");
                String login = rs.getString("login_usuario");
                String senha = rs.getString("senha_usuario");
                int cpf = rs.getInt("cpf_usuario");
                String endereco = rs.getString("endereco_usuario");
                int numero = rs.getInt("numero_usuario");
                int acesso = rs.getInt("acesso");

                model.addRow(new Object[]{id, nome, login, senha, cpf, endereco, numero, acesso});
            }

            table.setModel(model);

            // Fechar as conexões e liberar os recursos
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuariosView frame = new UsuariosView();
            frame.setVisible(true);
        });
    }
}

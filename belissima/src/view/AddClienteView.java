package view;

import javax.swing.*;
import dal.ClienteDAL;
import dao.Cliente;
import connections.Conexao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddClienteView extends JFrame {
    private JTextField textFieldNome;
    private JTextField textFieldCpf;
    private JTextField textFieldNumero;
    private JTextField textFieldEndereco;
    private ClienteDAL clienteDAL;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                AddClienteView frame = new AddClienteView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public AddClienteView() {
        setTitle("Adicionar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 310, 266);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 20);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblCpf = new JLabel("Cpf:");
        lblCpf.setBounds(30, 70, 80, 20);
        getContentPane().add(lblCpf);

        textFieldCpf = new JTextField();
        textFieldCpf.setBounds(120, 70, 150, 20);
        getContentPane().add(textFieldCpf);
        textFieldCpf.setColumns(10);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(30, 148, 80, 20);
        getContentPane().add(lblEndereco);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setBounds(120, 148, 150, 20);
        getContentPane().add(textFieldEndereco);
        textFieldEndereco.setColumns(10);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(30, 107, 80, 20);
        getContentPane().add(lblNumero);

        textFieldNumero = new JTextField();
        textFieldNumero.setBounds(120, 107, 150, 20);
        getContentPane().add(textFieldNumero);
        textFieldNumero.setColumns(10);

        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(170, 188, 100, 30);
        getContentPane().add(btnAdicionar);

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String cpf = textFieldCpf.getText();
                String numero = textFieldNumero.getText();
                String endereco = textFieldEndereco.getText();

                if (adicionarCliente(nome, cpf, numero, endereco)) {
                    JOptionPane.showMessageDialog(AddClienteView.this, "Cliente adicionado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddClienteView.this, "Erro ao adicionar cliente!");
                }
            }
        });
    }

    private boolean adicionarCliente(String nome, String cpf, String numero, String endereco) {
        try {
            Connection conn = Conexao.ConectarBanco();
            clienteDAL = new ClienteDAL(conn);

            String sql = "INSERT INTO clientes (nome_cliente, cpf_cliente, numero_cliente, endereco_cliente) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, cpf);
            statement.setString(3, numero);
            statement.setString(4, endereco);

            int rowsInserted = statement.executeUpdate();

            statement.close();
            conn.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

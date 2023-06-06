package view;

import dal.ClienteDAL;
import dao.Cliente;
import connections.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EdtClienteView extends JFrame {
    private ClienteDAL clienteDAL;
    private JTextField textFieldNome;
    private JTextField textFieldCPF;
    private JTextField textFieldNumero;
    private JTextField textFieldEndereco;
    private Cliente cliente;

    public EdtClienteView(Cliente cliente) {
        this.cliente = cliente;

        setTitle("Editar Cliente");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 310, 269);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 20);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(30, 70, 80, 20);
        getContentPane().add(lblCPF);

        textFieldCPF = new JTextField();
        textFieldCPF.setBounds(120, 70, 150, 20);
        getContentPane().add(textFieldCPF);
        textFieldCPF.setColumns(10);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(30, 110, 80, 20);
        getContentPane().add(lblNumero);

        textFieldNumero = new JTextField();
        textFieldNumero.setBounds(120, 110, 150, 20);
        getContentPane().add(textFieldNumero);
        textFieldNumero.setColumns(10);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(30, 150, 80, 20);
        getContentPane().add(lblEndereco);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setBounds(120, 150, 150, 20);
        getContentPane().add(textFieldEndereco);
        textFieldEndereco.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(170, 190, 100, 30);
        getContentPane().add(btnSalvar);

        textFieldNome.setText(cliente.getNome());
        textFieldCPF.setText(String.valueOf(cliente.getCpf()));
        textFieldNumero.setText(String.valueOf(cliente.getNumero()));
        textFieldEndereco.setText(cliente.getEndereco());

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                int cpf = Integer.parseInt(textFieldCPF.getText());
                int numero = Integer.parseInt(textFieldNumero.getText());
                String endereco = textFieldEndereco.getText();

                if (editarCliente(nome, cpf, numero, endereco)) {
                    JOptionPane.showMessageDialog(EdtClienteView.this, "Cliente editado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(EdtClienteView.this, "Erro ao editar cliente!");
                }
            }
        });
    }

    private boolean editarCliente(String nome, int cpf, int numero, String endereco) {
        try {
            Connection conn = Conexao.ConectarBanco();
            clienteDAL = new ClienteDAL(conn);

            cliente.setNome(nome);
            cliente.setCpf(cpf);
            cliente.setNumero(numero);
            cliente.setEndereco(endereco);

            clienteDAL.editarCliente(cliente);

            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cliente cliente = new Cliente();
            EdtClienteView frame = new EdtClienteView(cliente);
            frame.setVisible(true);
        });
    }
}

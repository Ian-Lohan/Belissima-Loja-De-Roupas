package view;

import dal.VendaDAL;
import dao.Venda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import connections.Conexao;

public class AddVendaView extends JFrame {
	private VendaDAL vendaDAL;
    private JTextField textFieldData;
    private JComboBox<String> comboBoxPagamento;
    private JTextField textFieldTotal;
    private JTextField textFieldIdCliente;
    private JTextField textFieldIdUsuario;

    public AddVendaView() {
        setTitle("Adicionar Venda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 310, 315);
        getContentPane().setLayout(null);

        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(30, 30, 80, 20);
        getContentPane().add(lblData);
        
        JCheckBox checkBoxDataAtual = new JCheckBox("Data Atual?");
        checkBoxDataAtual.setBounds(120, 54, 150, 20);
        getContentPane().add(checkBoxDataAtual);

        textFieldData = new JTextField();
        textFieldData.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldData);
        textFieldData.setColumns(10);

        JLabel lblPagamento = new JLabel("Pagamento:");
        lblPagamento.setBounds(30, 79, 80, 20);
        getContentPane().add(lblPagamento);

        comboBoxPagamento = new JComboBox<>();
        comboBoxPagamento.setBounds(120, 79, 150, 20);
        comboBoxPagamento.addItem("dinheiro");
        comboBoxPagamento.addItem("crédito");
        comboBoxPagamento.addItem("débito");
        getContentPane().add(comboBoxPagamento);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(30, 116, 80, 20);
        getContentPane().add(lblTotal);

        textFieldTotal = new JTextField();
        textFieldTotal.setBounds(120, 116, 150, 20);
        getContentPane().add(textFieldTotal);
        textFieldTotal.setColumns(10);

        JLabel lblIdCliente = new JLabel("ID Cliente:");
        lblIdCliente.setBounds(30, 157, 80, 20);
        getContentPane().add(lblIdCliente);

        textFieldIdCliente = new JTextField();
        textFieldIdCliente.setBounds(120, 157, 150, 20);
        getContentPane().add(textFieldIdCliente);
        textFieldIdCliente.setColumns(10);

        JLabel lblIdUsuario = new JLabel("ID Usuário:");
        lblIdUsuario.setBounds(30, 197, 80, 20);
        getContentPane().add(lblIdUsuario);

        textFieldIdUsuario = new JTextField();
        textFieldIdUsuario.setBounds(120, 197, 150, 20);
        getContentPane().add(textFieldIdUsuario);
        textFieldIdUsuario.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(170, 237, 100, 30);
        getContentPane().add(btnSalvar);
        
        checkBoxDataAtual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textFieldData.setText("");
                textFieldData.setEnabled(!checkBoxDataAtual.isSelected());
            }
        });

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data_venda;
                if (checkBoxDataAtual.isSelected()) {
                    LocalDateTime dataAtual = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                    data_venda = dataAtual.format(formatter);
                } else {
                    data_venda = textFieldData.getText();
                }

                String pagamento_venda = comboBoxPagamento.getSelectedItem().toString();
                String total_venda = textFieldTotal.getText();
                String id_cliente = textFieldIdCliente.getText();
                String id_usuario = textFieldIdUsuario.getText();

                if (adicionarVenda(data_venda, pagamento_venda, total_venda, id_cliente, id_usuario)) {
                    JOptionPane.showMessageDialog(AddVendaView.this, "Venda adicionada com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddVendaView.this, "Erro ao adicionar venda!");
                }
            }
        });

    }

    private boolean adicionarVenda(String data, String pagamento, String total, String idCliente, String idUsuario) {
        try {
            Connection conn = Conexao.ConectarBanco();
            vendaDAL = new VendaDAL(conn);

            LocalDateTime dataVenda = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            int totalVenda = Integer.parseInt(total);
            int idClienteVenda = Integer.parseInt(idCliente);
            int idUsuarioVenda = Integer.parseInt(idUsuario);

            Venda venda = new Venda(0, dataVenda, pagamento, totalVenda, idClienteVenda, idUsuarioVenda);
            vendaDAL.adicionarVenda(venda);

            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AddVendaView frame = new AddVendaView();
            frame.setVisible(true);
        });
    }
}

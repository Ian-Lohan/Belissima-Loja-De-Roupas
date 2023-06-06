package view;

import dal.ClienteDAL;
import dao.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import connections.Conexao;

public class ClientesView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ClienteDAL clienteDAL;

    public ClientesView() {
        setTitle("Tabela de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("CPF");
        tableModel.addColumn("Número");
        tableModel.addColumn("Endereço");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        JButton addButton = new JButton("Adicionar");
        buttonPanel.add(addButton);

        JButton editButton = new JButton("Editar");
        buttonPanel.add(editButton);

        JButton deleteButton = new JButton("Deletar");
        buttonPanel.add(deleteButton);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddClienteView addClienteView = new AddClienteView();
                addClienteView.setVisible(true);
                addClienteView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        refreshTable();
                    }
                });
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int clienteId = (int) table.getValueAt(selectedRow, 0);

                    try {
                        Connection conn = Conexao.ConectarBanco();
                        clienteDAL = new ClienteDAL(conn);
                        Cliente cliente = clienteDAL.obterCliente(clienteId);
                        conn.close();

                        if (cliente != null) {
                            EdtClienteView edtClienteView = new EdtClienteView(cliente);
                            edtClienteView.setVisible(true);
                            edtClienteView.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    refreshTable();
                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(ClientesView.this, "Cliente não encontrado!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(ClientesView.this, "Selecione um cliente para editar!");
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int clienteId = (int) table.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(ClientesView.this, "Deseja realmente deletar o cliente?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deletarCliente(clienteId);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(ClientesView.this, "Selecione um cliente para deletar!");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        refreshTable();
    }

    private void refreshTable() {
        try {
            Connection conn = Conexao.ConectarBanco();
            clienteDAL = new ClienteDAL(conn);
            List<Cliente> clientes = clienteDAL.obterClientes();

            tableModel.setRowCount(0);

            for (Cliente cliente : clientes) {
                tableModel.addRow(new Object[]{
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getNumero(),
                        cliente.getEndereco()
                });
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletarCliente(int clienteId) {
        try {
            Connection conn = Conexao.ConectarBanco();
            clienteDAL = new ClienteDAL(conn);
            clienteDAL.deletarCliente(clienteId);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientesView frame = new ClientesView();
            frame.setVisible(true);
        });
    }
}

package view;

import dal.VendaDAL;
import dal.DetalhesVendaDAL;
import dao.DetalhesVenda;
import dao.Venda;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import view.DetalhesVendaView;

import connections.Conexao;

public class VendasView extends JFrame {
    private JTable table;
    private TableModel tableModel;
    private VendaDAL vendaDAL;
    private DetalhesVendaDAL detalhesVendaDAL;

    public VendasView() {
        setTitle("Tabela de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = createTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JButton btnDetalhes = new JButton("Detalhes");
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnDeletar = new JButton("Deletar");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnDetalhes);
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnEditar);
        buttonPanel.add(btnDeletar);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        btnDetalhes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarDetalhesVenda();
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddVendaView adicionarView = new AddVendaView();
                adicionarView.setVisible(true);
                adicionarView.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                        try {
                            carregarTabelaVendas();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarVenda();
            }
        });

        btnDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarVenda();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
            }
        });

        initializeDAL();
    }

    private void initializeDAL() {
        try {
            Connection conn = Conexao.ConectarBanco();
            vendaDAL = new VendaDAL(conn);
            detalhesVendaDAL = new DetalhesVendaDAL();
            carregarTabelaVendas();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private TableModel createTableModel() {
        return new VendasTableModel();
    }

    private class VendasTableModel extends AbstractTableModel {
        private final String[] columnNames = {"ID", "Data", "Pagamento", "Total", "Cliente", "Funcionário"};
        private List<Venda> vendas;

        public void setVendas(List<Venda> vendas) {
            this.vendas = vendas;
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            if (vendas != null) {
                return vendas.size();
            }
            return 0;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (vendas != null && rowIndex < vendas.size()) {
                Venda venda = vendas.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return venda.getId();
                    case 1:
                        return venda.getDataFormatada();
                    case 2:
                        return venda.getPagamento();
                    case 3:
                        return venda.getTotal();
                    case 4:
                        return venda.getIdCliente();
                    case 5:
                        return venda.getIdUsuario();
                }
            }
            return null;
        }
    }

    private void mostrarDetalhesVenda() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(VendasView.this, "Selecione uma venda para ver os detalhes!");
            return;
        }
        int vendaId = (int) table.getValueAt(row, 0);
        Venda vendaSelecionada = vendaDAL.obterVendaPorId(vendaId);
        if (vendaSelecionada != null) {
            List<DetalhesVenda> detalhesVendaList = detalhesVendaDAL.obterDetalhesVenda();
            DetalhesVendaView detalhesView = new DetalhesVendaView(vendaSelecionada, detalhesVendaList);
            detalhesView.setVisible(true);
        }
    }



    private void editarVenda() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(VendasView.this, "Selecione uma venda para editar!");
            return;
        }
        int vendaId = (int) table.getValueAt(row, 0);
        Venda vendaSelecionada = vendaDAL.obterVendaPorId(vendaId);
        if (vendaSelecionada != null) {
            EdtVendaView editarView = new EdtVendaView(vendaSelecionada);
            editarView.setVisible(true);
            editarView.addWindowListener(new java.awt.event.WindowAdapter() {
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    try {
                        carregarTabelaVendas();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
    }

    private void deletarVenda() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(VendasView.this, "Selecione uma venda para deletar!");
            return;
        }
        int vendaId = (int) table.getValueAt(row, 0);
        int confirmacao = JOptionPane.showConfirmDialog(VendasView.this, "Tem certeza que deseja deletar esta venda?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            vendaDAL.deletarVenda(vendaId);
            try {
                carregarTabelaVendas();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(VendasView.this, "Venda deletada com sucesso!");
        }
    }

    private void carregarTabelaVendas() throws SQLException {
        List<Venda> vendas = vendaDAL.obterVendas();
        ((VendasTableModel) tableModel).setVendas(vendas);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VendasView frame = new VendasView();
            frame.setVisible(true);
        });
    }
}

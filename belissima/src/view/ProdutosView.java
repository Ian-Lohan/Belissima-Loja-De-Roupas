package view;

import dal.ProdutoDAL;
import dao.Produto;

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

public class ProdutosView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private ProdutoDAL produtoDAL;

    public ProdutosView() {
        setTitle("Tabela de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Tipo");
        tableModel.addColumn("Cor");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Estoque");
        tableModel.addColumn("Detalhe");

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
                AddProdutoView addProdutoView = new AddProdutoView();
                addProdutoView.setVisible(true);
                addProdutoView.addWindowListener(new WindowAdapter() {
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
                    int produtoId = (int) table.getValueAt(selectedRow, 0);

                    try {
                        Connection conn = Conexao.ConectarBanco();
                        produtoDAL = new ProdutoDAL(conn);
                        Produto produto = produtoDAL.obterProduto(produtoId);
                        conn.close();

                        if (produto != null) {
                            EdtProdutoView edtProdutoView = new EdtProdutoView(produto.getId()); // Passando o id do produto
                            edtProdutoView.setVisible(true);
                            edtProdutoView.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    refreshTable();
                                }
                            });
                        } else {
                            JOptionPane.showMessageDialog(ProdutosView.this, "Produto não encontrado!");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(ProdutosView.this, "Selecione um produto para editar!");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int produtoId = (int) table.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(ProdutosView.this, "Deseja realmente deletar o produto?", "Confirmar Deleção", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        deletarProduto(produtoId);
                        refreshTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(ProdutosView.this, "Selecione um produto para deletar!");
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
            produtoDAL = new ProdutoDAL(conn);
            List<Produto> produtos = produtoDAL.obterProdutos();

            tableModel.setRowCount(0);

            for (Produto produto : produtos) {
                tableModel.addRow(new Object[]{
                        produto.getId(),
                        produto.getNome(),
                        produto.getTipo(),
                        produto.getCor(),
                        produto.getPreco(),
                        produto.getEstoque(),
                        produto.getDetalhe()
                });
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletarProduto(int produtoId) {
        try {
            Connection conn = Conexao.ConectarBanco();
            produtoDAL = new ProdutoDAL(conn);
            produtoDAL.deletarProduto(produtoId);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProdutosView frame = new ProdutosView();
            frame.setVisible(true);
        });
    }
}

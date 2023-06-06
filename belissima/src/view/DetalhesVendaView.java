package view;

import dao.DetalhesVenda;
import dao.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DetalhesVendaView extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public DetalhesVendaView(Venda venda, List<DetalhesVenda> detalhesVendaList) {
        setTitle("Detalhes da Venda");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Produto");
        tableModel.addColumn("Quantidade");
        tableModel.addColumn("Preço Unitário");
        tableModel.addColumn("Subtotal");

        for (DetalhesVenda detalhesVenda : detalhesVendaList) {
            tableModel.addRow(new Object[]{
                    detalhesVenda.getProduto().getNome(),
                    detalhesVenda.getQuantidade(),
                    detalhesVenda.getPrecounitario(),
                    detalhesVenda.getSubtotal()
            });
        }

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JLabel lblTotal = new JLabel("Total: " + venda.getTotal());
        getContentPane().add(lblTotal, BorderLayout.SOUTH);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                dispose();
            }
        });

        setVisible(true);
    }
}

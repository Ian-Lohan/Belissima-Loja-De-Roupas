package view;

import dal.ProdutoDAL;
import dao.Produto;
import connections.Conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

public class EdtProdutoView extends JFrame {
    private ProdutoDAL produtoDAL;
    private int id;
    private JTextField textFieldNome;
    private JTextField textFieldTipo;
    private JTextField textFieldCor;
    private JTextField textFieldPreco;
    private JTextField textFieldEstoque;
    private JTextField textFieldDetalhe;

    public EdtProdutoView(int id) {
        this.id = id;

        setTitle("Editar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 310, 343);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 20);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(30, 70, 80, 20);
        getContentPane().add(lblTipo);

        textFieldTipo = new JTextField();
        textFieldTipo.setBounds(120, 70, 150, 20);
        getContentPane().add(textFieldTipo);
        textFieldTipo.setColumns(10);

        JLabel lblCor = new JLabel("Cor:");
        lblCor.setBounds(30, 110, 80, 20);
        getContentPane().add(lblCor);

        textFieldCor = new JTextField();
        textFieldCor.setBounds(120, 110, 150, 20);
        getContentPane().add(textFieldCor);
        textFieldCor.setColumns(10);

        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(30, 150, 80, 20);
        getContentPane().add(lblPreco);

        textFieldPreco = new JTextField();
        textFieldPreco.setBounds(120, 150, 150, 20);
        getContentPane().add(textFieldPreco);
        textFieldPreco.setColumns(10);

        JLabel lblEstoque = new JLabel("Estoque:");
        lblEstoque.setBounds(30, 190, 80, 20);
        getContentPane().add(lblEstoque);

        textFieldEstoque = new JTextField();
        textFieldEstoque.setBounds(120, 190, 150, 20);
        getContentPane().add(textFieldEstoque);
        textFieldEstoque.setColumns(10);

        JLabel lblDetalhe = new JLabel("Detalhe:");
        lblDetalhe.setBounds(30, 230, 80, 20);
        getContentPane().add(lblDetalhe);

        textFieldDetalhe = new JTextField();
        textFieldDetalhe.setBounds(120, 230, 150, 20);
        getContentPane().add(textFieldDetalhe);
        textFieldDetalhe.setColumns(10);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(170, 270, 100, 30);
        getContentPane().add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String tipo = textFieldTipo.getText();
                String cor = textFieldCor.getText();
                BigDecimal preco = new BigDecimal(textFieldPreco.getText());
                int estoque = Integer.parseInt(textFieldEstoque.getText());
                String detalhe = textFieldDetalhe.getText();

                if (editarProduto(id, nome, tipo, cor, preco, estoque, detalhe)) {
                    JOptionPane.showMessageDialog(EdtProdutoView.this, "Produto atualizado com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(EdtProdutoView.this, "Erro ao atualizar produto!");
                }
            }
        });

        carregarProduto();
    }

    private void carregarProduto() {
        try {
            Connection conn = Conexao.ConectarBanco();
            produtoDAL = new ProdutoDAL(conn);

            Produto produto = produtoDAL.obterProduto(id);
            if (produto != null) {
                textFieldNome.setText(produto.getNome());
                textFieldTipo.setText(produto.getTipo());
                textFieldCor.setText(produto.getCor());
                textFieldPreco.setText(produto.getPreco().toString());
                textFieldEstoque.setText(String.valueOf(produto.getEstoque()));
                textFieldDetalhe.setText(produto.getDetalhe());
            }

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private boolean editarProduto(int id, String nome, String tipo, String cor, BigDecimal preco, int estoque, String detalhe) {
        try {
            Connection conn = Conexao.ConectarBanco();
            produtoDAL = new ProdutoDAL(conn);

            Produto produto = new Produto(id, nome, tipo, cor, preco, estoque, detalhe);
            produtoDAL.atualizarProduto(produto);

            conn.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EdtProdutoView frame = new EdtProdutoView(1);
            frame.setVisible(true);
        });
    }
}

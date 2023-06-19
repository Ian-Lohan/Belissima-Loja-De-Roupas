package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import controller.ControllerProdutos;
import model.ModelProdutos;
import java.awt.*;
import java.util.ArrayList;

public class ViewProdutosPDV extends JDialog {

    ControllerProdutos controllerProdutos;
    ArrayList<ModelProdutos> listaProdutos = new ArrayList<>(); 
    JTable tabelaProdutos;

    public ViewProdutosPDV(Frame owner) {
        super(owner, "Lista de Produtos", true);
        controllerProdutos = new ControllerProdutos();
        criarTabela();
        configurarJanela();
    }

    private void criarTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");

        tabelaProdutos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        listaProdutos = controllerProdutos.retornarListaProdutoController();

        for (ModelProdutos produto : listaProdutos) {
            Object[] rowData = {produto.getIdProduto(), produto.getProNome(), produto.getProValor()};
            modelo.addRow(rowData);
        }

        getContentPane().add(scrollPane);
    }

    private void configurarJanela() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(getOwner());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ViewProdutosPDV dialog = new ViewProdutosPDV(null);
        });
    }
}

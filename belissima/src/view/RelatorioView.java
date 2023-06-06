package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dao.*;

public class RelatorioView extends JFrame {
    private JTextArea relatorioTextArea;

    public RelatorioView(List<Venda> vendas, List<DetalhesVenda> detalhesVendas) {
        setTitle("Relatório de Vendas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        relatorioTextArea = new JTextArea();
        relatorioTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(relatorioTextArea);
        add(scrollPane, BorderLayout.CENTER);

        gerarRelatorio(vendas, detalhesVendas);
    }

    private void gerarRelatorio(List<Venda> vendas, List<DetalhesVenda> detalhesVendas) {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("Relatório de Vendas\n\n");

        int totalVendas = vendas.size();
        relatorio.append("Total de Vendas: ").append(totalVendas).append("\n");

        int totalArrecadado = 0;
        for (Venda venda : vendas) {
            totalArrecadado += venda.getTotal();
        }
        relatorio.append("Total Arrecadado: R$").append(totalArrecadado).append("\n\n");

        relatorio.append("Produtos Vendidos:\n");
        for (DetalhesVenda detalhes : detalhesVendas) {
            Produto produto = detalhes.getProduto();
            int quantidade = detalhes.getQuantidade();
            relatorio.append("- ").append(produto.getNome()).append(" (").append(quantidade).append(" unidade(s))\n");
        }

        relatorioTextArea.setText(relatorio.toString());
    }


    public static void main(String[] args) {
        // Simulação de dados do banco de dados
        List<Venda> vendas = criarVendasSimuladas();
        List<DetalhesVenda> detalhesVendas = criarDetalhesVendasSimuladas();

        SwingUtilities.invokeLater(() -> {
            RelatorioView relatorioView = new RelatorioView(vendas, detalhesVendas);
            relatorioView.setVisible(true);
        });
    }

    // Método de simulação para criar dados de vendas
    private static List<Venda> criarVendasSimuladas() {
        // Implemente a lógica para obter os dados do banco de dados
        // e criar uma lista de objetos Venda simulados
        return null;
    }

    // Método de simulação para criar dados de detalhes de vendas
    private static List<DetalhesVenda> criarDetalhesVendasSimuladas() {
        // Implemente a lógica para obter os dados do banco de dados
        // e criar uma lista de objetos DetalhesVenda simulados
        return null;
    }
}

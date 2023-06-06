package dal;

import dao.DetalhesVenda;
import dao.Venda;

import java.util.List;

import connections.Conexao;

public class DadosRelacionaisDAL {
    private VendaDAL vendaDAL;
    private DetalhesVendaDAL detalhesVendaDAL;

    public DadosRelacionaisDAL() {
        vendaDAL = new VendaDAL(Conexao.ConectarBanco());
        detalhesVendaDAL = new DetalhesVendaDAL();
    }

    public void obterDadosRelacionais() {
        List<Venda> vendas = vendaDAL.obterVendas();

        for (Venda venda : vendas) {
            List<DetalhesVenda> detalhesVendas = detalhesVendaDAL.obterDetalhesVenda();

            System.out.println("Venda #" + venda.getId() + ":");
            for (DetalhesVenda detalhesVenda : detalhesVendas) {
                System.out.println("Produto: " + detalhesVenda.getProduto().getNome());
                System.out.println("Quantidade: " + detalhesVenda.getQuantidade());
                System.out.println("Subtotal: " + detalhesVenda.getSubtotal());
            }
            System.out.println("--------------------");
        }
    }
}

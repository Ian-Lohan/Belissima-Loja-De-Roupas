package controller;

import DAO.DaoProdutosVendasProdutos;
import model.ModelProdutosVendasProdutos;
import java.util.ArrayList;

public class ControllerProdutosVendasProdutos {
	private DaoProdutosVendasProdutos daoProdutosVendasProdutos = new DaoProdutosVendasProdutos();
	
	public ArrayList<ModelProdutosVendasProdutos> retornarListaProdutosVendasProdutosController(int pCodigoVenda) {
		return this.daoProdutosVendasProdutos.retornarListaProdutosVendasProdutosDAO(pCodigoVenda);
	}
}

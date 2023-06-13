package controller;

import java.util.ArrayList;
import DAO.DaoVendasProdutos;
import model.ModelVendasProdutos;

public class ControllerVendasProdutos {

	private DaoVendasProdutos daoVendasProdutos = new DaoVendasProdutos();
	
	// Salvar VendasProdutos
	public int salvarVendaProdutosController(ModelVendasProdutos pModelVendasProdutos) {
		return this.daoVendasProdutos.salvarVendasProdutosDAO(pModelVendasProdutos);
	}
	
	// Excluir VendasProdutos
	public boolean excluirVendaProdutosController(int pIdVendaProdutos) {
		return this.daoVendasProdutos.excluirVendasProdutosDAO(pIdVendaProdutos);
	}
	
	// Alterar VendasProdutos
	public boolean alterarVendaProdutosController(ModelVendasProdutos pModelVendasProdutos) {
		return this.daoVendasProdutos.alterarVendasProdutosDAO(pModelVendasProdutos);
	}
	
	// Retornar Venda por Codigo
	public ModelVendasProdutos retornarVendaProdutosController(int pCodigo) {
		return this.daoVendasProdutos.retornarVendasProdutosDAO(pCodigo);
	}
	
	// Retornar Lista de VendasProdutos
	public ArrayList<ModelVendasProdutos> retornarListaVendasProdutosController() {
		return this.daoVendasProdutos.retornarListaVendasProdutosDAO();
	}

	// Salva uma Lista de Produtos da Venda
	public boolean salvarListaVendaProdutosController(ArrayList<ModelVendasProdutos> pListaModelVendasProdutos) {
		return this.daoVendasProdutos.salvarListaVendasProdutosDAO(pListaModelVendasProdutos);
	}
	
}

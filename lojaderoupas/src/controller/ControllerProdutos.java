package controller;

import java.util.ArrayList;
import DAO.DaoProdutos;
import model.ModelProdutos;

public class ControllerProdutos {

	private DaoProdutos daoProdutos = new DaoProdutos();
	
	// Salvar Produtos
	public int salvarProdutoController(ModelProdutos pModelProdutos) {
		return this.daoProdutos.salvarProdutosDAO(pModelProdutos);
	}
	
	// Excluir Produtos
	public boolean excluirProdutoController(int pCodigo) {
		return this.daoProdutos.excluirProdutoDAO(pCodigo);
	}
	
	// Alterar Produtos
	public boolean alterarProdutoController(ModelProdutos pModelProdutos) {
		return this.daoProdutos.alterarProdutoDAO(pModelProdutos);
	}
	
	// Retornar Produto por Codigo
	public ModelProdutos retornarProdutoController(int pCodigo) {
		return this.daoProdutos.retornarProdutoDAO(pCodigo);
	}
	
	// Retornar Produto por Nome
		public ModelProdutos retornarProdutoNomeController(String pNomeProduto) {
			return this.daoProdutos.retornarProdutoNomeDAO(pNomeProduto);
		}
	
	// Retornar Lista de Produtos
	public ArrayList<ModelProdutos> retornarListaProdutoController() {
		return this.daoProdutos.retornarListaProdutosDAO();
	}

	public boolean alterarEstoqueProdutoController(ArrayList<ModelProdutos> pListaModelProdutos) {
		return this.daoProdutos.alterarEstoqueProdutoDAO(pListaModelProdutos); 
	}
	
}

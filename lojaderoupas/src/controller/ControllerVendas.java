package controller;

import java.util.ArrayList;
import DAO.DaoVendas;
import model.ModelVendas;

public class ControllerVendas {

	private DaoVendas daoVendas = new DaoVendas();
	
	// Salvar Vendas
	public int salvarVendaController(ModelVendas pModelVendas) {
		return this.daoVendas.salvarVendasDAO(pModelVendas);
	}
	
	// Excluir Vendas
	public boolean excluirVendaController(int pCodigo) {
		return this.daoVendas.excluirVendaDAO(pCodigo);
	}
	
	// Alterar Vendas
	public boolean alterarVendaController(ModelVendas pModelVendas) {
		return this.daoVendas.alterarVendaDAO(pModelVendas);
	}
	
	// Retornar Venda por Codigo
	public ModelVendas retornarVendaController(int pCodigo) {
		return this.daoVendas.retornarVendaDAO(pCodigo);
	}
	
	// Retornar Lista de Vendas
	public ArrayList<ModelVendas> retornarListaVendaController() {
		return this.daoVendas.retornarListaVendasDAO();
	}
	
}

package controller;

import java.util.ArrayList;
import DAO.DaoClientes;
import model.ModelClientes;

public class ControllerClientes {

	private DaoClientes daoClientes = new DaoClientes();
	
	// Salvar Clientes
	public int salvarClienteController(ModelClientes pModelClientes) {
		return this.daoClientes.salvarClientesDAO(pModelClientes);
	}
	
	// Excluir Clientes
	public boolean excluirClienteController(int pCodigo) {
		return this.daoClientes.excluirClienteDAO(pCodigo);
	}
	
	// Alterar Clientes
	public boolean alterarClienteController(ModelClientes pModelClientes) {
		return this.daoClientes.alterarClienteDAO(pModelClientes);
	}
	
	// Retornar Cliente por Codigo
	public ModelClientes retornarClienteController(int pCodigo) {
		return this.daoClientes.retornarClienteDAO(pCodigo);
	}
	
	// Retornar Cliente por Nome
		public ModelClientes retornarClienteNomeController(String pNomeCliente) {
			return this.daoClientes.retornarClienteNomeDAO(pNomeCliente);
		}
	
	// Retornar Lista de Clientes
	public ArrayList<ModelClientes> retornarListaClienteController() {
		return this.daoClientes.retornarListaClientesDAO();
	}
	
}

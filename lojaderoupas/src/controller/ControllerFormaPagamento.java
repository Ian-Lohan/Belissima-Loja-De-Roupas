package controller;

import java.util.ArrayList;
import DAO.DaoFormaPagamento;
import model.ModelFormaPagamento;

public class ControllerFormaPagamento {

	private DaoFormaPagamento daoFormaPagamento = new DaoFormaPagamento();
	
	// Salvar FormaPagamento
	public int salvarFormaPagamentoController(ModelFormaPagamento pModelFormaPagamento) {
		return this.daoFormaPagamento.salvarFormaPagamentoDAO(pModelFormaPagamento);
	}
	
	// Excluir FormaPagamento
	public boolean excluirFormaPagamentoController(int pCodigo) {
		return this.daoFormaPagamento.excluirFormaPagamentoDAO(pCodigo);
	}
	
	// Alterar FormaPagamento
	public boolean alterarFormaPagamentoController(ModelFormaPagamento pModelFormaPagamento) {
		return this.daoFormaPagamento.alterarFormaPagamentoDAO(pModelFormaPagamento);
	}
	
	// Retornar FormaPagamento por Codigo
	public ModelFormaPagamento retornarFormaPagamentoController(int pCodigo) {
		return this.daoFormaPagamento.retornarFormaPagamentoDAO(pCodigo);
	}
	
	// Retornar Lista de FormaPagamento
	public ArrayList<ModelFormaPagamento> retornarListaFormaPagamentoController() {
		return this.daoFormaPagamento.retornarListaFormaPagamentoDAO();
	}
	
}
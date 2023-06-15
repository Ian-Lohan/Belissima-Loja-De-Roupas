package controller;

import java.util.ArrayList;
import DAO.DaoUsuarios;
import model.ModelUsuarios;

public class ControllerUsuarios {

	private DaoUsuarios daoUsuarios = new DaoUsuarios();
	
	// Salvar Usuarios
	public int salvarUsuarioController(ModelUsuarios pModelUsuarios) {
		return this.daoUsuarios.salvarUsuariosDAO(pModelUsuarios);
	}
	
	// Excluir Usuarios
	public boolean excluirUsuarioController(int pCodigo) {
		return this.daoUsuarios.excluirUsuarioDAO(pCodigo);
	}
	
	// Alterar Usuarios
	public boolean alterarUsuarioController(ModelUsuarios pModelUsuarios) {
		return this.daoUsuarios.alterarUsuarioDAO(pModelUsuarios);
	}
	
	// Retornar Usuario por Codigo
	public ModelUsuarios retornarUsuarioController(int pCodigo) {
		return this.daoUsuarios.retornarUsuarioDAO(pCodigo);
	}
	
	public ModelUsuarios retornarLoginUsuarioController(String pLogin) {
		return this.daoUsuarios.retornarLoginUsuarioDAO(pLogin);
	}
	
	// Retornar Lista de Usuarios
	public ArrayList<ModelUsuarios> retornarListaUsuarioController() {
		return this.daoUsuarios.retornarListaUsuariosDAO();
	}

	// Valida Login e Senha do Usuario
	public boolean validarUsuarioController(ModelUsuarios pModelUsuarios) {
		return this.daoUsuarios.getValidarUsuarioDAO(pModelUsuarios);
	}
	
}

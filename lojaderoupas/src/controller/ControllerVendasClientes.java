package controller;

import java.util.ArrayList;
import DAO.DaoVendasClientes;
import model.ModelVendasClientes;

public class ControllerVendasClientes {
	
	private DaoVendasClientes daoVendasClientes = new DaoVendasClientes();

	public ArrayList<ModelVendasClientes> retornarListaVendasClientesController() {
		return this.daoVendasClientes.retornarListaVendasClientesDAO();
	}

}

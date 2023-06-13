package model;

import java.util.ArrayList;

public class ModelVendasClientes {

	private ModelVendas modelVendas;
	private ModelClientes modelClientes;
	private ArrayList<ModelVendasClientes> listaModelVendasClientes;
	
	public ModelVendas getModelVendas() {
		return modelVendas;
	}
	public void setModelVendas(ModelVendas modelVendas) {
		this.modelVendas = modelVendas;
	}
	public ModelClientes getModelClientes() {
		return modelClientes;
	}
	public void setModelClientes(ModelClientes modelClientes) {
		this.modelClientes = modelClientes;
	}
	public ArrayList<ModelVendasClientes> getListaModelVendasClientes() {
		return listaModelVendasClientes;
	}
	public void setListaModelVendasClientes(ArrayList<ModelVendasClientes> listaModelVendasClientes) {
		this.listaModelVendasClientes = listaModelVendasClientes;
	}
	
}

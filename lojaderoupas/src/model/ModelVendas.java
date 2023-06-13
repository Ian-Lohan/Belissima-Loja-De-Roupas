package model;

import java.sql.Date;

public class ModelVendas {

	private int idVenda;
	private int cliente;
	private Date VenDataVenda;
	private double VenValorLiquido;
	private double VenValorBruto;
	private double VenDesconto;
	
	public ModelVendas() {}
	
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public int getCliente() {
		return cliente;
	}
	public void setCliente(int cliente) {
		this.cliente = cliente;
	}
	public Date getVenDataVenda() {
		return VenDataVenda;
	}
	public void setVenDataVenda(Date venDataVenda) {
		VenDataVenda = venDataVenda;
	}
	public double getVenValorLiquido() {
		return VenValorLiquido;
	}
	public void setVenValorLiquido(double venValorLiquido) {
		VenValorLiquido = venValorLiquido;
	}
	public double getVenValorBruto() {
		return VenValorBruto;
	}
	public void setVenValorBruto(double venValorBruto) {
		VenValorBruto = venValorBruto;
	}
	public double getVenDesconto() {
		return VenDesconto;
	}
	public void setVenDesconto(double venDesconto) {
		VenDesconto = venDesconto;
	}

}

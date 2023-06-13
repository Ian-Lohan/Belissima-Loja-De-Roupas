package model;

public class ModelVendasProdutos {

	private int idVendaProdutos;
	private int produto;
	private int vendas;
	private double VenProValor;
	private int VenProQuantidade;
	
	public int getIdVendaProdutos() {
		return idVendaProdutos;
	}
	public void setIdVendaProdutos(int idVendaProdutos) {
		this.idVendaProdutos = idVendaProdutos;
	}
	public int getProduto() {
		return produto;
	}
	public void setProduto(int produto) {
		this.produto = produto;
	}
	public int getVendas() {
		return vendas;
	}
	public void setVendas(int vendas) {
		this.vendas = vendas;
	}
	public double getVenProValor() {
		return VenProValor;
	}
	public void setVenProValor(double venProValor) {
		VenProValor = venProValor;
	}
	public int getVenProQuantidade() {
		return VenProQuantidade;
	}
	public void setVenProQuantidade(int venProQuantidade) {
		VenProQuantidade = venProQuantidade;
	}
	
}

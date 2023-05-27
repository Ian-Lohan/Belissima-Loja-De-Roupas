package domain;

import java.math.BigDecimal;

public class DetalhesVendas {
	private int id_venda;
	private int id_produto;
	private int quantidade;
	private BigDecimal precounitario;
	private BigDecimal subtotal;
	
	public DetalhesVendas() {
	}

	public DetalhesVendas(int id_venda, int id_produto, int quantidade, BigDecimal precounitario, BigDecimal subtotal) {
		super();
		this.id_venda = id_venda;
		this.id_produto = id_produto;
		this.quantidade = quantidade;
		this.precounitario = precounitario;
		this.subtotal = subtotal;
	}

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPrecounitario() {
		return precounitario;
	}

	public void setPrecounitario(BigDecimal precounitario) {
		this.precounitario = precounitario;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}

package dao;

import java.math.BigDecimal;

public class DetalhesVenda {
	private Venda venda;
	private Produto produto;
	private int quantidade;
	private BigDecimal precounitario;
	private BigDecimal subtotal;
	
	public DetalhesVenda() {
	}

	public DetalhesVenda(Venda venda, Produto produto, int quantidade, BigDecimal precounitario, BigDecimal subtotal) {
		super();
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
		this.precounitario = precounitario;
		this.subtotal = subtotal;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
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

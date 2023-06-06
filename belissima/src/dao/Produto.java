package dao;

import java.math.BigDecimal;

public class Produto {
    private int id;
    private String nome;
    private String tipo;
    private String cor;
    private BigDecimal preco;
    private int estoque;
    private String detalhe;

    public Produto() {
    }

    public Produto(int id, String nome, String tipo, String cor, BigDecimal preco, int estoque, String detalhe) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.cor = cor;
        this.preco = preco;
        this.estoque = estoque;
        this.detalhe = detalhe;
    }

    public Produto(String nome, String tipo, String cor, BigDecimal preco, int estoque, String detalhe) {
        this.nome = nome;
        this.tipo = tipo;
        this.cor = cor;
        this.preco = preco;
        this.estoque = estoque;
        this.detalhe = detalhe;
    }

	public String getCor() {
		return cor;
	}
	
	public String getDetalhe() {
		return detalhe;
	}
	
	public int getEstoque() {
		return estoque;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setCor(String cor) {
		this.cor = cor;
	}
	
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
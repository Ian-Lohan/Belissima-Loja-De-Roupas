package domain;

public class Clientes {
	private int id;
	private String nome;
	private int cpf;
	private int numero;
	private String endereco;
	
	public Clientes() {
	}
	
	public Clientes(int id, String nome, int cpf, int numero, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;
		this.endereco = endereco;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}

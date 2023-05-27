package domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuarios {
	private int id;
	private String nome;
	private String login;
	private String senha;
	private int cpf;
	private String endereco;
	private int numero;
	private int acesso;
	private boolean estado;
	private LocalDateTime dataCriacao;
	private LocalDateTime ultimoLogin;
	private Perfil perfil;
	
	public Usuarios() {
		this.estado = true;
	}
	
	public Usuarios(int id, String nome, String login, String senha, int cpf, String endereco, int numero, int acesso, boolean estado, LocalDateTime dataCriacao, LocalDateTime ultimoLogin) {
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numero = numero;
		this.acesso = acesso;
		this.estado = true;
		this.dataCriacao = dataCriacao;
		this.ultimoLogin = ultimoLogin;
		this.perfil = perfil;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getAcesso() {
		return acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(LocalDateTime ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 83 * hash + Objects.hashCode(this.id);
		return hash;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuarios other = (Usuarios) obj;
		if (Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}
	
	public void reset() {
		this.estado = true;
	}
	
	public void mudarEstado() {
		this.estado = !this.estado;
	}
	
}
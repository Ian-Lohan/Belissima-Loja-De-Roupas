package model;

public class ModelUsuarios {

	private int idUsuario;
	private String UsuNome;
	private String UsuLogin;
	private String UsuSenha;
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuNome() {
		return UsuNome;
	}
	public void setUsuNome(String usuNome) {
		UsuNome = usuNome;
	}
	public String getUsuLogin() {
		return UsuLogin;
	}
	public void setUsuLogin(String usuLogin) {
		UsuLogin = usuLogin;
	}
	public String getUsuSenha() {
		return UsuSenha;
	}
	public void setUsuSenha(String usuSenha) {
		UsuSenha = usuSenha;
	}
	
}

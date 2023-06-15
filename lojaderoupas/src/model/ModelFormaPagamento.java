package model;

public class ModelFormaPagamento {
	
	private int IdForPag;
	private String ForPagDescricao;
	private double ForPagDesconto;
	private int ForPagParcelas;
	private int ForPagSituacao;
	
	public int getIdForPag() {
		return IdForPag;
	}
	public void setIdForPag(int idForPag) {
		IdForPag = idForPag;
	}
	public String getForPagDescricao() {
		return ForPagDescricao;
	}
	public void setForPagDescricao(String forPagDescricao) {
		ForPagDescricao = forPagDescricao;
	}
	public double getForPagDesconto() {
		return ForPagDesconto;
	}
	public void setForPagDesconto(double forPagDesconto) {
		ForPagDesconto = forPagDesconto;
	}
	public int getForPagParcelas() {
		return ForPagParcelas;
	}
	public void setForPagParcelas(int forPagParcelas) {
		ForPagParcelas = forPagParcelas;
	}
	public int getForPagSituacao() {
		return ForPagSituacao;
	}
	public void setForPagSituacao(int forPagSituacao) {
		ForPagSituacao = forPagSituacao;
	}
}

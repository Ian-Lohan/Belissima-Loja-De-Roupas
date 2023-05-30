package model;

import java.time.LocalDateTime;

public class Venda {
	private int id;
	private LocalDateTime data;
	private String pagamento;
	private int total;
	
	public Venda() {
	}

	public Venda(int id, LocalDateTime data, String pagamento, int total) {
		super();
		this.id = id;
		this.data = data;
		this.pagamento = pagamento;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}

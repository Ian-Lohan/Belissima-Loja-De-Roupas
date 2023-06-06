package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venda {
    private int id;
    private LocalDateTime data;
    private String pagamento;
    private int total;
    private int idCliente;
    private int idUsuario;

    public Venda(int id, LocalDateTime data, String pagamento, int total, int idCliente, int idUsuario) {
        this.id = id;
        this.data = data;
        this.pagamento = pagamento;
        this.total = total;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
    }
    
    public Venda() {
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
    
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return data.format(formatter);
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
       
}

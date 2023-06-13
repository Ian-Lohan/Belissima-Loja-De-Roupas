package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import model.ModelVendas;
import model.ModelClientes;
import model.ModelVendasClientes;

public class DaoVendasClientes extends ConexaoMySql {

	public ArrayList<ModelVendasClientes> retornarListaVendasClientesDAO() {
		ArrayList<ModelVendasClientes> listaModelVendasClientes = new ArrayList<>();
		ModelVendas modelVendas = new ModelVendas();
		ModelClientes modelClientes = new ModelClientes();
		ModelVendasClientes modelVendasClientes = new ModelVendasClientes();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "tbl_vendas.pk_id_vendas, "
					+ "tbl_vendas.fk_cliente, "
					+ "tbl_vendas.ven_data_venda, "
					+ "tbl_vendas.ven_valor_liquido, "
					+ "tbl_vendas.ven_valor_bruto, "
					+ "tbl_vendas.desconto, "
					+ "tbl_cliente.pk_id_cliente, "
					+ "tbl_cliente.cli_nome, "
					+ "tbl_cliente.cli_endereco, "
					+ "tbl_cliente.cli_bairro, "
					+ "tbl_cliente.cli_cidade, "
					+ "tbl_cliente.cli_uf, "
					+ "tbl_cliente.cli_cep, "
					+ "tbl_cliente.cli_telefone "
					+ "FROM tbl_vendas INNER JOIN tbl_cliente "
					+ "ON tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente; "
					+ ";"
					);
			while (this.getResultSet().next()) {
				modelVendas = new ModelVendas();
				modelClientes = new ModelClientes();
				
				modelVendasClientes = new ModelVendasClientes();
				modelVendas.setIdVenda(this.getResultSet().getInt(1));
				modelVendas.setCliente(this.getResultSet().getInt(2));
				modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
				modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
				modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
				modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
				
				modelClientes.setIdCliente(this.getResultSet().getInt(7));
				modelClientes.setCliNome(this.getResultSet().getString(8));
				modelClientes.setCliEndereco(this.getResultSet().getString(9));
				modelClientes.setCliBairro(this.getResultSet().getString(10));
				modelClientes.setCliCidade(this.getResultSet().getString(11));
				modelClientes.setCliUf(this.getResultSet().getString(12));
				modelClientes.setCliCep(this.getResultSet().getString(13));
				modelClientes.setCliTelefone(this.getResultSet().getString(14));
				
				modelVendasClientes.setModelVendas(modelVendas);
				modelVendasClientes.setModelClientes(modelClientes);
				
				listaModelVendasClientes.add(modelVendasClientes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelVendasClientes;
	}
	
}

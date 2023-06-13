package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import model.ModelVendas;

public class DaoVendas extends ConexaoMySql {
	
	// Salva um Venda no Banco
	public int salvarVendasDAO (ModelVendas pModelVendas) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_vendas (" 
			+ "fk_cliente,"
			+ "ven_data_venda,"
			+ "ven_valor_liquido,"
			+ "ven_valor_bruto,"
			+ "desconto"
			+ ") VALUES ("
			+ "'" + pModelVendas.getCliente() + "',"
			+ "'" + pModelVendas.getVenDataVenda() + "',"
			+ "'" + pModelVendas.getVenValorLiquido() + "',"
			+ "'" + pModelVendas.getVenValorBruto() + "',"
			+ "'" + pModelVendas.getVenDesconto() + "'"
			+ ");"
			);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	
	//Excluir um Venda do Banco
	public boolean excluirVendaDAO (int pIdVenda) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"DELETE FROM tbl_vendas WHERE pk_id_vendas = '"+pIdVenda+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Alterar Venda no Banco
	public boolean alterarVendaDAO (ModelVendas pModelVendas) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"UPDATE tbl_vendas SET "
					+ "fk_cliente = '"+pModelVendas.getCliente()+"', "
					+ "ven_data_venda = '"+pModelVendas.getVenDataVenda()+"', "
					+ "ven_valor_liquido = '"+pModelVendas.getVenValorLiquido()+"', "
					+ "ven_valor_bruto = '"+pModelVendas.getVenValorBruto()+"', "
					+ "desconto = '"+pModelVendas.getVenDesconto()+"'"
					+ " WHERE pk_id_vendas = '"+pModelVendas.getIdVenda()+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Retorna um Venda do Banco
	public ModelVendas retornarVendaDAO (int pIdVenda) {
		ModelVendas modelVendas = new ModelVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_vendas, "
					+ "fk_cliente,"
					+ "ven_data_venda, "
					+ "ven_valor_liquido, "
					+ "ven_valor_bruto, "
					+ "desconto "
					+ "FROM tbl_vendas WHERE pk_id_vendas = '"+pIdVenda+"'"
					);
			while (this.getResultSet().next()) {
				modelVendas.setIdVenda(this.getResultSet().getInt(1));
				modelVendas.setCliente(this.getResultSet().getInt(2));
				modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
				modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
				modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
				modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return modelVendas;
	}

	// Retorna uma Lista de Vendas do Banco
	public ArrayList<ModelVendas> retornarListaVendasDAO() {
		ArrayList<ModelVendas> listaModelVendas = new ArrayList<>();
		ModelVendas modelVendas = new ModelVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_vendas, "
					+ "fk_cliente, "
					+ "ven_data_venda, "
					+ "ven_valor_liquido, "
					+ "ven_valor_bruto, "
					+ "desconto "
					+ "FROM tbl_vendas;"
					);
			while (this.getResultSet().next()) {
				modelVendas = new ModelVendas();
				modelVendas.setIdVenda(this.getResultSet().getInt(1));
				modelVendas.setCliente(this.getResultSet().getInt(2));
				modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
				modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
				modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
				modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
				listaModelVendas.add(modelVendas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelVendas;
	}
}

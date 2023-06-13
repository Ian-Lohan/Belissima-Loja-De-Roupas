package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import model.ModelVendasProdutos;

public class DaoVendasProdutos extends ConexaoMySql {
	
	// Salva um Venda no Banco
	public int salvarVendasProdutosDAO (ModelVendasProdutos pModelVendasProdutos) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_vendas_produtos (" 
			+ "fk_produto,"
			+ "fk_vendas,"
			+ "ven_pro_valor,"
			+ "ven_pro_quantidade"
			+ ") VALUES ("
			+ "'" + pModelVendasProdutos.getProduto() + "',"
			+ "'" + pModelVendasProdutos.getVendas() + "',"
			+ "'" + pModelVendasProdutos.getVenProValor() + "',"
			+ "'" + pModelVendasProdutos.getVenProQuantidade() + "'"
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
	public boolean excluirVendasProdutosDAO (int pIdVendaProdutos) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"DELETE FROM tbl_vendas_produtos WHERE fk_vendas = '"+pIdVendaProdutos+"'"
					+ ";"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Alterar Venda no Banco
	public boolean alterarVendasProdutosDAO (ModelVendasProdutos pModelVendasProdutos) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"UPDATE tbl_vendas_produtos SET "
					+ "fk_produto = '"+pModelVendasProdutos.getProduto()+"', "
					+ "fk_vendas = '"+pModelVendasProdutos.getVendas()+"', "
					+ "ven_pro_valor = '"+pModelVendasProdutos.getVenProValor()+"', "
					+ "ven_pro_quantidade = '"+pModelVendasProdutos.getVenProQuantidade()+"'"
					+ " WHERE pk_id_venda_produto = '"+pModelVendasProdutos.getIdVendaProdutos()+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Retorna um Venda do Banco
	public ModelVendasProdutos retornarVendasProdutosDAO (int pIdVendaProdutos) {
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_venda_produto, "
					+ "fk_produto,"
					+ "fk_vendas,"
					+ "ven_pro_valor,"
					+ "ven_pro_quantidade,"
					+ "FROM tbl_vendas_produtos WHERE pk_id_venda_produto = '"+pIdVendaProdutos+"'"
					);
			while (this.getResultSet().next()) {
				modelVendasProdutos.setIdVendaProdutos(this.getResultSet().getInt(1));
				modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
				modelVendasProdutos.setVendas(this.getResultSet().getInt(3));
				modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(4));
				modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return modelVendasProdutos;
	}

	// Retorna uma Lista de Vendas do Banco
	public ArrayList<ModelVendasProdutos> retornarListaVendasProdutosDAO() {
		ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_venda_produto, "
					+ "fk_produto, "
					+ "fk_vendas, "
					+ "ven_pro_valor, "
					+ "ven_pro_quantidade "
					+ "FROM tbl_vendas_produtos;"
					);
			while (this.getResultSet().next()) {
				modelVendasProdutos = new ModelVendasProdutos();
				modelVendasProdutos.setIdVendaProdutos(this.getResultSet().getInt(1));
				modelVendasProdutos.setProduto(this.getResultSet().getInt(2));
				modelVendasProdutos.setVendas(this.getResultSet().getInt(3));
				modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(4));
				modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(5));
				listaModelVendasProdutos.add(modelVendasProdutos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelVendasProdutos;
	}
	
	public boolean salvarListaVendasProdutosDAO (ArrayList<ModelVendasProdutos> pListaModelVendasProdutos) {
		try {
			this.conectar();
			int cont = pListaModelVendasProdutos.size();
			for (int i = 0; i < cont; i++) {
				this.insertSQL("INSERT INTO tbl_vendas_produtos (" 
				+ "fk_vendas,"
				+ "fk_produto,"
				+ "ven_pro_valor,"
				+ "ven_pro_quantidade"
				+ ") VALUES ("
				+ "'" + pListaModelVendasProdutos.get(i).getVendas() + "',"
				+ "'" + pListaModelVendasProdutos.get(i).getProduto() + "',"
				+ "'" + pListaModelVendasProdutos.get(i).getVenProValor() + "',"
				+ "'" + pListaModelVendasProdutos.get(i).getVenProQuantidade() + "'"
				+ ");"
					);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
}

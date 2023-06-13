package DAO;

import java.util.ArrayList;
import model.ModelProdutos;
import model.ModelProdutosVendasProdutos;
import model.ModelVendasProdutos;
import conexoes.ConexaoMySql;

public class DaoProdutosVendasProdutos extends ConexaoMySql {

	public ArrayList<ModelProdutosVendasProdutos> retornarListaProdutosVendasProdutosDAO (int pCodigoVenda) {
		ArrayList<ModelProdutosVendasProdutos> listaModelProdutosVendasProdutos = new ArrayList<>();
		ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
		ModelProdutos modelProdutos = new ModelProdutos();
		ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
		try {
			this.conectar();
			this.executarSQL("SELECT tbl_produto.pk_id_produto, "
					+ "tbl_produto.pro_estoque, "
					+ "tbl_produto.pro_nome, "
					+ "tbl_produto.pro_valor, "
					+ "tbl_vendas_produtos.fk_produto, "
					+ "tbl_vendas_produtos.fk_vendas, "
					+ "tbl_vendas_produtos.pk_id_venda_produto, "
					+ "tbl_vendas_produtos.ven_pro_quantidade, "
					+ "tbl_vendas_produtos.ven_pro_valor "
					+ "FROM tbl_vendas_produtos "
					+ "INNER JOIN tbl_produto ON tbl_produto.pk_id_produto = tbl_vendas_produtos.fk_produto "
					+ "WHERE tbl_vendas_produtos.fk_vendas = '"+pCodigoVenda+"';"
					);
			while (this.getResultSet().next()) {
				modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
				modelProdutos = new ModelProdutos();
				modelVendasProdutos = new ModelVendasProdutos();
				
				modelProdutos.setIdProduto(this.getResultSet().getInt(1));
				modelProdutos.setProEstoque(this.getResultSet().getInt(2));
				modelProdutos.setProNome(this.getResultSet().getString(3));
				modelProdutos.setProValor(this.getResultSet().getDouble(4));
				
				modelVendasProdutos.setProduto(this.getResultSet().getInt(5));
				modelVendasProdutos.setVendas(this.getResultSet().getInt(6));
				modelVendasProdutos.setIdVendaProdutos(this.getResultSet().getInt(7));
				modelVendasProdutos.setVenProQuantidade(this.getResultSet().getInt(8));
				modelVendasProdutos.setVenProValor(this.getResultSet().getDouble(9));

				modelProdutosVendasProdutos.setModelProdutos(modelProdutos);
				modelProdutosVendasProdutos.setModelVendasProdutos(modelVendasProdutos);
				
				listaModelProdutosVendasProdutos.add(modelProdutosVendasProdutos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelProdutosVendasProdutos;
	}
	
}

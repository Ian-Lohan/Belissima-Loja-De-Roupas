package DAO;

import java.util.ArrayList;
import conexoes.ConexaoMySql;
import model.ModelFormaPagamento;

public class DaoFormaPagamento extends ConexaoMySql{

	// Salva um Pagamento no Banco
		public int salvarFormaPagamentoDAO (ModelFormaPagamento pModelFormaPagamento) {
			try {
				this.conectar();
				return this.insertSQL("INSERT INTO tbl_forma_pagamento (" 
				+ "descricao_for_pag"
				+ "desconto_for_pag,"
				+ "parcelas_for_pag,"
				+ "situacao_for_pag"
				+ ") VALUES ("
				+ "'" + pModelFormaPagamento.getForPagDescricao() + "',"
				+ "'" + pModelFormaPagamento.getForPagDesconto() + "',"
				+ "'" + pModelFormaPagamento.getForPagParcelas() + "',"
				+ "'" + pModelFormaPagamento.getForPagSituacao() + "'"
				+ ");"
				);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			} finally {
				this.fecharConexao();
			}
		}
		
		//Excluir um Pagamento do Banco
		public boolean excluirFormaPagamentoDAO (int pIdForPag) {
			try {
				this.conectar();
				return this.executarUpdateDeleteSQL(
						"DELETE FROM tbl_forma_pagamento WHERE pk_id_forma_pagamento = '"+pIdForPag+"'"
						);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				this.fecharConexao();
			}
		}
		
		// Alterar Pagamento no Banco
		public boolean alterarFormaPagamentoDAO (ModelFormaPagamento pModelFormaPagamento) {
			try {
				this.conectar();
				return this.executarUpdateDeleteSQL(
						"UPDATE tbl_forma_pagamento SET "
						+ "descricao_for_pag = '"+pModelFormaPagamento.getForPagDescricao()+"', "
						+ "desconto_for_pag = '"+pModelFormaPagamento.getForPagDesconto()+"', "
						+ "parcelas_for_pag = '"+pModelFormaPagamento.getForPagParcelas()+"', "
						+ "situacao_for_pag = '"+pModelFormaPagamento.getForPagSituacao()+"'"
						+ " WHERE pk_id_forma_pagamento = '"+pModelFormaPagamento.getIdForPag()+"'"
						);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				this.fecharConexao();
			}
		}
		
		// Retorna uma Forma Pagamento do Banco
		public ModelFormaPagamento retornarFormaPagamentoDAO (int pIdForPag) {
			ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "pk_id_forma_pagamento, "
						+ "descricao_for_pag,"
						+ "desconto_for_pag,"
						+ "parcelas_for_pag, "
						+ "situacao_for_pag "
						+ "FROM tbl_forma_pagamento WHERE pk_id_forma_pagamento = '"+pIdForPag+"'"
						);
				while (this.getResultSet().next()) {
					modelFormaPagamento.setIdForPag(this.getResultSet().getInt(1));
					modelFormaPagamento.setForPagDescricao(this.getResultSet().getString(2));
					modelFormaPagamento.setForPagDesconto(this.getResultSet().getDouble(3));
					modelFormaPagamento.setForPagParcelas(this.getResultSet().getInt(4));
					modelFormaPagamento.setForPagSituacao(this.getResultSet().getInt(5));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.fecharConexao();
			}
			return modelFormaPagamento;
		}

		// Retorna uma Lista de Pagamentos do Banco
		public ArrayList<ModelFormaPagamento> retornarListaFormaPagamentoDAO() {
			ArrayList<ModelFormaPagamento> listaModelFormaPagamento = new ArrayList<>();
			ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "pk_id_forma_pagamento, "
						+ "descricao_for_pag,"
						+ "desconto_for_pag,"
						+ "parcelas_for_pag, "
						+ "situacao_for_pag "
						+ "FROM tbl_forma_pagamento;"
						);
				while (this.getResultSet().next()) {
		            modelFormaPagamento = new ModelFormaPagamento();
					modelFormaPagamento.setIdForPag(this.getResultSet().getInt(1));
					modelFormaPagamento.setForPagDescricao(this.getResultSet().getString(2));
					modelFormaPagamento.setForPagDesconto(this.getResultSet().getDouble(3));
					modelFormaPagamento.setForPagParcelas(this.getResultSet().getInt(4));
					modelFormaPagamento.setForPagSituacao(this.getResultSet().getInt(5));
					listaModelFormaPagamento.add(modelFormaPagamento);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.fecharConexao();
			}
			return listaModelFormaPagamento;
		}
}

package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import model.ModelClientes;

public class DaoClientes extends ConexaoMySql {
	
	// Adicionar um Cliente ao Banco
	public int salvarClientesDAO (ModelClientes pModelClientes) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_cliente (" 
			+ "cli_nome,"
			+ "cli_endereco,"
			+ "cli_bairro,"
			+ "cli_cidade,"
			+ "cli_uf,"
			+ "cli_cep,"
			+ "cli_telefone"
			+ ") VALUES ("
			+ "'" + pModelClientes.getCliNome() + "',"
			+ "'" + pModelClientes.getCliEndereco() + "',"
			+ "'" + pModelClientes.getCliBairro() + "',"
			+ "'" + pModelClientes.getCliCidade() + "',"
			+ "'" + pModelClientes.getCliUf() + "',"
			+ "'" + pModelClientes.getCliCep() + "',"
			+ "'" + pModelClientes.getCliTelefone() + "'"
			+ ");"
			);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	
	//Excluir um Cliente do Banco
	public boolean excluirClienteDAO (int pIdCliente) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"DELETE FROM tbl_cliente WHERE pk_id_cliente = '"+pIdCliente+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Alterar Cliente no Banco
	public boolean alterarClienteDAO (ModelClientes pModelClientes) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"UPDATE tbl_cliente SET "
					+ "cli_nome = '"+pModelClientes.getCliNome()+"', "
					+ "cli_endereco = '"+pModelClientes.getCliEndereco()+"', "
					+ "cli_bairro = '"+pModelClientes.getCliBairro()+"', "
					+ "cli_cidade = '"+pModelClientes.getCliCidade()+"', "
					+ "cli_uf = '"+pModelClientes.getCliUf()+"', "
					+ "cli_cep = '"+pModelClientes.getCliCep()+"', "
					+ "cli_telefone = '"+pModelClientes.getCliTelefone()+"'"
					+ " WHERE pk_id_cliente = '"+pModelClientes.getIdCliente()+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Retorna um Cliente do Banco
	public ModelClientes retornarClienteDAO (int pIdCliente) {
		ModelClientes modelClientes = new ModelClientes();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_cliente, "
					+ "cli_nome,"
					+ "cli_endereco,"
					+ "cli_bairro,"
					+ "cli_cidade,"
					+ "cli_uf,"
					+ "cli_cep,"
					+ "cli_telefone "
					+ "FROM tbl_cliente WHERE pk_id_cliente = '"+pIdCliente+"'"
					);
			while (this.getResultSet().next()) {
				modelClientes.setIdCliente(this.getResultSet().getInt(1));
				modelClientes.setCliNome(this.getResultSet().getString(2));
				modelClientes.setCliEndereco(this.getResultSet().getString(3));
				modelClientes.setCliBairro(this.getResultSet().getString(4));
				modelClientes.setCliCidade(this.getResultSet().getString(5));
				modelClientes.setCliUf(this.getResultSet().getString(6));
				modelClientes.setCliCep(this.getResultSet().getString(7));
				modelClientes.setCliTelefone(this.getResultSet().getString(8));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return modelClientes;
	}
	
	// Retorna um Cliente do Banco
		public ModelClientes retornarClienteNomeDAO (String pNomeCliente) {
			ModelClientes modelClientes = new ModelClientes();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "pk_id_cliente, "
						+ "cli_nome,"
						+ "cli_endereco,"
						+ "cli_bairro,"
						+ "cli_cidade,"
						+ "cli_uf,"
						+ "cli_cep,"
						+ "cli_telefone "
						+ "FROM tbl_cliente WHERE cli_nome = '"+pNomeCliente+"'"
						);
				while (this.getResultSet().next()) {
					modelClientes.setIdCliente(this.getResultSet().getInt(1));
					modelClientes.setCliNome(this.getResultSet().getString(2));
					modelClientes.setCliEndereco(this.getResultSet().getString(3));
					modelClientes.setCliBairro(this.getResultSet().getString(4));
					modelClientes.setCliCidade(this.getResultSet().getString(5));
					modelClientes.setCliUf(this.getResultSet().getString(6));
					modelClientes.setCliCep(this.getResultSet().getString(7));
					modelClientes.setCliTelefone(this.getResultSet().getString(8));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				this.fecharConexao();
			}
			return modelClientes;
		}

	// Retorna uma Lista de Clientes do Banco
	public ArrayList<ModelClientes> retornarListaClientesDAO() {
		ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
		ModelClientes modelClientes = new ModelClientes();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_cliente, "
					+ "cli_nome, "
					+ "cli_endereco, "
					+ "cli_bairro, "
					+ "cli_cidade, "
					+ "cli_uf, "
					+ "cli_cep, "
					+ "cli_telefone "
					+ "FROM tbl_cliente;"
					);
			while (this.getResultSet().next()) {
				modelClientes = new ModelClientes();
				modelClientes.setIdCliente(this.getResultSet().getInt(1));
				modelClientes.setCliNome(this.getResultSet().getString(2));
				modelClientes.setCliEndereco(this.getResultSet().getString(3));
				modelClientes.setCliBairro(this.getResultSet().getString(4));
				modelClientes.setCliCidade(this.getResultSet().getString(5));
				modelClientes.setCliUf(this.getResultSet().getString(6));
				modelClientes.setCliCep(this.getResultSet().getString(7));
				modelClientes.setCliTelefone(this.getResultSet().getString(8));
				listaModelClientes.add(modelClientes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelClientes;
	}
}

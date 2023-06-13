package DAO;

import java.util.ArrayList;

import conexoes.ConexaoMySql;
import model.ModelUsuarios;

public class DaoUsuarios extends ConexaoMySql {
	
	// Salva um Usuario no Banco
	public int salvarUsuariosDAO (ModelUsuarios pModelUsuarios) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tbl_usuario (" 
			+ "usu_nome,"
			+ "usu_login,"
			+ "usu_senha"
			+ ") VALUES ("
			+ "'" + pModelUsuarios.getUsuNome() + "',"
			+ "'" + pModelUsuarios.getUsuLogin() + "',"
			+ "'" + pModelUsuarios.getUsuSenha() + "'"
			+ ");"
			);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	
	//Excluir um Usuario do Banco
	public boolean excluirUsuarioDAO (int pIdUsuario) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"DELETE FROM tbl_usuario WHERE pk_id_usuario = '"+pIdUsuario+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Alterar Usuario no Banco
	public boolean alterarUsuarioDAO (ModelUsuarios pModelUsuarios) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL(
					"UPDATE tbl_usuario SET "
					+ "usu_nome = '"+pModelUsuarios.getUsuNome()+"', "
					+ "usu_login = '"+pModelUsuarios.getUsuLogin()+"', "
					+ "usu_senha = '"+pModelUsuarios.getUsuSenha()+"'"
					+ " WHERE pk_id_usuario = '"+pModelUsuarios.getIdUsuario()+"'"
					);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	
	// Retorna um Usuario do Banco
	public ModelUsuarios retornarUsuarioDAO (int pIdUsuario) {
		ModelUsuarios modelUsuarios = new ModelUsuarios();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_usuario, "
					+ "usu_nome,"
					+ "usu_login,"
					+ "usu_senha "
					+ "FROM tbl_usuario WHERE pk_id_usuario = '"+pIdUsuario+"'"
					);
			while (this.getResultSet().next()) {
				modelUsuarios.setIdUsuario(this.getResultSet().getInt(1));
				modelUsuarios.setUsuNome(this.getResultSet().getString(2));
				modelUsuarios.setUsuLogin(this.getResultSet().getString(3));
				modelUsuarios.setUsuSenha(this.getResultSet().getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return modelUsuarios;
	}

	// Retorna uma Lista de Usuarios do Banco
	public ArrayList<ModelUsuarios> retornarListaUsuariosDAO() {
		ArrayList<ModelUsuarios> listaModelUsuarios = new ArrayList<>();
		ModelUsuarios modelUsuarios = new ModelUsuarios();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_usuario, "
					+ "usu_nome, "
					+ "usu_login, "
					+ "usu_senha "
					+ "FROM tbl_usuario;"
					);
			while (this.getResultSet().next()) {
				modelUsuarios = new ModelUsuarios();
				modelUsuarios.setIdUsuario(this.getResultSet().getInt(1));
				modelUsuarios.setUsuNome(this.getResultSet().getString(2));
				modelUsuarios.setUsuLogin(this.getResultSet().getString(3));
				modelUsuarios.setUsuSenha(this.getResultSet().getString(4));
				listaModelUsuarios.add(modelUsuarios);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.fecharConexao();
		}
		return listaModelUsuarios;
	}

	public boolean getValidarUsuarioDAO(ModelUsuarios pModelUsuarios) {
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "pk_id_usuario, "
					+ "usu_nome,"
					+ "usu_login,"
					+ "usu_senha "
					+ "FROM tbl_usuario WHERE usu_login = '"+pModelUsuarios.getUsuLogin() + "' AND usu_senha = '"+pModelUsuarios.getUsuSenha() +"'"
					);
			if(getResultSet().next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
}

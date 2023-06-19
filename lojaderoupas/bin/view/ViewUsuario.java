package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.RowSorter;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import model.ModelUsuarios;
import controller.ControllerUsuarios;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class ViewUsuario extends JFrame {
	
	private JPanel contentPane;
	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JFormattedTextField jtfLogin;
	private JFormattedTextField jtfSenha;
	private JTextField jtfPesquisar;
	private JTable jtUsuario;
	private JButton jbSalvar;
	
	ArrayList<ModelUsuarios> listaModelUsuarios = new ArrayList<>();
	ControllerUsuarios controllerUsuarios = new ControllerUsuarios();
	ModelUsuarios modelUsuarios = new ModelUsuarios();
	String salvarAlterar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewUsuario frame = new ViewUsuario();
					frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewUsuario() {
		setResizable(false);
		setTitle("Usuarios");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 8, 49, 20);
		contentPane.add(lblNewLabel);
		
		jtfCodigo = new JTextField();
		jtfCodigo.setEditable(false);
		jtfCodigo.setBounds(10, 32, 58, 20);
		contentPane.add(jtfCodigo);
		jtfCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(81, 10, 502, 17);
		contentPane.add(lblNewLabel_1);
		
		jtfNome = new JTextField();
		jtfNome.setBounds(81, 32, 502, 20);
		contentPane.add(jtfNome);
		jtfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Login:");
		lblNewLabel_2.setBounds(10, 50, 267, 40);
		contentPane.add(lblNewLabel_2);
		
		jtfLogin = new JFormattedTextField();
		jtfLogin.setBounds(10, 86, 267, 20);
		contentPane.add(jtfLogin);
		jtfLogin.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Senha:");
		lblNewLabel_3.setBounds(287, 60, 296, 20);
		contentPane.add(lblNewLabel_3);
		
		jtfSenha = new JFormattedTextField();
		jtfSenha.setBounds(287, 86, 296, 20);
		contentPane.add(jtfSenha);
		jtfSenha.setColumns(10);
		
		jtUsuario = new JTable();
		jtUsuario.setFillsViewportHeight(true);
		jtUsuario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Login", "Senha"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtUsuario.getColumnModel().getColumn(0).setPreferredWidth(36);
		jtUsuario.getColumnModel().getColumn(1).setPreferredWidth(157);
		jtUsuario.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtUsuario.getColumnModel().getColumn(3).setPreferredWidth(69);
		jtUsuario.setBounds(10, 146, 573, 254);
		JScrollPane scrollPane = new JScrollPane(jtUsuario);
		scrollPane.setBounds(10, 146, 573, 254);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_4 = new JLabel("Pesquisar:");
		lblNewLabel_4.setBounds(10, 113, 72, 30);
		contentPane.add(lblNewLabel_4);
		
		jtfPesquisar = new JTextField();
		jtfPesquisar.setBounds(81, 118, 372, 20);
		contentPane.add(jtfPesquisar);
		jtfPesquisar.setColumns(10);
		
		JButton jbPesquisar = new JButton("Pesquisar");
		jbPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) ViewUsuario.this.jtUsuario.getModel();
				final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
				ViewUsuario.this.jtUsuario.setRowSorter(classificador);
				String texto = jtfPesquisar.getText();
				classificador.setRowFilter(RowFilter.regexFilter("(?i)"+texto, 1));
			}
		});
		jbPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jbPesquisar.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-pesquisar-18.png")));
		jbPesquisar.setBounds(463, 116, 120, 23);
		contentPane.add(jbPesquisar);
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUsuario.this.habilitarDesabilitarCampos(false);
				ViewUsuario.this.limparCampos();
			}
		});
		jbCancelar.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-cancelar-18.png")));
		jbCancelar.setBounds(10, 408, 114, 23);
		contentPane.add(jbCancelar);
		
		JButton jbNovo = new JButton("Novo");
		jbNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewUsuario.this.habilitarDesabilitarCampos(true);
				salvarAlterar = "salvar";
			}
		});
		jbNovo.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-documento-18.png")));
		jbNovo.setBounds(359, 408, 104, 23);
		contentPane.add(jbNovo);
		
		JButton jbAlterar = new JButton("Alterar");
		jbAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "alterar";
				int linha = ViewUsuario.this.jtUsuario.getSelectedRow();
				try {
					int codigoUsuario = (int) ViewUsuario.this.jtUsuario.getValueAt(linha, 0);
					modelUsuarios = controllerUsuarios.retornarUsuarioController(codigoUsuario);
					ViewUsuario.this.jtfCodigo.setText(String.valueOf(modelUsuarios.getIdUsuario()));
					ViewUsuario.this.jtfNome.setText(modelUsuarios.getUsuNome());
					ViewUsuario.this.jtfLogin.setText(String.valueOf(modelUsuarios.getUsuLogin()));
					ViewUsuario.this.jtfSenha.setText(String.valueOf(modelUsuarios.getUsuSenha()));
					ViewUsuario.this.habilitarDesabilitarCampos(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(ViewUsuario.this, "Código invalido ou nenhum registro selecionado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbAlterar.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-lápis-18.png")));
		jbAlterar.setBounds(245, 408, 104, 23);
		contentPane.add(jbAlterar);
		
		jbSalvar = new JButton("Salvar");
		jbSalvar.setEnabled(false);
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (salvarAlterar.equals("salvar")) {
					ViewUsuario.this.salvarUsuario();
				} else if (salvarAlterar.equals("alterar")){
					ViewUsuario.this.alterarUsuario();
				}
			}
		});
		jbSalvar.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-salvar-como-18.png")));
		jbSalvar.setBounds(479, 408, 104, 23);
		contentPane.add(jbSalvar);
		
		JButton jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = jtUsuario.getSelectedRow();
				int codigoUsuario = (int) jtUsuario.getValueAt(linha, 0);
				if (controllerUsuarios.excluirUsuarioController(codigoUsuario)) {
					JOptionPane.showMessageDialog(ViewUsuario.this, "Usuario excluido com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					ViewUsuario.this.carregarUsuarios();
					ViewUsuario.this.limparCampos();
					ViewUsuario.this.habilitarDesabilitarCampos(false);
				} else {
					JOptionPane.showMessageDialog(ViewUsuario.this, "Erro ao excluir o usuario!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbExcluir.setIcon(new ImageIcon(ViewUsuario.class.getResource("/imagens/icons8-lixo-18.png")));
		jbExcluir.setBounds(131, 408, 104, 23);
		contentPane.add(jbExcluir);
		
		carregarUsuarios();
		setLocationRelativeTo(null);
		habilitarDesabilitarCampos(false);
	}
	
		private void carregarUsuarios() {
			listaModelUsuarios = controllerUsuarios.retornarListaUsuarioController();			
			DefaultTableModel modelo = (DefaultTableModel) jtUsuario.getModel();
			modelo.setNumRows(0);
			int cont = listaModelUsuarios.size();
			for (int i = 0; i < cont; i++) {
				modelo.addRow(new Object[] {
					listaModelUsuarios.get(i).getIdUsuario(),
					listaModelUsuarios.get(i).getUsuNome(),
					listaModelUsuarios.get(i).getUsuLogin(),
					listaModelUsuarios.get(i).getUsuSenha()
				});
			}
		}
		
		private void salvarUsuario() {
			try {
				modelUsuarios.setIdUsuario(Integer.parseInt(ViewUsuario.this.jtfCodigo.getText()));
			} catch (NumberFormatException e) {
			}
			modelUsuarios.setUsuNome(ViewUsuario.this.jtfNome.getText());
			modelUsuarios.setUsuLogin(ViewUsuario.this.jtfLogin.getText());
			modelUsuarios.setUsuSenha(this.jtfSenha.getText());
			if(controllerUsuarios.salvarUsuarioController(modelUsuarios)>0) {
				JOptionPane.showMessageDialog(ViewUsuario.this, "Usuario cadastrado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				ViewUsuario.this.carregarUsuarios();
				ViewUsuario.this.limparCampos();
				ViewUsuario.this.habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewUsuario.this, "Erro ao cadastrar o Usuario!", "ERRO", JOptionPane.ERROR_MESSAGE);
			} 
		}
		
		private void alterarUsuario() {
			modelUsuarios.setUsuNome(ViewUsuario.this.jtfNome.getText());
			modelUsuarios.setUsuLogin(ViewUsuario.this.jtfLogin.getText());
			modelUsuarios.setUsuSenha(this.jtfSenha.getText());
			if(controllerUsuarios.alterarUsuarioController(modelUsuarios)) {
				JOptionPane.showMessageDialog(ViewUsuario.this, "Usuario alterado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				ViewUsuario.this.carregarUsuarios();
				ViewUsuario.this.limparCampos();
				ViewUsuario.this.habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewUsuario.this, "Erro ao alterar o Usuario!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void habilitarDesabilitarCampos(boolean condicao) {
			jbSalvar.setEnabled(condicao);
			jtfNome.setEditable(condicao);
			jtfLogin.setEditable(condicao);
			jtfSenha.setEditable(condicao);
		}
		
		private void limparCampos() {
			jtfCodigo.setText("");
			jtfNome.setText("");
			jtfLogin.setText("");
			jtfSenha.setText("");
		}
}

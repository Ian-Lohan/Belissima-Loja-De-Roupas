package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import controller.ControllerClientes;
import controller.ControllerProdutos;
import model.ModelClientes;
import model.ModelProdutos;
import java.awt.Font;

public class ViewCliente extends JFrame {

	private JPanel contentPane;
	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JTextField jtfEndereco;
	private JLabel lblNewLabel_3;
	private JTextField jtfBairro;
	private JLabel lblNewLabel_4;
	private JTextField jtfCidade;
	private JTextField jtfCep;
	private JTextField jtfTelefone;
	private JTable jtCliente;
	private JComboBox<String> jcbUf;
	private JButton jbSalvar;

	ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
	ControllerClientes controllerClientes = new ControllerClientes();
	ModelClientes modelClientes = new ModelClientes();
	String salvarAlterar;
	private JLabel lblNewLabel_8;
	private JTextField jtfPesquisar;
	private JButton jbPesquisar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCliente frame = new ViewCliente();
					frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ViewCliente() {
		setResizable(false);
		setTitle("Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 8, 49, 20);
		contentPane.add(lblNewLabel);
		
		jtfCodigo = new JTextField();
		jtfCodigo.setEditable(false);
		jtfCodigo.setBounds(10, 32, 56, 20);
		contentPane.add(jtfCodigo);
		jtfCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(80, 8, 503, 20);
		contentPane.add(lblNewLabel_1);
		
		jtfNome = new JTextField();
		jtfNome.setEditable(false);
		jtfNome.setBounds(80, 32, 503, 20);
		contentPane.add(jtfNome);
		jtfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Endereço:");
		lblNewLabel_2.setBounds(10, 60, 67, 20);
		contentPane.add(lblNewLabel_2);
		
		jtfEndereco = new JTextField();
		jtfEndereco.setEditable(false);
		jtfEndereco.setBounds(10, 85, 173, 20);
		contentPane.add(jtfEndereco);
		jtfEndereco.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Bairro:");
		lblNewLabel_3.setBounds(191, 62, 171, 17);
		contentPane.add(lblNewLabel_3);
		
		jtfBairro = new JTextField();
		jtfBairro.setEditable(false);
		jtfBairro.setBounds(191, 85, 159, 20);
		contentPane.add(jtfBairro);
		jtfBairro.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Cidade:");
		lblNewLabel_4.setBounds(360, 62, 159, 17);
		contentPane.add(lblNewLabel_4);
		
		jtfCidade = new JTextField();
		jtfCidade.setEditable(false);
		jtfCidade.setBounds(360, 85, 159, 20);
		contentPane.add(jtfCidade);
		jtfCidade.setColumns(10);
		
		jcbUf = new JComboBox<String>();
		jcbUf.setEnabled(false);
		jcbUf.setModel(new DefaultComboBoxModel<>(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		jcbUf.setBounds(529, 83, 54, 22);
		contentPane.add(jcbUf);
		
		JLabel lblNewLabel_5 = new JLabel("UF:");
		lblNewLabel_5.setBounds(529, 61, 61, 18);
		contentPane.add(lblNewLabel_5);
		
		jtfCep = new JTextField();
		jtfCep.setEditable(false);
		jtfCep.setBounds(13, 137, 96, 20);
		contentPane.add(jtfCep);
		jtfCep.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("CEP:");
		lblNewLabel_6.setBounds(10, 112, 49, 20);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Telefone:");
		lblNewLabel_7.setBounds(120, 114, 157, 17);
		contentPane.add(lblNewLabel_7);
		
		jtfTelefone = new JTextField();
		jtfTelefone.setEditable(false);
		jtfTelefone.setBounds(120, 137, 249, 20);
		contentPane.add(jtfTelefone);
		jtfTelefone.setColumns(10);
		
		jtCliente = new JTable();
		jtCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome do Cliente", "Endere\u00E7o", "Bairro", "Telefone"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		jtCliente.getColumnModel().getColumn(0).setPreferredWidth(44);
		jtCliente.getColumnModel().getColumn(1).setPreferredWidth(108);
		jtCliente.getColumnModel().getColumn(4).setPreferredWidth(91);
		jtCliente.setBounds(10, 169, 573, 235);
		JScrollPane scrollPane = new JScrollPane(jtCliente);
		scrollPane.setBounds(10, 199, 573, 234);
		contentPane.add(scrollPane);
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCliente.this.habilitarDesabilitarCampos(false);
				ViewCliente.this.limparCampos();
			}
		});
		jbCancelar.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-cancelar-18.png")));
		jbCancelar.setBounds(10, 438, 114, 23);
		contentPane.add(jbCancelar);
		
		JButton jbNovo = new JButton("Novo");
		jbNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCliente.this.habilitarDesabilitarCampos(true);
				salvarAlterar = "salvar";
			}
		});
		jbNovo.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-documento-18.png")));
		jbNovo.setBounds(359, 438, 104, 23);
		contentPane.add(jbNovo);
		
		JButton jbAlterar = new JButton("Alterar");
		jbAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "alterar";
				int linha = ViewCliente.this.jtCliente.getSelectedRow();
				try {
					int codigoCliente = (int) ViewCliente.this.jtCliente.getValueAt(linha, 0);
					modelClientes = controllerClientes.retornarClienteController(codigoCliente);
					ViewCliente.this.jtfCodigo.setText(String.valueOf(modelClientes.getIdCliente()));
					ViewCliente.this.jtfNome.setText(modelClientes.getCliNome());
					ViewCliente.this.jtfEndereco.setText(modelClientes.getCliEndereco());
					ViewCliente.this.jtfBairro.setText(modelClientes.getCliBairro());
					ViewCliente.this.jtfCidade.setText(modelClientes.getCliCidade());
					String cliUf = modelClientes.getCliUf();
					int index = -1;
					for (int i = 0; i < jcbUf.getItemCount(); i++) {
					  if (cliUf.equals(jcbUf.getItemAt(i))) {
					    index = i;
					    break;
					  }
					}
					ViewCliente.this.jcbUf.setSelectedIndex(index);
					ViewCliente.this.jtfCep.setText(modelClientes.getCliCep());
					ViewCliente.this.jtfTelefone.setText(modelClientes.getCliTelefone());
					ViewCliente.this.habilitarDesabilitarCampos(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(ViewCliente.this, "Código invalido ou nenhum registro selecionado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbAlterar.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-lápis-18.png")));
		jbAlterar.setBounds(245, 438, 104, 23);
		contentPane.add(jbAlterar);
		
		JButton jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = jtCliente.getSelectedRow();
				int codigoCliente = (int) jtCliente.getValueAt(linha, 0);
				if (controllerClientes.excluirClienteController(codigoCliente)) {
					JOptionPane.showMessageDialog(ViewCliente.this, "Cliente excluido com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					ViewCliente.this.carregarClientes();
					ViewCliente.this.limparCampos();
					ViewCliente.this.habilitarDesabilitarCampos(false);
				} else {
					JOptionPane.showMessageDialog(ViewCliente.this, "Erro ao excluir o cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbExcluir.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-lixo-18.png")));
		jbExcluir.setBounds(131, 438, 104, 23);
		contentPane.add(jbExcluir);
		
		jbSalvar = new JButton("Salvar");
		jbSalvar.setEnabled(false);
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarCliente();
			}
		});
		jbSalvar.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-salvar-como-18.png")));
		jbSalvar.setBounds(479, 438, 104, 23);
		contentPane.add(jbSalvar);
		
		lblNewLabel_8 = new JLabel("Pesquisar:");
		lblNewLabel_8.setBounds(10, 165, 72, 25);
		contentPane.add(lblNewLabel_8);
		
		jtfPesquisar = new JTextField();
		jtfPesquisar.setColumns(10);
		jtfPesquisar.setBounds(80, 167, 372, 20);
		contentPane.add(jtfPesquisar);
		
		jbPesquisar = new JButton("Pesquisar");
		jbPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) ViewCliente.this.jtCliente.getModel();
				final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
				ViewCliente.this.jtCliente.setRowSorter(classificador);
				String texto = jtfPesquisar.getText();
				classificador.setRowFilter(RowFilter.regexFilter("(?i)"+texto, 1));
			}
		});
		jbPesquisar.setIcon(new ImageIcon(ViewCliente.class.getResource("/imagens/icons8-pesquisar-18.png")));
		jbPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jbPesquisar.setBounds(462, 165, 120, 23);
		contentPane.add(jbPesquisar);
		
		carregarClientes();
		setLocationRelativeTo(null);
	}
	
	private void carregarClientes() {
		listaModelClientes = controllerClientes.retornarListaClienteController();			
		DefaultTableModel modelo = (DefaultTableModel) jtCliente.getModel();
		modelo.setNumRows(0);
		int cont = listaModelClientes.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[] {
				listaModelClientes.get(i).getIdCliente(),
				listaModelClientes.get(i).getCliNome(),
				listaModelClientes.get(i).getCliEndereco(),
				listaModelClientes.get(i).getCliBairro(),
				listaModelClientes.get(i).getCliTelefone()
			});
		}
	}
	
	private void salvarCliente() {
		try {
			modelClientes.setIdCliente(Integer.parseInt(ViewCliente.this.jtfCodigo.getText()));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		modelClientes.setCliNome(ViewCliente.this.jtfNome.getText());
		modelClientes.setCliEndereco(ViewCliente.this.jtfEndereco.getText());
		modelClientes.setCliBairro(ViewCliente.this.jtfBairro.getText());
		modelClientes.setCliCidade(ViewCliente.this.jtfCidade.getText());
		modelClientes.setCliUf(ViewCliente.this.jcbUf.getSelectedItem().toString());
		modelClientes.setCliCep(ViewCliente.this.jtfCep.getText());
		modelClientes.setCliTelefone(ViewCliente.this.jtfTelefone.getText());
		if(salvarAlterar.equals("salvar")) {
			if(controllerClientes.salvarClienteController(modelClientes)>0){
				JOptionPane.showMessageDialog(ViewCliente.this, "Registro salvo com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				carregarClientes();
				limparCampos();
				habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewCliente.this, "Erro ao salvar cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			if(controllerClientes.alterarClienteController(modelClientes)){
				JOptionPane.showMessageDialog(ViewCliente.this, "Registro alterado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				carregarClientes();
				limparCampos();
				habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewCliente.this, "Erro ao alterar cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void alterarCliente() {
		modelClientes.setCliNome(ViewCliente.this.jtfNome.getText());
		modelClientes.setCliEndereco(ViewCliente.this.jtfEndereco.getText());
		modelClientes.setCliBairro(ViewCliente.this.jtfBairro.getText());
		modelClientes.setCliCidade(ViewCliente.this.jtfCidade.getText());
		modelClientes.setCliCep(ViewCliente.this.jcbUf.getSelectedItem().toString());
		modelClientes.setCliCep(ViewCliente.this.jtfCep.getText());
		modelClientes.setCliTelefone(ViewCliente.this.jtfTelefone.getText());
		if(controllerClientes.alterarClienteController(modelClientes)) {
			JOptionPane.showMessageDialog(ViewCliente.this, "Cliente alterado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
			ViewCliente.this.carregarClientes();
			ViewCliente.this.limparCampos();
			ViewCliente.this.habilitarDesabilitarCampos(false);
		} else {
			JOptionPane.showMessageDialog(ViewCliente.this, "Erro ao alterar o cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void habilitarDesabilitarCampos(boolean condicao) {
		jbSalvar.setEnabled(condicao);
		jtfNome.setEditable(condicao);
		jtfEndereco.setEditable(condicao);
		jtfBairro.setEditable(condicao);
		jtfCidade.setEditable(condicao);
		jcbUf.setEnabled(condicao);
		jtfCep.setEditable(condicao);
		jtfTelefone.setEditable(condicao);
	}
	
	private void limparCampos() {
		jtfCodigo.setText("");
		jtfNome.setText("");
		jtfEndereco.setText("");
		jtfBairro.setText("");
		jtfCidade.setText("");
		jcbUf.setSelectedIndex(-1);
		jtfCep.setText("");
		jtfTelefone.setText("");
	}
}

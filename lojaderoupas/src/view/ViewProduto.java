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
import model.ModelProdutos;
import controller.ControllerProdutos;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class ViewProduto extends JFrame {
	
	private JPanel contentPane;
	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JFormattedTextField jtfEstoque;
	private JFormattedTextField jtfValor;
	private JTextField jtfPesquisar;
	private JTable jtProduto;
	private JButton jbSalvar;
	
	ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ModelProdutos modelProdutos = new ModelProdutos();
	String salvarAlterar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewProduto frame = new ViewProduto();
					frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewProduto() {
		setResizable(false);
		setTitle("Produtos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 607, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Código:");
		lblNewLabel.setBounds(10, 3, 49, 31);
		contentPane.add(lblNewLabel);
		
		jtfCodigo = new JTextField();
		jtfCodigo.setEditable(false);
		jtfCodigo.setBounds(10, 32, 58, 20);
		contentPane.add(jtfCodigo);
		jtfCodigo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Nome:");
		lblNewLabel_1.setBounds(81, 3, 502, 31);
		contentPane.add(lblNewLabel_1);
		
		jtfNome = new JTextField();
		jtfNome.setBounds(81, 32, 502, 20);
		contentPane.add(jtfNome);
		jtfNome.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Estoque:");
		lblNewLabel_2.setBounds(10, 55, 96, 37);
		contentPane.add(lblNewLabel_2);
		
		jtfEstoque = new JFormattedTextField();
		jtfEstoque.setBounds(10, 86, 96, 20);
		contentPane.add(jtfEstoque);
		jtfEstoque.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Valor:");
		lblNewLabel_3.setBounds(121, 55, 49, 31);
		contentPane.add(lblNewLabel_3);
		
		jtfValor = new JFormattedTextField();
		jtfValor.setBounds(121, 86, 96, 20);
		contentPane.add(jtfValor);
		jtfValor.setColumns(10);
		
		jtProduto = new JTable();
		jtProduto.setFillsViewportHeight(true);
		jtProduto.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Código", "Nome", "Valor", "Quantidade"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		jtProduto.getColumnModel().getColumn(0).setPreferredWidth(36);
		jtProduto.getColumnModel().getColumn(1).setPreferredWidth(157);
		jtProduto.getColumnModel().getColumn(2).setPreferredWidth(60);
		jtProduto.getColumnModel().getColumn(3).setPreferredWidth(69);
		jtProduto.setBounds(10, 146, 573, 254);
		JScrollPane scrollPane = new JScrollPane(jtProduto);
		scrollPane.setBounds(10, 146, 573, 254);
		contentPane.add(scrollPane);
		
		JLabel lblNewLabel_4 = new JLabel("Pesquisar:");
		lblNewLabel_4.setBounds(10, 113, 72, 31);
		contentPane.add(lblNewLabel_4);
		
		jtfPesquisar = new JTextField();
		jtfPesquisar.setBounds(81, 118, 372, 20);
		contentPane.add(jtfPesquisar);
		jtfPesquisar.setColumns(10);
		
		JButton jbPesquisar = new JButton("Pesquisar");
		jbPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) ViewProduto.this.jtProduto.getModel();
				final TableRowSorter<TableModel> classificador = new TableRowSorter<>(modelo);
				ViewProduto.this.jtProduto.setRowSorter(classificador);
				String texto = jtfPesquisar.getText();
				classificador.setRowFilter(RowFilter.regexFilter("(?i)"+texto, 1));
			}
		});
		jbPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		jbPesquisar.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-pesquisar-18.png")));
		jbPesquisar.setBounds(463, 116, 120, 23);
		contentPane.add(jbPesquisar);
		
		JButton jbCancelar = new JButton("Cancelar");
		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewProduto.this.habilitarDesabilitarCampos(false);
				ViewProduto.this.limparCampos();
			}
		});
		jbCancelar.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-cancelar-18.png")));
		jbCancelar.setBounds(10, 408, 114, 23);
		contentPane.add(jbCancelar);
		
		JButton jbNovo = new JButton("Novo");
		jbNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewProduto.this.habilitarDesabilitarCampos(true);
				salvarAlterar = "salvar";
			}
		});
		jbNovo.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-documento-18.png")));
		jbNovo.setBounds(359, 408, 104, 23);
		contentPane.add(jbNovo);
		
		JButton jbAlterar = new JButton("Alterar");
		jbAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvarAlterar = "alterar";
				int linha = ViewProduto.this.jtProduto.getSelectedRow();
				try {
					int codigoProduto = (int) ViewProduto.this.jtProduto.getValueAt(linha, 0);
					modelProdutos = controllerProdutos.retornarProdutoController(codigoProduto);
					ViewProduto.this.jtfCodigo.setText(String.valueOf(modelProdutos.getIdProduto()));
					ViewProduto.this.jtfNome.setText(modelProdutos.getProNome());
					ViewProduto.this.jtfValor.setText(String.valueOf(modelProdutos.getProValor()));
					ViewProduto.this.jtfEstoque.setText(String.valueOf(modelProdutos.getProEstoque()));
					ViewProduto.this.habilitarDesabilitarCampos(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(ViewProduto.this, "Código invalido ou nenhum registro selecionado.", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbAlterar.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-lápis-18.png")));
		jbAlterar.setBounds(245, 408, 104, 23);
		contentPane.add(jbAlterar);
		
		jbSalvar = new JButton("Salvar");
		jbSalvar.setEnabled(false);
		jbSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (salvarAlterar.equals("salvar")) {
					ViewProduto.this.salvarProduto();
				} else if (salvarAlterar.equals("alterar")){
					ViewProduto.this.alterarProduto();
				}
			}
		});
		jbSalvar.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-salvar-como-18.png")));
		jbSalvar.setBounds(479, 408, 104, 23);
		contentPane.add(jbSalvar);
		
		JButton jbExcluir = new JButton("Excluir");
		jbExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linha = jtProduto.getSelectedRow();
				int codigoProduto = (int) jtProduto.getValueAt(linha, 0);
				if (controllerProdutos.excluirProdutoController(codigoProduto)) {
					JOptionPane.showMessageDialog(ViewProduto.this, "Produto excluido com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					ViewProduto.this.carregarProdutos();
					ViewProduto.this.limparCampos();
					ViewProduto.this.habilitarDesabilitarCampos(false);
				} else {
					JOptionPane.showMessageDialog(ViewProduto.this, "Erro ao excluir o produto!", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		jbExcluir.setIcon(new ImageIcon(ViewProduto.class.getResource("/imagens/icons8-lixo-18.png")));
		jbExcluir.setBounds(131, 408, 104, 23);
		contentPane.add(jbExcluir);
		
		carregarProdutos();
		setLocationRelativeTo(null);
		habilitarDesabilitarCampos(false);
	}
	
		private void carregarProdutos() {
			listaModelProdutos = controllerProdutos.retornarListaProdutoController();			
			DefaultTableModel modelo = (DefaultTableModel) jtProduto.getModel();
			modelo.setNumRows(0);
			int cont = listaModelProdutos.size();
			for (int i = 0; i < cont; i++) {
				modelo.addRow(new Object[] {
					listaModelProdutos.get(i).getIdProduto(),
					listaModelProdutos.get(i).getProNome(),
					listaModelProdutos.get(i).getProValor(),
					listaModelProdutos.get(i).getProEstoque()
				});
			}
		}
		
		private void salvarProduto() {
			modelProdutos.setProNome(ViewProduto.this.jtfNome.getText());
			modelProdutos.setProEstoque(Integer.parseInt(ViewProduto.this.jtfEstoque.getText()));
			modelProdutos.setProValor(Double.parseDouble(this.jtfValor.getText().replace(',', '.')) );
			if(controllerProdutos.salvarProdutoController(modelProdutos)>0) {
				JOptionPane.showMessageDialog(ViewProduto.this, "Produto cadastrado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				ViewProduto.this.carregarProdutos();
				ViewProduto.this.limparCampos();
				ViewProduto.this.habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewProduto.this, "Erro ao cadastrar o produdo!", "ERRO", JOptionPane.ERROR_MESSAGE);
			} 
		}
		
		private void alterarProduto() {
			modelProdutos.setProNome(ViewProduto.this.jtfNome.getText());
			modelProdutos.setProEstoque(Integer.parseInt(ViewProduto.this.jtfEstoque.getText()));
			modelProdutos.setProValor(Double.parseDouble(this.jtfValor.getText().replace(',', '.')) );
			if(controllerProdutos.alterarProdutoController(modelProdutos)) {
				JOptionPane.showMessageDialog(ViewProduto.this, "Produto alterado com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				ViewProduto.this.carregarProdutos();
				ViewProduto.this.limparCampos();
				ViewProduto.this.habilitarDesabilitarCampos(false);
			} else {
				JOptionPane.showMessageDialog(ViewProduto.this, "Erro ao alterar o produdo!", "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		private void habilitarDesabilitarCampos(boolean condicao) {
			jbSalvar.setEnabled(condicao);
			jtfNome.setEditable(condicao);
			jtfEstoque.setEditable(condicao);
			jtfValor.setEditable(condicao);
		}
		
		private void limparCampos() {
			jtfCodigo.setText("");
			jtfNome.setText("");
			jtfEstoque.setText("");
			jtfValor.setText("");
		}
}

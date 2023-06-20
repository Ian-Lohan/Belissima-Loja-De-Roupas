package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.ControllerProdutos;
import model.ModelProdutos;
import model.ModelVendas;
import controller.ControllerVendas;
import model.ModelVendasProdutos;
import model.ModelSessaoUsuario;
import util.Datas;
import util.Mascaras;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import controller.ControllerVendasProdutos;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import view.ViewPagamentoPDV;
import view.ViewProdutosPDV;
import javax.swing.JButton;
import model.ModelClientes;
import controller.ControllerClientes;

public class ViewPDV extends JFrame {

	private JPanel contentPane;
	private JTable jtProdutos;
	private JTextField jtfCodigoProduto;
	private JTextField jtfValorTotal;
	private ControllerProdutos controllerProdutos = new ControllerProdutos();
	private ModelProdutos modelProdutos = new ModelProdutos();
	private ModelVendas modelVendas = new ModelVendas();
	private ControllerVendas controllerVendas = new ControllerVendas();
	private ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
	private ModelClientes modelClientes = new ModelClientes();
	private ControllerClientes controllerClientes = new ControllerClientes();
	private ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
	private ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	private Datas datas = new Datas();
	private Mascaras mascaras = new Mascaras();
	private ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
	private ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	private ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();
	private JLabel jlOperador;
	private JLabel jlCaixa;
	private JLabel jlStatus;
	private JButton jbtPagamento;
	int quantidade;
	private ViewPagamentoPDV viewPagamentoPDV;
	private ViewProdutosPDV viewProdutosPDV;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPDV frame = new ViewPDV();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewPDV() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				jtfCodigoProduto.requestFocus();
			}
		});
		viewPagamentoPDV = new ViewPagamentoPDV(this, true);

		viewPagamentoPDV.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		        if (viewPagamentoPDV.isPago()) {
		            finalizarVenda();
		        } else {
		            viewPagamentoPDV = new ViewPagamentoPDV(ViewPDV.this, true);
		            limparTela();
		        }
		    }
		});

		setTitle("PDV");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu_1 = new JMenu("Arquivos");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem jmiSair = new JMenuItem("Sair");
		jmiSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		jmiSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0));
		mnNewMenu_1.add(jmiSair);
		
		JMenu mnNewMenu = new JMenu("Comandos");
		menuBar.add(mnNewMenu);
		
		JMenuItem jmiAlterarQuantidade = new JMenuItem("Alterar Quantidade");
		jmiAlterarQuantidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantidade = Integer.parseInt(JOptionPane.showInputDialog("Informe a quantidade!"));
				System.err.println("A quantidade é: " + quantidade);
			}
		});
		
		JMenuItem jmiExcluirProduto = new JMenuItem("Excluir Produto");
		jmiExcluirProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantLinha = jtProdutos.getRowCount();
				if (quantLinha < 1) {
					JOptionPane.showMessageDialog(ViewPDV.this, "Não existem items para excluir!", "ERRO", JOptionPane.ERROR_MESSAGE);
				} else {
					DefaultTableModel modelo = (DefaultTableModel) jtProdutos.getModel();
					int linha = Integer.parseInt(JOptionPane.showInputDialog("Informe o item que deseja excluir."));
					modelo.removeRow(linha-1);
					verificarConteudo();
					jtfValorTotal.setText(somarValorTotal()+"");
					for (int i = 0; i < quantLinha; i++) {
						modelo.setValueAt(i+1, i, 0);
					}
				}	
			}
		});
		jmiExcluirProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
		mnNewMenu.add(jmiExcluirProduto);
		jmiAlterarQuantidade.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
		mnNewMenu.add(jmiAlterarQuantidade);
		
		JMenuItem jmiFinalizarVenda = new JMenuItem("Finalizar Venda");
		jmiFinalizarVenda.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	try {
		    		viewPagamentoPDV.setValorTotal(Float.parseFloat(jtfValorTotal.getText()));
			        viewPagamentoPDV.setarValorTotal();
			        viewPagamentoPDV.setVisible(true);
			        viewPagamentoPDV.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosed(WindowEvent e) {
			                System.out.println(viewPagamentoPDV.getValorTotal());
			                System.out.println(viewPagamentoPDV.getValorRecebido());
			                System.out.println(viewPagamentoPDV.getDesconto());
			                System.out.println(viewPagamentoPDV.getTroco());
			                System.out.println(viewPagamentoPDV.getFormaPagamento());
					        if (viewPagamentoPDV.isPago()) {
					        	finalizarVenda();
					        }
			            }
			        });
		    	} catch (Exception e1) {
		    		JOptionPane.showMessageDialog(ViewPDV.this, "Nenhum produto adicionado!");
		    	}
		    }
		});

		jmiFinalizarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0));
		mnNewMenu.add(jmiFinalizarVenda);
		
		JMenuItem jmiPesquisarProdutos = new JMenuItem("Pesquisar Produtos");
		jmiPesquisarProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewProdutosPDV = new ViewProdutosPDV(ViewPDV.this);
		        viewProdutosPDV.setVisible(true);
			}
		});
		jmiPesquisarProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mnNewMenu.add(jmiPesquisarProdutos);
		
		JMenuItem jmiCancelarVenda = new JMenuItem("Cancelar Venda");
		jmiCancelarVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});
		jmiCancelarVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0));
		mnNewMenu.add(jmiCancelarVenda);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(213, 186, 174));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		
		jtProdutos = new JTable();
		jtProdutos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Item", "C\u00F3digo", "Nome", "Quantidade", "Valor Un\u00EDt.", "Valor Total"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Integer.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		jtProdutos.getColumnModel().getColumn(0).setPreferredWidth(15);
		jtProdutos.getColumnModel().getColumn(1).setPreferredWidth(15);
		jtProdutos.getColumnModel().getColumn(2).setPreferredWidth(200);
		jtProdutos.getColumnModel().getColumn(3).setPreferredWidth(15);
		jtProdutos.getColumnModel().getColumn(4).setPreferredWidth(20);
		jtProdutos.getColumnModel().getColumn(5).setPreferredWidth(20);
		scrollPane.setViewportView(jtProdutos);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(249, 249, 249));
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Caixa:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 11, 77, 27);
		panel.add(lblNewLabel_1);
		
		jlCaixa = new JLabel("1");
		jlCaixa.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlCaixa.setBounds(98, 13, 133, 23);
		panel.add(jlCaixa);
		
		jlOperador = new JLabel("Operador");
		jlOperador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jlOperador.setBounds(98, 39, 132, 23);
		panel.add(jlOperador);
		
		JLabel lblNewLabel_1_1 = new JLabel("Operador:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(10, 37, 101, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Status:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1_1.setBounds(10, 62, 101, 27);
		panel.add(lblNewLabel_1_1_1);
		
		jlStatus = new JLabel("Status");
		jlStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlStatus.setBounds(98, 64, 133, 23);
		panel.add(jlStatus);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(249, 249, 249));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(ViewPDV.class.getResource("/imagens/belissimaLogoMenor.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNewLabel)
					.addContainerGap(332, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(19)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(244, 244, 244));
		
		jtfCodigoProduto = new JTextField();
		jtfCodigoProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pegarConteudo();
			}
		});
		jtfCodigoProduto.requestFocus();
		jtfCodigoProduto.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(249, 249, 249));
		
		JLabel lblNewLabel_4 = new JLabel("Valor Total");
		lblNewLabel_4.setBounds(10, 11, 220, 33);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		
		jtfValorTotal = new JTextField();
		jtfValorTotal.setBounds(10, 41, 220, 79);
		jtfValorTotal.setEditable(false);
		jtfValorTotal.setFont(new Font("Tahoma", Font.BOLD, 32));
		jtfValorTotal.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Comandos");
		lblNewLabel_5.setBounds(10, 131, 220, 22);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel_6 = new JLabel("F3 - Quantidade");
		lblNewLabel_6.setBounds(10, 188, 220, 33);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel_6_1 = new JLabel("F4 - Finalizar Venda");
		lblNewLabel_6_1.setBounds(10, 219, 220, 33);
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel_6_2 = new JLabel("F5 - Pesquisar Produtos");
		lblNewLabel_6_2.setBounds(10, 252, 220, 33);
		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel_6_2_1 = new JLabel("F6 - Cancelar Venda");
		lblNewLabel_6_2_1.setBounds(10, 283, 220, 33);
		lblNewLabel_6_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE))
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
					.addGap(5))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
							.addGap(11)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)))
					.addGap(6))
		);
		
		JLabel lblNewLabel_6_3 = new JLabel("F2 - Excluir Produto");
		lblNewLabel_6_3.setBounds(10, 159, 220, 28);
		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel_3.setLayout(null);
		panel_3.add(lblNewLabel_4);
		panel_3.add(jtfValorTotal);
		panel_3.add(lblNewLabel_5);
		panel_3.add(lblNewLabel_6);
		panel_3.add(lblNewLabel_6_1);
		panel_3.add(lblNewLabel_6_2_1);
		panel_3.add(lblNewLabel_6_2);
		panel_3.add(lblNewLabel_6_3);
		
		jbtPagamento = new JButton("PAGAMENTO");
		jbtPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		    		viewPagamentoPDV.setValorTotal(Float.parseFloat(jtfValorTotal.getText()));
			        viewPagamentoPDV.setarValorTotal();
			        viewPagamentoPDV.setVisible(true);
			        viewPagamentoPDV.addWindowListener(new WindowAdapter() {
			            @Override
			            public void windowClosed(WindowEvent e) {
			                System.out.println(viewPagamentoPDV.getValorTotal());
			                System.out.println(viewPagamentoPDV.getValorRecebido());
			                System.out.println(viewPagamentoPDV.getDesconto());
			                System.out.println(viewPagamentoPDV.getTroco());
			                System.out.println(viewPagamentoPDV.getFormaPagamento());
					        if (viewPagamentoPDV.isPago()) {
					        	finalizarVenda();
					        }
			            }
			        });
		    	} catch (Exception e1) {
		    		JOptionPane.showMessageDialog(ViewPDV.this, "Nenhum produto adicionado!");
		    	}
			}
		});
		
		jbtPagamento.setEnabled(false);
		jbtPagamento.setFont(new Font("Tahoma", Font.BOLD, 22));
		jbtPagamento.setBounds(10, 424, 220, 71);
		panel_3.add(jbtPagamento);
		
		JLabel lblNewLabel_6_2_1_1 = new JLabel("F9 - Sair");
		lblNewLabel_6_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6_2_1_1.setBounds(10, 315, 220, 33);
		panel_3.add(lblNewLabel_6_2_1_1);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(jtfCodigoProduto, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
					.addGap(10))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(5)
					.addComponent(jtfCodigoProduto, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
		setLocationRelativeTo(null);
		quantidade = 1;
		setarOperador();
		limparTela();
		jtfCodigoProduto.requestFocus();
	}
	
	private void pegarConteudo() {
		jlStatus.setText("VENDA ABERTA");
		jlStatus.setForeground(new Color(13, 217, 44));
		DefaultTableModel modelo = (DefaultTableModel) jtProdutos.getModel();
		try {
			modelProdutos = controllerProdutos.retornarProdutoController(Integer.parseInt(jtfCodigoProduto.getText()));
			modelo.addRow(new Object[] {
				modelo.getRowCount()+1,
				modelProdutos.getIdProduto(),
				modelProdutos.getProNome(),
				quantidade,
				modelProdutos.getProValor(),
				modelProdutos.getProValor() * quantidade
			});
			jtfValorTotal.setText(somarValorTotal()+"");
			jtfCodigoProduto.setText("");
			quantidade = 1;
			verificarConteudo();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(ViewPDV.this, "Produto não encontrado!", "ERRO", JOptionPane.ERROR_MESSAGE);
			jtfCodigoProduto.setText("");
		}
	}
	
	private float somarValorTotal() {
		float soma = 0, valor = 0;
		int cont = jtProdutos.getRowCount();
		for (int i = 0; i < cont; i++) {
			valor = Float.parseFloat(String.valueOf(jtProdutos.getValueAt(i, 5)));
			soma += valor;
		}
		return soma;
	}
	
	private void setarOperador() {
		jlOperador.setText(ModelSessaoUsuario.nome);
	}
	
	private void finalizarVenda() {
		int cont = jtProdutos.getRowCount();
		int codigoProduto = 0;
		int codigoVenda = 0;
		modelVendas = new ModelVendas();
		modelVendas.setCliente(1);
		try {
			modelVendas.setVenDataVenda(datas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
		} catch (Exception e1) {
			Logger.getLogger(ViewPDV.class.getName()).log(Level.SEVERE, null, e1);
		}
		modelVendas.setVenValorBruto(mascaras.converterArredondar2CasaPontoDouble(Double.parseDouble(jtfValorTotal.getText())));
		modelVendas.setVenValorLiquido(mascaras.converterArredondar2CasaPontoDouble(viewPagamentoPDV.getValorRecebido()));
		modelVendas.setVenDesconto(mascaras.converterArredondar2CasaPontoDouble(viewPagamentoPDV.getDesconto()));
		codigoVenda = controllerVendas.salvarVendaController(modelVendas);
		for (int i = 0; i < cont; i++) {
        	codigoProduto = (int) jtProdutos.getValueAt(i, 1);
    		modelVendasProdutos = new ModelVendasProdutos();
    		modelProdutos = new ModelProdutos();
    		modelVendasProdutos.setProduto(codigoProduto);
    		modelVendasProdutos.setVendas(codigoVenda);
    		modelVendasProdutos.setVenProValor((double) jtProdutos.getValueAt(i, 4));
    		modelVendasProdutos.setVenProQuantidade((int) jtProdutos.getValueAt(i, 3));
    		modelProdutos.setIdProduto(codigoProduto);
    		int quantidadeVendida = ((int) jtProdutos.getValueAt(i, 3));
    		ModelProdutos produto = controllerProdutos.retornarProdutoController(codigoProduto);
    		int estoqueNovo = produto.getProEstoque() - quantidadeVendida;
    		System.out.println(estoqueNovo);
    		produto.setProEstoque(estoqueNovo);
    		modelProdutos.setProEstoque(estoqueNovo);
    		listaModelVendasProdutos.add(modelVendasProdutos);
    		listaModelProdutos.add(modelProdutos);
    	}
		if (controllerVendasProdutos.salvarListaVendaProdutosController(listaModelVendasProdutos)) {
			controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
			quantidade = 1;
			limparTela();
		} else {
			JOptionPane.showMessageDialog(ViewPDV.this, "Erro ao salvar produtos da venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limparTela() {
		jtfValorTotal.setText("");
		DefaultTableModel modelo = (DefaultTableModel) jtProdutos.getModel();
		modelo.setNumRows(0);
		jlStatus.setText("CAIXA LIVRE");
		jlStatus.setForeground(new Color(255, 204, 20));
		jtfCodigoProduto.requestFocus();
		jbtPagamento.setEnabled(false);
	}
	
	private void verificarConteudo() {
		DefaultTableModel modelo = (DefaultTableModel) jtProdutos.getModel();
	    if (modelo.getRowCount() == 0) {
	        jbtPagamento.setEnabled(false);
	    } else {
	        jbtPagamento.setEnabled(true);
	    }	
	}
}

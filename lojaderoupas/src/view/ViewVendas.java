package view;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.util.ArrayList;

import model.ModelClientes;
import model.ModelProdutos;
import model.ModelVendas;
import model.ModelVendasClientes;
import model.ModelVendasProdutos;
import model.ModelProdutosVendasProdutos;
import controller.ControllerClientes;
import controller.ControllerProdutos;
import controller.ControllerVendas;
import controller.ControllerVendasClientes;
import controller.ControllerVendasProdutos;
import controller.ControllerProdutosVendasProdutos;
import util.Datas;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class ViewVendas extends JFrame {

	private JPanel contentPane;
	private JTextField jtfCodigoCliente;
	private JTextField jtfNumeroVenda;
	private JTextField jtfCodigoProduto;
	private JTextField jtfQuantidade;
	private JTable jtProdutosVendas;
	private JTextField jtfValorTotal;
	private JTextField jtfDesconto;
	private JTextField jtfPesquisa;
	private JTable jtVendas;
	private JComboBox jcbNomeCliente;
	private JComboBox jcbNomeProduto;
	private JTabbedPane tabbedPane;
	private JButton jbSalvar;
	private JButton jbAdicionar;
	private JButton jbCancelar;
	private JButton jbRemover;
	
	ModelClientes modelClientes = new ModelClientes();
	ControllerClientes controllerClientes = new ControllerClientes();
	ArrayList<ModelClientes> listaModelClientes = new ArrayList<>();
	ModelProdutos modelProdutos = new ModelProdutos();
	ControllerProdutos controllerProdutos = new ControllerProdutos();
	ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
	ModelVendas modelVendas = new ModelVendas();
	ControllerVendas controllerVendas = new ControllerVendas();
	ArrayList<ModelVendas> listaModelVendas = new ArrayList<>();
	ControllerVendasClientes controllerVendasClientes = new ControllerVendasClientes();
	ArrayList<ModelVendasClientes> listaModelVendasClientes = new ArrayList<>();
	ModelVendasProdutos modelVendasProdutos = new ModelVendasProdutos();
	ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
	ArrayList<ModelVendasProdutos> listaModelVendasProdutos = new ArrayList<>();
	ModelProdutosVendasProdutos modelProdutosVendasProdutos = new ModelProdutosVendasProdutos();
	ControllerProdutosVendasProdutos controllerProdutosVendasProdutos = new ControllerProdutosVendasProdutos();
	ArrayList<ModelProdutosVendasProdutos> listaModelProdutosVendasProdutos = new ArrayList<>();
	Datas datas = new Datas();
	String AlterarSalvar;
	Boolean condicao;
	int quantidade;
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                ViewVendas frame = new ViewVendas();
	                frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	public ViewVendas() {
		setResizable(false);
		setTitle("Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(669, 494);

        tabbedPane = new JTabbedPane();

        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();

        tabbedPane.addTab("Cadastro", tab1);
        tab1.setLayout(null);
        
        jtfCodigoCliente = new JTextField();
        jtfCodigoCliente.setEnabled(false);
        jtfCodigoCliente.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		String codigoClienteText = jtfCodigoCliente.getText();
        		if (!codigoClienteText.isEmpty()) {
        		    int codigoCliente = Integer.parseInt(codigoClienteText);
        		    modelClientes = controllerClientes.retornarClienteController(codigoCliente);
        		    jcbNomeCliente.setSelectedItem(modelClientes.getCliNome());
        		}
        	}
        });
        jtfCodigoCliente.setBounds(10, 27, 74, 20);
        tab1.add(jtfCodigoCliente);
        jtfCodigoCliente.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Cód. Cliente");
        lblNewLabel.setBounds(10, 8, 96, 14);
        tab1.add(lblNewLabel);
        
        jcbNomeCliente = new JComboBox();
        jcbNomeCliente.setEnabled(false);
        jcbNomeCliente.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		if(jcbNomeCliente.isPopupVisible()) {
        			preencherCodigoClientePeloCombobox();
        		}
        	}
        	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	}
        });
        jcbNomeCliente.setBounds(93, 25, 404, 22);
        tab1.add(jcbNomeCliente);
        
        JLabel lblNewLabel_1 = new JLabel("Nome do Cliente");
        lblNewLabel_1.setBounds(94, 8, 140, 14);
        tab1.add(lblNewLabel_1);
        
        jtfNumeroVenda = new JTextField();
        jtfNumeroVenda.setEditable(false);
        jtfNumeroVenda.setBackground(new Color(189, 255, 189));
        jtfNumeroVenda.setBounds(505, 27, 135, 20);
        tab1.add(jtfNumeroVenda);
        jtfNumeroVenda.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Número da Venda");
        lblNewLabel_2.setBounds(506, 8, 134, 14);
        tab1.add(lblNewLabel_2);
        
        JLabel lblCdProduto = new JLabel("Cód. Produto");
        lblCdProduto.setBounds(10, 57, 96, 14);
        tab1.add(lblCdProduto);
        
        jtfCodigoProduto = new JTextField();
        jtfCodigoProduto.setEnabled(false);
        jtfCodigoProduto.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		String codigoProdutoText = jtfCodigoProduto.getText();
        		if (!codigoProdutoText.isEmpty()) {
        		    int codigoProduto = Integer.parseInt(codigoProdutoText);
        		    modelProdutos = controllerProdutos.retornarProdutoController(codigoProduto);
        		    jcbNomeProduto.setSelectedItem(modelProdutos.getProNome());
        		}
        	}
        });
        jtfCodigoProduto.setColumns(10);
        jtfCodigoProduto.setBounds(10, 76, 74, 20);
        tab1.add(jtfCodigoProduto);
        
        jcbNomeProduto = new JComboBox();
        jcbNomeProduto.setEnabled(false);
        jcbNomeProduto.addPopupMenuListener(new PopupMenuListener() {
        	public void popupMenuCanceled(PopupMenuEvent e) {
        	}
        	public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
        		if(jcbNomeProduto.isPopupVisible()) {
        			preencherCodigoProdutoPeloCombobox();
        		}
        	}
        	public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
        	}
        });
        jcbNomeProduto.setBounds(93, 74, 334, 22);
        tab1.add(jcbNomeProduto);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nome do Produto");
        lblNewLabel_1_1.setBounds(94, 57, 140, 14);
        tab1.add(lblNewLabel_1_1);
        
        jtfQuantidade = new JTextField();
        jtfQuantidade.setEnabled(false);
        jtfQuantidade.setBounds(435, 76, 77, 20);
        tab1.add(jtfQuantidade);
        jtfQuantidade.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Quantidade");
        lblNewLabel_3.setBounds(435, 57, 85, 14);
        tab1.add(lblNewLabel_3);
        
        jbAdicionar = new JButton("Adicionar");
        jbAdicionar.setEnabled(false);
        jbAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (jtfQuantidade.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(ViewVendas.this, "Nenhuma quantidade inserida.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        quantidade = Integer.parseInt(jtfQuantidade.getText());
                        modelProdutos = controllerProdutos.retornarProdutoController(Integer.parseInt(jtfCodigoProduto.getText()));
                        DefaultTableModel modelo = (DefaultTableModel) jtProdutosVendas.getModel();
                        int cont = 0;
                        for (int i = 0; i < cont; i++) {
                            modelo.setNumRows(0);
                        }
                        modelo.addRow(new Object[]{
                                modelProdutos.getIdProduto(),
                                modelProdutos.getProNome(),
                                quantidade,
                                modelProdutos.getProValor(),
                                quantidade * modelProdutos.getProValor()
                        });
                        somarValorTotalProdutos();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ViewVendas.this, "Valor de quantidade inválido.", "ERRO", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
		jbAdicionar.setFocusable(false);
        jbAdicionar.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-mais-18.png")));
        jbAdicionar.setBounds(518, 74, 121, 23);
        tab1.add(jbAdicionar);
        
        jtProdutosVendas = new JTable();
        jtProdutosVendas.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"C\u00F3digo", "Nome", "Quantidade", "Valor Unit\u00E1rio", "Valor Total"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, String.class, Integer.class, Double.class, Double.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        jtProdutosVendas.getColumnModel().getColumn(0).setPreferredWidth(15);
        jtProdutosVendas.getColumnModel().getColumn(1).setPreferredWidth(250);
        jtProdutosVendas.getColumnModel().getColumn(2).setPreferredWidth(20);
        jtProdutosVendas.getColumnModel().getColumn(3).setPreferredWidth(30);
        jtProdutosVendas.getColumnModel().getColumn(4).setPreferredWidth(30);

        JScrollPane scrollPane = new JScrollPane(jtProdutosVendas);
        scrollPane.setBounds(10, 110, 630, 236);
        tab1.add(scrollPane);
        
        jbCancelar = new JButton("Cancelar");
        jbCancelar.setEnabled(false);
        jbCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		condicao = false;
        		limparFormulario();
        		habilitarCampos(condicao);
        	}
        });
		jbCancelar.setFocusable(false);
        jbCancelar.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-cancelar-18.png")));
        jbCancelar.setBounds(9, 399, 114, 23);
        tab1.add(jbCancelar);
        
        JButton jbNovo = new JButton("Novo");
        jbNovo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AlterarSalvar = "salvar";
        		condicao = true;
        		limparFormulario();
        		habilitarCampos(condicao);
        	}
        });
		jbNovo.setFocusable(false);
        jbNovo.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-documento-18.png")));
        jbNovo.setBounds(128, 399, 104, 23);
        tab1.add(jbNovo);
        
        jbSalvar = new JButton("Salvar");
        jbSalvar.setEnabled(false);
        jbSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int codigoVenda = 0, codigoProduto = 0;
        		double desconto = 0;
        		if(jtfDesconto.getText().equals("")) {
        			desconto = 0;
        		} else {
        			desconto = Double.parseDouble(jtfDesconto.getText());
        		}
        		if (!jtfNumeroVenda.getText().equals("")) {
            		modelVendas.setIdVenda(Integer.parseInt(jtfNumeroVenda.getText()));
        		}
        		listaModelVendasProdutos = new ArrayList<>();
        		modelVendas.setCliente(Integer.parseInt(jtfCodigoCliente.getText()));
        		try {
        		modelVendas.setVenDataVenda(datas.converterDataParaDateUS(new java.util.Date(System.currentTimeMillis())));
        	    } catch (Exception e1) {
        	    }
        		modelVendas.setVenValorLiquido(Double.parseDouble(jtfValorTotal.getText()));
        		modelVendas.setVenValorBruto(Double.parseDouble(jtfValorTotal.getText()) + desconto);
        		modelVendas.setVenDesconto(desconto);
        		if(AlterarSalvar.equals("salvar")) {
        			codigoVenda = controllerVendas.salvarVendaController(modelVendas);
            		if (codigoVenda > 0) {
            	        JOptionPane.showMessageDialog(ViewVendas.this, "Venda salva com sucesso!", "ATENÇÃO",
            	                JOptionPane.WARNING_MESSAGE);
            	    } else {
            	        JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao salvar venda!", "ERRO",
            	                JOptionPane.ERROR_MESSAGE);
            	    }
            		int cont = jtProdutosVendas.getRowCount(), i = 0;
            		for (i = 0; i < cont; i++) {
                		codigoProduto = (int) jtProdutosVendas.getValueAt(i, 0);
            			modelVendasProdutos = new ModelVendasProdutos();
            			modelProdutos = new ModelProdutos();
            			modelVendasProdutos.setProduto(codigoProduto);
            			modelVendasProdutos.setVendas(codigoVenda);
            			modelVendasProdutos.setVenProValor((double) jtProdutosVendas.getValueAt(i, 3));
            			modelVendasProdutos.setVenProQuantidade((int) jtProdutosVendas.getValueAt(i, 2));
            			modelProdutos.setIdProduto(codigoProduto);
            			int quantidadeVendida = ((Integer) jtProdutosVendas.getValueAt(i, 2)).intValue();
            			ModelProdutos produto = controllerProdutos.retornarProdutoController(codigoProduto);
            			int estoqueNovo = produto.getProEstoque() - quantidadeVendida;
                		System.out.println(estoqueNovo);
                		produto.setProEstoque(estoqueNovo);
                		modelProdutos.setProEstoque(estoqueNovo);
                		listaModelVendasProdutos.add(modelVendasProdutos);
                		listaModelProdutos.add(modelProdutos);
            		}
            		if(controllerVendasProdutos.salvarListaVendaProdutosController(listaModelVendasProdutos)) {
            			controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos);
            			//JOptionPane.showMessageDialog(ViewVendas.this, "Produtos da venda salvos com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            	        limparFormulario();
            		} else {
            			JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao salvar produtos da venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
            		}
            		condicao = false;
            		habilitarCampos(condicao);
        		} else if (AlterarSalvar.equals("alterar")){
        			int linha = jtVendas.getSelectedRow();
            		codigoVenda = (int) jtVendas.getValueAt(linha, 0);
            		listaModelProdutos = new ArrayList<>();
            		listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos.retornarListaProdutosVendasProdutosController(codigoVenda);
            		for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
            			modelProdutos = new ModelProdutos();
            			modelProdutos.setIdProduto(listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto());
            			modelProdutos.setProEstoque(
            					listaModelProdutosVendasProdutos.get(i).getModelProdutos().getProEstoque()
            					+ 
            					listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProQuantidade()
            					);
            			listaModelProdutos.add(modelProdutos);
            		}
            		if (controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos)) {
            			ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
            			if (controllerVendasProdutos.excluirVendaProdutosController(codigoVenda)) {
            				//JOptionPane.showMessageDialog(ViewVendas.this, "Venda excluida com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            				ViewVendas.this.carregarVendas();
            			} else {
            				JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao excluir a venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
            			} 
            		} else {
            			JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao excluir a venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
            		}
            		
            		if (controllerVendas.alterarVendaController(modelVendas)) {
            			JOptionPane.showMessageDialog(ViewVendas.this, "Venda alterada com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            		} else {
            			JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao alterar venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
            		}
            		
            		int cont = jtProdutosVendas.getRowCount(), i = 0;
            		for (i = 0; i < cont; i++) {
                		codigoProduto = (int) jtProdutosVendas.getValueAt(i, 0);
            			modelVendasProdutos = new ModelVendasProdutos();
            			modelProdutos = new ModelProdutos();
            			modelVendasProdutos.setProduto(codigoProduto);
            			modelVendasProdutos.setVendas(codigoVenda);
            			modelVendasProdutos.setVenProValor((double) jtProdutosVendas.getValueAt(i, 3));
            			modelVendasProdutos.setVenProQuantidade((int) jtProdutosVendas.getValueAt(i, 2));
            			modelProdutos.setIdProduto(codigoProduto);
            			int quantidadeVendida = ((Integer) jtProdutosVendas.getValueAt(i, 2)).intValue();
            			ModelProdutos produto = controllerProdutos.retornarProdutoController(codigoProduto);
            			int estoqueAtualizado = produto.getProEstoque() - quantidadeVendida;
            			produto.setProEstoque(estoqueAtualizado);
            			listaModelVendasProdutos.add(modelVendasProdutos);
            			listaModelProdutos.add(modelProdutos);
            		}
            		
            		if (controllerVendasProdutos.salvarListaVendaProdutosController(listaModelVendasProdutos)) {
            			//JOptionPane.showMessageDialog(ViewVendas.this, "Produtos da venda salvos com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
            			limparFormulario();
            		} else {
            			JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao salvar produtos da venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
            		}
            		condicao = false;
            		habilitarCampos(condicao);
        	}
        }});
		jbSalvar.setFocusable(false);
        jbSalvar.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-salvar-como-18.png")));
        jbSalvar.setBounds(535, 399, 104, 23);
        tab1.add(jbSalvar);
        
        jtfValorTotal = new JTextField();
        jtfValorTotal.setEnabled(false);
        jtfValorTotal.setBounds(534, 372, 105, 20);
        tab1.add(jtfValorTotal);
        jtfValorTotal.setColumns(10);
        
        jtfDesconto = new JTextField();
        jtfDesconto.setEnabled(false);
        jtfDesconto.setText("0");
        jtfDesconto.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		somarValorTotalProdutos();
        	}
        });
        jtfDesconto.setColumns(10);
        jtfDesconto.setBounds(420, 372, 105, 20);
        tab1.add(jtfDesconto);
        
        JLabel lblNewLabel_4 = new JLabel("Valor Total");
        lblNewLabel_4.setBounds(535, 353, 103, 14);
        tab1.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("Desconto");
        lblNewLabel_4_1.setBounds(421, 353, 105, 14);
        tab1.add(lblNewLabel_4_1);
        
        jbRemover = new JButton("Remover");
        jbRemover.setEnabled(false);
        jbRemover.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int linha = jtProdutosVendas.getSelectedRow();
        		DefaultTableModel modelo = (DefaultTableModel) jtProdutosVendas.getModel();
        		modelo.removeRow(linha);
        		somarValorTotalProdutos();
        	}
        });
		jbRemover.setFocusable(false);
        jbRemover.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-limpar-símbolo-18.png")));
        jbRemover.setBounds(237, 399, 114, 23);
        tab1.add(jbRemover);

        tabbedPane.addTab("Consultar/Excluir/Alterar", tab2);
        tab2.setLayout(null);
        
        JLabel lblNewLabel_5 = new JLabel("Pesquisar");
        lblNewLabel_5.setBounds(11, 10, 63, 14);
        tab2.add(lblNewLabel_5);
        
        jtfPesquisa = new JTextField();
        jtfPesquisa.setBounds(78, 8, 427, 20);
        tab2.add(jtfPesquisa);
        jtfPesquisa.setColumns(10);
        
        JButton jbPesquisar = new JButton("Pesquisar");
        jbPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(jbPesquisar)) {
                    String textoPesquisa = jtfPesquisa.getText();
                    // Realize a ação desejada com o texto da pesquisa
                }
            }
        });
		jbPesquisar.setFocusable(false);
        jbPesquisar.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-pesquisar-18.png")));
        jbPesquisar.setBounds(511, 6, 128, 23);
        tab2.add(jbPesquisar);
        
        jtVendas = new JTable();
        jtVendas.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"C\u00F3digo", "Nome do Cliente", "Data"
        	}
        ) {
        	Class[] columnTypes = new Class[] {
        		Integer.class, String.class, Object.class
        	};
        	public Class getColumnClass(int columnIndex) {
        		return columnTypes[columnIndex];
        	}
        });
        jtVendas.getColumnModel().getColumn(0).setPreferredWidth(20);
        jtVendas.getColumnModel().getColumn(1).setPreferredWidth(200);
        jtVendas.getColumnModel().getColumn(2).setPreferredWidth(30);

        JScrollPane scrollPane_1 = new JScrollPane(jtVendas);
        scrollPane_1.setBounds(10, 37, 630, 355);
        tab2.add(scrollPane_1);
        
        JButton jbExcluir = new JButton("Excluir");
        jbExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int linha = jtVendas.getSelectedRow();
        		int codigoVenda = (int) jtVendas.getValueAt(linha, 0);
        		listaModelProdutos = new ArrayList<>();
        		listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos.retornarListaProdutosVendasProdutosController(codigoVenda);
        		for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
        			modelProdutos = new ModelProdutos();
        			modelProdutos.setIdProduto(listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto());
        			modelProdutos.setProEstoque(
        					listaModelProdutosVendasProdutos.get(i).getModelProdutos().getProEstoque()
        					+ 
        					listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProQuantidade()
        					);
        			listaModelProdutos.add(modelProdutos);
        		}
        		if (controllerProdutos.alterarEstoqueProdutoController(listaModelProdutos)) {
        			ControllerVendasProdutos controllerVendasProdutos = new ControllerVendasProdutos();
        			controllerVendasProdutos.excluirVendaProdutosController(codigoVenda);
        			if (controllerVendas.excluirVendaController(codigoVenda)) {
        				JOptionPane.showMessageDialog(ViewVendas.this, "Venda excluida com sucesso!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
        				ViewVendas.this.carregarVendas();
        			} else {
        				JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao excluir a venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
        			} 
        		} else {
        			JOptionPane.showMessageDialog(ViewVendas.this, "Erro ao excluir a venda!", "ERRO", JOptionPane.ERROR_MESSAGE);
        		}
        	}
        });
		jbExcluir.setFocusable(false);
        jbExcluir.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-lixo-18.png")));
        jbExcluir.setBounds(9, 399, 104, 23);
        tab2.add(jbExcluir);
        
        JButton jbAlterar = new JButton("Alterar");
        jbAlterar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AlterarSalvar = "alterar";
        		int linha = jtVendas.getSelectedRow();
        		int codigoVendas = (int) jtVendas.getValueAt(linha, 0);
        		ModelVendas modelVendas = controllerVendas.retornarVendaController(codigoVendas);
        		listaModelProdutosVendasProdutos = controllerProdutosVendasProdutos.retornarListaProdutosVendasProdutosController(codigoVendas);
        		DefaultTableModel modelo = (DefaultTableModel) jtProdutosVendas.getModel();
        		modelo.setNumRows(0);
        		for (int i = 0; i < listaModelProdutosVendasProdutos.size(); i++) {
            		jtfNumeroVenda.setText(String.valueOf(listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVendas()));
        			modelo.addRow(new Object[]{
        				listaModelProdutosVendasProdutos.get(i).getModelProdutos().getIdProduto(),
        				listaModelProdutosVendasProdutos.get(i).getModelProdutos().getProNome(),
        				listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProQuantidade(),
        				listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProValor(),
        				listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProQuantidade() *
        				listaModelProdutosVendasProdutos.get(i).getModelVendasProdutos().getVenProValor()
        			});
        		}
        		
        		int cliente = modelVendas.getCliente();
                jtfCodigoCliente.setText(String.valueOf(cliente));
                
                String codigoClienteText = jtfCodigoCliente.getText();
        		if (!codigoClienteText.isEmpty()) {
        		    int codigoCliente = Integer.parseInt(codigoClienteText);
        		    modelClientes = controllerClientes.retornarClienteController(codigoCliente);
        		    jcbNomeCliente.setSelectedItem(modelClientes.getCliNome());
        		}
        		
        		somarValorTotalProdutos();
				tabbedPane.setSelectedIndex(0);
				condicao = true;
				habilitarCampos(condicao);
        	}
        });
		jbAlterar.setFocusable(false);
        jbAlterar.setIcon(new ImageIcon(ViewVendas.class.getResource("/imagens/icons8-lápis-18.png")));
        jbAlterar.setBounds(119, 399, 104, 23);
        tab2.add(jbAlterar);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        listarClientes();
        listarProdutos();
        carregarVendas();
        try {
        	preencherCodigoClientePeloCombobox();
        	preencherCodigoProdutoPeloCombobox();
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(this, "Nenhuma venda encontrada.");
        }
	}
	
	private void listarClientes() {
		listaModelClientes = controllerClientes.retornarListaClienteController();
		jcbNomeCliente.removeAllItems();
		for (int i = 0; i < listaModelClientes.size(); i++) {
			jcbNomeCliente.addItem(listaModelClientes.get(i).getCliNome());
		}
	}
	
	private void listarProdutos() {
		listaModelProdutos = controllerProdutos.retornarListaProdutoController();
		jcbNomeProduto.removeAllItems();
		for (int i = 0; i < listaModelProdutos.size(); i++) {
			jcbNomeProduto.addItem(listaModelProdutos.get(i).getProNome());
		}
	}
	
	private void aplicarDescontos() {
		String descontoText = jtfDesconto.getText();
		if (!descontoText.isEmpty()) {
		    double desconto = Double.parseDouble(descontoText);
		    double valorTotal = Double.parseDouble(jtfValorTotal.getText());
		    valorTotal -= desconto;
		    jtfValorTotal.setText(String.valueOf(valorTotal));
		}
	}
	
	private void somarValorTotalProdutos() {
		double soma = 0, valor;
		int cont = jtProdutosVendas.getRowCount();
		for (int i = 0; i < cont; i++) {
			valor = (double) jtProdutosVendas.getValueAt(i, 4);
			soma = soma + valor;
		}
		jtfValorTotal.setText(String.valueOf(soma));
		aplicarDescontos();
	}
	
	private void preencherCodigoClientePeloCombobox() {
		modelClientes = controllerClientes.retornarClienteNomeController(jcbNomeCliente.getSelectedItem().toString());
		jtfCodigoCliente.setText(String.valueOf(modelClientes.getIdCliente()));
	}
	
	private void preencherCodigoProdutoPeloCombobox() {
		modelProdutos = controllerProdutos.retornarProdutoNomeController(jcbNomeProduto.getSelectedItem().toString());
		jtfCodigoProduto.setText(String.valueOf(modelProdutos.getIdProduto()));
	}
	
	private void limparFormulario() {
	    jtfCodigoCliente.setText("");
	    jcbNomeCliente.setSelectedIndex(0);
	    jtfNumeroVenda.setText("");
	    jtfCodigoProduto.setText("");
	    jcbNomeProduto.setSelectedIndex(0);
	    jtfQuantidade.setText("");
	    jtfValorTotal.setText("");
	    jtfDesconto.setText("0");

	    DefaultTableModel modelo = (DefaultTableModel) jtProdutosVendas.getModel();
	    modelo.setRowCount(0);

	    carregarVendas();
	}
	
	private void carregarVendas() {
		DefaultTableModel modelo = (DefaultTableModel) jtVendas.getModel();
		modelo.setRowCount(0);
		listaModelVendasClientes = controllerVendasClientes.retornarListaVendasClientesController();
		int cont = listaModelVendasClientes.size();
		for (int i = 0; i < cont; i++) {
			modelo.addRow(new Object[]{
				listaModelVendasClientes.get(i).getModelVendas().getIdVenda(),
				listaModelVendasClientes.get(i).getModelClientes().getCliNome(),
				listaModelVendasClientes.get(i).getModelVendas().getVenDataVenda()
			});
		}
	}
	
	private void habilitarCampos(boolean condicao) {
		jbSalvar.setEnabled(condicao);
		jtfCodigoCliente.setEnabled(condicao);
		jcbNomeCliente.setEnabled(condicao);
		jtfCodigoProduto.setEnabled(condicao);
		jcbNomeProduto.setEnabled(condicao);
		jtfDesconto.setEnabled(condicao);
		jtfQuantidade.setEnabled(condicao);
		jbAdicionar.setEnabled(condicao);
		jbCancelar.setEnabled(condicao);
		jbRemover.setEnabled(condicao);
		jtfValorTotal.setEnabled(condicao);
	}
}

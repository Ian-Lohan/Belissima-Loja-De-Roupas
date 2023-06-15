package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import model.ModelSessaoUsuario;

public class ViewPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel jpPaginas;
	private JLabel jlUsuario;
	private JLabel jlDataHora;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPrincipal frame = new ViewPrincipal();
					frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewPrincipal() {
		setResizable(false);
		setTitle("Belissima - Loja de Roupas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 547);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivos");
		menuBar.add(mnNewMenu);
		
		JMenuItem jmiSair = new JMenuItem("Sair");
		jmiSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jmiSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		jmiSair.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-sair-18.png")));
		mnNewMenu.add(jmiSair);
		
		JMenu mnNewMenu_1 = new JMenu("Cadastros");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem jmiClientes = new JMenuItem("Clientes");
		jmiClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewCliente().setVisible(true);
			}
		});
		jmiClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		jmiClientes.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-gestão-de-cliente-18.png")));
		mnNewMenu_1.add(jmiClientes);
		
		JMenuItem jmiProdutos = new JMenuItem("Produtos");
		jmiProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewProduto().setVisible(true);
			}
		});
		jmiProdutos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		jmiProdutos.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-o-t-shirt-das-mulheres-18.png")));
		mnNewMenu_1.add(jmiProdutos);
		
		JMenuItem jmiUsuarios = new JMenuItem("Usuários");
		jmiUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewUsuario().setVisible(true);
			}
		});
		jmiUsuarios.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		jmiUsuarios.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-grupo-de-usuários-18.png")));
		mnNewMenu_1.add(jmiUsuarios);
		
		JMenu mnNewMenu_2 = new JMenu("Vendas");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem jmiVendas = new JMenuItem("Vendas");
		jmiVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewVendas().setVisible(true);
			}
		});
		jmiVendas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
		jmiVendas.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-vendas-totais-18.png")));
		mnNewMenu_2.add(jmiVendas);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.requestFocusInWindow();
		
		jpPaginas = new JPanel();
		jpPaginas.setBackground(new Color(254, 160, 184));
		jpPaginas.setBounds(22, 0, 211, 488);
		contentPane.add(jpPaginas);
		jpPaginas.setVisible(true);
		jpPaginas.setLayout(null);
		
		JButton jbClientes = new JButton("Clientes");
		jbClientes.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-clientes-32.png")));
		jbClientes.setBackground(new Color(255, 230, 236));
		jbClientes.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new ViewCliente().setVisible(true);
		    }
		});
		jbClientes.setFocusPainted(false);
		jbClientes.setFocusable(false);
		jbClientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jbClientes.setBounds(34, 61, 141, 60);
		jpPaginas.add(jbClientes);
		
		JButton jbProdutos = new JButton("Produtos");
		jbProdutos.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-vestido-preto-pequeno-32.png")));
		jbProdutos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new ViewProduto().setVisible(true);
		    }
		});
		jbProdutos.setFocusPainted(false);
		jbProdutos.setFocusable(false);
		jbProdutos.setBackground(new Color(255, 230, 236));
		jbProdutos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jbProdutos.setBounds(34, 132, 141, 60);
		jpPaginas.add(jbProdutos);
		
		JButton jbUsuarios = new JButton("Usuarios");
		jbUsuarios.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-usuário-32.png")));
		jbUsuarios.setBackground(new Color(255, 230, 236));
		jbUsuarios.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new ViewUsuario().setVisible(true);
		    }
		});
		jbUsuarios.setFocusPainted(false);
		jbUsuarios.setFocusable(false);
		jbUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jbUsuarios.setBounds(34, 274, 141, 60);
		jpPaginas.add(jbUsuarios);
		
		JButton jbVendas = new JButton("Vendas");
		jbVendas.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-vendas-32.png")));
		jbVendas.setBackground(new Color(255, 230, 236));
		jbVendas.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        new ViewVendas().setVisible(true);
		    }
		});
		jbVendas.setFocusPainted(false);
		jbVendas.setFocusable(false);
		jbVendas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jbVendas.setBounds(34, 203, 141, 60);
		jpPaginas.add(jbVendas);
		
		JLabel lblNewLabel_1 = new JLabel("- MENU -");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(10, 22, 191, 28);
		jpPaginas.add(lblNewLabel_1);
		
		JButton btnPdv = new JButton("PDV");
		btnPdv.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/icons8-carrinho-32.png")));
		btnPdv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewPDV().setVisible(true);
			}
		});
		btnPdv.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPdv.setFocusable(false);
		btnPdv.setFocusPainted(false);
		btnPdv.setBackground(new Color(255, 230, 236));
		btnPdv.setBounds(34, 345, 141, 60);
		jpPaginas.add(btnPdv);
		
		jlUsuario = new JLabel("Usuario");
		jlUsuario.setForeground(new Color(255, 255, 255));
		jlUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		jlUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlUsuario.setBounds(10, 436, 191, 21);
		jpPaginas.add(jlUsuario);
		
		jlDataHora = new JLabel("Data Hora");
		jlDataHora.setHorizontalAlignment(SwingConstants.CENTER);
		jlDataHora.setForeground(Color.WHITE);
		jlDataHora.setFont(new Font("Tahoma", Font.BOLD, 14));
		jlDataHora.setBounds(10, 456, 191, 21);
		jpPaginas.add(jlDataHora);
		
		JLabel jlLogo = new JLabel("");
		jlLogo.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/belissimaLogo.png")));
		jlLogo.setBounds(242, 168, 605, 363);
		contentPane.add(jlLogo);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewPrincipal.class.getResource("/imagens/background.jpg")));
		lblNewLabel.setBounds(0, 0, 1291, 691);
		contentPane.add(lblNewLabel);
		
		Thread atualizacaoThread = new Thread(() -> {
            while (true) {
                LocalDateTime dataHoraAtual = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String dataHoraFormatada = dataHoraAtual.format(formatter);
                jlDataHora.setText(dataHoraFormatada);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        atualizacaoThread.start();
		
		setLocationRelativeTo(null);
		setarUsuario();
	}
	
	private void setarUsuario() {
		jlUsuario.setText(ModelSessaoUsuario.nome);
	}
}

package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import controller.ControllerUsuarios;
import model.ModelUsuarios;
import javax.swing.ImageIcon;
import model.ModelSessaoUsuario;
import conexoes.ConexaoMySql;

public class ViewLogin extends JFrame {

	private JPanel contentPane;
	private JTextField jtfLogin;
	private JPasswordField jpfSenha;
	private JButton jbEntrar;
	
	ControllerUsuarios controllerUsuarios = new ControllerUsuarios();
	ModelUsuarios modelUsuarios = new ModelUsuarios();
	ModelSessaoUsuario modelSessaoUsuario = new ModelSessaoUsuario();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewLogin frame = new ViewLogin();
					frame.setVisible(true);
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewLogin() {
		ConexaoMySql conexao = new ConexaoMySql();
	    conexao.conectar();

	    if (!conexao.isStatus()) {
	        JOptionPane.showMessageDialog(ViewLogin.this, "O banco de dados não foi ativado.", "ERRO", JOptionPane.ERROR_MESSAGE);
	        System.exit(0);
	    }
	    
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuário:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(50, 183, 52, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Senha:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(49, 214, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		jtfLogin = new JTextField();
		jtfLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jpfSenha.requestFocus();
			}
		});
		jtfLogin.setBounds(118, 187, 218, 20);
		contentPane.add(jtfLogin);
		jtfLogin.setColumns(10);
		
		jpfSenha = new JPasswordField();
		jpfSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		jpfSenha.setColumns(10);
		jpfSenha.setBounds(118, 213, 218, 20);
		contentPane.add(jpfSenha);
		
	    jbEntrar = new JButton("Entrar");
		jbEntrar.setBackground(new Color(126, 250, 130));
		jbEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entrar();
			}
		});
		jbEntrar.setFocusable(false);
		jbEntrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		jbEntrar.setBounds(50, 249, 129, 23);
		contentPane.add(jbEntrar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ViewLogin.class.getResource("/imagens/belissimaLogoMenor.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(50, 10, 287, 126);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Login do Sistema");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(49, 157, 143, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton jbSair = new JButton("Sair");
		jbSair.setBackground(new Color(255, 121, 121));
		jbSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jbSair.setFocusable(false);
		jbSair.setFont(new Font("Segoe UI", Font.BOLD, 12));
		jbSair.setBounds(209, 249, 129, 23);
		contentPane.add(jbSair);
		setLocationRelativeTo(null);
	}
	
	private void entrar() {
		modelUsuarios.setUsuLogin(jtfLogin.getText());
		modelUsuarios.setUsuSenha(String.valueOf(jpfSenha.getPassword()));
		if (controllerUsuarios.validarUsuarioController(modelUsuarios)) {
			modelUsuarios = controllerUsuarios.retornarLoginUsuarioController(jtfLogin.getText());
			modelSessaoUsuario.codigo = modelUsuarios.getIdUsuario();
			modelSessaoUsuario.nome = modelUsuarios.getUsuNome();
			modelSessaoUsuario.login = modelUsuarios.getUsuLogin();
			new ViewPrincipal().setVisible(true);
			dispose();
		} else {
			JOptionPane.showMessageDialog(ViewLogin.this, "Usuário ou Senha inválidos!", "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}

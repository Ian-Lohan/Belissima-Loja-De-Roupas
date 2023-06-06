package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import connections.Conexao;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;

public class LoginView extends JFrame {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

    private JTextField textFieldLogin;
    private JPasswordField passwordField;
    
    private String nome_usuario;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginView frame = new LoginView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LoginView() {
    	conexao = Conexao.ConectarBanco();
    	
    	setTitle("Belissima - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(null);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(25, 63, 53, 20);
        getContentPane().add(lblLogin);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(126, 63, 144, 20);
        getContentPane().add(textFieldLogin);
        textFieldLogin.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(25, 87, 53, 20);
        getContentPane().add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(126, 87, 144, 20);
        getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(171, 122, 99, 30);
        getContentPane().add(btnLogin);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(30, 122, 99, 30);
        getContentPane().add(btnCadastrar);
        
        JLabel lblNewLabel = new JLabel("Realize seu Login!");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(10, 11, 260, 41);
        getContentPane().add(lblNewLabel);
        
        JLabel lblStatus = new JLabel("");
        lblStatus.setText("");
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        lblStatus.setBounds(260, 11, 10, 10);
        getContentPane().add(lblStatus);

    	if (conexao != null) {
    		lblStatus.setIcon(new ImageIcon(LoginView.class.getResource("/images/conectado.png")));
    	} else {
    		lblStatus.setIcon(new ImageIcon(LoginView.class.getResource("/images/desconectado.png")));
    	}
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            CadastroView frame = new CadastroView();
                            frame.setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                dispose();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                String senha = new String(passwordField.getPassword());

                if (validarLogin(login, senha)) {
                    JOptionPane.showMessageDialog(LoginView.this, "Login bem-sucedido!");

                    EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            try {
                                MenuView frame = new MenuView();
                                frame.setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "Login ou senha inválidos!");
                }

            }
        });
    }

    private boolean validarLogin(String login, String senha) {

        String sql = "SELECT * FROM usuarios WHERE login_usuario = '" + login + "' AND senha_usuario = '" + senha + "'";

        try {
            Statement statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            boolean loginValido = resultSet.next();
            nome_usuario = resultSet.getString("nome_usuario");
            System.out.println(nome_usuario);
            resultSet.close();
            statement.close();

            return loginValido;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

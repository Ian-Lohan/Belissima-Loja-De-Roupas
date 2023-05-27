package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class CadastroView extends JFrame {

    private JTextField textFieldNome;
    private JTextField textFieldLogin;
    private JPasswordField passwordFieldSenha;
    private JTextField textFieldCPF;
    private JTextField textFieldEndereco;
    private JTextField textFieldNumero;

    public static void main(String[] args) {
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
    }

    public CadastroView() {
        setTitle("Belissima - Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 310, 347);
        getContentPane().setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 30, 80, 20);
        getContentPane().add(lblNome);

        textFieldNome = new JTextField();
        textFieldNome.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldNome);
        textFieldNome.setColumns(10);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(30, 70, 80, 20);
        getContentPane().add(lblLogin);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(120, 70, 150, 20);
        getContentPane().add(textFieldLogin);
        textFieldLogin.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 110, 80, 20);
        getContentPane().add(lblSenha);

        passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setBounds(120, 110, 150, 20);
        getContentPane().add(passwordFieldSenha);

        JLabel lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(30, 150, 80, 20);
        getContentPane().add(lblCPF);

        textFieldCPF = new JTextField();
        textFieldCPF.setBounds(120, 150, 150, 20);
        getContentPane().add(textFieldCPF);
        textFieldCPF.setColumns(10);

        JLabel lblEndereco = new JLabel("Endereço:");
        lblEndereco.setBounds(30, 190, 80, 20);
        getContentPane().add(lblEndereco);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setBounds(120, 190, 150, 20);
        getContentPane().add(textFieldEndereco);
        textFieldEndereco.setColumns(10);

        JLabel lblNumero = new JLabel("Número:");
        lblNumero.setBounds(30, 230, 80, 20);
        getContentPane().add(lblNumero);

        textFieldNumero = new JTextField();
        textFieldNumero.setBounds(120, 230, 150, 20);
        getContentPane().add(textFieldNumero);
        textFieldNumero.setColumns(10);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(30, 269, 100, 30);
        getContentPane().add(btnLogin);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(170, 269, 100, 30);
        getContentPane().add(btnCadastrar);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

                dispose();
            }
        });
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String login = textFieldLogin.getText();
                String senha = new String(passwordFieldSenha.getPassword());
                String cpf = textFieldCPF.getText();
                String endereco = textFieldEndereco.getText();
                String numero = textFieldNumero.getText();

                if (cadastrarUsuario(nome, login, senha, cpf, endereco, numero)) {
                    JOptionPane.showMessageDialog(CadastroView.this, "Usuário cadastrado com sucesso!");

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

                    dispose();
                } else {
                    JOptionPane.showMessageDialog(CadastroView.this, "Erro ao cadastrar usuário!");
                }
            }
        });
    }

    private boolean cadastrarUsuario(String nome, String login, String senha, String cpf, String endereco,
            String numero) {
        String url = "jdbc:mysql://localhost:3306/lojaderoupas";
        String username = "root";
        String password = "root";

        String sql = "INSERT INTO usuarios (nome_usuario, login_usuario, senha_usuario, cpf_usuario, endereco_usuario, numero_usuario, acesso) VALUES ('"
                + nome + "', '" + login + "', '" + senha + "', '" + cpf + "', '" + endereco + "', '" + numero + "', 1)";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            int rowsInserted = statement.executeUpdate(sql);

            statement.close();
            connection.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

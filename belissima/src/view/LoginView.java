package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

    private JTextField textFieldLogin;
    private JPasswordField passwordField;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(null);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(30, 30, 80, 20);
        getContentPane().add(lblLogin);

        textFieldLogin = new JTextField();
        textFieldLogin.setBounds(120, 30, 150, 20);
        getContentPane().add(textFieldLogin);
        textFieldLogin.setColumns(10);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(30, 70, 80, 20);
        getContentPane().add(lblSenha);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 150, 20);
        getContentPane().add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 120, 80, 30);
        getContentPane().add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                String senha = new String(passwordField.getPassword());

                if (validarLogin(login, senha)) {
                    // Login válido
                    JOptionPane.showMessageDialog(LoginView.this, "Login bem-sucedido!");
                    // Faça o redirecionamento para a próxima tela ou execute outras ações necessárias
                } else {
                    // Login inválido
                    JOptionPane.showMessageDialog(LoginView.this, "Login ou senha inválidos!");
                }
            }
        });
    }

    private boolean validarLogin(String login, String senha) {
        // Informações de conexão ao banco de dados
        String url = "jdbc:mysql://localhost:3306/lojaderoupas";
        String username = "root";
        String password = "root";

        // Declaração SQL para selecionar registros
        String sql = "SELECT * FROM usuarios WHERE login_usuario = '" + login + "' AND senha_usuario = '" + senha + "'";

        try {
            // Conectar ao banco de dados
            Connection connection = DriverManager.getConnection(url, username, password);

            // Criar uma declaração
            Statement statement = connection.createStatement();

            // Executar a consulta
            ResultSet resultSet = statement.executeQuery(sql);

            // Verificar se a consulta retornou algum resultado
            boolean loginValido = resultSet.next();

            // Fechar a conexão e recursos
            resultSet.close();
            statement.close();
            connection.close();

            return loginValido;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

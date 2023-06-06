package view;

import java.awt.EventQueue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.SwingConstants;

public class MenuView extends JFrame {

	private JPanel contentPane;
    private JLabel lblClock;
    private JLabel lblDate;
    
    public static void main(String[] args) {
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
    }

    public MenuView() {
    	setTitle("Belissima - Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 634, 446);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(224, 224, 224));
        panel.setBounds(0, 0, 171, 409);
        contentPane.add(panel);
        panel.setLayout(null);

        JButton btnProdutos = new JButton("Produtos");
        btnProdutos.setBounds(10, 11, 150, 69);
        panel.add(btnProdutos);
        btnProdutos.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnClientes = new JButton("Clientes");
        btnClientes.setBounds(10, 88, 150, 69);
        panel.add(btnClientes);
        btnClientes.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnVendas = new JButton("Vendas");
        btnVendas.setBounds(10, 167, 150, 69);
        panel.add(btnVendas);
        btnVendas.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnUsuarios = new JButton("Usuários");
        btnUsuarios.setBounds(10, 247, 150, 69);
        panel.add(btnUsuarios);
        btnUsuarios.setFont(new Font("Tahoma", Font.BOLD, 18));

        JButton btnRelatorio = new JButton("Relatório");
        btnRelatorio.setBounds(10, 327, 150, 69);
        panel.add(btnRelatorio);
        btnRelatorio.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnRelatorio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RelatorioView relatorioView = new RelatorioView(null, null);
                relatorioView.setVisible(true);
            }
        });
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(224, 224, 224));
        panel_1.setBounds(449, 0, 171, 409);
        contentPane.add(panel_1);
        
                lblDate = new JLabel();
                lblDate.setBounds(0, 379, 171, 30);
                panel_1.add(lblDate);
                lblDate.setHorizontalAlignment(SwingConstants.CENTER);
                lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
                
                        lblClock = new JLabel();
                        lblClock.setBounds(0, 361, 171, 30);
                        panel_1.add(lblClock);
                        lblClock.setHorizontalAlignment(SwingConstants.CENTER);
                        lblClock.setFont(new Font("Tahoma", Font.BOLD, 16));
                        
                        JLabel lblNewLabel = new JLabel("Bem-Vindo!");
                        lblNewLabel.setBounds(0, 5, 171, 62);
                        panel_1.add(lblNewLabel);
                        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
                        
                        JButton btnSair = new JButton("Sair");
                        btnSair.setFont(new Font("Tahoma", Font.BOLD, 12));
                        btnSair.setBounds(96, 57, 65, 23);
                        panel_1.add(btnSair);
                        
                        JLabel lblNewLabel_1 = new JLabel("Data");
                        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
                        lblNewLabel_1.setBounds(10, 341, 105, 30);
                        panel_1.add(lblNewLabel_1);
                        
        btnUsuarios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuariosView usuariosView = new UsuariosView();
                usuariosView.setVisible(true);
            }
        });
        btnVendas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VendasView vendasView = new VendasView();
                vendasView.setVisible(true);
            }
        });
        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClientesView clientesView = new ClientesView();
                clientesView.setVisible(true);
            }
        });
        btnProdutos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProdutosView produtosView = new ProdutosView();
                produtosView.setVisible(true);
            }
        });
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Sair?", JOptionPane.YES_NO_OPTION);
            	if (sair == JOptionPane.YES_OPTION) {
            		dispose();
            		LoginView loginView = new LoginView();
            		loginView.setVisible(true);
            	}
            }
        });

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void updateTime() {
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfDayOfWeek = new SimpleDateFormat("EEEE");
        
        String currentTime = sdfTime.format(new Date());
        String currentDate = sdfDate.format(new Date());
        String currentDayOfWeek = sdfDayOfWeek.format(new Date());
        
        lblClock.setText(currentTime);
        lblDate.setText(currentDate + " (" + currentDayOfWeek + ")");
    }
}

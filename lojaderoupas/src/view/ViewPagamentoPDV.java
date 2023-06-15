package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.util.ArrayList;
import controller.ControllerFormaPagamento;
import model.ModelFormaPagamento;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import util.Mascaras;

public class ViewPagamentoPDV extends JDialog {

	ControllerFormaPagamento controllerFormaPagamento = new ControllerFormaPagamento();
	ModelFormaPagamento modelFormaPagamento = new ModelFormaPagamento();
	ArrayList<ModelFormaPagamento> listaModelFormaPagamento = new ArrayList<>();
	Mascaras mascaras = new Mascaras();
	
	private final JPanel contentPanel = new JPanel();
	private JTextField jtfTroco;
	private JLabel lblNewLabel_3;
	private JTextField jtfSubtotal;
	private JLabel lblNewLabel_4;
	private JTextField jtfDesconto;
	private JLabel lblNewLabel;
	private JTextField jtfRecebido;
	private JLabel jlValorTotal;
	private JButton jbConfirmar;
	private JLabel lblNewLabel_2;
	private JComboBox jcbPagamento;
	public float valorTotal;
	public float desconto;
	public float valorRecebido;
	public float troco;
	public String formaPagamento;
	private boolean pago;
	
	public static void main(String[] args) {
		try {
			ViewPagamentoPDV dialog = new ViewPagamentoPDV(new JFrame(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ViewPagamentoPDV(Frame parent, boolean modal) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				jtfDesconto.requestFocus();
			}
		});
		setTitle("Pagamento");
		setBounds(100, 100, 450, 406);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel_3 = new JLabel("Subtotal:");
			lblNewLabel_3.setBounds(10, 14, 101, 27);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		{
			lblNewLabel_4 = new JLabel("Desconto:");
			lblNewLabel_4.setBounds(10, 87, 101, 27);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		{
			lblNewLabel = new JLabel("Recebido:");
			lblNewLabel.setBounds(10, 125, 101, 27);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		{
			jtfSubtotal = new JTextField();
			jtfSubtotal.setText("0");
			jtfSubtotal.setEditable(false);
			jtfSubtotal.setBounds(121, 15, 302, 24);
			jtfSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			jtfDesconto = new JTextField();
			jtfDesconto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jtfRecebido.requestFocus();
				}
			});
			jtfDesconto.setText("0");
			jtfDesconto.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					jtfDesconto.setText(mascaras.converterVirgulaParaPonto(jtfDesconto.getText()));
					calcularPagamento();
				}
			});
			jtfDesconto.setBounds(121, 88, 302, 24);
			jtfDesconto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			jtfRecebido = new JTextField();
			jtfRecebido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (!(Float.parseFloat(jtfTroco.getText()) < 0)) {
						confirmarVenda();
					} else {
						JOptionPane.showMessageDialog(ViewPagamentoPDV.this, "Pagamento incompleto.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			jtfRecebido.setText("0");
			jtfRecebido.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					jtfRecebido.setText(mascaras.converterVirgulaParaPonto(jtfRecebido.getText()));
					calcularPagamento();
				}
			});
			jtfRecebido.setBounds(121, 126, 302, 24);
			jtfRecebido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 184, 413, 141);
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Valor Total a Pagar", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0));
		titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD, 16));
		panel.setBorder(titledBorder);
		panel.setLayout(null);
		{
			jlValorTotal = new JLabel("valor");
			jlValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
			jlValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 42));
			jlValorTotal.setBounds(10, 11, 393, 119);
			panel.add(jlValorTotal);
		}
		
		jbConfirmar = new JButton("Confirmar");
		jbConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Float.parseFloat(jtfTroco.getText()) < 0)) {
					confirmarVenda();
				} else {
					JOptionPane.showMessageDialog(ViewPagamentoPDV.this, "Pagamento incompleto.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		jbConfirmar.setBounds(314, 335, 109, 23);
		
		JLabel lblNewLabel_1 = new JLabel("Troco:");
		lblNewLabel_1.setBounds(10, 333, 71, 22);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		jtfTroco = new JTextField();
		jtfTroco.setEditable(false);
		jtfTroco.setBounds(64, 335, 240, 23);
		jtfTroco.setColumns(10);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel_3);
		contentPanel.add(jtfSubtotal);
		contentPanel.add(lblNewLabel_4);
		contentPanel.add(jtfDesconto);
		contentPanel.add(lblNewLabel);
		contentPanel.add(jtfRecebido);
		contentPanel.add(panel);
		contentPanel.add(jtfTroco);
		contentPanel.add(lblNewLabel_1);
		contentPanel.add(jbConfirmar);
		
		{
			lblNewLabel_2 = new JLabel("Pagamento:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_2.setBounds(10, 49, 101, 27);
			contentPanel.add(lblNewLabel_2);
		}
		{
			jcbPagamento = new JComboBox();
			jcbPagamento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jtfDesconto.requestFocus();
				}
			});
			jcbPagamento.requestFocus();
			jcbPagamento.setBounds(121, 52, 302, 24);
			contentPanel.add(jcbPagamento);
		}
		
		listarFormaPagamento();
		setLocationRelativeTo(null);
		this.pago = false;
		limparCampos();
		calcularPagamento();
		jtfDesconto.requestFocus();
	}
	
	private void listarFormaPagamento() {
		listaModelFormaPagamento = controllerFormaPagamento.retornarListaFormaPagamentoController();
		for (int i = 0; i < listaModelFormaPagamento.size(); i++) {
			jcbPagamento.addItem(listaModelFormaPagamento.get(i).getForPagDescricao());
		}
	}
	
	public void setarValorTotal() {
		this.jtfSubtotal.setText(this.valorTotal + "");
		this.jlValorTotal.setText(this.valorTotal + "");
	}
	
	public void confirmarVenda() {
		try {
			this.desconto = Float.parseFloat(this.jtfDesconto.getText());
		} catch (Exception e) {
			this.jtfDesconto.setText("0");;
			confirmarVenda();
		}
		try {
			this.troco = Float.parseFloat(jtfTroco.getText());
		} catch (Exception e) {
			this.jtfTroco.setText("0");;
			confirmarVenda();
		}
		try {
			this.valorRecebido = Float.parseFloat(jtfRecebido.getText());
		} catch (Exception e) {
			this.jtfRecebido.setText("0");;
			confirmarVenda();
		}
		try {
			this.valorTotal = Float.parseFloat(jtfSubtotal.getText());
		} catch (Exception e) {
			this.jtfSubtotal.setText("0");;
			confirmarVenda();
		}
		try {
			this.formaPagamento = (jcbPagamento.getSelectedItem().toString());
		} catch (Exception e) {
			this.formaPagamento = "A Vista";
			confirmarVenda();
		}
		this.pago = true;
		dispose();
	}
	
	private void calcularPagamento() {
		float total, desconto, recebido, pagar, troco;
		try {
			total = Float.parseFloat(jtfSubtotal.getText());
		} catch (Exception e) {
			total = 0;
			jtfSubtotal.setText("0");
		}
		try {
			desconto = Float.parseFloat(jtfDesconto.getText());
		} catch (Exception e) {
			desconto = 0;
			jtfDesconto.setText("0");

		}
		try {
			recebido = Float.parseFloat(jtfRecebido.getText());
		} catch (Exception e) {
			recebido = 0;
			jtfRecebido.setText("0");

		}
		pagar = total - desconto;
		jlValorTotal.setText(mascaras.converterArredondar2CasaPontoString(pagar));
		troco = recebido - pagar;
		jtfTroco.setText(mascaras.converterArredondar2CasaPontoString(troco));
	}
	
	private void limparCampos() {
		jcbPagamento.setSelectedItem(0);
		jtfDesconto.setText("");
		jtfRecebido.setText("");
	}
	

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public float getDesconto() {
		return desconto;
	}

	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}

	public float getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(float valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public float getTroco() {
		return troco;
	}

	public void setTroco(float troco) {
		this.troco = troco;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}
}

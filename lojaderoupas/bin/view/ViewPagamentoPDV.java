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
	private JLabel lblNewLabel_3;
	private JTextField jtfSubtotal;
	private JLabel lblNewLabel_4;
	private JTextField jtfDesconto;
	private JLabel jlValorTotal;
	private JButton jbConfirmar;
	private JLabel lblNewLabel_2;
	private JComboBox jcbPagamento;
	public float valorTotal;
	public float valorRecebido;
	public String formaPagamento;
	private boolean pago;
	private JLabel jlTroco;
	private JTextField jtfParcelas;
	float total, desconto, recebido, pagar, troco;
	private JLabel jlRecebido;
	private JTextField jtfRecebido;
	
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
				jtfRecebido.requestFocus();
			}
		});
		setTitle("Pagamento");
		setBounds(100, 100, 450, 440);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblNewLabel_3 = new JLabel("Subtotal:");
			lblNewLabel_3.setBounds(10, 11, 101, 27);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		{
			lblNewLabel_4 = new JLabel("Desconto:");
			lblNewLabel_4.setBounds(10, 159, 101, 27);
			lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		{
			jtfSubtotal = new JTextField();
			jtfSubtotal.setText("0");
			jtfSubtotal.setEditable(false);
			jtfSubtotal.setBounds(121, 12, 302, 24);
			jtfSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		{
			jtfDesconto = new JTextField();
			jtfDesconto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (recebido >= pagar) {
					    jbConfirmar.requestFocus();
					} else {
						jtfRecebido.requestFocus();
					}
				}
			});
			jtfDesconto.setText("0");
			jtfDesconto.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					if (jtfDesconto.getText().isEmpty()) {
						jtfDesconto.setText("0");
					}
					jtfDesconto.setText(mascaras.converterVirgulaParaPonto(jtfDesconto.getText()));
					calcularPagamento();
				}
			});
			jtfDesconto.setBounds(121, 160, 302, 24);
			jtfDesconto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 197, 413, 141);
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Valor Total a Pagar | Troco do Cliente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0));
		titledBorder.setTitleFont(titledBorder.getTitleFont().deriveFont(Font.BOLD, 16));
		panel.setBorder(titledBorder);
		panel.setLayout(null);
		{
			jlValorTotal = new JLabel("0.00");
			jlValorTotal.setHorizontalAlignment(SwingConstants.CENTER);
			jlValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 42));
			jlValorTotal.setBounds(10, 11, 200, 119);
			panel.add(jlValorTotal);
		}
		
		jbConfirmar = new JButton("Confirmar");
		jbConfirmar.setFont(new Font("Tahoma", Font.BOLD, 18));
		jbConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(Float.parseFloat(jlTroco.getText()) < 0)) {
					confirmarVenda();
				} else {
					JOptionPane.showMessageDialog(ViewPagamentoPDV.this, "Pagamento incompleto.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		jbConfirmar.setBounds(93, 344, 255, 46);
		contentPanel.setLayout(null);
		contentPanel.add(lblNewLabel_3);
		contentPanel.add(jtfSubtotal);
		contentPanel.add(lblNewLabel_4);
		contentPanel.add(jtfDesconto);
		contentPanel.add(panel);
		{
			jlTroco = new JLabel("0.00");
			jlTroco.setHorizontalAlignment(SwingConstants.CENTER);
			jlTroco.setFont(new Font("Tahoma", Font.PLAIN, 42));
			jlTroco.setBounds(215, 11, 188, 119);
			panel.add(jlTroco);
		}
		contentPanel.add(jbConfirmar);
		
		{
			lblNewLabel_2 = new JLabel("Pagamento:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
			lblNewLabel_2.setBounds(10, 45, 101, 27);
			contentPanel.add(lblNewLabel_2);
		}
		{
			jcbPagamento = new JComboBox();
			jcbPagamento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indiceSelecionado = jcbPagamento.getSelectedIndex();
			        if (indiceSelecionado >= 0 && indiceSelecionado < listaModelFormaPagamento.size()) {
			            ModelFormaPagamento formaPagamentoSelecionada = listaModelFormaPagamento.get(indiceSelecionado);
			            int parcelas = formaPagamentoSelecionada.getForPagParcelas();
			            jtfParcelas.setText(String.valueOf(parcelas));
			        }
					jtfRecebido.requestFocus();
				}
			});
			jcbPagamento.requestFocus();
			jcbPagamento.setBounds(121, 48, 302, 24);
			contentPanel.add(jcbPagamento);
		}
		
		JLabel jlParcelas = new JLabel("Parcelas:");
		jlParcelas.setFont(new Font("Tahoma", Font.BOLD, 15));
		jlParcelas.setBounds(10, 83, 101, 27);
		contentPanel.add(jlParcelas);
		
		jtfParcelas = new JTextField();
		jtfParcelas.setEditable(false);
		jtfParcelas.setText("1");
		jtfParcelas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jtfParcelas.setBounds(121, 84, 302, 24);
		contentPanel.add(jtfParcelas);
		{
			jlRecebido = new JLabel("Recebido:");
			jlRecebido.setFont(new Font("Tahoma", Font.BOLD, 15));
			jlRecebido.setBounds(10, 121, 101, 27);
			contentPanel.add(jlRecebido);
		}
		{
			jtfRecebido = new JTextField();
			jtfRecebido.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					calcularPagamento();
				}
			});
			jtfRecebido.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					jtfDesconto.requestFocus();
				}
			});
			jtfRecebido.setText("0");
			jtfRecebido.setFont(new Font("Tahoma", Font.PLAIN, 15));
			jtfRecebido.setBounds(121, 122, 302, 24);
			contentPanel.add(jtfRecebido);
		}
		
		listarFormaPagamento();
		setLocationRelativeTo(null);
		this.pago = false;
		limparCampos();
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
			this.troco = Float.parseFloat(jlTroco.getText());
		} catch (Exception e) {
			this.jlTroco.setText("0");;
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
	    total = Float.parseFloat(jtfSubtotal.getText());
	    
	    if (jtfDesconto.getText().isEmpty()) {
	        desconto = 0;
	    } else {
	        desconto = Float.parseFloat(jtfDesconto.getText());
	    }
	    
	    if (jtfRecebido.getText().isEmpty()) {
	        recebido = 0;
	    } else {
	        recebido = Float.parseFloat(jtfRecebido.getText());
	    }
	    
	    pagar = total - desconto;

	    if (recebido >= pagar) {
	        troco = recebido - pagar;
	        jlValorTotal.setText("0.00");
	        jlTroco.setText(String.format("%.2f", troco));
	        jbConfirmar.setEnabled(true);
	    } else {
	        troco = 0;
	        jlValorTotal.setText(String.format("%.2f", pagar));
	        jlTroco.setText(String.format("%.2f", troco));
	        jbConfirmar.setEnabled(false);
	    }
	}
	
	private void limparCampos() {
		jcbPagamento.setSelectedItem(0);
		jtfDesconto.setText("0");
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

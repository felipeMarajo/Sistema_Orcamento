package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Cliente;
import Model.Ordem_Servico;
import Model.Status;
import Model.dao.ClienteDao;
import Model.dao.Ordem_ServicoDao;
import controller.ClienteController;
import controller.OrdemServicoController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.JTextArea;

public class ScreenOrdemServico extends JDialog {

	private NumberFormat amountFormat;
	private double amount = 00.00;
	
	private int valorSelecionado = 0;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField txtData;
	private JFormattedTextField txtPreco;
	private JLabel lblValidation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ScreenOrdemServico dialog = new ScreenOrdemServico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ScreenOrdemServico() {
		setBounds(100, 100, 648, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("Cliente:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(103, 45, 40, 14);
		contentPanel.add(label);
		
		JComboBox cbCliente = new JComboBox();
		cbCliente.setModel(new DefaultComboBoxModel(new String[] {"Selecione um Cliente"}));
		cbCliente.setBounds(153, 23, 249, 22);
		viewComboBox(cbCliente);
		contentPanel.add(cbCliente);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(153, 59, 249, 20);
		contentPanel.add(textField);
		
		JLabel label_1 = new JLabel("Computador:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(73, 93, 70, 14);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("Data de Entrada:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(53, 310, 90, 14);
		contentPanel.add(label_2);
		
		txtData = new JTextField();
		txtData.setColumns(10);
		txtData.setBounds(153, 308, 107, 20);
		contentPanel.add(txtData);
		
		JLabel label_3 = new JLabel("Valor:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(112, 341, 31, 14);
		contentPanel.add(label_3);
		
		txtPreco = new JFormattedTextField(amountFormat);
		txtPreco.setValue(new Double(amount));
		txtPreco.setColumns(10);
		//txtPreco.addPropertyChangeListener("value", (PropertyChangeListener) this);
		
		txtPreco.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				try {
					float preco = Float.parseFloat(txtPreco.getText()+e.getKeyChar());
					lblValidation.setText("");
				} catch (NumberFormatException e2) {
					lblValidation.setText("Digite apenas numeros");
				}
				
			}
		});
		txtPreco.setColumns(10);
		txtPreco.setBounds(153, 339, 107, 20);
		contentPanel.add(txtPreco);
		
		JButton bntCadastrar = new JButton("Cadastrar");
		bntCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bntCadastrarActionPermorfed(e, cbCliente);
				
			}
		});
		
		lblValidation = new JLabel("");
		lblValidation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblValidation.setForeground(Color.RED);
		lblValidation.setBounds(153, 370, 140, 14);
		contentPanel.add(lblValidation);
		bntCadastrar.setBounds(483, 332, 96, 31);
		contentPanel.add(bntCadastrar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione um computador"}));
		comboBox.setBounds(153, 90, 249, 22);
		contentPanel.add(comboBox);
		
		JLabel lblProblemaApresentado = new JLabel("Problema Apresentado:");
		lblProblemaApresentado.setBounds(28, 219, 115, 14);
		contentPanel.add(lblProblemaApresentado);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(153, 214, 298, 83);
		contentPanel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(153, 136, 298, 67);
		contentPanel.add(textArea_1);
		
		JLabel lblConfigurao = new JLabel("Configura\u00E7\u00E3o:");
		lblConfigurao.setBounds(73, 141, 70, 14);
		contentPanel.add(lblConfigurao);
	}
	
	public void bntCadastrarActionPermorfed(java.awt.event.ActionEvent evt, JComboBox cbCliente) {
	
		OrdemServicoController.cadastrarOrdemServico((String)cbCliente.getSelectedItem() ,txtData.getText(), txtPreco.getText());

	}
	
	public void viewComboBox(JComboBox cbCliente) {
		for (String[] c: ClienteController.lerClientes()) {
			cbCliente.addItem(c[0]+"-"+c[1]);
		}

	}
	
	public int getValorSelecionado() {
	    return valorSelecionado;
	}
}

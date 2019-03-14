package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Model.Cliente;
import Model.Ordem_Servico;
import Model.Status;
import Model.dao.ClienteDao;
import Model.dao.Ordem_ServicoDao;
import controller.OrdemServicoController;

import javax.swing.JSeparator;
import javax.swing.JButton;

public class ScreenRelatorio extends JFrame {

	private JPanel contentPane;
	private JTextField txtCliente;
	private JRadioButton rbCliente;
	private JComboBox cbStatus;
	private JRadioButton rbStatus;
	private ButtonGroup bntGroup;
	private JTable jTRelatorio;
	private JSeparator separator;
	private JRadioButton rbTodosServicos;
	private JButton btnExcluir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScreenRelatorio frame = new ScreenRelatorio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ScreenRelatorio() {
		
		initialize();
		//this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		DefaultTableModel modelo = (DefaultTableModel) jTRelatorio.getModel();
        jTRelatorio.setRowSorter(new TableRowSorter(modelo));
        
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		bntExcluirPerformedAction(e);
        		
        	}
        });
        btnExcluir.setBounds(433, 164, 89, 23);
        contentPane.add(btnExcluir);
        
        readJTable();

	}
	
	public void initialize() {
		setTitle("Consultar Ordem de Srvi\u00E7o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 558, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consultar por:");
		lblNewLabel.setBounds(21, 25, 86, 14);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(lblNewLabel);
		
		txtCliente = new JTextField();
		txtCliente.setEnabled(false);
		txtCliente.setBounds(193, 76, 166, 20);
		contentPane.add(txtCliente);
		txtCliente.setColumns(10);
		
		bntGroup = new ButtonGroup();
		
		rbCliente = new JRadioButton("Cliente");
		rbCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSelecionarCLiente(e);
				
			}
		});
		rbCliente.setBounds(78, 75, 109, 23);
		contentPane.add(rbCliente);
		
		bntGroup.add(rbCliente);

		cbStatus = new JComboBox();
		cbStatus.setEnabled(false);
		cbStatus.setModel(new DefaultComboBoxModel(new String[] {"Selecione um status", "Pendente", "Finalizado"}));
		cbStatus.setBounds(193, 105, 166, 22);
		contentPane.add(cbStatus);
		
		rbStatus = new JRadioButton("Status do servi\u00E7o");
		rbStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rbSelecionarStatus(e);
								
			}
		});
		rbStatus.setBounds(78, 105, 109, 23);
		contentPane.add(rbStatus);
		bntGroup.add(rbStatus);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 198, 542, 264);
		contentPane.add(panel);
		panel.setLayout(null);
		
		separator = new JSeparator();
        separator.setBounds(0, 151, 542, 2);
        contentPane.add(separator);
        
        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		bntSalvarActionPerformed(e);
        		
        	}
        });
        btnSalvar.setBounds(332, 164, 91, 23);
        contentPane.add(btnSalvar);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		bntPesquisarActionPerformed(e);
        		
        	}
        });
        btnPesquisar.setBounds(407, 60, 99, 23);
        contentPane.add(btnPesquisar);
        
        rbTodosServicos = new JRadioButton("Todas as Ordens Servi\u00E7o");
        rbTodosServicos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		rbSelecionarTodosServicos(e);
        		
        	}
        });
        rbTodosServicos.setBounds(78, 46, 166, 23);
        contentPane.add(rbTodosServicos);
        bntGroup.add(rbTodosServicos);
        
        JButton btnMudarStatus = new JButton("Mudar Status");
        btnMudarStatus.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		bntMudarStatusActionPerformed(e);
        		
        	}
        });
        btnMudarStatus.setBounds(207, 164, 115, 23);
        contentPane.add(btnMudarStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 522, 264);
		panel.add(scrollPane);
		
		jTRelatorio = new JTable();
		jTRelatorio.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome Cliente", "Data entrada", "Data saida", "ID Serviço", "Valor", "Status"
			}
		));
		scrollPane.setViewportView(jTRelatorio);
		
	}
	public void rbSelecionarTodosServicos(java.awt.event.ActionEvent evt) {
		bntGroup.clearSelection();
		
		if(rbCliente.isEnabled()) {	
			txtCliente.setEnabled(false);
			cbStatus.setEnabled(false);
		}//else {
			//txtCliente.setEnabled(false);
		//}
		rbTodosServicos.setSelected(true);
	}
	
	public void rbSelecionarCLiente(java.awt.event.ActionEvent evt) {
		bntGroup.clearSelection();
		
		if(rbCliente.isEnabled()) {	
			txtCliente.setEnabled(true);
			cbStatus.setEnabled(false);
		}else {
			txtCliente.setEnabled(false);
		}
		rbCliente.setSelected(true);
	}
	
	
	public void rbSelecionarStatus(java.awt.event.ActionEvent evt) {
		bntGroup.clearSelection();			
		if(rbStatus.isEnabled()) {
			cbStatus.setEnabled(true);
			txtCliente.setEnabled(false);
		}else {
			cbStatus.setEnabled(false);
		}
		rbStatus.setSelected(true);
	}
	
	public void bntPesquisarActionPerformed(java.awt.event.ActionEvent evt) {
		if (txtCliente.isEnabled()) {
			readJTable(txtCliente.getText());
		}else if (cbStatus.isEnabled()) {
			readJTable((String)cbStatus.getSelectedItem());
		}else if (rbTodosServicos.isSelected()) {
			readJTable();
		}else {
			JOptionPane.showMessageDialog(null, "Escolha um tipo de pesquisa");
		}
	}
	
	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel) jTRelatorio.getModel();
    	modelo.setNumRows(0);    	
    	/* 
    	 * A coluna Data Entrada é o objeto Ordem_Servico
    	 * Na classe Ordem_Servico o metodo toString foi sobreescrito
    	 * O parametro 1 em os.Dao executa o select com ordens de serviços
    	*/    	
    	
    	for(String[] os: OrdemServicoController.lerRelatorios("1", true)) {
    		modelo.addRow(new Object [] {
    				os[6], 	// Nome Cliente
    				os[1],	// Data entrada OS
    				os[2],	// Data Saída
    				os[0],	// ID Serviço
    				os[4],	// Valor
    				os[3]	// Status OS
    			
    		});
    	}
    	
    }
	
	public void readJTable(String pesquisa) {
		DefaultTableModel modelo = (DefaultTableModel) jTRelatorio.getModel();
    	modelo.setNumRows(0);
    	
    	Ordem_ServicoDao osDao = new Ordem_ServicoDao();
    	
    	/* 
    	 * A coluna Data Entrada é o objeto Ordem_Servico
    	 * Na classe Ordem_Servico o metodo toString foi sobreescrito
    	 * O parametro 1 em os.Dao executa o select com ordens de serviços
    	*/	
    	
    	for(String[] os: OrdemServicoController.lerRelatorios(pesquisa, false)) {
    		modelo.addRow(new Object [] {
    				os[6], 	// Nome Cliente
    				os[1],	// Data entrada OS
    				os[2],	// Data Saída
    				os[0],	// ID Serviço
    				os[4],	// Valor
    				os[3]	// Status OS
    			
    		});
    	}
    	
    	
    }
	
	public void bntMudarStatusActionPerformed(java.awt.event.ActionEvent evt){
		
		if(jTRelatorio.getSelectedRow() != -1){	
			String idOS = (String) jTRelatorio.getValueAt(jTRelatorio.getSelectedRow(), 3);
			OrdemServicoController.mudarStatus(idOS);
			readJTable();
			
		}else {
			JOptionPane.showMessageDialog(null,"Selecione um produto para Mudar o status");
		}
		
	}
	
	public void bntSalvarActionPerformed(java.awt.event.ActionEvent evt) {
		if(jTRelatorio.getSelectedRow() != -1){
			String idOS = (String) jTRelatorio.getValueAt(jTRelatorio.getSelectedRow(), 3);
			
			String dataSaida = (String) jTRelatorio.getValueAt(jTRelatorio.getSelectedRow(), 2);

			float valor = Float.parseFloat((String) jTRelatorio.getValueAt(jTRelatorio.getSelectedRow(), 4));
			
			OrdemServicoController.salvarServico(idOS, dataSaida, valor);
			
			readJTable();
		}else {
			JOptionPane.showMessageDialog(null,"Selecione um produto para Alterar");
		}
	}
	
	public void bntExcluirPerformedAction(java.awt.event.ActionEvent evt) {
		if(jTRelatorio.getSelectedRow() != -1){
			String idOS = (String) jTRelatorio.getValueAt(jTRelatorio.getSelectedRow(), 3);
			OrdemServicoController.excluirServico(idOS);
			readJTable();
			
		}else{
	        JOptionPane.showMessageDialog(null,"Selecione um Orçamento para Excluir");
	    }
	}
	
	
}

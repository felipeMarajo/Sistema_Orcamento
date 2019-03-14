package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.OrdemServicoController;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Launch {

	private JFrame frame;
	private JTable jTServicos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launch window = new Launch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launch() {
		initialize();		
		DefaultTableModel modelo = (DefaultTableModel) jTServicos.getModel();
        jTServicos.setRowSorter(new TableRowSorter(modelo));
        
        readJTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 606, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem menuOrcamento = new JMenuItem("Novo Or\u00E7amento");
		menuOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bntNewOrcamentoActionPerformed(e);
				
			}
		});
		menuOrcamento.setIcon(new ImageIcon(Launch.class.getResource("/Image/icon_OS2.png")));
		menuOrcamento.setSelectedIcon(null);
		menuBar.add(menuOrcamento);
		
		JMenuItem menuRelatorio = new JMenuItem("Relat\u00F3rio");
		menuRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bntRalatorioActionPerformed(e);
				
			}
		});
		menuRelatorio.setIcon(new ImageIcon(Launch.class.getResource("/Image/icon_relatorio.png")));
		menuBar.add(menuRelatorio);
		
		JMenuItem menuClientes = new JMenuItem("Clientes");
		menuClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bntClientesActionPerformed(e);
				
			}
		});
		menuClientes.setIcon(new ImageIcon(Launch.class.getResource("/Image/icon_clientes.png")));
		menuBar.add(menuClientes);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 54, 590, 265);
		frame.getContentPane().add(scrollPane);
		
		jTServicos = new JTable();
		jTServicos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Cliente", "Nome Cliente", "Data de Entrada", "ID Serviço", "Status"
			}
		));
		scrollPane.setViewportView(jTServicos);
		
		JLabel lblServiosPendentes = new JLabel("Servi\u00E7os Pendentes");
		lblServiosPendentes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblServiosPendentes.setBounds(10, 20, 126, 19);
		frame.getContentPane().add(lblServiosPendentes);
		
		JButton btnMudarStatus = new JButton("Mudar Status Servi\u00E7o");
		btnMudarStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				bntMudarStatusActionPerformed(e);
				
				
			}
		});
		btnMudarStatus.setBounds(406, 20, 174, 23);
		frame.getContentPane().add(btnMudarStatus);
	}
	
	//	Metodo que atualiza a tabela
	public void readJTable() {
    	DefaultTableModel modelo = (DefaultTableModel) jTServicos.getModel();
    	modelo.setNumRows(0);
    	 
    	// O parametro 2 em lerRelatorios executa o select com ordens de serviços pendentes	
    	for(String[] os: OrdemServicoController.lerRelatorios("2", true)) {
    		modelo.addRow(new Object [] {
    				os[0],	// ID Ordem de Serviço
    				os[6], 	// Nome Cliente
    				os[1],	// Data entrada OS
    				os[0],	// IS OS
    				os[3]	// Status OS
    			
    		});
    	}
    	
    }
	
	public void bntNewOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {
		//ScreenOrdemServico ordemServico = new ScreenOrdemServico();
		//ordemServico.main(null);
		
		ScreenOrdemServico t = new ScreenOrdemServico();
		t.setModal(true);
		t.setVisible(true);
		int aux = t.getValorSelecionado();
		
		if (aux == 0) {
			readJTable();
		}
		
	}
	
	public void bntRalatorioActionPerformed(java.awt.event.ActionEvent evt) {
		ScreenRelatorio tela = new ScreenRelatorio();
		tela.main(null);
	}
	
	// Botão que direciona o programa para a tela de CRUD de clientes
	public void bntClientesActionPerformed(java.awt.event.ActionEvent evt) {
		ScreenClientes tela = new ScreenClientes();
		tela.main(tela);
	}
	
	// Altera o status de uma ordem de serviço
	public void bntMudarStatusActionPerformed(java.awt.event.ActionEvent evt){
		
		if(jTServicos.getSelectedRow() != -1){	
			String idOS =  (String) jTServicos.getValueAt(jTServicos.getSelectedRow(), 3);
			OrdemServicoController.mudarStatus(idOS);
			readJTable();
			
		}else {
			JOptionPane.showMessageDialog(null,"Selecione um produto para Mudar o status");
		}
		
	}   
	
	
}

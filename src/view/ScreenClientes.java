package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.ClienteController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class ScreenClientes {

	private JFrame frmEditarClientes;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTable jTClientes;
	private JButton bntSalvar;

	/**
	 * Launch the application.
	 */
	public static void main(ScreenClientes window) {//String[] args
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//ScreenClientes window = new ScreenClientes();
					window.frmEditarClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ScreenClientes() {
		initialize();
        DefaultTableModel modelo = (DefaultTableModel) jTClientes.getModel();
        jTClientes.setRowSorter(new TableRowSorter(modelo));
        
        readJTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEditarClientes = new JFrame();
		frmEditarClientes.setTitle("Editar Clientes");
		frmEditarClientes.setIconImage(Toolkit.getDefaultToolkit().getImage(ScreenClientes.class.getResource("/Image/icon_clientes.png")));
		frmEditarClientes.setBounds(100, 100, 470, 427);
		frmEditarClientes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEditarClientes.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 453, 142);
		frmEditarClientes.getContentPane().add(panel);
		
		bntSalvar = new JButton();
		bntSalvar.setBounds(21, 90, 81, 23);
		bntSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSalvarActionPerformed(e);
			}
		});
		bntSalvar.setText("Salvar");
		
		JButton bntExcluir = new JButton();
		bntExcluir.setBounds(148, 90, 79, 23);
		bntExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExcluirActionPerformed(e);
			}
		});
		bntExcluir.setText("Excluir");
		
		JLabel label = new JLabel();
		label.setBounds(21, 21, 43, 14);
		label.setText("Nome");
		
		txtNome = new JTextField();
		txtNome.setBounds(21, 42, 243, 20);
		
		txtTelefone = new JTextField();
		txtTelefone.setBounds(304, 42, 102, 20);
		
		JLabel label_1 = new JLabel();
		label_1.setBounds(304, 21, 57, 14);
		label_1.setText("Telefone");
		panel.setLayout(null);
		panel.add(bntSalvar);
		panel.add(bntExcluir);
		panel.add(txtNome);
		panel.add(label);
		panel.add(txtTelefone);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 142, 453, 246);
		frmEditarClientes.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 453, 246);
		panel_1.add(scrollPane);
		
		jTClientes = new JTable();
		jTClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jTProdutosMouseClicked(e);
			}
		});
		jTClientes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				jTProdutosKeyReleased(e);
			}
		});
		jTClientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID Cliente", "Nome", "Telefone"
			}
		));
		scrollPane.add(jTClientes);
		scrollPane.setViewportView(jTClientes);
		//scrollPane.setColumnHeaderView(table);
	}
	
	//	Escreve as informações na tabela e atualiza informações
	public void readJTable() {
    	DefaultTableModel modelo = (DefaultTableModel) jTClientes.getModel();
    	modelo.setNumRows(0);
    	
    	//ClienteController.lerClientes();
    	for (String[] c: ClienteController.lerClientes()) {
    		modelo.addRow(new Object [] {
    				c[0],
    				c[1],
    				c[2]
    		});
		}
    	
    }
	
	private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
	
		if(jTClientes.getSelectedRow() != -1){
			
			int id = Integer.parseInt((String) jTClientes.getValueAt(jTClientes.getSelectedRow(), 0));
			ClienteController.atualizarCliente(id, txtNome.getText(), txtTelefone.getText());
            
		}else if(!txtNome.getText().isEmpty()) {
			ClienteController.cadastrarCliente(txtNome.getText(), txtTelefone.getText());
		
		}else {
			JOptionPane.showMessageDialog(null, "Selecione um cliente para editar ou cadastre um novo cliente");
		}
		
		readJTable();
		txtNome.setText("");
		txtTelefone.setText("");
	        
    }
	
/*
	//	Metodo usado na ação do botão salvar
	public void cadastrar() {
		System.out.println("Campo nome "+txtNome.getText());
		Cliente c = new Cliente(-1, txtNome.getText(), txtTelefone.getText());
		ClienteDao Cdao = new ClienteDao();
		Cdao.create(c);
		
		txtNome.setText("");
		txtTelefone.setText("");
		
		readJTable();
	}
	
	//	Metodo usado na ação do botão salvar
	
	
	public void atualizar() {
		int id = (int) jTClientes.getValueAt(jTClientes.getSelectedRow(), 0);
    	Cliente c = new Cliente(id, txtNome.getText(), txtTelefone.getText());
        ClienteDao Cdao = new ClienteDao();
        
        Cdao.update(c);
    	
        txtNome.setText("");
        txtTelefone.setText("");
        
        readJTable();
	}
*/
	
	private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
	    
		// if para saber se a linha está selecionada
	    if(jTClientes.getSelectedRow() != -1){
	    	
	    	int id = Integer.parseInt((String) jTClientes.getValueAt(jTClientes.getSelectedRow(), 0));
	        ClienteController.excluirCliente(id, txtNome.getText(), txtTelefone.getText());

	        txtNome.setText("");
	        txtTelefone.setText("");
	        readJTable();
	    	
	    }else{
	        JOptionPane.showMessageDialog(null,"Selecione um cliente para Excluir");
	    }
	}

	
    private void jTProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTProdutosMouseClicked
        // TODO add your handling code here:
        if(jTClientes.getSelectedRow() != -1){
            txtNome.setText(jTClientes.getValueAt(jTClientes.getSelectedRow(), 1).toString());
            txtTelefone.setText(jTClientes.getValueAt(jTClientes.getSelectedRow(), 2).toString());
        }
    }//GEN-LAST:event_jTProdutosMouseClicked

    private void jTProdutosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTProdutosKeyReleased
        // TODO add your handling code here:
        if(jTClientes.getSelectedRow() != -1){
            txtNome.setText(jTClientes.getValueAt(jTClientes.getSelectedRow(), 0).toString());
            txtTelefone.setText(jTClientes.getValueAt(jTClientes.getSelectedRow(), 2).toString());
        }
    }//GEN-LAST:event_jTProdutosKeyReleased

    

}

package Zv;



import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JButton;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import java.awt.Font;


@SuppressWarnings("serial")
public class FrameTextField extends JFrame 
{

	private JPanel contentPane;
	private JTextField txtNama;
	private JLabel lblAlamat;
	private JTextArea textAlamat;
	private JTextField txtTelp;
	private JLabel lblBg;
	private JLabel lblAuto;

	/**
	 * Create the frame.
	 */
	public FrameTextField() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNama = new JLabel("Nama : ");
		lblNama.setBounds(12, 17, 70, 15);
		contentPane.add(lblNama);
		
		txtNama = new JTextField();
		txtNama.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
					Connection konek = Koneksi.getKoneksi();
					Statement state = konek.createStatement();
					String query = "SELECT Alamat,Telp FROM Data WHERE Nama = '"+txtNama.getText()+"'";
					ResultSet rs = state.executeQuery(query);
					while(rs.next())
					{
						textAlamat.setText(rs.getString(1));
						txtTelp.setText(rs.getString(2));
					}
					rs.close();
					state.close();
				}
				
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
		txtNama.setBounds(91, 10, 245, 29);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		setLocationRelativeTo(null);
		
		txtNama.addKeyListener(new keyTextField(txtNama));
		
		lblAlamat = new JLabel("Alamat : ");
		lblAlamat.setBounds(12, 51, 70, 15);
		contentPane.add(lblAlamat);
		
		textAlamat = new JTextArea();
		textAlamat.setBounds(91, 51, 275, 103);
		contentPane.add(textAlamat);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/Simpan.png"));
		btnSimpan.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent act) 
			{
				try
				{
				Connection konek = Koneksi.getKoneksi();
				String query = "INSERT INTO Data VALUES(?,?,?)";
				PreparedStatement prepare = konek.prepareStatement(query);
				
				prepare.setString(1,txtNama.getText());
				prepare.setString(2,textAlamat.getText());
				prepare.setString(3,txtTelp.getText());
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null,"Data berhasil disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
				prepare.close();
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null,"Data gagal disimpan","Pesan",JOptionPane.INFORMATION_MESSAGE);
					System.out.println(ex);
				}
				finally
				{
					txtNama.setText("");
					textAlamat.setText("");
					txtTelp.setText("");
					txtNama.requestFocus();
				}
				
			}
		});
		btnSimpan.setBounds(91, 220, 130, 44);
		contentPane.add(btnSimpan);
		
		JButton btnKeluar = new JButton("Keluar");
		btnKeluar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent act)
			{
				JOptionPane.showMessageDialog(null, "Terima kasih","Pesan",JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		});
		btnKeluar.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/hapus.png"));
		btnKeluar.setBounds(266, 220, 130, 44);
		contentPane.add(btnKeluar);
		
		JLabel lblNotelp = new JLabel("No.Telp : ");
		lblNotelp.setBounds(12, 176, 80, 15);
		contentPane.add(lblNotelp);
		
		txtTelp = new JTextField();
		txtTelp.setBounds(91, 166, 233, 27);
		contentPane.add(txtTelp);
		txtTelp.setColumns(10);
		
		lblAuto = new JLabel("* AutoComplete Text");
		lblAuto.setFont(new Font("FreeSerif", Font.PLAIN, 15));
		lblAuto.setBounds(348, 17, 179, 15);
		contentPane.add(lblAuto);
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/mobil.png"));
		lblIcon.setBounds(398, 70, 159, 135);
		contentPane.add(lblIcon);
		
		lblBg = new JLabel("");
		lblBg.setIcon(new ImageIcon("/home/resa/Aplikasi Java/SwingComponents/src/Gambar/pinkBg.jpg"));
		lblBg.setBounds(0, 0, 589, 296);
		contentPane.add(lblBg);
		
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					FrameTextField frame = new FrameTextField();
					frame.setVisible(true);
				} 
				catch (UnsupportedLookAndFeelException e) {
				} 
				catch (ClassNotFoundException e){
				} 
				catch (InstantiationException e) {
				} 
				catch (IllegalAccessException e) {
				}
			}
		});
	}
}

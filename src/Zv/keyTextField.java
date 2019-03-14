package Zv;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import java.sql.*;

public class keyTextField extends KeyAdapter
{
	private JTextField txtField;
	@SuppressWarnings("rawtypes")
	private List daftar;
	
	@SuppressWarnings("rawtypes")
	public keyTextField(JTextField txtFieldParam)
	{
		txtField = txtFieldParam;
		daftar = new ArrayList();
		databaseNama();
	}
	
	public void keyPressed(KeyEvent key)
	{
		switch(key.getKeyCode())
		{
		case KeyEvent.VK_BACK_SPACE : 
										break;
		case KeyEvent.VK_ENTER : 
										txtField.setText(txtField.getText());
										break;
		default : 
										EventQueue.invokeLater(new Runnable() 
										{
											@Override
											public void run() 
											{
												// TODO Auto-generated method stub
												String kt = txtField.getText();
												autoComplete(kt);
												
											}
										});
		}
										
	}
	
	public void autoComplete(String kt)
	{
		String complete = "";
		int start = kt.length();
		int last = kt.length();
		int a;
		
		for(a=0;a<daftar.size();a++)
		{
			if(daftar.get(a).toString().startsWith(kt))
			{
				complete = daftar.get(a).toString();
				last = complete.length();
				break;
			}
		}
		if(last>start)
		{
			txtField.setText(complete);
			txtField.setCaretPosition(last);
			txtField.moveCaretPosition(start);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void databaseNama()
	{
		try
		{
			Connection konek = Koneksi.getKoneksi();
			Statement state = konek.createStatement();
			String query = "SELECT Nama FROM Data";
			ResultSet rs = state.executeQuery(query);
			while(rs.next())
			{
				daftar.add(rs.getString(1));
			}
			rs.close();
			state.close();
		}
		
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
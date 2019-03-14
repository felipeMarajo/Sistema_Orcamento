package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.List;

import Model.Cliente;
import connection.ConnectionFactory;

public class ClienteDao {

	public void create(Cliente c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO tb_cliente (nome, telefone) VALUES(?, ?)");
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ex);

		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Cliente> read(){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Cliente> listaClientes = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM tb_cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("telefone"));
				listaClientes.add(cliente);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao buscar a informaçõa: "+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return listaClientes;
		
	}
	
	public void update(Cliente c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("UPDATE tb_cliente SET nome = ?, telefone = ? WHERE id_cliente = ?");
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setInt(3, c.getIdCliente());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);

		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Cliente c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("DELETE FROM tb_cliente WHERE id_cliente = ?");
			stmt.setInt(1, c.getIdCliente());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);

		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

public Cliente read(int id){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
//		List<Cliente> listaClientes = new ArrayList<>();
		Cliente cliente = null;
		
		try {
			stmt = con.prepareStatement("SELECT * FROM tb_cliente WHERE id_cliente = ? ");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("telefone"));
				//listaClientes.add(cliente);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Erro ao buscar a informaçõa: "+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return cliente;
		
	}
	
}

package Model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Model.Cliente;
import Model.Ordem_Servico;
import Model.Status;
import connection.ConnectionFactory;

public class Ordem_ServicoDao {
	
	public void create(Ordem_Servico os) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("INSERT INTO tb_ordemservico (dataEnt, id_cliente, statusP) VALUES(?, ?, ?)");
			stmt.setString(1, os.getDataEntrada());
			stmt.setInt(2, os.getCliente().getIdCliente());
			stmt.setString(3, os.getStatus().enumToString(os.getStatus()));
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Ordem de Serviço cadastrada");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao cadastrar: "+ex);

		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	
	//	O parametro tipo refere-se a qual select será executado
	// 	Tipo 1 busca todos as ordens de Serviço
	//	Tipo 2 Busca apenas ordens de serviços que estão pendente
	public List<Ordem_Servico> read(String tipo, boolean nUsado){
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Ordem_Servico> listaServicos = new ArrayList<>();
		
		try {
			if(tipo.equals("1")) {
				stmt = con.prepareStatement("select * from tb_cliente c join tb_ordemservico os on c.id_cliente = os.id_cliente;");
				
			}else if (tipo.equals("2")) {
				stmt = con.prepareStatement("select * from tb_cliente c join tb_ordemservico os on c.id_cliente = os.id_cliente AND os.statusP = 'Pendente';");
			}
			rs = stmt.executeQuery();
			
			Status st = null;
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("telefone"));
				
				if(rs.getString("statusP").equals("Pendente")){
					st = Status.PENDENTE;
				}else{
					st = Status.FINALIZADO;
				}
				
				Ordem_Servico servico = new Ordem_Servico(rs.getInt("id_OS"), cliente, rs.getString("dataEnt"), rs.getString("dataSai"), rs.getFloat("valor"), st);
				
				listaServicos.add(servico);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nenhum Serviço Cadastrado: "+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return listaServicos;
		
	}
	
	
	/*
	 * Recebe o nome de um cliente ou o status da Ordem de Serviço presente no combobox
	 * */
	public List<Ordem_Servico> read(String pesquisa){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		List<Ordem_Servico> listaServicos = new ArrayList<>();
		
		try {
		
			stmt = con.prepareStatement("select * from tb_cliente c join tb_ordemservico os on c.id_cliente = os.id_cliente AND (os.statusP = ? OR id_OS = ? OR c.nome LIKE ?);");
			stmt.setString(1, pesquisa);
			stmt.setString(2, pesquisa);
			stmt.setString(3, "%"+pesquisa+"%");
			rs = stmt.executeQuery();
			
			Status st = null;
			
			while(rs.next()) {
				
				Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome"), rs.getString("telefone"));
				
				if(rs.getString("statusP").equals("Pendente")){
					st = Status.PENDENTE;
				}else{
					st = Status.FINALIZADO;
				}
				
				Ordem_Servico servico = new Ordem_Servico(rs.getInt("id_OS"), cliente, rs.getString("dataEnt"), rs.getString("dataSai"), rs.getFloat("valor"), st);
				
				listaServicos.add(servico);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Nenhum Serviço Cadastrado: "+e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return listaServicos;
		
	}

	public void update(Ordem_Servico os) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("UPDATE tb_ordemservico SET dataEnt = ?, dataSai = ?, valor = ?, statusP = ? WHERE id_OS = ?");
			stmt.setString(1, os.getDataEntrada());
			stmt.setString(2, os.getDatasaida());
			stmt.setFloat(3, os.getValor());
			stmt.setString(4, os.getStatus().enumToString(os.getStatus()));
			stmt.setInt(5, os.getIdordemservicao());
			//stmt.setInt(5, os.getCliente().getIdCliente());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Orde de Serviço atualizado com sucesso");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao atualizar: "+ex);
	
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void delete(Ordem_Servico os) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("DELETE FROM tb_ordemservico WHERE id_OS = ?");
			stmt.setInt(1, os.getIdordemservicao());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: "+ex);

		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	
}

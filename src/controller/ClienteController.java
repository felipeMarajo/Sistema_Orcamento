package controller;

import java.util.ArrayList;

import Model.Cliente;
import Model.dao.ClienteDao;

public class ClienteController {
	
	public static void cadastrarCliente(String nome, String telefone) {
		
		Cliente c = new Cliente(-1, nome, telefone);
		ClienteDao Cdao = new ClienteDao();
		Cdao.create(c);
		
	}
	
	public static void atualizarCliente(int id, String nome, String telefone) {
	
    	Cliente c = new Cliente(id, nome, telefone);
        ClienteDao Cdao = new ClienteDao();
        
        Cdao.update(c);
		
	}
	
	public static void excluirCliente(int id, String nome, String telefone) {
    	
    	Cliente c = new Cliente(id, nome, telefone);
        ClienteDao Cdao = new ClienteDao();
        
        Cdao.delete(c);
	}
	
	public static ArrayList<String[]> lerClientes(){
		ArrayList<String[]> clientes = new ArrayList();
		
		ClienteDao cDao = new ClienteDao();
		
    	ArrayList<Cliente> clientesObj = (ArrayList<Cliente>) cDao.read();
		
    	for(int i=0; i<clientesObj.size(); i++) {
    		String aux[] = new String[3];
    		aux[0] = String.valueOf(clientesObj.get(i).getIdCliente());
    		aux[1] = clientesObj.get(i).getNome();
    		aux[2] = clientesObj.get(i).getTelefone();
    		
    		clientes.add(aux);
    	}
    	
    	return clientes;
	}
	

}

package Model;

public class Cliente {
	
	private int idCliente;
	private String nome;
	private String telefone;
	
	
	public Cliente(int id, String nome, String telefone) {
		if(id != -1) {
			this.idCliente = id;
		}
		this.nome = nome;
		this.telefone = telefone;

	}

	
	// Gets and Sets
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getNome();
	}

	
}

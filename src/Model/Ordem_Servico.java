package Model;

public class Ordem_Servico {
	
	private int idordemservicao;
	private Cliente cliente;
	//private Computador computador;
	private String dataEntrada;
	private String datasaida;
	private float valor;
	private Status status;
	
	
	public Ordem_Servico(int idordemservicao, Cliente cliente, String dataEntrada,
			String datasaida, float valor, Status status) {
		
		if(idordemservicao != -1) {
			this.idordemservicao = idordemservicao;
		}
		this.cliente = cliente;
		//this.computador = computador;
		this.dataEntrada = dataEntrada;
		this.datasaida = datasaida;
		this.valor = valor;
		this.status = status;
	}
	//	Possiveis Metodos
	
	/*
	 * public void mudar status
	 * */
	
	// Gets and Sets
	public int getIdordemservicao() {
		return idordemservicao;
	}
	public void setIdordemservicao(int idordemservicao) {
		this.idordemservicao = idordemservicao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	/*
	public Computador getComputador() {
		return computador;
	}
	public void setComputador(Computador computador) {
		this.computador = computador;
	}*/

	public String getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public String getDatasaida() {
		return datasaida;
	}
	public void setDatasaida(String datasaida) {
		this.datasaida = datasaida;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getDataEntrada();
	}
	
	
}
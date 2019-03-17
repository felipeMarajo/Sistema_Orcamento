package Model;

public class Computador {
	
	private int idComputador;
	private String modelo;
	
	
	public Computador(int idComputador, String modelo) {
		this.idComputador = idComputador;
		this.modelo = modelo;
	}

	// Gets and Sets
	public int getIdComputador() {
		return idComputador;
	}
	
	public void setIdComputador(int idComputador) {
		this.idComputador = idComputador;
	}
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}

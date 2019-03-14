package Model;

public class Computador {
	
	private String defeito;
	private String resolucao;
	


	public Computador(String defeito, String resolucao) {
		this.defeito = defeito;
		this.resolucao = resolucao;
	}

	// Gets and Sets
	public String getDefeito() {
		return defeito;
	}

	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	
	public String getResolucao() {
		return resolucao;
	}

	public void setResolucao(String resolucao) {
		this.resolucao = resolucao;
	}

}

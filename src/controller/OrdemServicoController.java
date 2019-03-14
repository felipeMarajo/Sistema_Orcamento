package controller;

import java.util.ArrayList;
import java.util.List;

import Model.Cliente;
import Model.Ordem_Servico;
import Model.Status;
import Model.dao.ClienteDao;
import Model.dao.Ordem_ServicoDao;

public class OrdemServicoController {
	
	public static void cadastrarOrdemServico(String clienteCB, String dataEntr, String preco) {
		float valor = 0.0f;
		if (Float.parseFloat(preco) >= 0.0f) {
			valor = 0.0f;
			System.out.println("Entrou no if");
		}else {
			valor = Float.parseFloat(preco);
		}
		
		System.out.println("PREÇO "+valor);
		ClienteDao cDao = new ClienteDao();
		Cliente cliente = cDao.read(Integer.parseInt(clienteCB.split("-")[0]));
		
		Ordem_ServicoDao osDao = new Ordem_ServicoDao();
		
		
		Ordem_Servico os = new Ordem_Servico(-1, cliente, dataEntr, " ", valor, Status.PENDENTE);
		osDao.create(os);
	}
	
	// O parametro tipo executa o select com ordens de serviços pendentes ou todos os serviços
	public static ArrayList<String[]> lerRelatorios(String pesquisa, boolean modo) {		
		
		ArrayList<String[]> ordemServico = new ArrayList();
		
		Ordem_ServicoDao osDao = new Ordem_ServicoDao();
		
		ArrayList<Ordem_Servico> osObjeto;
		if (modo == true) {
			osObjeto = (ArrayList<Ordem_Servico>) osDao.read(pesquisa, modo);
		}else {
			osObjeto = (ArrayList<Ordem_Servico>) osDao.read(pesquisa);
		}
		
		for (int i = 0; i < osObjeto.size(); i++) {
			String aux[] = new String[8];
			aux[0] = String.valueOf(osObjeto.get(i).getIdordemservicao());
			aux[1] = osObjeto.get(i).getDataEntrada();
			aux[2] = osObjeto.get(i).getDatasaida();
			aux[3] = osObjeto.get(i).getStatus().enumToString(osObjeto.get(i).getStatus());
			aux[4] = String.valueOf(osObjeto.get(i).getValor());
			aux[5] = String.valueOf(osObjeto.get(i).getCliente().getIdCliente());
			aux[6] = String.valueOf(osObjeto.get(i).getCliente().getNome());
			aux[7] = String.valueOf(osObjeto.get(i).getCliente().getTelefone());
			
			ordemServico.add(aux);
		}
		return ordemServico;
	}
	
	public static void mudarStatus(String idOS) {
		
		Ordem_ServicoDao osDao = new Ordem_ServicoDao();
		List<Ordem_Servico> osObjeto = osDao.read(idOS);
		
		Ordem_Servico updateOS = new Ordem_Servico(osObjeto.get(0).getIdordemservicao(), osObjeto.get(0).getCliente(), 
				osObjeto.get(0).getDataEntrada(), osObjeto.get(0).getDatasaida(), osObjeto.get(0).getValor(), Status.FINALIZADO);  	
		osDao.update(updateOS);
	}
	
	
	public static void salvarServico(String idOS, String dataSaida, float valor) {
		
		Ordem_ServicoDao osDao = new Ordem_ServicoDao();
		List<Ordem_Servico> osObjeto = osDao.read(idOS);
		
	
		Ordem_Servico updateOS = new Ordem_Servico(osObjeto.get(0).getIdordemservicao(), osObjeto.get(0).getCliente(),
				osObjeto.get(0).getDataEntrada(), dataSaida, valor, osObjeto.get(0).getStatus());

		osDao.update(updateOS);
	}
	
	public static void excluirServico(String idOS) {
		
		Ordem_ServicoDao osDao = new Ordem_ServicoDao();
		Ordem_Servico osObjeto = osDao.read(idOS).get(0);

		osDao.delete(osObjeto);
	}
	

}

package Model;

public enum Status {
	PENDENTE, FINALIZADO;

    
    public String enumToString(Status status) {
        String s = "";
        if(Status.PENDENTE.equals(status))
            s += "Pendente";
        if(Status.FINALIZADO.equals(status))
            s += "Finalizado";
        return s;
    }
    
    public Status paraSalvar(String status) {
    	Status s = null;
    	if("Pendente" == status)
    		s = Status.PENDENTE;
    	if ("Finalizado" == status)
			s = Status.FINALIZADO;
		return s;
    }
	
}

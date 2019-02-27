package scc_project;



public class Saida extends Evento {

	
	Saida (double i, Simulador s, Servico serv, Servico fina, Cliente c){
		super(i, s, serv,fina,c );
	}

	
	void executa (){
		
        serv.removeServico();
    }

    
    public String toString(){
         return "Sa�da em " + instante;
    }


}
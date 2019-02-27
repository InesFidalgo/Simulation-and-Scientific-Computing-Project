package scc_project;


public class Chegada extends Evento {
    
    Chegada (double i, Simulador s, Servico serv, Servico fina, Cliente c){
		super (i, s, serv,fina,c);
               
	}
    

    void executa (){
        
       Cliente c = new Cliente();
       if (c.getTipo()==1) s.getServ1().insereServico(c);
       else s.getServ3().insereServico(c);
       s.insereEvento (new Chegada(s.getInstante()+Aleatorio.exponencial(s.getMedia_cheg()), s, serv,target,c));
    }

   
    public String toString(){
         return "Chegada em " + instante;
    }
}
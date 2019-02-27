package scc_project;

import java.util.Random;


public class Simulador {

    private double instante;
    private double media_cheg, media_serv1, media_serv2, media_serv3;
    //private int n_clientes;
    //private int tempo_simulacao;
    private Servico servico1;
    private Servico servico2;
    private Servico servico3;
   
    // Lista de eventos - onde ficam registados todos os eventos que v�o ocorrer na simula��o
    // Cada simulador s� tem uma
    private ListaEventos lista;
    private int simulationType;


    // Construtor
    public Simulador() {
        media_cheg = 1.2;
        //n_clientes = 100;
        //tempo_simulacao = 600;
        
    }


	void insereEvento (Evento e1){
		lista.insereEvento (e1);
	}

	private void act_stats(){
            if(simulationType==1){
                servico1.act_stats();
                servico2.act_stats();
                servico3.act_stats();
            }
            else{
                servico1.act_stats();
            }
		
                
	}

	private String relat (){
            String saida="";
            
            saida+="Estatisticas:\n";
            saida+="Tempo de simulação  " + ((double)((int)(instante*1000))/1000);
            
            if(simulationType==1){
                saida+="\nCenário 1: original \n";
                saida+="1:";
                saida+=servico1.relat();
                saida+="2:";
                saida+=servico3.relat();
                saida+="loja:";
                saida+=servico2.relat();
               
            }
            else if(simulationType == 2){
                
                saida+="\nCenario 2: com self service\n";
                saida+="1:";
                saida+=servico1.relat();
                
               
               
            }
            return saida;
            
	}
       
        public String executa(int maximo, int tipo, int simulationtype, int machineService1, int machineService2, int machineService3) { //caso o tipo seja 1, executa por numero de clientes, caso seja 2 executa por tempo de simulação
            this.simulationType=simulationtype;
            
            instante = 0;

            Evento e1;
            String saida="";

            if(simulationtype==1){
                servico3 = new Servico(this, 3, 4, 2.5, servico2, machineService3, simulationtype);
                servico2 = new Servico(this, 2, 1, 0.5, null, machineService2, simulationtype);
                servico1 = new Servico(this, 1, 4, 2.5, servico2, machineService1, simulationtype);

                lista = new ListaEventos(this);
                Cliente c =new Cliente();
                
                ////////////////////////////////////////////////////////////////////////////////
                if(c.getTipo()==1){
                    insereEvento(new Chegada(instante, this, null, servico1,c ));
                }
                else{
                    insereEvento(new Chegada(instante, this, null, servico3,c ));
                }
                /////////////////////////////////////////////////////////////////////////////////////
                ///////////CORRE EM RELAÇÃO AO N CLIENTE
                if(tipo==1){
                    while (servico2.getAtendidos() < maximo) {

                    e1 = (Evento) (lista.removeFirst());  
                    instante = e1.getInstante();         
                    act_stats();                         

                    e1.executa();

                };
                saida=relat();

                }
                ///////////CORRE EM RELAÇÃO AO TEMPO
                if(tipo==2){

                    while (instante < maximo) {

                        e1 = (Evento) (lista.removeFirst());
                        instante = e1.getInstante();         
                        act_stats();                         

                        e1.executa();
                    };
                    instante=maximo;
                    saida=relat();  
                }
            }
            else if(simulationtype==2){
                
                //um serviço com 4 maquinas self service
                servico1 = new Servico(this, 1, 4.5, 2, null, machineService1, simulationtype);

                lista = new ListaEventos(this);
                Cliente c = new Cliente();
                
                ////////////////////////////////////////////////////////////////////////////////
                
                insereEvento(new Chegada(instante, this, null, servico1, c ));
                
                /////////////////////////////////////////////////////////////////////////////////////
                ///////////CORRE EM RELAÇÃO AO N CLIENTE
                if(tipo==1){
                    while ((servico1.getAtendidos()) < maximo) {

                    e1 = (Evento) (lista.removeFirst());  
                    instante = e1.getInstante();         
                    act_stats();                         

                    e1.executa();

                };
                saida=relat();



                }
                ///////////CORRE EM RELAÇÃO AO TEMPO
                if(tipo==2){

                    while (instante < maximo) {

                        e1 = (Evento) (lista.removeFirst());
                        instante = e1.getInstante();         
                        act_stats();                         

                        e1.executa();
                    };
                    instante=maximo;
                    saida=relat();  
                }
            }
            return saida;

    }

    
    public double getInstante() {
        return instante;
    }

    
    public double getMedia_cheg() {
        return media_cheg;
    }

    // M�todo que devolve a m�dia dos tempos de servi�o
    public double getMedia_serv1() {
        return media_serv1;
    }
    
    public double getMedia_serv2() {
        return media_serv2;
    }
    
    public double getMedia_serv3(){
        return media_serv3;
    }
    
    public Servico getServ2 (){
        return servico2;
    }
    
    public Servico getServ1 (){
        return servico1;
    }
    
    public Servico getServ3(){
        return servico3;
    }
    
    

}
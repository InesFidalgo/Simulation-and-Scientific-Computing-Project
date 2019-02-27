package scc_project;

import java.util.*;

// Classe que representa um servi�o com uma fila de espera associada

public class Servico {
	private int estado; // Vari�vel que regista o estado do servi�o: 0 - livre; 1 - ocupado
        private int atendidos; 
        private double temp_ult, soma_temp_esp, soma_temp_serv; 
	private Vector<Cliente> fila; 
	private Simulador s; 
        private int atendedores;
        private int numero;
        private Servico next = null;
        private double media; //media
        private double desvio; //desvio
        private int simulationType;
        double [] valores = new double [2];
        boolean flag = true;

	// Construtor
    Servico (Simulador s, int numero, double media, double desvio, Servico next, int n, int simulationtype){
    	this.s = s;
        this.next = next;
        fila = new Vector <Cliente>(); 
        estado = 0; // Livre
        temp_ult = s.getInstante(); 
        atendidos = 0;  
        soma_temp_esp = 0;
        soma_temp_serv = 0;
        this.atendedores=n;
        this.media = media;
        this.desvio = desvio;
        
        this.numero = numero;
        
        this.simulationType = simulationtype;
        
    }
    
    
    
    public void insereServico (Cliente c){
        if(simulationType==1){
            
            //System.out.println("numero1"+numero);
            
            if (estado <atendedores) { 
                //System.out.println("entrou");
                estado++; 
                //System.out.println("estado:"+estado);
                //System.out.println("numero"+numero);
                if (flag) {
                    valores = Aleatorio.normal(media, desvio,1);
                    flag = false;
                }
                else {
                    valores [0] = valores [1];
                    flag = true;
                }
                
                if (numero == 1) {
                    //System.out.println("transitou1");
                    s.insereEvento(new Transicao(s.getInstante() + valores[0], s, this, this.next, c));
                } else if (numero == 3) {
                    //System.out.println("transitou2");
                    s.insereEvento(new Transicao(s.getInstante() + valores[0], s, this, this.next, c));
                } else if (numero == 2) {
                    
                    //System.out.println("SAIU");
                    s.insereEvento(new Saida(s.getInstante() + valores[0], s, this, this.next, c));
                }

            } else {
                //System.out.println("adicionou a fila!!!!!!!!!!!");
                fila.addElement(c); 
            }
        }
        else{
            if (estado <atendedores) { 
                //System.out.println("entrou");
                estado++; 
                //System.out.println("estado:"+estado);
                //System.out.println("numero"+numero);
                if (flag) {
                    valores = Aleatorio.normal(media, desvio,1);
                    flag = false;
                }
                else {
                    valores [0] = valores [1];
                    flag = true;
                }
                
               
                s.insereEvento(new Saida(s.getInstante() + valores[0], s, this, this.next, c));
                

            } else {
                
                fila.addElement(c); 
            }
        }
    }

	
    public void removeServico (){
	if(simulationType==1){
            atendidos++;
            //System.out.println("ATENDIDOS"+atendidos);

            if (fila.size() == 0) {
                estado--;
            } 
            else {
                Cliente c;
                c = fila.get(0);
                fila.removeElementAt(0);
             if (flag) {
                    valores = Aleatorio.normal(media, desvio,1);
                    flag = false;
                }
                else {
                    valores [0] = valores [1];
                    flag = true;
                }
             

                if (numero == 1) {


                    s.insereEvento(new Transicao(s.getInstante() + valores[0], s, this, next, c));
                    
                } else if (numero == 3) {
                    
                    s.insereEvento(new Transicao(s.getInstante() + valores[0], s, this, next, c));
                    
                } else if (numero == 2) {
                    
                    s.insereEvento(new Saida(s.getInstante() + valores[0], s, this, next, c));
                }

            }
        }
        else{
            atendidos++;
            //System.out.println("ATENDIDOS"+atendidos);

            if (fila.size() == 0) {
                estado--;
            } 
            else {
                Cliente c;
                c = fila.get(0);
                fila.removeElementAt(0);
             if (flag) {
                valores = Aleatorio.normal(media, desvio,1);
                flag = false;
            }
            else {
                valores [0] = valores [1];
                flag = true;
            }

            s.insereEvento(new Saida(s.getInstante() + valores[0], s, this, next, c));

           
        }
    }
}
   
    // M�todo que devolve o n�mero de clientes atendidos no servi�o at� ao momento
    public int getAtendidos() {
        return atendidos;
    }
    public void act_stats() { //actualiza estatisticas
        //System.out.println("TEMPO:"+s.getInstante());
        double temp_desde_ult = s.getInstante() - temp_ult;
        ///////////////////////////////////////////////////////////////TAMANHO DA FILA ESTA SEMPRE A ZERO - corrigir /ines
        temp_ult = s.getInstante();
        //System.out.println("TEMPO2"+soma_temp_esp);
        soma_temp_esp += fila.size() * temp_desde_ult;
        
        soma_temp_serv += estado * temp_desde_ult;
    }

    public String relat() { //providencia o relatório deste serviço
        //System.out.println("Pessoas atendidas "+atendidos);
        String saida="";
        double temp_med_fila = soma_temp_esp / (atendidos + fila.size()); //tempo medio de espera na fila
        
        double comp_med_fila = soma_temp_esp / s.getInstante(); //comprimento medio da fila de espera
        
        double utilizacao_serv = soma_temp_serv / s.getInstante();//tempo medio de atendimento no serviço
        //saida+="tempo espera total"+((double)((int)(soma_temp_esp*1000))/1000)+"\ntempo serviço total"+((double)((int)(soma_temp_serv*1000))/1000);
        
        saida+="\n\nTempo médio de espera  " + ((double)((int)(temp_med_fila*1000))/1000)+"\n Comprimento médio da fila   " + ((double)((int)(comp_med_fila*1000))/1000)+"\n Utilização do serviço   " + (((double)((int)(utilizacao_serv*1000))/1000)/atendedores)+"\n Número de clientes atendidos   " + atendidos+"\n Número de clientes na fila   " + fila.size()+"\n"; // Valor actual
        return saida;
        
    }

}
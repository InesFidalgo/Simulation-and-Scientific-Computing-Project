package scc_project;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author utilizador
 */
public class Transicao extends Evento{
    Cliente cliente;
    Transicao (double i, Simulador s, Servico serv,Servico fina, Cliente c){
		super(i, s, serv,fina,c);
                this.cliente =c;
                
	}
    void executa (){
        serv.removeServico();
        s.getServ2().insereServico(c);
        
    }
    public String toString(){
         return "Transição em " + instante;
    }

   
}

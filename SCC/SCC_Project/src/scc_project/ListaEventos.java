package scc_project;

import java.util.*;



public class ListaEventos extends LinkedList<Evento> {

    private Simulador s;  
    private static final long serialVersionUID = 1; 
    

    ListaEventos (Simulador s){
        this.s = s;
    }

   
    public void insereEvento (Evento e1){
	int i = 0;
	  
	while (i < size() && ((Evento)get(i)).menor(e1)) i++;
	  
        add(i, e1);
    }

    
    public void print (){
    int i;
        System.out.println ("--- Lista de eventos em " + s.getInstante() + " ---");
        for (i = 0; i < size(); i++) System.out.println ("Evento " + (i+1) + " � uma " + (Evento)(get(i)));
    }
}
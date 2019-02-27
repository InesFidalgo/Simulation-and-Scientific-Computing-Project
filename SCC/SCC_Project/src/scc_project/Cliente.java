package scc_project;



public class Cliente {
    protected int tipo;
    
    public Cliente (){
        tipo = Aleatorio.tipoCliente();
    }
    
    
    public int getTipo(){
        return tipo;
    }
}
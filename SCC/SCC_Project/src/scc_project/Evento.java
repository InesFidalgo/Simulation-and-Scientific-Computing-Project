package scc_project;


public abstract class Evento {

	protected double instante;  
	protected Simulador s;
        protected Servico serv;
        protected Servico target;
        Cliente c;


    Evento (double i, Simulador s, Servico serv, Servico target, Cliente c){
		instante = i;
		this.s = s;
                this.serv = serv;
                this.target = target;
                this.c = c;
	}


    public boolean menor (Evento e1){
		return (instante < e1.instante);
	}


    
    abstract void executa ();
    
    
    public Servico getTarget() {
        return target;
    }

    public double getInstante() {
        return instante;
    }
     public Cliente getC() {
        return c;
    }
}
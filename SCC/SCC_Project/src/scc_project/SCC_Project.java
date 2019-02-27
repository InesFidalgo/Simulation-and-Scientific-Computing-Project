/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scc_project;

/**
 *
 * @author utilizador
 */
public class SCC_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Simulador simulator = new Simulador();
        //simulator.executa(1000,1,1,3,1,1);
        
        new Interface(simulator).setVisible(true);
    }
    
}

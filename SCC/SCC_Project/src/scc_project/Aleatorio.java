package scc_project;

// Classe para gera��o de n�meros aleat�rios segundos v�rias distribui��es

import java.util.Random;

// Apenas a distribui��o exponencial negativa est� definida

public class Aleatorio {
    
    
     public static int tipoCliente(){
        //isto está mal /ines corrigido
        
        //int random  = new Random().nextInt();
        if(RandomGenerator.rand64(10)>0.20){
            return 1;
        }
        else{
            return 3;
        }

    }
     
    // Gera um número segundo uma distribuição exponencial negativa de média m
    static Double[] myArray = new Double[3]; //armazena os valores se ainda houverem
    static Boolean if1 = false; //verifica se há numeros
    static Boolean if2 = false; //verifica se há numeros
    static Boolean if3 = false; //verifica se há numeros
    
    static double exponencial(double m) {

        return (-m * Math.log(RandomGenerator.rand64(0)));
    }
    
    static double[] normal(double media, double devpad, int seed)// o id verifica que seccao pede o aleatorio
    { 
        double v1, v2, w, y1, y2, temp, u1, u2;
        double [] x = new double [2];
        do{
            do {
            u1 = RandomGenerator.rand(seed);
            u2 = RandomGenerator.rand(seed);
            v1 = 2 * u1 - 1;
            v2 = 2 * u2 - 1;
            w = v1 * v1 + v2 * v2;
        } while (w > 1);

        temp = Math.pow(((-2) * Math.log(w) / w), 0.5);
        y1 = v1 * temp;
        y2 = v2 * temp;

        x[0] = media + y1 * devpad;
        x[1] = media + y2 * devpad;
        }while (x[0]<0 || x[1]<0);

    
        return x;

    }
    
    
   
    

}
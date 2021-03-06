/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author bruno
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        String[] commands = args;
        
        String filePath = commands[0];
        int numberOfThreads = Integer.parseInt(commands[1]);
        int time = Integer.parseInt(commands[2]);
        int numberOfPaths = Integer.parseInt(commands[3]);
        double mutation_rate = Float.parseFloat(commands[4]);
        
        Import imp = new Import(filePath);  
        Matrix matrix = imp.importFile();
        System.out.println(matrix.toString());
        System.out.println("A procurar o melhor caminho (versão base)");
        long beginB = System.currentTimeMillis();
        Base base = new Base(numberOfThreads, matrix, numberOfPaths, time, mutation_rate);
        base.execute();
        
        System.out.println("Melhor caminho encontrado->"+Base.best.toString());
        System.out.println("Fitness: "+Base.best.fitness());
        System.out.println("Distancia: "+Base.best.getDistance(matrix));
        
        long endB = System.currentTimeMillis() - beginB;
        System.out.println("Tempo total BASE: " + endB);
        
        System.out.println("A procurar o melhor caminho (versão avançada)");
        
        long beginA = System.currentTimeMillis();
        
        Advanced advanced = new Advanced(numberOfThreads, matrix, numberOfPaths, time,20, mutation_rate);

        advanced.execute();
        
        System.out.println("Melhor caminho encontrado->"+Advanced.best.toString());
        System.out.println("Fitness: "+Advanced.best.fitness());
        System.out.println("Distancia: "+Advanced.best.getDistance(matrix));
        
        long endA = System.currentTimeMillis() - beginA;
        System.out.println("Tempo total AVANÇADA: " + endA);
        
    }
    
}

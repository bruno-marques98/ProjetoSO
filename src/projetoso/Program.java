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
        
        Import imp = new Import(filePath);  
        Matrix matrix = imp.importFile();
        System.out.println(matrix.toString());
        
        Base base = new Base(numberOfThreads, matrix, numberOfPaths, time);
        base.execute();
        
        System.out.println("Melhor caminho encontrado->"+Base.best.toString());
        System.out.println("Fitness: "+Base.best.fitness());
        System.out.println("Distancia: "+Base.best.getDistance(matrix));

        
    }
    
}

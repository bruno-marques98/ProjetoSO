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
        
        int numberOfThreads = Integer.parseInt(commands[0]);
        String filePath = commands[1];
        
        Import imp = new Import(filePath);  
        Matrix matrix = imp.importFile();
        System.out.println(matrix.toString());
        
        Base base = new Base(numberOfThreads,matrix);
        base.execute();
        System.out.println("Count: "+base.getCount());
        System.out.println("Val: "+base.getVal());
        
        System.out.println("Melhor caminho encontrado->"+base.best().toString());
        System.out.println("Fitness: "+base.best().fitness());
        
    }
    
}

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
       /*int n = 5;
       int[][] distances = new int[][]{
           {0,23,10,4,1},
           {23,0,9,5,4},
           {10,9,0,8,2},
           {4,5,8,0,11},
           {1,4,2,11,0},
        }; 
        Matrix matrix = new Matrix(distances,5);
        System.out.println("Distancia entre a cidade 1 1"+matrix.getDistances()[1][1]);
        System.out.println(matrix.toString());
        AlgorithmAJ alg = new AlgorithmAJ(matrix);
        alg.execute(matrix, 100);
        */
        Import imp = new Import();
        Matrix matrix = imp.importFile();
        System.out.println(matrix.toString());
        /*AlgorithmAJ alg = new AlgorithmAJ(matrix);
        alg.execute(matrix, 8860 );*/
        Base base = new Base(10,matrix);
        base.execute();
        System.out.println("Count: "+base.getCount());
        
        
        
        /*
        AlgorithmAJ alg = new AlgorithmAJ();
        
        int path[] = new int[n];
        for(int i = 0; i < path.length; i++){
            path[i] = -1;
        }
        
        //System.out.println(path.toString());
        
        alg.createPopulation(matrix);
        ArrayList<Path> paths = alg.getPaths();
        /*for(Path pa: paths){
            System.out.println("Path");
            System.out.println(pa.toString());
        }*/
        
        //System.out.println(paths.get(0).toString() + "Fitness-> " + alg.fitness(paths.get(0)));
               
        
        
        /*
        System.out.println("Number of paths->" + paths.size());
        for(Path pa : paths){
            if(pa != null){
                System.out.println(pa.toString() +"Distance-> "+pa.getDistance()+ " Fitness-> " + alg.fitness(pa));
                System.out.println();
            }
            
        }
        System.out.println("Best paths");
        for(Path bestPath : alg.getTwoBestPaths()){
            System.out.println(bestPath.toString()+" Fitness-> "+ alg.fitness(bestPath));
        }
        System.out.println("Crossover");
        int[] parent1= new int[n];
        int[] parent2= new int[n];
        int offSpring1[] = new int[n];
        for(int i = 0; i < 5; i++){
            parent1[i] = alg.getTwoBestPaths().get(0).getPath()[i];
        }
        int offSpring2[] = new int[n];
        for(int i = 0; i < 5; i++){
            parent2[i] = alg.getTwoBestPaths().get(1).getPath()[i];
        }
        Random rand = new Random();
        System.out.println("Crossover");
        AlgorithmAJ.pmxCrossover(parent1,parent2,offSpring1,offSpring2,n,rand);
        
        
        for (int i=0; i< n; i++){
            System.out.printf("%2d ",offSpring1[i]);
        }
        System.out.println();
        for (int i=0; i< n; i++){
            System.out.printf("%2d ",offSpring2[i]);
        }
        System.out.println();
        double prob = (Math.random()*100);
        
        System.out.println("Before mutation");
        alg.exchangeMutation(offSpring1,prob);
        alg.exchangeMutation(offSpring2,prob);
        System.out.println("After mutation");
                for (int i=0; i< n; i++){
            System.out.printf("%2d ",offSpring1[i]);
        }
        System.out.println();
        alg.getTwoBestPaths().get(0).setPath(offSpring1);
        for (int i=0; i< n; i++){
            System.out.printf("%2d ",offSpring2[i]);
        }
         System.out.println();
         alg.getTwoBestPaths().get(1).setPath(offSpring2);
         int distance = 0;
         for(Path pt : alg.getTwoBestPaths()){
             System.out.println("Distancia: "+pt.getDistance());
             distance+=pt.getDistance();
         }
         System.out.println("Total Distance: " + distance);
         alg.removeTwoWorst();
        for(Path pat : alg.getTwoBestPaths()){
            alg.addPath(pat);
         }*/
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author bruno
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numberOfCities = 5;
       int[][] distances = new int[][]{
           {0,23,10,4,1},
           {23,0,9,5,4},
           {10,9,0,8,2},
           {4,5,8,0,11},
           {1,4,2,11,0},
        };
        Matrix matrix = new Matrix(distances,5);
        System.out.println(matrix.toString());
        
        AlgorithmAJ alg = new AlgorithmAJ();
        
        int path[] = new int[numberOfCities];
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
        System.out.println("Number of paths->" + paths.size());
    }
    
}

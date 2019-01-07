/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.Arrays;

/**
 *
 * @author bruno
 */
public class ProjetoSO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int[][] distances = new int[][]{
           {0,23,10,4,1},
           {23,0,9,5,4},
           {10,9,0,8,2},
           {4,5,8,0,11},
           {1,4,2,11,0},
        };
        System.out.println(Arrays.deepToString(distances).replace("], ", "]\n"));
        System.out.println("");
        for(int i = 0; i<5; i++)
        { 
            for(int j = 0; j<5; j++)
            {
                System.out.print("|"+distances[i][j]+" ");
            }
            System.out.println();
        }
        AlgorithmAJ alg = new AlgorithmAJ();
        int[] path = alg.createPath(distances);
        System.out.println("Caminho aleatório");
        for(int i = 0; i < path.length; i++){
            System.out.println(path[i]);
        }
    }
    
}

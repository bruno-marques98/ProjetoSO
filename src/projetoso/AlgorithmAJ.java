/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

/**
 *
 * @author bruno
 */
public class AlgorithmAJ {

    public AlgorithmAJ() {
    }
    public int[] createPath(int[][] distances){
        int[] path = new int[distances.length];
        int firstCity = (int) (Math.random()*distances.length);
        for(int i = 0; i < distances.length; i++){
            int idCity = (int) (Math.random()*distances.length);
            System.out.println(idCity);
            path[i] = distances[idCity][firstCity];
        }
        return path;
    }
}

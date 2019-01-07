/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class AlgorithmAJ {
    private ArrayList<Path> paths;
    public AlgorithmAJ() {
        paths = new ArrayList<>();
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }
    
    public ArrayList createPopulation(Matrix matrix){
        int numberOfPaths = (int) (Math.random()*100);
        for(int i = 0 ; i < numberOfPaths; i++){
            Path path = new Path(matrix);
            path.createRandomPath(matrix.getDistances());
            paths.add(path);
        }
        return paths;
    }

    private boolean isEmpty(int arr[]){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == -1){
                return true;
            }
        }
        return false;
    }
    private boolean containsCity(int idCity, int arr[]){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == -1){
                return true;
            }
        }
        return false; 
    }
    
}

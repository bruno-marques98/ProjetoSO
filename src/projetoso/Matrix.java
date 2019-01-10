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
class Matrix {
    private int distances[][];
    private int numberOfCities;

    public Matrix(int[][] distances,int numberOfCities) {
        this.distances = distances;
        this.numberOfCities = numberOfCities;
    }

    public int[][] getDistances() {
        return distances;
    }

    public void setDistances(int[][] distances) {
        this.distances = distances;
    }
    public int getNumberOfCities(){
        return this.numberOfCities;
    }
    public int getDistanceBetween(int idCity, int idNextCity){
        //System.out.println("Id City -> "+idCity+" ID next city -> " +idNextCity);
        return distances[idCity-1][idNextCity-1];
    }

    @Override
    public String toString() {
         String str = "Matrix \n" + Arrays.deepToString(distances).replace("], ", "]\n");
         return str;
    }
    
}

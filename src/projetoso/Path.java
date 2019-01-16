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
public class Path {
    private int path[];
    private int numberOfCities;
    private Matrix matrix;
    public Path(Matrix matrix) {
        this.matrix = matrix;
        this.path = new int[matrix.getNumberOfCities()+1];
        for(int i=0;i<this.path.length;i++){
            this.path[i] = -1;
        }
        this.numberOfCities = matrix.getNumberOfCities();
    }

    public Path(Matrix matrix, int[] path) {
        if(path!= null) this.path = path;
        else {
            this.path = new int[matrix.getNumberOfCities()+1];
            for(int i=0;i<this.path.length;i++){
                this.path[i] = -1;
            }
        }
        this.numberOfCities = matrix.getNumberOfCities();
    }
    public int[] getPath() {
        return path;
    }

    public void setPath(int[] path) {
        this.path = path;
    }
    
    public void createRandomPath(int[][] distances){
        int[] aux = this.path;
        int idCity = (int) (Math.random()*distances.length+1);

        int position = 0;
        while(isEmpty(aux)){
            do{
                //System.out.println("Distances length "+distances.length);
                idCity = (int) (Math.random()*distances.length+1);
            }while(containsCity(idCity,aux));
            aux[position++] = idCity;
            //System.out.println("Id "+idCity);
        }
        aux[numberOfCities] = aux[0];
        this.path = aux;
    }
    public int getDistance(Matrix matrix){
        if(path!= null){
            int distance = 0;
            for(int i = 0; i < path.length-2; i++){
                //System.out.println("i = " + i + " | i+1 = " + (i+1));
                distance += matrix.getDistanceBetween(path[i],path[i+1]);
            }
            return distance;
        }
        return 0;
        
    }
    private boolean isEmpty(int arr[]){
        for(int i = 0; i < arr.length-1; i++){
            if(arr[i] == -1){
                return true;
            }
        }
        return false;
    }
    private boolean containsCity(int idCity, int arr[]){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == idCity){
                return true;
            }
        }
        return false; 
    }
    @Override
    public String toString() {
        String pathString = "";
        for(int i = 0; i < path.length; i++){
            pathString += path[i] + " ";
        }
        return pathString;
    }

    
    public double fitness(){
        if(getDistance(matrix) == 0 || this == null) return 0.0;
        double fitness = 1 /(double)getDistance(matrix);
        return fitness;
    }
    
}

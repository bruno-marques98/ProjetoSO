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
    private Matrix matrix;
    private int path[];
    public Path(Matrix matrix) {
        this.matrix = matrix;
        this.path = new int[matrix.getNumberOfCities()];
    }
    public void createRandomPath(int[][] distances){
        int[] path = new int[distances.length];
        int firstCity = (int) (Math.random()*distances.length);
        for(int i = 0; i < distances.length; i++){
            int idCity = (int) (Math.random()*distances.length);
            //System.out.println(idCity);
            path[i] = distances[idCity][firstCity];
        }
        this.path = path;
    }

    @Override
    public String toString() {
        String pathString = "";
        for(int i = 0; i < path.length; i++){
            pathString += path[i] + " ";
        }
        return pathString;
    }
    
}

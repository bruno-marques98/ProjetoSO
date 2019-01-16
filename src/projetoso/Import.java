/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Import {
    
    private String filePath;

    public Import(String filePath) {
        this.filePath = filePath;
    }
    /*public String getFilePath(){
        Scanner scanner = new Scanner(System.in);
        String filePath;
        System.out.println("Enter filename:");
        filePath = (scanner.next());
        return filePath;
    }*/
    public Matrix importFile()  throws IOException{
        //String filePath = file;
        //String currentLine = null;
        Scanner sc = new Scanner(new File(filePath));
        // read number of cities
        int n = sc.nextInt();
        // initialize distance matrix
        int[][] dist = new int[n][n];
        // fill distance matrix
        for(int i=0; i<n; i++){
            //String[] array = currentLine.split(" ", n);
            for(int j=0; j<n; j++){
                if(sc.hasNextInt()){
                    //System.out.println("Has next int");
                    dist[i][j] = sc.nextInt();
                    dist[j][i] = dist[i][j];
                }
                
            }
        }
        // create and return TSP data
        return new Matrix(dist,n);

    }
    
}

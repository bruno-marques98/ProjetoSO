/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Import {

    public Import() {
    }
    public String getFilePath(){
        Scanner scanner = new Scanner(System.in);
        String filePath;
        System.out.println("Enter filename:");
        filePath = (scanner.next());
        return filePath;
    }
    public Matrix importFile()  throws FileNotFoundException{
        String filePath = getFilePath();
        Scanner sc = new Scanner(new File(filePath));
        sc.useLocale(Locale.ROOT);
        // read number of cities
        int n = sc.nextInt();
        // initialize distance matrix
        int[][] dist = new int[n][n];
        // fill distance matrix
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                dist[i][j] = sc.nextInt();
                dist[j][i] = dist[i][j];
            }
        }
        // create and return TSP data
        return new Matrix(dist,n);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author bruno
 */
public class AlgorithmAJ {
    private ArrayList<Path> paths;
    private final double MUTATION_RATE = 0.01; 
    private Matrix matrix;
    private int numberOfPaths;
    private Random rand;
    
    public AlgorithmAJ(Matrix matrix, int numberOfPaths) {
        this.numberOfPaths = numberOfPaths;
        paths = new ArrayList<>();
        if(matrix != null) this.matrix = matrix;
        this.rand = new Random();
    }
    
    public ArrayList<Path> getPaths() {
        return paths;
    }
    
    public void createPopulation(){
        for(int i = 0 ; i < numberOfPaths; i++){
            Path path = new Path(matrix);
            path.createRandomPath(matrix.getDistances());
            paths.add(path);
        }
    }
    private void swap(int minIndex,int i){
        Path aux = paths.get(minIndex);
        paths.set(minIndex, paths.get(i));
        paths.set(i,aux);
    }
    private void evaluate(){
        for(int i = 0; i < paths.size()-1; i++){
            int minIndex = i;
            for(int j = i+1; j < paths.size(); j++){
                if(paths.get(j).getDistance(matrix) < paths.get(minIndex).getDistance(matrix)){
                    minIndex = j;
                }
            }
            swap(minIndex,i);
        }
    }
    public double fitness(Path path){
        double fitness = 1 /(double)path.getDistance(matrix);
        return fitness;
    }

    public boolean addPath(Path path){
        if(path != null){
            paths.add(path);
            return true;
        }
        return false;
    }
    
    public ArrayList<Path> getTwoBestPaths(){
        ArrayList<Path> topTwo = new ArrayList<>();
        topTwo.add(paths.get(0));
        topTwo.add(paths.get(1));
        return topTwo;
    }
    
    public void removeTwoWorst(ArrayList<Path> paths){
        int index = paths.size();
        paths.remove(index-1);
        paths.remove(index-2);
    }
    
    public static void pmxCrossover(Path parent1,Path parent2,Path offSpring1,Path offSpring2,int n,Random rand) {
        int replacement1[] = new int[n+1];
        int replacement2[] = new int[n+1];
        int i, n1, m1, n2, m2;
        int swap;
        /*
        for (i=0; i< n; i++)
            System.out.printf("%2d ",parent1.getPath()[i]);
            System.out.println();
        for (i=0; i< n; i++)
            System.out.printf("%2d ",parent2.getPath()[i]);
            System.out.println();
        */
        int cuttingPoint1 = rand.nextInt(n);
        int cuttingPoint2 = rand.nextInt(n);

        //int cuttingPoint1 = 3;
        //int cuttingPoint2 = 5;

        while (cuttingPoint1 == cuttingPoint2) {
            cuttingPoint2 = rand.nextInt(n);
        }
        if (cuttingPoint1 > cuttingPoint2) {
            swap = cuttingPoint1;
            cuttingPoint1 = cuttingPoint2;
            cuttingPoint2 = swap;
        }

        //System.out.printf("cp1 = %d cp2 = %d\n",cuttingPoint1,cuttingPoint2);

        for (i=0; i < n+1; i++) {
            replacement1[i] = -1;
            replacement2[i] = -1;
        }
        for (i=cuttingPoint1; i <= cuttingPoint2; i++) {
            offSpring1.getPath()[i] = parent2.getPath()[i];
            offSpring2.getPath()[i] = parent1.getPath()[i];
            replacement1[parent2.getPath()[i]] = parent1.getPath()[i];
            replacement2[parent1.getPath()[i]] = parent2.getPath()[i];
        }

        /*for (i=0; i< n+1; i++)
        System.out.printf("%2d ",replacement1[i]);
        System.out.println();
        for (i=0; i< n+1; i++)
        System.out.printf("%2d ",replacement2[i]);
        System.out.println();*/
        // fill in remaining slots with replacements
        for (i = 0; i < n; i++) {
            if ((i < cuttingPoint1) || (i > cuttingPoint2)) {
                n1 = parent1.getPath()[i];
                m1 = replacement1[n1];
                n2 = parent2.getPath()[i];
                m2 = replacement2[n2];
            while (m1 != -1) {
                n1 = m1;
                m1 = replacement1[m1];
            }
            while (m2 != -1) {
                n2 = m2;
                m2 = replacement2[m2];
            }
                offSpring1.getPath()[i] = n1;
                offSpring2.getPath()[i] = n2;
            }
        }
    }
    
    public void exchangeMutation(Path parent1,Path parent2){
        double prob = rand.nextDouble();
        int size = parent1.getPath().length;
        for(int i = 0; i < size; i++){
            if(prob <= MUTATION_RATE){
                int idCity = (int) (Math.random()*size);
                int idCity2 = (int) (Math.random()*size);
                while(idCity2 == idCity){
                    idCity2 = (int) (Math.random()*size);
                }
                parent1.setPath(swap(parent1.getPath(),idCity,idCity2));
                parent2.setPath(swap(parent2.getPath(),idCity,idCity2));
            }
        }
    }
    
    public int[] swap(int arr[],int position,int position2){
        int aux = arr[position];
        arr[position] = arr[position2];
        arr[position2] = aux;
        return arr;
    }
    public Path getBestPath(){
        return paths.get(0);
    }
    public void execute(Matrix matrix,int timeSeconds){

        createPopulation();
        int numberOfCities = matrix.getNumberOfCities();
        ArrayList<Path> topTwo = new ArrayList<>();
        evaluate();
        long end = System.currentTimeMillis() + timeSeconds*1000;
         while(/*position < iterations || */System.currentTimeMillis() < end){ 
             topTwo = getTwoBestPaths();
             Path parent1 = new Path(matrix);
             Path parent2 = new Path(matrix);
             //int offSpring1[] = new int[numberOfCities];
             Path offSpring1 = new Path(matrix); 
             Path offSpring2 = new Path(matrix);
             for(int i = 0; i < numberOfCities; i++){
                 parent1.getPath()[i] = getTwoBestPaths().get(0).getPath()[i];
                 parent2.getPath()[i] = getTwoBestPaths().get(1).getPath()[i];
             }
             pmxCrossover(parent1,parent2,offSpring1,offSpring2,numberOfCities,rand);
             exchangeMutation(offSpring1,offSpring2);
             topTwo.set(0, offSpring1);
             topTwo.set(1, offSpring2);
             parent1.getPath()[numberOfCities] = parent1.getPath()[0];
             parent2.getPath()[numberOfCities] = parent2.getPath()[0];
             removeTwoWorst(paths);
             paths.add(parent1);
             paths.add(parent2);
             //position++;
         }

         //System.out.println("Best path found");
         //System.out.println(bestPath.toString()+"Distancia: " + bestPath.getDistance(matrix));
         //System.out.println("Fitness-> "+ fitness(bestPath));
   
    }

    public void setPaths(ArrayList<Path> paths) {
        this.paths = paths;
    }

    
}

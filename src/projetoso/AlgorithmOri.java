/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bruno
 */
public class AlgorithmOri {
    private ArrayList<Path> paths;
    private final double MUTATION_RATE = Math.random()*100; 
    private Matrix matrix;
    
    public AlgorithmOri(Matrix matrix) {
        paths = new ArrayList<>();
        if(matrix != null) this.matrix = matrix;
    }
    
    public ArrayList<Path> getPaths() {
        return paths;
    }
    
    public void createPopulation(){
        int numberOfPaths = (int) (Math.random()*100);
        for(int i = 0 ; i < numberOfPaths; i++){
            Path path = new Path(matrix);
            path.createRandomPath(matrix.getDistances());
            paths.add(path);
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
    
    public ArrayList<Path> getThreeBestPaths(){
        ArrayList<Path> auxList = new ArrayList<>(paths);
        ArrayList<Path> topThree = new ArrayList<>();
        
        double maxFitness = 0.0;
        for(Path path : auxList){
            double fit = fitness(path);
            if(fit > maxFitness){
                maxFitness = fit;
                if(!topThree.isEmpty())
                    topThree.remove(0);
                topThree.add(path);
            }
        }
        if(topThree.get(0) != null){
            auxList.remove(topThree.get(0));
        }
        maxFitness = 0.0;
        for(Path path : auxList){
            double fit = fitness(path);
            if(fit > maxFitness){
                maxFitness = fit;
                if(topThree.size() > 1)
                    topThree.remove(1);
                topThree.add(path);
            }
        }
        if(topThree.get(0) != null){
            auxList.remove(topThree.get(0));
        }
        maxFitness = 0.0;
        for(Path path : auxList){
            double fit = fitness(path);
            if(fit > maxFitness){
                maxFitness = fit;
                if(topThree.size() > 2)
                    topThree.remove(1);
                topThree.add(path);
            }
        }
        return topThree;
    }
    
    public Path getBestPath(){
        Path path = null;
        double maxFitness = 0.0;
        for(Path pa : paths){
            double fit = fitness(pa);
            if(fit > maxFitness){
                path = pa;
            }
        }
        return path;
    }
    
    public void removeThreeWorst(ArrayList<Path> paths){
        ArrayList<Path> bottomThree = new ArrayList<>(); 
        double minFitness = 1.0;
        for(Path path : paths){
            double fit = fitness(path);
            if(fit < minFitness){
                minFitness = fit;
                if(!bottomThree.isEmpty())
                    bottomThree.remove(0);
                bottomThree.add(path);
            }
        }
        
        paths.remove(bottomThree.get(0));
        
        minFitness = 1.0;
        for(Path path : paths){
            double fit = fitness(path);
            if(fit < minFitness){
                minFitness = fit;
                if(bottomThree.size() > 1)
                    bottomThree.remove(1);
                bottomThree.add(path);
            }
        }
        
        paths.remove(bottomThree.get(1));
                paths.remove(bottomThree.get(1));
        
        minFitness = 1.0;
        for(Path path : paths){
            double fit = fitness(path);
            if(fit < minFitness){
                minFitness = fit;
                if(bottomThree.size() > 2)
                    bottomThree.remove(2);
                bottomThree.add(path);
            }
        }
        paths.remove(bottomThree.get(2));
    }
    
    public static void pmxCrossover(Path parent1,Path parent2,Path parent3,Path offSpring1,Path offSpring2,Path offSpring3,int n,Random rand) {
        int replacement1[] = new int[n+1];
        int replacement2[] = new int[n+1];
        int replacement3[] = new int[n+1];
        int i, n1, m1, n2, m2, n3, m3;
        int swap;

        int cuttingPoint1 = rand.nextInt(n);
        int cuttingPoint2 = rand.nextInt(n);

        while (cuttingPoint1 == cuttingPoint2) {
            cuttingPoint2 = rand.nextInt(n);
        }
        if (cuttingPoint1 > cuttingPoint2) {
            swap = cuttingPoint1;
            cuttingPoint1 = cuttingPoint2;
            cuttingPoint2 = swap;
        }

        for (i=0; i < n+1; i++) {
            replacement1[i] = -1;
            replacement2[i] = -1;
            replacement3[i] = -1;
        }
        for (i=cuttingPoint1; i <= cuttingPoint2; i++) {
            offSpring1.getPath()[i] = parent3.getPath()[i];
            offSpring2.getPath()[i] = parent1.getPath()[i];
            offSpring3.getPath()[i] = parent2.getPath()[i];
            replacement1[parent3.getPath()[i]] = parent1.getPath()[i];
            replacement2[parent1.getPath()[i]] = parent2.getPath()[i];
            replacement3[parent2.getPath()[i]] = parent3.getPath()[i];
        }

        // fill in remaining slots with replacements
        for (i = 0; i < n; i++) {
            if ((i < cuttingPoint1) || (i > cuttingPoint2)) {
                n1 = parent1.getPath()[i];
                m1 = replacement1[n1];
                n2 = parent2.getPath()[i];
                m2 = replacement2[n2];
                n3 = parent3.getPath()[i];
                m3 = replacement3[n3];
            while (m1 != -1) {
                n1 = m1;
                m1 = replacement1[m1];
            }
            while (m2 != -1) {
                n2 = m2;
                m2 = replacement2[m2];
            }
            while (m3 != -1) {
                n3 = m3;
                m3 = replacement3[m3];
            }
                offSpring1.getPath()[i] = n1;
                offSpring2.getPath()[i] = n2;
                offSpring3.getPath()[i] = n3;
            }
        }
    }
    
    public void exchangeMutation(Path parent1,Path parent2,double probOfMutation){
        double prob = (Math.random()*100);
        int size = parent1.getPath().length;
        for(int i = 0; i < size; i++){
            if(prob < MUTATION_RATE){
                int idCity = (int) (Math.random()*size);
                int idCity2 = (int) (Math.random()*size);
                while(idCity2 == idCity){
                    idCity2 = (int) (Math.random()*size);
                }
                parent2.setPath(swap(parent2.getPath(),idCity,idCity2));
                parent1.setPath(swap(parent1.getPath(),idCity,idCity2));
            }
        }
    }
    
    public int[] swap(int arr[],int position,int position2){
        int aux = arr[position];
        arr[position] = arr[position2];
        arr[position2] = aux;
        return arr;
    }
    
    public void execute(Matrix matrix,int iterations,int timeSeconds){

        createPopulation();
        int numberOfCities = matrix.getNumberOfCities();

        long end = System.currentTimeMillis() + timeSeconds*1000;
        int position = 0;
         while(position < iterations || System.currentTimeMillis() < end){ 
             ArrayList<Path> topThree = getThreeBestPaths();
             Path parent1 = new Path(matrix);
             Path parent2 = new Path(matrix);
             Path parent3 = new Path(matrix);
             //int offSpring1[] = new int[numberOfCities];
             Path offSpring1 = new Path(matrix); 
             Path offSpring2 = new Path(matrix);
             Path offSpring3 = new Path(matrix);
             for(int i = 0; i < numberOfCities; i++){
                 parent1.getPath()[i] = getThreeBestPaths().get(0).getPath()[i];
                 parent2.getPath()[i] = getThreeBestPaths().get(1).getPath()[i];
                 parent3.getPath()[i] = getThreeBestPaths().get(2).getPath()[i];
             }
             Random rand = new Random();
             pmxCrossover(parent1,parent2,parent3,offSpring1,offSpring2,offSpring3,numberOfCities,rand);
             exchangeMutation(offSpring1,offSpring2,MUTATION_RATE);
             topThree.set(0, offSpring1);
             topThree.set(1, offSpring2);
             topThree.set(2, offSpring3);
             parent1.getPath()[numberOfCities] = parent1.getPath()[0];
             parent2.getPath()[numberOfCities] = parent2.getPath()[0];
             parent3.getPath()[numberOfCities] = parent3.getPath()[0];
             removeThreeWorst(paths);
             paths.add(parent1);
             paths.add(parent2);
             paths.add(parent3);
             position++;
         }

         System.out.println("Best path found");
         Path bestPath = getBestPath();
         System.out.println(bestPath.toString()+"Distancia: " + bestPath.getDistance(matrix));
         System.out.println("Fitness-> "+ fitness(bestPath));

        
        
    }
}

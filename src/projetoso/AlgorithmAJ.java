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
public class AlgorithmAJ {
    private ArrayList<Path> paths;
    private final double MAX_PERCENTAGE = 100; 
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
    public double fitness(Path path){
            
        double fitness = 1 /(double)path.getDistance();
        return fitness;
    }
    
    public ArrayList<Path> getTwoBestPaths(){
        ArrayList<Path> auxList = new ArrayList<>(paths);
        ArrayList<Path> topTwo = new ArrayList<>();
        
        double maxFitness = 0.0;
        for(Path path : auxList){
            if(fitness(path) > maxFitness){
                maxFitness = fitness(path);
                if(!topTwo.isEmpty())
                    topTwo.remove(0);
                topTwo.add(path);
            }
        }
        
        auxList.remove(topTwo.get(0));
        
        maxFitness = 0.0;
        for(Path path : auxList){
            if(fitness(path) > maxFitness){
                maxFitness = fitness(path);
                if(topTwo.size() > 1)
                    topTwo.remove(1);
                topTwo.add(path);
            }
        }
        return topTwo;
    }
    public static void pmxCrossover(int parent1[],int parent2[],int offSpring1[],int offSpring2[],int n,Random rand) {
        int replacement1[] = new int[n+1];
        int replacement2[] = new int[n+1];
        int i, n1, m1, n2, m2;
        int swap;

        for (i=0; i< n; i++)
            System.out.printf("%2d ",parent1[i]);
            System.out.println();
        for (i=0; i< n; i++)
            System.out.printf("%2d ",parent2[i]);
            System.out.println();

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

        System.out.printf("cp1 = %d cp2 = %d\n",cuttingPoint1,cuttingPoint2);

        for (i=0; i < n+1; i++) {
            replacement1[i] = -1;
            replacement2[i] = -1;
        }
        for (i=cuttingPoint1; i <= cuttingPoint2; i++) {
            offSpring1[i] = parent2[i];
            offSpring2[i] = parent1[i];
            replacement1[parent2[i]] = parent1[i];
            replacement2[parent1[i]] = parent2[i];
        }

        for (i=0; i< n+1; i++)
        System.out.printf("%2d ",replacement1[i]);
        System.out.println();
        for (i=0; i< n+1; i++)
        System.out.printf("%2d ",replacement2[i]);
        System.out.println();
        // fill in remaining slots with replacements
        for (i = 0; i < n; i++) {
            if ((i < cuttingPoint1) || (i > cuttingPoint2)) {
                n1 = parent1[i];
                m1 = replacement1[n1];
                n2 = parent2[i];
                m2 = replacement2[n2];
            while (m1 != -1) {
                n1 = m1;
                m1 = replacement1[m1];
            }
            while (m2 != -1) {
                n2 = m2;
                m2 = replacement2[m2];
            }
                offSpring1[i] = n1;
                offSpring2[i] = n2;
            }
        }
    }

    public void exchangeMutation(int parent1[],int parent2[],double probOfMutation){
        double prob = (double)(Math.random()*MAX_PERCENTAGE+1);
    }
    /*
    GA()
   initialize population
   find fitness of population
   
   while (termination criteria is reached) do
      parent selection
      crossover with probability pc
      mutation with probability pm
      decode and fitness calculation
      survivor selection
      find best
   return best
    */
}

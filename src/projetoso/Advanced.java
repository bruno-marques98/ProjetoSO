/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

/**
 *
 * @author bruno
 */
public class Advanced {
    private int numberOfThreads;
    private Matrix matrix;
    private int count;
    private int val;
    private double fitness;
    private AlgorithmAJ alg;
    private Path best;
    private ArrayList<MyThread> threads;
    private Semaphore sem;

    public Advanced(int numberOfThreads, Matrix matrix) {
     /*   this.numberOfThreads = numberOfThreads;
        this.matrix = matrix;
        this.fitness = 1;
        this.alg = new AlgorithmAJ(matrix);
        this.threads = new ArrayList<>();
        this.best = null;
        this.sem = sem;
    }

    public void execute(){
        //Allow only one thread to write
        sem = new Semaphore(1);
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix);
            myT.startT();
            if(best == null){
                 best = myT.getBestPath();
                 this.threads.add(myT); 
            }
            if(myT.getBestPath().fitness() >  best.fitness() ){
                try {
                    //Get lock
                    sem.acquire();
                    
                    best = myT.getBestPath();
                    this.threads.add(myT);                    
                } catch (InterruptedException exc) {
                     System.out.println(exc); 
                }
            }
            
            //Release lock
            sem.release();
           
            count++;
        }
    }
    public int getVal(){
        return val;
    }
    public int getCount(){
        return count;
    }

    public Path getBest() {
        for(MyThread t : threads){
            double fit = t.getBestPath().fitness();
            System.out.println("Fitness -> " + fit);
            if(fit < fitness ){
                fitness = fit;
                best = t.getBestPath();
            }
        }
        return best;
    }
    public Path best(){
        return best;
    }
    
    public ArrayList<Path> globalPopulation(){
        ArrayList<Path> population = new ArrayList<>();
        for(MyThread t : threads){
            for(Path path : t.getPopulation()){
                population.add(path);
            }
        }
        return population;
    }
    
    public void orderPopulation(){
        ArrayList<Path> ordered = globalPopulation();
        double fitness = 0.0;
        Collections.sort(ordered,comparator);
        
    }
    public static Comparator<Path> comparator = new Comparator<Path>(){
        @Override
        public int compare(Path path1,Path path2){
            double fitness = path1.fitness();
            double fitness2 = path2.fitness();
            return (int) (fitness2-fitness);
        }
    };*/
    }
}
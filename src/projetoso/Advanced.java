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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bruno
 */
public class Advanced {
    private int numberOfThreads;
    private int numberOfPaths;
    private Matrix matrix;
    private int time;
    private double fitness;
    private AlgorithmAJ alg;
    public static Path best= null;
    private ArrayList<MyThread> threads;
    private static Semaphore sem = new Semaphore(1);;

    public Advanced(int numberOfThreads, Matrix matrix) {
        this.time = time;
        this.numberOfThreads = numberOfThreads;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.fitness = 1;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.threads = new ArrayList<>();
    }
     

    public void execute(){
        //Allow only one thread to write
        sem = new Semaphore(1);
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix, numberOfPaths, time);
            myT.start();
            threads.add(myT);
        }
        joinThreads();
    }

    public static void setBest(Path best) {
        try {
            sem.acquire();
            if(Base.best == null || best.fitness() > Base.best.fitness()){
                 Base.best = best;
            }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        sem.release();
    }
    
    public void joinThreads(){
        for (MyThread t : threads) {
            try {
                t.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author bruno
 */
public class Base {
    private int numberOfThreads;
    private int numberOfPaths;
    private Matrix matrix;
    private int count;
    private int time;
    private int val;
    private double fitness;
    private AlgorithmAJ alg;
    private Path best;
    private ArrayList<MyThread> threads;
    private Semaphore sem;

    public Base(int numberOfThreads, Matrix matrix, int numberOfPaths, int time) {
        this.time = time;
        this.numberOfThreads = numberOfThreads;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.fitness = 1;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.threads = new ArrayList<>();
        this.best = null;
        this.sem = sem;
    }

    public void execute(){
        //Allow only one thread to write
        sem = new Semaphore(1);
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix, numberOfPaths, time);
            myT.startT();
            
                if(best == null){
                     best = myT.getBestPath();
                     this.threads.add(myT); 
                }
            
        }
           
            count++;
            joinThreads();
            for(MyThread myT: threads){
                if( myT.getBestPath() != null && myT.getBestPath().fitness() >  best.fitness() ){
                    try {
                        //Get lock
                        sem.acquire();

                        best = myT.getBestPath();
                        this.threads.add(myT);                    
                    } catch (InterruptedException exc) {
                         System.out.println(exc); 
                    }
                }
            }
            
    }
    public void joinThreads(){
        for (MyThread t : threads) {
            t.joinT();
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

}

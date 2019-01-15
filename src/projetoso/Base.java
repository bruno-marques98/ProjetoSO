/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.time.Duration;
import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class Base {
    private int numberOfThreads;
    private Matrix matrix;
    private int count;
    private int val;
    private double fitness;
    private AlgorithmAJ alg;
    private Path best;
    private ArrayList<MyThread> threads;

    public Base(int numberOfThreads, Matrix matrix) {
        this.numberOfThreads = numberOfThreads;
        this.matrix = matrix;
        this.count = 0;
        this.val = 0;
        this.fitness = 1;
        this.alg = new AlgorithmAJ(matrix);
        this.threads = new ArrayList<>();
    }

    public void execute(){
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix);
            myT.startT();
            this.threads.add(myT);
           
            count++;
        }
    }
    public int getVal(){
        return val;
    }
    public int getCount(){
        return count;
    }

    public double getFitness() {
        return fitness;
    }
    
    public Path getBest() {
        for(MyThread t : threads){
            double fit = t.getFitness();
            System.out.println("Fitness -> " + fit);
            if(fit < fitness ){
                fitness = fit;
                best = t.getBestPath();
            }
        }
        return best;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.time.Duration;

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

    public Base(int numberOfThreads, Matrix matrix) {
        this.numberOfThreads = numberOfThreads;
        this.matrix = matrix;
        this.count = 0;
        this.val = 0;
        this.alg = new AlgorithmAJ(matrix);
    }

    public void execute(){
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix);
            myT.startT();
            double fit = alg.fitness(myT.getBestPath());
            if(fit <= fitness){
                fitness = fit;
            }
            count++;
        }
    }
    public int getVal(){
        return val;
    }
    public int getCount(){
        return count;
    }
}

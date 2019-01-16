/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;

/**
 *
 * @author bruno
 */
public class MyThread {

    private Matrix matrix;
    private Thread myThread;
    private AlgorithmAJ alg;
    private ArrayList<Path> population;
    private int numberOfPaths;
    private int time;
    
    public MyThread(Matrix matrix, int numberOfPaths, int time) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.population = new ArrayList<>();
    }
       
    
    public void runT() {
        System.out.println("Did run");
        myThread = new Thread(){
                @Override
                public void run(){
                    alg.execute(matrix, 8860, time);
                }
            };
        myThread.start();
    }

    public void startT() {
        
        this.runT();
        
    }
    public Path getBestPath(){
        return alg.getBestPath();
    }
    
    public void joinT() {
        try {
            this.myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Path> getPopulation(){
        for(Path path : alg.getPaths()){
            population.add(path);
        }
        return population;
    }
    
}

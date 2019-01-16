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
public class MyThread extends Thread{

    private Matrix matrix;
    private Thread myThread;
    private AlgorithmAJ alg;
    private ArrayList<Path> population;
    public MyThread(Matrix matrix) {
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix);
        this.population = new ArrayList<>();
    }
    
    public void runT() {
        System.out.println("Did run");
        myThread = new Thread(){
                @Override
                public void run(){
                    alg.execute(matrix, 8860,1 );
                }
                
            };
        myThread.start();
    }

    public void startT() {
        
        this.runT();
        
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Path getBestPath(){
        return alg.getBestPath();
    }
    public ArrayList<Path> getPopulation(){
        for(Path path : alg.getPaths()){
            population.add(path);
        }
        return population;
    }
    
}

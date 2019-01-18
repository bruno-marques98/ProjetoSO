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
       
    @Override
    public void run() {
        alg.execute(matrix, 8860, time);
        Base.setBest(alg.getBestPath());
        Advanced.setBest(alg.getBestPath());
        population = alg.getPaths();
        
    }

    public void setPopulation(ArrayList<Path> population) {
        this.population = population;
    }
    
    public ArrayList<Path> getPopulation() {
        return population;
    }
    
    
    
    
    
    
    


}

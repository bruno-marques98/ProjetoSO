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
    private boolean isBase;
    private ArrayList<Path> global;
    
    public MyThread(Matrix matrix, int numberOfPaths, int time, boolean isBase) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.population = new ArrayList<>();
        this.isBase = isBase;
    }
    public MyThread(Matrix matrix, int numberOfPaths, int time, boolean isBase, ArrayList<Path> global) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.population = new ArrayList<>();
        this.isBase = isBase;
        this.global = global;
    }
       
    @Override
    public void run() {
        if(isBase){
            alg.execute(matrix, time);
            Base.setBest(alg.getBestPath());
        }else{
            alg.executeAdvanced(matrix, time, 10, global);
            Advanced.setBest(alg.getBestPath());
        }
        
        
        population = alg.getPaths();
        
    }

    public void setPopulation(ArrayList<Path> population) {
        this.population = population;
    }
    
    public ArrayList<Path> getPopulation() {
        return population;
    }
    
    
    
    
    
    
    


}

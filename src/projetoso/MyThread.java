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
    private boolean isFirst;
    
    public MyThread(Matrix matrix, int numberOfPaths, int time, boolean isBase) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.population = new ArrayList<>();
        this.isBase = isBase;
    }    
    public MyThread(Matrix matrix, int numberOfPaths, int time, boolean isBase, ArrayList<Path> global,boolean isFirst) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.population = new ArrayList<>();
        this.isBase = isBase;
        this.global = global;
        this.isFirst = isFirst;
    }
       
    @Override
    public void run() {
        population = alg.getPaths();
        if(isBase){
            alg.execute(matrix, time);
            Base.setBest(alg.getBestPath());
        }else{
            
            if(isFirst){
                alg.execute(matrix, time);
            }else{
                alg.execute(matrix, time);
            }
            
            Advanced.setBest(alg.getBestPath());
        }
        
        
        
        
    }

    public void setPopulation(ArrayList<Path> population) {
        this.population = population;
        //alg.setPaths(global);
    }
    
    public ArrayList<Path> getPopulation() {
        return population;
    }

}

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
    
    //VERSÃO A-JE++
    private AlgorithmAJ alg;
    
    //VERSÃO ORIGINAL
    //private AlgorithmOri alg;
    
    private ArrayList<Path> population;
    private int numberOfPaths;
    private int time;
    private boolean isBase;
    private ArrayList<Path> global;
    private boolean isFirst;
    
    public MyThread(Matrix matrix, int numberOfPaths, int time, boolean isBase, double mutation_rate) {
        this.time = time;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        //VERSÃO A-JE++
        this.alg = new AlgorithmAJ(matrix, numberOfPaths, mutation_rate);
        
        //VERSÃO ORIGINAL
        //this.alg = new AlgorithmOri(matrix, numberOfPaths, mutation_rate);
        
        this.population = new ArrayList<>();
        this.isBase = isBase;
    }    

       
    @Override
    public void run() {
        population = alg.getPaths();
        if(isBase){
            alg.execute(matrix, time);
            Base.setBest(alg.getBestPath());
        }else{    
            alg.execute(matrix, time);
            Advanced.setBest(alg.getBestPath());
        }          
    }

    public void setPopulation(ArrayList<Path> population) {
        this.population = population;
        alg.setPaths(global);
    }
    
    public ArrayList<Path> getPopulation() {
        return population;
    }

}

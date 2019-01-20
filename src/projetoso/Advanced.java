/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
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
    private static Semaphore sem = new Semaphore(1);
    private int percentage;
    private ArrayList<Path> global;

    public Advanced(int numberOfThreads, Matrix matrix, int numberOfPaths, int time, int percentage) {
        this.time = time;
        this.numberOfThreads = numberOfThreads;
        this.numberOfPaths = numberOfPaths;
        this.matrix = matrix;
        this.fitness = 1;
        this.alg = new AlgorithmAJ(matrix, numberOfPaths);
        this.threads = new ArrayList<>();
        this.percentage = percentage;
    }
     

    public void execute(){
        //Allow only one thread to write
        sem = new Semaphore(1);
        final Timer timer = new Timer();
        for(int i = 0; i < numberOfThreads; i++){
            MyThread myT = new MyThread(matrix, numberOfPaths, time,false);
            myT.start();
            threads.add(myT); 
            timer.scheduleAtFixedRate(new TimerTask(){
            int i = (time)*(percentage/100);
                public void run(){
                    //System.out.println("run timer");
                    if(i<0){
                        timer.cancel();
                        myT.interrupt();
                        System.out.println("Set new pop");
                        myT.setPopulation(newPopulation());
                        myT.start();
                    }
                }
            },0,time);
            
        }
        joinThreads();
    }

    public static void setBest(Path best) {
        try {
            sem.acquire();
            if(Advanced.best == null || best.fitness() > Advanced.best.fitness()){
                 Advanced.best = best;
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
                Logger.getLogger(Advanced.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    private void swap(ArrayList<Path> paths,int minIndex,int i){
        Path aux = paths.get(minIndex);
        paths.set(minIndex, paths.get(i));
        paths.set(i,aux);
    }
    private void order(ArrayList<Path> paths){
        for(int i = 0; i < paths.size()-1; i++){
            int minIndex = i;
            for(int j = i+1; j < paths.size(); j++){
                if(paths.get(j).getDistance(matrix) < paths.get(minIndex).getDistance(matrix)){
                    minIndex = j;
                }
            }
            swap(paths,minIndex,i);
        }
    }
    public ArrayList<Path> globalPopulation(){
        ArrayList<Path> population = new ArrayList<>();
        for(MyThread t : threads){
            if(t.getPopulation()!= null){
                Iterator<Path> it = t.getPopulation().iterator();
                while(it.hasNext()){
                    population.add(it.next());
                }
            }            
        }
        return population;
    }
    public ArrayList<Path> newPopulation(){
        ArrayList<Path> aux = new ArrayList<>();
        aux = globalPopulation();
        order(aux);
        Iterator<Path> it = aux.iterator();
        while(it.hasNext() && global.size() < numberOfPaths-1){
            global.add(it.next());
        }
        return global;
    }
}

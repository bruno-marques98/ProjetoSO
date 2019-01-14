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
    public int count;

    public Base(int numberOfThreads, Matrix matrix) {
        this.numberOfThreads = numberOfThreads;
        this.matrix = matrix;
        this.count = 0;
    }

    public void execute(){
        for(int i = 0; i < numberOfThreads; i++){
            new Thread(){
                @Override
                public void run(){
                    
                    AlgorithmAJ alg = new AlgorithmAJ(matrix);
                    alg.execute(matrix, 8860,10 );
                    
                }
                
            }.start();
            count++;
        }
    }
    public int getCount(){
        return count;
    }
}

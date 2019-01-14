/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoso;

/**
 *
 * @author bruno
 */
public enum IterationsType {
    TIME, QUANTITY;

    @Override
    public String toString() {
        switch(this){
            case TIME:
                return "Time";
            case QUANTITY:
                return "Quantity";
            default:
                return "";
        }
    }
    
}

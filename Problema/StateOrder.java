/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

/**
 *
 * @author Lucas
 */
public class StateOrder  implements Comparable{
    /**
    Constructs a state order with a given priority and description.
    @param aPriority the priority of state
    @param aDescription the description of state
    */
    private int priority;
    private String description;

    public StateOrder (int aPriority, String aDescription){
        priority = aPriority;
        description = aDescription;
    }

    @Override
    public String toString(){
        return description;
    }


    public int compareTo(Object otherObject){
        StateOrder other = (StateOrder) otherObject;

        if (priority < other.priority) return -1;

        if (priority > other.priority) return 1;

        return 0;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Lucas
 */
public class Aestrella {
    /* A*
    * declaracion priorityQueue para la cola
    * add root node a priorityQueue
    * mientras priorityQueue no este vacia, se realiza lo siguiente:
    * 1. se obtiene y luego se remueve el primer nodo de la lista
    * 2. se verifica el status del nodo recuperado
    *      Si es el nodo objetivo, se corto con un break el loop y se muestra la solucion
    *      Mientras no sea el nodo objetivo:
    *      - expand del nodo recuperado
    *      - se evalua con f(n)
    *      - add a priorityQueue
    *      - se sigue con el loop
    *
    * f(n) = h(n) + g(n)
    * n = currentState (estado actual)
    * h(n) = valor heuristic
    * g(n) = costo
    *
    * inicio = "135782460" corresponde a
    * 1 3 5
    * 7 8 2
    * 4 6 0
    *
    */

    String start = ""; // estado inicial
    String obj = ""; // estado final

    PriorityQueue <StateOrder> queue;

    Map<String,Integer> levelDepth;

    Map<String,String> stateHistory;

    int nodes = 0; // contador de creacion de nodos
    int limit = 100; // contador para el limite
    int unique = -1;
    int newValue; // contador limite profundidad
    int a; // posicion del 0
    int h; // heuristico

    String currState;
    boolean solution = false;

    Aestrella (String start,String obj){
            queue = new PriorityQueue <StateOrder> ();
            levelDepth = new HashMap<String, Integer>();
            stateHistory = new HashMap<String,String>();
            this.start = start;
            this.obj = obj;
            addToQueue(start,null);
    }

    void doSearch (){

        while (!queue.isEmpty()){

            currState = queue.poll().toString();// Se recupera y luego se remueve el nodo de priorityQueue

            if (currState.equals(obj)){ // se verifica si el estado es el objetivo
                solution = true;
                printSolution(currState);// si lo es, se muestra la solucion
                break;
            }

            if (levelDepth.get(currState) == limit){ // se verifica si se esta dentro de los limites
                solution = false;
                printSolution(currState); // se muestran las souciones
                break;
            }else { // expand currentstate y luego add expanded node a openlist
                a = currState.indexOf("0");// get index de 0
                // izq
                while (a != 0 && a != 3 && a != 6){// si el 0 no esta en la columna de la izquierda, entonces l movemos a la izquierda
                    String nextState = currState.substring(0,a-1)+"0"+currState.charAt(a-1)+currState.substring(a+1);// se intercambia el 0 con el valor del lugar que va a ocupar
                    addToQueue(nextState, currState);
                    nodes++;
                    break;
                }
                // arriba
                while (a!=0 && a!=1 && a!=2){// si el 0 no esta en la fila de mas arriba, entonces lo movemos hacia arriba
                    String nextState = currState.substring(0,a-3)+"0"+currState.substring(a-2,a)+currState.charAt(a-3)+currState.substring(a+1);// intercambian valores
                    addToQueue(nextState, currState);
                    nodes++; // un nodo es generado y se van sumando en el contador de nodos
                    break;
                }
                // der
                while(a != 2 && a != 5 && a != 8){// si el 0 no esta en la columna de la derecha, entonces lo movemos hacia la derecha
                    String nextState = currState.substring(0,a)+currState.charAt(a+1)+"0"+currState.substring(a+2);
                    addToQueue(nextState, currState);
                    nodes++;
                    break;
                }
                // abajo
                while (a != 6 && a != 7 && a != 8) {// si el 0 no esta en la fila de mas abajo, entonces lo movemos hacia abajo
                    String nextState = currState.substring(0,a)+currState.substring(a+3,a+4)+currState.substring(a+1,a+3)+"0"+currState.substring(a+4);
                    addToQueue(nextState, currState);
                    nodes++;
                    break;
                }
            }
        }

        if (solution){
            System.out.println("Hay solucion");
        }else {
            System.out.println("No se encontro solucion.");
            System.out.println("Probar subiendo el limite (variable limit en Aestrella.java)");
        }

    }

    private void addToQueue (String newState, String oldState){
        if(!levelDepth.containsKey(newState)){ // se verifica si el estado esta repetido
            newValue = oldState == null ? 0 : levelDepth.get(oldState) + 1;
            unique ++;
            levelDepth.put(newState, newValue);
            h =  calcManhattan(newState,obj) + newValue; // f(n)
            queue.add(new StateOrder(h,newState));// se agraga
            stateHistory.put(newState, oldState);
        }
    }

    int calcMismatch (String currState, String goalState){
        int mismatch = 0;
        for (int i=1;i<9;i++){
            if (currState.indexOf(String.valueOf(i))!= goalState.indexOf(String.valueOf(i))){
                mismatch++;
            }
        }
        return mismatch;
    }

    int calcManhattan(String currState, String goalState){
        // tabla para manhattan distance
        int [][] manValue = {
            {0,1,2,1,2,3,2,3,4},
            {1,0,1,2,1,2,3,2,3},
            {2,1,0,3,2,1,4,3,2},
            {1,2,3,0,1,2,1,2,3},
            {2,1,2,1,0,1,2,1,2},
            {3,2,1,2,1,0,3,2,1},
            {2,3,4,1,2,3,0,1,2},
            {3,2,3,2,1,2,1,0,1},
            {4,3,2,3,2,1,2,1,0},
        };
        // calcular Manhattan distance
        int heu = 0 ;
        int result = 0;
        
        for (int i=1; i<9;i++){
            heu = manValue[currState.indexOf(String.valueOf(i))][goalState.indexOf(String.valueOf(i))];
            result = result + heu;

        }
        return result;
    }

    void printSolution (String currState){
        if (solution){
            System.out.println("Solucion encontrado en el paso: " +levelDepth.get(currState)+".");
            System.out.println("Cantidad de nodos creados: "+ nodes);
        }else{
            System.out.println("No se encontro solucion!");
            System.out.println("Se alcanzo el limite.");
            System.out.println("Cantidad de nodos creados: "+ nodes);
        }

        String traceState = currState;
        while (traceState != null) {
            System.out.println(traceState + " en el paso " + levelDepth.get(traceState));
            try{
                for(int z=0;z<9;z++){
                    System.out.print(" " + String.valueOf(traceState.charAt(z)) + " ");
                    if ((z+1) % 3 == 0){System.out.println();}
                }
            }
            catch (NullPointerException e) {}
                traceState = stateHistory.get(traceState);
       }
        //System.exit(0); //break
    }
}

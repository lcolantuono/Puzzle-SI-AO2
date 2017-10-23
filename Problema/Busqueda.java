/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Fabian
 */
public class Busqueda {
    //Primero en profundidad
    Queue <puzzle> nodosAnalizados = new LinkedList();
    Queue <puzzle> nodosAnalizados2 = new LinkedList();
    Queue <puzzle> auxProf = new LinkedList();
    Queue <puzzle> auxAnt=new LinkedList();
    Stack <puzzle> pilaAux=new <puzzle>Stack();
    int profArbol=4;int nodos=0; int prof=0;
    int nodosExplorar=100;
    Stack <Queue<puzzle>> pilaAuxColas=new <Queue<puzzle>>Stack();
//    Queue <puzzle>auxProf2;
    int iCero=-1,jCero=-1;
    public void busquedaPrimeroProfundidad(puzzle p1,puzzle p2){
        puzzle ori=p1;puzzle ori2=p2;
        
       // p1.mostrar();
        buscarP(p1,p2,auxProf);
    }
    public void buscarP(puzzle p1,puzzle p2, Queue <puzzle> auxProf){
        Queue <puzzle> auxProf2;
       p1.mostrar();
       System.out.println("Profundidad: " + prof);
        
        boolean vx=esNodoAnalizado(p1);
        System.out.println(vx);
        if(iguales(p1,p2)||nodos>=nodosExplorar){
            if(nodos>=nodosExplorar){
                System.out.println("Supero la cantidad de nodos a explorar");
                return;
            }else{
                System.out.println("encontró solución");
                p1.mostrar();
                p2.mostrar();
                return;
            }
        }else if(vx){
            if(!auxProf.isEmpty()){
                p1=auxProf.poll();
                buscarP(p1,p2,auxProf);
            }else{
                
                Queue <puzzle> aux;
                puzzle aux2;
                auxProf=pilaAuxColas.pop();
                prof--;
                while(auxProf.isEmpty()){
                    auxProf=pilaAuxColas.pop();
                    prof--;
                    if(prof==0){
                            System.out.println("No se encontró la solución.");
                            return;
                    }
                }
                buscarP(auxProf.poll(),p2,auxProf);
//                    do{
//                        aux=pilaAuxColas.pop();
//                        aux2=aux.poll();
//                        prof--;
//                    //    System.out.println(prof);
////                        aux2.mostrar();
//                    }while(aux2==null); 
                   // System.out.println("Profundidad: " + prof);
                //buscarP(aux2,p2,aux);
                
            }//Tengo que ver como resolver.
        }else{
           nodosAnalizados.offer(generarPuzzle(p1));
            nodos++;
            
            if(prof<profArbol){
                
                auxProf2=extenderNodosProf(generarPuzzle(p1));
//                System.out.println("Expansion");
//                Iterator <puzzle>prueba=auxProf2.iterator();
//                while(prueba.hasNext()){
//                    prueba.next().mostrar();
//                }
//                System.out.println("Fin expansion");
                if(auxProf2.isEmpty()){
                   
                    do{
                        auxProf=pilaAuxColas.pop();
                        prof--;

                    }while(auxProf.isEmpty()); 
                    p1=auxProf.poll();
                    buscarP(p1,p2,auxProf);
                }else{
                
                p1=auxProf2.poll();
                pilaAuxColas.push(auxProf);
                prof++;
                buscarP(p1,p2,auxProf2);
                }
            
            }else{
                            System.out.println("Estoy aca");        

                if(auxProf.isEmpty()){
                  //  System.out.println("Estoy aca");
                    
                    //    auxProf=pilaAuxColas.pop();
                    
                    while(auxProf.isEmpty()){
                        auxProf=pilaAuxColas.pop();
//                           System.out.println("Expansion");
//                Iterator <puzzle>prueba=auxProf.iterator();
//                while(prueba.hasNext()){
//                    prueba.next().mostrar();
//                }
//                System.out.println("Fin expansion");
                        prof--;
                        System.out.println("Aca"+prof);
                        if(prof==0){
                            System.out.println("No se encontró la solución.");
                            return;
                        }
                        //aux2.mostrar();
                    }
                    
                    //prof++;
                        p1=auxProf.poll();
                        buscarP(p1,p2,auxProf);
                    
                }else{
                p1=auxProf.poll();
                buscarP(p1,p2,auxProf);
                }
                //System.out.println("Excedio la profundidad del arbol");
                
            }
           
            //if(!auxProf.isEmpty())
            //pilaAuxColas.push(auxProf);
            
            
            
            
        }
        
  
        
        
        
        
        
        
        //auxProf2=extenderNodosProf(p1);
                    
                    
                    
            
           
            
            
            
//              p1=auxProf.poll();
//              buscarP(p1,p2);
//            if(esNodoAnalizado(p1)){
//                    return;
//            }else{
//                nodosAnalizados.offer(p1);
//                p1=auxProf.poll();
//                    buscarP(p1,p2);
//            }
                
            
            
          //  nodosAnalizados.offer(p1);
        
    }
    public boolean esNodoAnalizado(puzzle p){
        Iterator <puzzle>it=nodosAnalizados.iterator();
       // System.out.println("Analisis de nodos: ");
      //System.out.println("Analice tal nodo: ");  p.mostrar();
        while(it.hasNext()){
            puzzle t=generarPuzzle(it.next());
          
            if(igual(p,t)){
                
                return true;
            }
        }
      //  System.out.println("fin de análisis ");
    
        
        return false;
    }
    public boolean esNodoAnalizado2(puzzle p){
        Iterator <puzzle>it=nodosAnalizados2.iterator();
       // System.out.println("Analisis de nodos: ");
      //System.out.println("Analice tal nodo: ");  p.mostrar();
        while(it.hasNext()){
            puzzle t=generarPuzzle(it.next());
          
            if(igual(p,t)){
                
                return true;
            }
        }
      //  System.out.println("fin de análisis ");
    
        
        return false;
    }
    public boolean igual(puzzle p1,puzzle p2){
        for(int i=0;i<3; i++){
            for(int j=0;j<3;j++){
                if(p1.getPuz()[i][j]!=p2.getPuz()[i][j]){
                    
                    return false;
                }
            }
        
        }
       
        
        return true;
    }
    public Queue<puzzle> extenderNodosProf(puzzle ori){
        Queue<puzzle> auxProf = new LinkedList();
        determinaCeros(ori);
        puzzle p1=generarPuzzle(ori);
       // System.out.println("i cero: "+ iCero + " jCero: " + jCero);
       // ori.mostrar();
        if(iCero==0){
                if(jCero==0){
//                    System.out.println("Antes ");
//                    ori.mostrar();p1.mostrar();
                    ori.moverAbajo(jCero, jCero);
                    puzzle x=generarPuzzle(ori);
//                    System.out.println("Despues ");
//                    ori.mostrar();p1.mostrar();
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    
                    auxProf.offer(x);auxProf.offer(x2);
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x3);auxProf.offer(x2);
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x2);
                }
        }else if(iCero==1){
                if(jCero==0){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    auxProf.offer(x);auxProf.offer(x3);auxProf.offer(x2);
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x4=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    auxProf.offer(x);auxProf.offer(x2);auxProf.offer(x3);auxProf.offer(x4);
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x3);auxProf.offer(x2);
                }
            }else if(iCero==2){
                if(jCero==0){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x2);
                }else if(jCero==1){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x3);auxProf.offer(x2);
                }else if(jCero==2){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    auxProf.offer(x);auxProf.offer(x2);
                }
            }
        return auxProf;
    }
    
    
    //Primero en anchura
    
    Queue <puzzle> ls = new LinkedList();
    
    
    
    public void busquedaPrimeroAnchura(puzzle p1, puzzle p2){
       puzzle ori=p1;puzzle ori2=p2;
       
       ls.add(generarPuzzle(p1));
           
        puzzle d = generarPuzzle(p1);
        
      
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //ls.add(null);
       recurBuscar(d,p2,auxProf);
//        while(!iguales(d,ori2)){
//                
//                d=generarPuzzle(ls.poll());
//                
//                
//                d.mostrar();
//                extenderNodos(d);
//                
//            
//            
//        }
        
       
        
        
        
    }
  
    Queue <puzzle> auxAnchura = new <puzzle>LinkedList();
    Queue <puzzle> auxAnchura2 = new <puzzle>LinkedList();
    Queue <Integer>colaAux2=new <Integer>LinkedList();
    int cantidad=1,cantidadFutura=0;int nAnalizados=0;
    public void recurBuscar(puzzle p1,puzzle p2,Queue <puzzle> auxProf){
        
       Queue <puzzle> auxProf2;
       
       boolean vx=esNodoAnalizado2(generarPuzzle(p1));
        if(iguales(p1,p2)||nodos>=nodosExplorar){
            if(nodos>=nodosExplorar){
                System.out.println("Supero la cantidad de nodos a explorar");
                return;
            }else{
                System.out.println("encontró solución");
                p1.mostrar();
                p2.mostrar();
                return;
            }       
        }else if(vx){
            cantidad--;
            recurBuscar(auxProf.poll(),p2,auxProf);
        }else{
            
            cantidadFutura+=contarFuturosNodos(generarPuzzle(p1));
            cantidad--;
            p1.mostrar();
            System.out.println(prof);
            
            if(cantidad==0){
                prof++;
            }
            auxAnchura.offer(p1);
            if(prof>=profArbol){
                System.out.println("No se encontro solucion");
                return;
            }else{
            if(auxProf.isEmpty()&&prof<profArbol){
               auxProf2=extenderNodosProf(auxAnchura.poll());
               //prof++;
               if(cantidad==0){
               cantidad=cantidadFutura;
               cantidadFutura=0;
               }
               recurBuscar(auxProf2.poll(),p2,auxProf2);
            }else{
                
                puzzle paux=auxProf.poll();
                recurBuscar(paux,p2,auxProf);
                
               
            }}
            
        }
    }
    
    public void determinaCeros(puzzle d){
        boolean v=false;
        for(int i=0;i<3;i++){
                for(int j=0; j<3;j++){
                    if(d.getPuz()[i][j]==0){
                        v=true;
                        
                        iCero=i;jCero=j;
                        //System.out.println("El cero esta en: "+ i + " " + j);
                        
                        break;
                        
                    }
                    if(v)break;
                }
            }
    }
    public boolean iguales(puzzle p1,puzzle p2){
        for(int i=0;i<3; i++){
            for(int j=0;j<3;j++){
                if(p1.getPuz()[i][j]!=p2.getPuz()[i][j]){
                    return false;
                }
            }
        
        }
        System.out.println("son iguales:");
        p1.mostrar();
        p2.mostrar();
        
        return true;
    }
    public puzzle generarPuzzle(puzzle d){
        puzzle p1=new puzzle();
        for(int i =0; i<3; i++){
            for(int j=0;j<3;j++){
                p1.puz[i][j]=d.puz[i][j];
            }
        }
        return p1;
    }
    public int  contarFuturosNodos( puzzle ori){
        determinaCeros(ori);
        int po=0;
        puzzle p1=generarPuzzle(ori);
        if(iCero==0){
                if(jCero==0){
//                    System.out.println("Antes ");
//                    ori.mostrar();p1.mostrar();
                    ori.moverAbajo(jCero, jCero);
                    puzzle x=generarPuzzle(ori);
                    po++;
//                    System.out.println("Despues ");
//                    ori.mostrar();p1.mostrar();
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    po++;
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    return po;
                    
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    po++;
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    po++;
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    po++;
                    return po;
                    
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 2;
                    
                }
        }else if(iCero==1){
                if(jCero==0){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 3;
                    
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x4=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 4;
                    
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 3;
                    
                }
            }else if(iCero==2){
                if(jCero==0){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    return 2;
                }else if(jCero==1){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 3;
                    
                }else if(jCero==2){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    return 2;
                    
                }
            }
            return -1;
    }
    public void extenderNodos( puzzle ori){
        determinaCeros(ori);
        puzzle p1=generarPuzzle(ori);
        if(iCero==0){
                if(jCero==0){
//                    System.out.println("Antes ");
//                    ori.mostrar();p1.mostrar();
                    ori.moverAbajo(jCero, jCero);
                    puzzle x=generarPuzzle(ori);
//                    System.out.println("Despues ");
//                    ori.mostrar();p1.mostrar();
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    
                    ls.offer(x);ls.offer(x2);
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x3);ls.offer(x2);
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x2);
                }
        }else if(iCero==1){
                if(jCero==0){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    ls.offer(x);ls.offer(x3);ls.offer(x2);
                }else if(jCero==1){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x4=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    
                    ls.offer(x);ls.offer(x2);ls.offer(x3);ls.offer(x4);
                }else if(jCero==2){
                    ori.moverAbajo(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverArriba(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x3);ls.offer(x2);
                }
            }else if(iCero==2){
                if(jCero==0){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x2);
                }else if(jCero==1){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverDerecha(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x3=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x3);ls.offer(x2);
                }else if(jCero==2){
                    ori.moverArriba(iCero, jCero);
                    puzzle x= generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ori.moverIzquierda(iCero, jCero);
                    puzzle x2=generarPuzzle(ori);
                    ori=generarPuzzle(p1);
                    ls.offer(x);ls.offer(x2);
                }
            }
            
    }
    
}

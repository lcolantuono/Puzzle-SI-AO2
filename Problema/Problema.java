/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;
import java.io.*;
/**
 *
 * @author Fabian & Lucas
 */
public class Problema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        boolean flag=true;
        String objetivoAe = "";
        String inicioAe = "";
        BufferedReader io=new BufferedReader(new InputStreamReader(System.in));
        while(flag){
            System.out.println("MENU--------");
            System.out.println("1. Metodo A* --------");
            System.out.println("2. Metodo por Anchura--------");
            System.out.println("3. Metodo por profundidad--------");
            System.out.println("0. Salir");
            int opcion=Integer.parseInt(io.readLine());
            if(opcion==0){
                flag=false;
            }else{
                int[][] matrizInicio = new int[3][3];
                int[][] matrizObjetivo=new int[3][3];
                int contador=0;
                System.out.println("A CONTINUACIÓN SE SOLICITAN LOS ELEMENTOS DE LA MATRIZ OBJETIVO");
                
                for(int i =0;i<3;i++){
                    for(int j=0; j<3;j++){
                        System.out.println("Ingrese el elemento de fila: " + i + " , col: " + j);
                        String charElem = io.readLine();

                        if (!isNumeric(charElem)){
                            System.out.println("Ingrese un valor numerico.");
                            j--;
                        }else{
                            int elemento = Integer.parseInt(charElem);
                            if (!(elemento>=0 && elemento <=8)){
                                System.out.println("Ingrese un numero entre 0 y 8.");
                                j--;
                            }else if (!(objetivoAe.contains(charElem))){
                                matrizObjetivo[i][j]=elemento;

                                if (i==0 && j==0){
                                    objetivoAe = ""+elemento;
                                }else{
                                    objetivoAe = objetivoAe+""+elemento;
                                }
                            }else{
                                System.out.println("Ese numero ya se encuentra cargado");
                                j--;
                            }
                        }
                    }
                }
                
                System.out.println("A CONTINUACIÓN SE SOLICITAN LOS ELEMENTOS DE LA MATRIZ INICIO");
                for(int i =0; i<3;i++){
                    for(int j=0;j<3;j++){
                        System.out.println("Ingrese el elemento de fila: " + i + " , col: " + j);
                        String charElem = io.readLine();

                        if (!isNumeric(charElem)){
                            System.out.println("Ingrese un valor numerico.");
                            j--;
                        }else{
                            int elemento = Integer.parseInt(charElem);
                            if (!(elemento>=0 && elemento <=8)){
                                System.out.println("Ingrese un numero entre 0 y 8.");
                                j--;
                            }else if (!(inicioAe.contains(charElem))){
                                matrizInicio [i][j]=elemento;

                                if (i==0 && j==0){
                                    inicioAe = ""+elemento;
                                }else{
                                    inicioAe = inicioAe+""+elemento;
                                }
                            }else{
                                System.out.println("Ese numero ya se encuentra cargado");
                                j--;
                            }
                        }
                    }
                }
                puzzle inicio = new puzzle(matrizInicio);
                puzzle objetivo=new puzzle(matrizObjetivo);
                Busqueda b1=new Busqueda();
                if(opcion==1){ // Codigo para llamar metodo A*.
                    System.out.println("------- A* | A Estrella -------");
                    System.out.println("------- Inicio: "+inicioAe);
                    System.out.println("------- Objetivo: "+objetivoAe);
                    Aestrella aest = new Aestrella(inicioAe,objetivoAe);
                    aest.doSearch();
                }else if(opcion==2){ // Codigo para llamar PrimeroAnchura
                    System.out.println("------- Primero en Anchura -------");
                    System.out.println("------- Inicio: "+inicioAe);
                    System.out.println("------- Objetivo: "+objetivoAe);
                    b1.busquedaPrimeroAnchura(inicio, objetivo);
                }else if(opcion==3){ // Codigo para llamar PrimeroProfundidad
                    System.out.println("------- Primero en Profunidad -------");
                    System.out.println("------- Inicio: "+inicioAe);
                    System.out.println("------- Objetivo: "+objetivoAe);
                    b1.busquedaPrimeroProfundidad(inicio, objetivo);
                }
            }
        }
    }
    
    /*
    * Funcion para validar que un String sea un numero.
    */
    public static boolean isNumeric(String str)
    {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    
}

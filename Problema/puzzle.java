/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Problema;

/**
 *
 * @author Fabian
 */
public class puzzle {
    int can_filas;
    int can_cols;
    int [][] puz;
    public puzzle(int [][] mat){
        puz=mat;
    } 
    public puzzle(){
        puz=new int[3][3];
    } 
    public void setPuz(int [][] d){
        this.puz=d;
    }
    public int [][] getPuz(){
        return puz;
    }
    public void moverDerecha(int fila, int col){
        int aux = this.puz[fila][col + 1];
        this.puz[fila][col +1 ]=this.puz[fila][col];
        this.puz[fila][col] = aux;
                
    }
    
    public void moverIzquierda(int fila, int col){
         int aux = this.puz[fila][col -1];
        this.puz[fila][col -1 ]=this.puz[fila][col];
        this.puz[fila][col] = aux;
    }
    
    public void moverArriba(int fila, int col){
         int aux = this.puz[fila-1][col];
        this.puz[fila-1][col]=this.puz[fila][col];
        this.puz[fila][col] = aux;
    }
    
    public void moverAbajo(int fila, int col){
        int aux = this.puz[fila+1][col];
        this.puz[fila+1][col]=this.puz[fila][col];
        this.puz[fila][col] = aux;
    }
    public puzzle getPuzzle(){
        return this;
    }
    public void mostrar(){
        System.out.println("Mostrar matriz: ");
        for(int i = 0 ;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(this.puz[i][j] + " ");
            }
            System.out.println();
        }
    }
}

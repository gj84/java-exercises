// Implementacion del juego de la vida

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Ex_02 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int iterations = 1; //sc.nextInt();
        Life lf = new Life(8, 20);
        int[][] lvCells = {{2,0}, {3, 0}, {0, 2}, {1,2}};
        lf.setInitialBoard(lvCells);
        for (int i = 0; i < iterations; i++){
            lf.updateBoard();
            lf.showBoard();
        }
    }

}

abstract class Board{
    public int rows;
    public int columns;
    public int[][] board = new int[rows][columns] ; 
}

class Life {//extends Board {
    
    // Reglas
    // Si una célula está viva y tiene dos o tres vecinas vivas, sobrevive.
    // Si una célula está muerta y tiene tres vecinas vivas, nace.
    // Si una célula está viva y tiene más de tres vecinas vivas, muere.
    
    public int rows;
    public int columns;
    public String[][] board;// = new int[rows][columns] ; 
    
    public Life(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.board = new String[rows][columns] ; 
    }
    
    public void setInitialBoard(int[][] liveCells){
        for (int r=0; r < this.rows; r++){
            for (int c=0; c < this.columns; c++){
                this.board[r][c] = "-";
            }
        }
        for (int[] coord: liveCells){
            this.board[coord[0]][coord[1]] = "*";
        }
    }

    
    public void updateBoard(){
        
    }
    
    public void showBoard(){
        //System.out.println(Arrays.deepToString(this.board));
        for (int r=0; r < this.rows; r++){
            String row = String.join("", this.board[r]);
            System.out.println(row);
        }
        
    }
}

// Implementacion del juego de la vida, correr https://replit.com/@gj84/LifeGame#Main.java

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.lang.Runtime;
import java.util.concurrent.TimeUnit;
public class Main {

    public static void wait(int ms){
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }    
    }
    
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int iterations = 200; //sc.nextInt();
        Life lf = new Life(16, 32);
        //int[][] lvCells = {{0, 2}, {1,2}, {1,3}, {2,2}, {2,0}, {3, 0}, {4, 6}, {5,6}, {5,7}, {6,6} };
        //lf.setInitialBoard(lvCells);
        lf.randomizeBoard();
        lf.showBoard();
        for (int i = 0; i < iterations; i++){
            wait(150);
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

    
    public int rows;
    public int columns;
    public String[][] board;// = new int[rows][columns] ; 
    
    public Life(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.board = new String[rows][columns] ; 
    }
    
    public void randomizeBoard(){
        String[] cellStates = {"*", "-", "-", "-","-"};  
        Random random = new java.util.Random();
        String state;
        for (int r=0; r < this.rows; r++){
            for (int c=0; c < this.columns; c++){
                state = cellStates[random.nextInt(cellStates.length)];
                this.board[r][c] = state;
            }
        }
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

    public int countNeighbours(int cRow, int cCol){
        // Se va a realizar en sentido de las agujas del reloj
        // comenzando por arriba a la izquierda
        
        int neighbours = 0;
        
        // Arriba a la izquierda
        if (cRow > 0 && cCol > 0){
            if (this.board[cRow-1][cCol-1] == "*"){
                neighbours += 1;
            }    
        }
        
        // Arriba
        if (cRow > 0){
            if (this.board[cRow-1][cCol] == "*"){
                neighbours += 1;
            }        
        }
        
        // Arriba a la derecha
        if (cRow > 0 && cCol < this.columns - 1){
            if (this.board[cRow-1][cCol+1] == "*"){
                neighbours += 1;
            }        
        }
        
        // Derecha
        if (cCol < this.columns - 1){
            if (this.board[cRow][cCol+1] == "*"){
                neighbours += 1;
            }                    
        }
        
        // Abajo a la derecha
        if (cRow < this.rows - 1 && cCol < this.columns - 1){
            if (this.board[cRow+1][cCol+1] == "*"){
                neighbours += 1;
            }                    
        }
        
        // Abajo
        if (cRow < this.rows - 1){
            if (this.board[cRow+1][cCol] == "*"){
                neighbours += 1;
            }        
        }
        
        // Abajo a la izquierda
        if (cRow < this.rows - 1 && cCol > 0){
            if (this.board[cRow+1][cCol-1] == "*"){
                neighbours += 1;
            }        
        }
        
        // Izquierda
        if (cCol > 0){
            if (this.board[cRow][cCol-1] == "*"){
                neighbours += 1;
            }        
        }
        return neighbours;
    }
    
    public String[][] getEmptyBoard(){
        String[][] emptyBoard = new String[this.rows][this.columns];
        for (int r=0; r < this.rows; r++){
            for (int c=0; c < this.columns; c++){
                //emptyBoard[r][c] = "-";
                emptyBoard[r][c] = this.board[r][c];
            }
        }
        //System.out.println(Arrays.deepToString(emptyBoard));
        return emptyBoard;
    }
    
    public void updateBoard(){
        String[][] emptyBoard = getEmptyBoard();
        for (int r=0; r < this.rows; r++){
            for (int c=0; c < this.columns; c++){
                int neighbors = countNeighbours(r, c);
                // Reglas
                // Any live cell with two or three live neighbours survives.
                // Any dead cell with three live neighbours becomes a live cell.
                // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                // Muerta
                if (this.board[r][c] == "-"){
                    // Si una célula está muerta y tiene tres vecinas vivas, nace.
                    if (neighbors == 3){
                        emptyBoard[r][c] = "*";
                    }
                }

                // Viva
                else if (this.board[r][c] == "*"){}
                    // Si una célula está viva y tiene dos o tres vecinas vivas, sobrevive.
                    
                    // Si una célula está viva y tiene más de tres vecinas vivas, muere.
                    if (!(neighbors == 3 || neighbors == 2)){
                        emptyBoard[r][c] = "-";
                    }
                
            }
        }
        //System.out.println(Arrays.deepToString(emptyBoard));
        this.board = emptyBoard;
    }

    public void clearConsole(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
    public void showBoard() {    
        //clearConsole();
        
        //System.out.println(Arrays.deepToString(this.board));
        // System.out.println("");
        String outputboard = "";
        for (int r=0; r < this.rows; r++){
            String row = String.join("", this.board[r]);
            row = row.replace("*", "◎");//"▢");//"█");
            row = row.replace("-", " ");

            outputboard += row + "\n";                       
            
        }
        System.out.println(outputboard);
    }
}

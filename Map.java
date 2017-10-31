/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istassignment;

import static istassignment.Game.Lose;
import static istassignment.MovePiece.checkDown;
import static istassignment.MovePiece.moveDown;
import static istassignment.MovePiece.placePiece;
import static istassignment.MovePiece.value;
import static istassignment.Pieces.randomPiece;
import static istassignment.Game.Score;
import static istassignment.Game.Lines;


/**
 *
 * @author zhuynh022
 */
public class Map {
    
    
    public final static int EDGE = 4;
    public final static int ROWS = 24+2*EDGE;
    public final static int COLS = 10+2*EDGE;
    
    public static int[][] map = new int[ROWS][COLS];
    
    static void placeEdges() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                if ((y <= EDGE) || (y >= ROWS-EDGE)) {
                    map[y][x] = -1;
                }
                else if ((x < EDGE) || (x >= COLS-EDGE)) {
                    map[y][x] = -1;
                }
            }
        }
    }
    
    static void clearRow() {
        int lineAmount = 0;
        int moveValue = 0;
        for (int rowY = ROWS-EDGE-1; rowY > EDGE+1; rowY--) {
            int clear = 0;
            for (int x = EDGE; x < COLS-EDGE; x++) {
                if ((map[rowY][x] != 0)) {
                    clear++;
                }
            }
            if (clear == COLS-(EDGE*2)) {
                Lines++;
                lineAmount++;
                for (int y = rowY; y > EDGE+1; y--) {
                    for (int x = EDGE; x < COLS-EDGE; x++) {
                        if (x < ROWS-EDGE) {
                            map[y][x] = map[y-1][x];
                            map[y-1][x] = 0;
                        }
                    }
                }
            }
        }
        //
        Score += clearValue(1, lineAmount);
    }
    
    static int clearValue(int level, int lineAmount) {
        int value = 0;
        if (lineAmount == 1) {
            value = 40*(level+1);
        }
        else if (lineAmount == 2) {
            value = 100*(level+1);
        }
        else if (lineAmount == 3) {
            value = 300*(level+1);
        }
        else if (lineAmount == 4) {
            value = 1200*(level+1);
        }
        return value;
    }
    
    static void printMap() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                if (map[y][x] >= 0) {
                    System.out.print(" ");
                }
                System.out.print(map[y][x]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    /*
    public static void main(String[] args) {
        placeEdges();
        int [][] piece = randomPiece();
        int [] origin = placePiece(piece);
        coordY = origin[0];
        coordX = origin[1];
        while (checkDown(piece, coordY, coordX)) {
            printMap();
            moveDown(piece, coordY, coordX);
        }
        placePiece(piece);
        while (checkDown(piece, coordY, coordX)) {
            printMap();
            moveDown(piece, coordY, coordX);
        }
        printMap();
    }
    */
}

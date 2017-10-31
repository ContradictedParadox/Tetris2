/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istassignment;

import static istassignment.Map.COLS;
import static istassignment.Map.EDGE;
import static istassignment.Map.ROWS;
import static istassignment.Map.map;
import static istassignment.Pieces.MATRIXSIZE;

import static istassignment.Game.Lose;
import static istassignment.Game.coordY;
import static istassignment.Game.coordX;
import static istassignment.Pieces.rotate;

/**
 *
 * @author zhuynh022
 */
public class MovePiece {
    
    public static int value(int [][] piece) {
        int value = 0;
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                if (piece[y][x] != 0) {
                    value = piece[y][x];
                }
            }
        }
        return value;
    }
    
    public static int[] placePiece(int[][] piece) {
        int [] origin = new int[2];
        boolean valid = false;
        boolean stop = false;
        int finalY = 0;
        int finalX = 0;
        int count = 0;
        while (!stop && !valid) {
            for (int fromY = 0; fromY < EDGE+1; fromY++) {
                for (int fromX = 0; fromX < COLS-MATRIXSIZE; fromX++) {
                    count = 0;
                    for (int y = 0; y < MATRIXSIZE; y++) {
                        for (int x = 0; x < MATRIXSIZE; x++) {
                            if (map[fromY+y][fromX+x] == 0) {
                                count++;
                            }
                            else if ((map[fromY+y][fromX+x] != 0) && (piece[y][x] == 0)) {
                                count++;
                            }
                        }
                    }
                    if (count == MATRIXSIZE*MATRIXSIZE) {
                        valid = true;
                        stop = true;
                        finalX = fromX;
                        finalY = fromY;
                        break;
                        //System.out.println(finalX + "" + finalY);
                    }
                }
                if (fromY == EDGE) {
                    stop = true;
                }
                if (valid) {
                    break;
                }
            }
        }
        if ((finalX > 0) && (finalY > 0)) {
            //place piceg
            for (int y = 0; y < MATRIXSIZE; y++) {
                for (int x = 0; x < MATRIXSIZE; x++) {
                    map[finalY+y][finalX+x] += piece[y][x];
                }
            }
        }
        origin[0] = finalY;
        origin[1] = finalX;
        coordY = finalY;
        coordX = finalX;
        if (finalY == 0) {
            Lose = true;
        }
        return origin;
    }    
    
    public static boolean checkDown(int[][] piece, int fromY, int fromX) {
        int count = 0;
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                if (map[fromY+y+1][fromX+x] == 0) {
                    count++;
                }
                else if ((map[fromY+y+1][fromX+x] != 0) && (piece[y][x] == 0)) {
                    count++;
                }
                else if ((map[fromY+y+1][fromX+x] != 0) && (map[fromY+y][fromX+x] == 0)) {
                    count++;
                }
                else if (piece[y][x] == value(piece)) {
                    if ((map[fromY+y+1][fromX+x] == value(piece)) && (piece[y+1][x] == value(piece))) {
                        count++;
                    }
                }
            }
        }
        return (count == MATRIXSIZE*MATRIXSIZE);
    }
     
    public static void moveDown(int[][] piece, int fromY, int fromX) {
        if (checkDown(piece, fromY, fromX)) {
            for (int y = 0; y < MATRIXSIZE; y++) {
                for (int x = 0; x < MATRIXSIZE; x++) {
                    map[fromY+y][fromX+x] -= piece[y][x];
                    map[fromY+y+1][fromX+x] += piece[y][x];
                }
            }
            coordY++;
        }
    }
    
    public static boolean checkRight(int[][] piece, int fromY, int fromX) {
        int count = 0;
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                if (map[fromY+y][fromX+x+1] == 0) {
                    count++;
                }
                else if ((map[fromY+y][fromX+x+1] != 0) && (piece[y][x] == 0)) {
                    count++;
                }
                else if ((map[fromY+y][fromX+x+1] != 0) && (map[fromY+y][fromX+x] == 0)) {
                    count++;
                }
                else if (piece[y][x] == value(piece)) {
                    if ((map[fromY+y][fromX+x+1] == value(piece)) && (piece[y][x+1] == value(piece))) {
                        count++;
                    }
                }
            }
        }
        return (count == MATRIXSIZE*MATRIXSIZE);
    }
    
    public static void moveRight(int[][] piece, int fromY, int fromX) {
        if (checkRight(piece, fromY, fromX)) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                for (int y = 0; y < MATRIXSIZE; y++) {
                    map[fromY+y][fromX+x] -= piece[y][x];
                    map[fromY+y][fromX+x+1] += piece[y][x];
                }
            }
            coordX++;
        }
    }
    
    public static boolean checkLeft(int[][] piece, int fromY, int fromX) {
        int count = 0;
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                if (map[fromY+y][fromX+x-1] == 0) {
                    count++;
                }
                else if ((map[fromY+y][fromX+x-1] != 0) && (piece[y][x] == 0)) {
                    count++;
                }
                else if ((map[fromY+y][fromX+x-1] != 0) && (map[fromY+y][fromX+x] == 0)) {
                    count++;
                }
                else if (piece[y][x] == value(piece)) {
                    if ((map[fromY+y][fromX+x-1] == value(piece)) && (piece[y][x-1] == value(piece))) {
                        count++;
                    }
                }
            }
        }
        return (count == MATRIXSIZE*MATRIXSIZE);
    }
    
    public static void moveLeft(int[][] piece, int fromY, int fromX) {
        if (checkLeft(piece, fromY, fromX)) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                for (int y = 0; y < MATRIXSIZE; y++) {
                    map[fromY+y][fromX+x] -= piece[y][x];
                    map[fromY+y][fromX+x-1] += piece[y][x];
                }
            }
            coordX--;
        }
    }
    
    public static boolean checkRotate(int [][] piece, int fromY, int fromX) {
        int count = 0;
        piece = rotate(piece);
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                if (map[fromY+y][fromX+x] == 0) {
                    count++;
                }
                else if ((map[fromY+y][fromX+x] != 0 ) && (piece[y][x] == 0)) {
                    count++;
                }
                else if (piece[y][x] == value(piece)) {
                    if (map[fromY+y][fromX+x] == value(piece)) {
                        count++;
                    }
                }
            }
        }
        return (count == MATRIXSIZE*MATRIXSIZE);
    }
    
    public static void moveRotate(int [][] piece, int fromY, int fromX) {
        if (checkRotate(piece, fromY, fromX)) {
            int [][] rotated = rotate(piece);
            for (int y = 0; y < MATRIXSIZE; y++) {
                for (int x = 0; x < MATRIXSIZE; x++) {
                    map[fromY+y][fromX+x] += rotated[y][x];
                }
            }
            for (int y = 0; y < MATRIXSIZE; y++) {
                for (int x = 0; x < MATRIXSIZE; x++) {
                    map[fromY+y][fromX+x] -= piece[y][x];
                }
            }
        }
    }
}

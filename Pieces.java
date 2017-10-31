/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istassignment;

/**
 *
 * @author zhuynh022
 */
import java.util.Random;

public class Pieces {
    //0 is wildcard
    //pieces can be placed inside the main double array which is updated
    //in the graphics.
    public static int MATRIXSIZE = 6;
    public final static int[][] IBLOCK = {
        {0,0,0,0,0,0},
        {0,0,1,0,0,0},
        {0,0,1,0,0,0},
        {0,0,1,0,0,0},
        {0,0,1,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like an I Block
    
    public final static int[][] JBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,2,2,2,0,0},
        {0,0,0,2,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like a J Block
    
    public final static int[][] LBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,0,3,3,3,0},
        {0,0,3,0,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like a L Block
    
    public final static int[][] OBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,0,4,4,0,0},
        {0,0,4,4,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like an O Block
    
    public final static int[][] SBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,0,5,5,0,0},
        {0,5,5,0,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like an S Block
    
    public final static int[][] TBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,6,6,6,0,0},
        {0,0,6,0,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like a T Block
    
    public final static int[][] ZBLOCK = {
        {0,0,0,0,0,0},
        {0,0,0,0,0,0},
        {0,7,7,0,0,0},
        {0,0,7,7,0,0},
        {0,0,0,0,0,0},
        {0,0,0,0,0,0}
    }; //Looks like a Z Block

    public static int [][][] blocks = {IBLOCK, JBLOCK, LBLOCK, OBLOCK, SBLOCK, TBLOCK, ZBLOCK};
    
    //rotate piece 90 clockwise
    public static int[][] rotate(int[][] piece) {
        int [][] rotated = new int[MATRIXSIZE][MATRIXSIZE];
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                rotated[(MATRIXSIZE-1)-x][y] = piece[y][x];
            }
        }
        return rotated;
    }
    
    public static int[][] randomPiece() {
        Random randomGenerator = new Random();
        int random = randomGenerator.nextInt(7);
        int[][] piece = blocks[random];
        return piece;
    }
    
    
}

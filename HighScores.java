/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istassignment;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author zhuynh022
 */

public class HighScores {
    public static char [] name = new char [6];
    
    public static int finalLines = 0;
    public static int finalScore = 0;
    
    public static String input(int name, int finalLines, int finalScore) {
        String Name = "     ";
        return Name;
    }
    
    public static void sortScores () {
        //reads into file and sorts scores
    }
    
    public static void writeToFile() {
        try {
            FileWriter writer = new FileWriter("Scores.txt", true);
            writer.write(name+ ": ");
            writer.write("lines: " +finalLines+ " ");
            writer.write("score: " +finalScore);
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        writeToFile();
        try {
            FileReader reader = new FileReader("Scores.txt");
            int character;
            
            while ((character = reader.read()) != -1) {
                //while ((character = reader.read()) != null)
                System.out.print((char) character);
            }
            System.out.println("");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

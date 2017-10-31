/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package istassignment;

import static istassignment.Map.COLS;
import static istassignment.Map.ROWS;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static istassignment.Map.map;
import static istassignment.Map.placeEdges;
import static istassignment.Map.clearRow;
import static istassignment.MovePiece.placePiece;
import static istassignment.MovePiece.checkDown;
import static istassignment.MovePiece.moveDown;
import static istassignment.MovePiece.moveRight;
import static istassignment.MovePiece.moveLeft;
import static istassignment.MovePiece.moveRotate;
import static istassignment.MovePiece.checkRotate;
import static istassignment.MovePiece.value;
import static istassignment.Pieces.MATRIXSIZE;
import static istassignment.HighScores.finalScore;
import static istassignment.HighScores.finalLines;

import static istassignment.Pieces.randomPiece;
import static istassignment.Pieces.rotate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author zhuynh022
 */
public class Game extends Application
        implements EventHandler <KeyEvent> {
    
    public static double time = 0;
    public static double change = 0;
    
    public static final int SQUARESIZE = 30;
    public static final int HEIGHT = ROWS*SQUARESIZE;
    public static final int WIDTH = COLS*SQUARESIZE;
    
    public static boolean Start = false;
    public static boolean Pause = true;
    public static int Score = 0;
    public static int Lines = 0; //amount of lines
    
    public static boolean Lose = false;
    public static boolean Ground = false;
    public static int [] origin = new int [2];
    
    public static boolean moveRight = false;
    public static boolean moveLeft = false;
    public static boolean moveUp = false;
    public static boolean moveDown = false;
    
    public static int[][] piece;
    public static int[][] nextPiece;
    
    public static int coordY;
    public static int coordX;
    
    public static Rectangle [][] rectangleMap = new Rectangle[ROWS][COLS];
    public static Rectangle [][] display = new Rectangle[MATRIXSIZE][MATRIXSIZE];
    
    public static void painter(Rectangle square, int y, int x) {
        if (map[y][x] == -1) {
            square.setFill(Color.rgb(0,120,255));
        }
        if (map[y][x] == 1) {
            square.setFill(Color.CYAN);
        }
        if (map[y][x] == 2) {
            square.setFill(Color.BLUE);
        }
        if (map[y][x] == 3) {
            square.setFill(Color.ORANGE);
        }
        if (map[y][x] == 4) {
            square.setFill(Color.YELLOW);
        }
        if (map[y][x] == 5) {
            square.setFill(Color.GREEN);
        }
        if (map[y][x] == 6) {
            square.setFill(Color.PURPLE);
        }
        if (map[y][x] == 7) {
            square.setFill(Color.RED);
        }
        if (map[y][x] == 0) {
            square.setFill(Color.BLACK);
        }
    }
    
    public static void displayPainter(int[][] nextPiece, Rectangle square, int y, int x) {
        if (nextPiece[y][x] == 1) {
            square.setFill(Color.CYAN);
        }
        if (nextPiece[y][x] == 2) {
            square.setFill(Color.BLUE);
        }
        if (nextPiece[y][x] == 3) {
            square.setFill(Color.ORANGE);
        }
        if (nextPiece[y][x] == 4) {
            square.setFill(Color.YELLOW);
        }
        if (nextPiece[y][x] == 5) {
            square.setFill(Color.GREEN);
        }
        if (nextPiece[y][x] == 6) {
            square.setFill(Color.PURPLE);
        }
        if (nextPiece[y][x] == 7) {
            square.setFill(Color.RED);
        }
        if (nextPiece[y][x] == 0) {
            square.setFill(Color.BLACK);
        }
    }
    
    public static void update() {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                Rectangle rectangle = new Rectangle(x*SQUARESIZE, y*SQUARESIZE, SQUARESIZE, SQUARESIZE);
                painter(rectangle, y, x);
                rectangleMap[y][x] = rectangle;
            }
        }
    }
    
    public static void addRectangleMap(Group root) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                root.getChildren().addAll(rectangleMap[y][x]);
            }
        }
    }
    
    public static void removeRectangleMap(Group root) {
        for (int y = 0; y < ROWS; y++) {
            for (int x = 0; x < COLS; x++) {
                root.getChildren().remove(rectangleMap[y][x]);
            }
        }
    }
    
    public static void updateDisplay(int[][] nextPiece) {
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                Rectangle rect = new Rectangle(WIDTH+x*SQUARESIZE,y*SQUARESIZE,SQUARESIZE, SQUARESIZE);
                displayPainter(nextPiece, rect, y, x);
                display[y][x] = rect;
            }
        }
    }
    
    public static void addDisplay(Group root) {
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                root.getChildren().addAll(display[y][x]);
            }
        }
    }
    
    public static void removeDisplay(Group root) {
        for (int y = 0; y < MATRIXSIZE; y++) {
            for (int x = 0; x < MATRIXSIZE; x++) {
                root.getChildren().remove(display[y][x]);
            }
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        //initialising Game
        //start screen
        Image image = new Image(new FileInputStream("StartScreen.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        
        Group start = new Group(imageView);
        Scene startScene = new Scene(start, WIDTH, HEIGHT);
        
        Image gameOver = new Image(new FileInputStream("GameOver.png"));
        ImageView gameOverDisplay = new ImageView(gameOver);
        gameOverDisplay.setX(0);
        gameOverDisplay.setY(0);
        
        
        placeEdges();
        update();
        
        piece = randomPiece();
        nextPiece = randomPiece();
        origin = placePiece(piece);
        coordY = origin[0];
        coordX = origin[1];
        
        Text score = new Text(WIDTH,SQUARESIZE*(MATRIXSIZE+1), "Score: " +Score);
        Text lines = new Text(WIDTH, SQUARESIZE*(MATRIXSIZE+2), "Lines: " +Lines);
        score.setFill(Color.WHITE);
        lines.setFill(Color.WHITE);
        
        stage.setTitle("Tetris");
        Group root = new Group();
        Scene scene = new Scene(root, WIDTH+MATRIXSIZE*SQUARESIZE, HEIGHT);
        stage.setResizable(false);
        updateDisplay(nextPiece);
        addDisplay(root);
        
        Rectangle control = new Rectangle(0,0,WIDTH+300,HEIGHT);
        control.setFocusTraversable(true);
        control.requestFocus();
        control.setOnKeyPressed(this);
        
        Rectangle pause = new Rectangle(0,0,WIDTH, HEIGHT);
        pause.setFill(Color.BLUE);
        root.getChildren().addAll(control, score, lines, imageView);
        
        
        stage.setScene(scene);
        stage.show();
        
        AnimationTimer animator = new AnimationTimer(){
 
            @Override
            public void handle(long whatever) {
                // UPDATE
                if (Start) {
                    time += 0.025;
                    change = (double)Score/50000;
                    time += change;
                    if (moveDown && checkDown(piece, coordY, coordX)) {
                        time += 1;
                        moveDown = false;
                    }
                }
                
                //time += 0.01;
                
                
                if (time >= 0.5) {
                    
                    //game loop here
                    //if statements
                    
                    if (Ground) {
                        clearRow();
                        clearRow();
                        clearRow();
                        clearRow();
                        score.setText("Score: " +Score);
                        lines.setText("Lines: " +Lines);
                        piece = nextPiece;
                        nextPiece = randomPiece();
                        //removeDisplay(root);
                        updateDisplay(nextPiece);
                        addDisplay(root);
                        origin = placePiece(piece);
                        coordY = origin[0];
                        coordX = origin[1];
                        Ground = false;
                        
                    }
                    
                    if (moveRight) {
                        moveRight(piece, coordY, coordX);
                        moveRight = false;
                    }
                    
                    if (moveLeft) {
                        moveLeft(piece, coordY, coordX);
                        moveLeft = false;
                    }
                    
                    if (checkDown(piece, coordY, coordX)) {
                        moveDown(piece, coordY, coordX);
                    } else {
                        Ground = true;
                    }
                    
                    if (moveUp && checkRotate(piece, coordY, coordX)) {
                        moveRotate(piece, coordY, coordX);
                        piece = rotate(piece);
                        moveUp = false;
                    }
                    
                    if (moveDown && checkDown(piece, coordY, coordX)) {
                        //time += 1;
                        //moveDown(piece, coordY, coordX);
                        //moveDown = false;
                    }
                    
                    removeRectangleMap(root);
                    update();
                    addRectangleMap(root);
                    time = 0;
                }
                if (Lose) {
                        finalScore = Score;
                        finalLines = Lines;
                        
                        Rectangle background = new Rectangle(0,0,WIDTH+(SQUARESIZE*MATRIXSIZE), HEIGHT);
                        background.setFill(Color.BLACK);
                        Text gameover = new Text(WIDTH/2, HEIGHT/5, "GAMEOVER");
                        Text scoreDisplay = new Text(WIDTH/2, HEIGHT/2, "Score: " +finalScore);
                        Text linesDisplay = new Text(WIDTH/2, (HEIGHT/2)+50, "Lines: " +finalLines);
                        scoreDisplay.setFill(Color.WHITE);
                        linesDisplay.setFill(Color.WHITE);
                        gameover.setFont(Font.font ("Verdana", 50));
                        gameover.setFill(Color.WHITE);
                        
                        Group GameOver = new Group();
                        GameOver.getChildren().addAll(background, gameover, scoreDisplay, linesDisplay);
                        Scene gameOverScene = new Scene(GameOver, WIDTH+MATRIXSIZE*SQUARESIZE, HEIGHT);
                        
                        stage.setScene(gameOverScene);
                    }
            }
        };
        animator.start();
    }
    
    @Override
    public void handle(KeyEvent arg0) {
              
        if ((arg0.getCode() == KeyCode.LEFT) || (arg0.getCode() == KeyCode.A)) {
            moveLeft = true;
        }
        if ((arg0.getCode() == KeyCode.RIGHT) || (arg0.getCode() == KeyCode.D)) {
            moveRight = true;
        }
        if ((arg0.getCode() == KeyCode.UP) || (arg0.getCode() == KeyCode.W)) {
            moveUp = true;
        }
        if ((arg0.getCode() == KeyCode.DOWN) || (arg0.getCode() == KeyCode.S)) {
            moveDown = true;
        }
        
        if ((arg0.getCode() == KeyCode.ENTER) && (!Start)) {
            Start = true;
        }
        
        if ((arg0.getCode() == KeyCode.SPACE) && (Start)) {
            Start = false;
        }
        
    }
}

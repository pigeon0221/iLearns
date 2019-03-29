import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends PApplet{
    private int exit_Button_X;
    private int exit_Button_Y;
    boolean begin = true;
    boolean game_Menu=false;
    public static void main(String[] args) {
        PApplet.main("Main");
        playMusic();

    }

    private static void playMusic() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Kids_Music.mp3");
            Player player = new Player(fileInputStream);
            player.play();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(JavaLayerException e) {
            e.printStackTrace();
        }


    }

    public void settings(){
        fullScreen();
        exit_Button_X=displayWidth-150;
        exit_Button_Y=0;
    }

    public void setup(){


    }

    public void draw(){
        if(begin) {
            load_Start_Screen();
        }
        if(game_Menu){
           load_Game_Menu();
        }

    }


    public void mousePressed(){
        try {
            FileInputStream fileInputStream = new FileInputStream("button.mp3");
            Player player = new Player(fileInputStream);
            player.play();

        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(JavaLayerException e) {
            e.printStackTrace();
        }
        if(over_Exit()){
            exit();
        }
        if(over_Start()){
            begin=false;
            game_Menu=true;
        }
    }
    public boolean over_Exit(){
        if(mouseX>displayWidth-150 && mouseY<100){
            return true;
        }
        return false;
    }
    public boolean over_Start(){
        if(mouseX>width/3 &&  mouseY>height/2-200 &&  mouseY<height/2-100){
            return true;
        }
        return false;
    }
    public void load_Start_Screen(){

        PImage img;
        img = loadImage("cartoonB.png");
        image(img,0,0,width,height);
        fill(0);

        rect(exit_Button_X,exit_Button_Y,150,100);
        PImage exit;
        exit = loadImage("Exit.png");
        image(exit,exit_Button_X,exit_Button_Y,150,100);
        PFont mono;
        mono = createFont("SqueakyChalkSound.ttf",100);
        textFont(mono);
        fill(255);
        text("iLearns",0,100);
        text("Click here to begin",width/3,height/2-100);

    } public void load_Game_Menu() {
        BufferedReader reader;
        String line;
        reader = createReader("Python Setup/output.txt");


        clear();

        PImage img;
        img = loadImage("cartoonB.png");
        image(img, 0, 0, width, height);
        fill(0);

        rect(exit_Button_X, exit_Button_Y, 150, 100);
        PImage exit;
        exit = loadImage("Exit.png");
        image(exit, exit_Button_X, exit_Button_Y, 150, 100);
        PFont mono;
        mono = createFont("SqueakyChalkSound.ttf", 100);
        textFont(mono);
        fill(255);
        text("iLearns", 0, 100);


        PImage dog;
        dog = loadImage("dog.png");
        image(dog, 1200, 550, 350, 350);

        stroke(0);
        strokeWeight(3);
        line(700, 500, 900, 500);
        line(1000, 500, 1200, 500);
        line(1300, 500, 1500, 500);


        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            line = null;
        }

        if (line != null) {
            char[] charArray = line.toCharArray();
            int x=750;
            for(char c :charArray){
                text(c,x,480);
                x+=500;
            }

        }




    }




}




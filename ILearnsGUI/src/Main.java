import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends PApplet{

    int pos1x = 350;
    int pos1y = 250;
    int pos2x = 630;
    int pos2y = 250;
    int pos3x = 920;
    int pos3y = 250;
    int pos4x = 350;
    int pos4y = 500;
    int pos5x = 630;
    int pos5y = 500;
    int pos6x = 920;
    int pos6y = 500;
    int pos7x = 350;
    int pos7y = 750;
    int pos8x = 630;
    int pos8y = 750;
    int pos9x = 920;
    int pos9y = 750;

    char spot1 = 'X';
    char spot2 = ' ';
    char spot3 = 'O';
    char spot4 = ' ';
    char spot5 = 'X';
    char spot6 = ' ';
    char spot7 = 'O';
    char spot8 = ' ';
    char spot9 = 'X';



    public static void main(String[] args) {
        PApplet.main("Main");
        playMusic();

    }

    private static void playMusic() {
        try {
            FileInputStream fileInputStream = new FileInputStream("farewell_226883231_soundcloud.mp3");
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

    }

    public void setup(){


    }

    public void draw(){
        PImage background;
        background=loadImage("black-and-white-board.jpg");
        image(background,0,0,displayWidth,displayHeight);
        PFont font = createFont("SqueakyChalkSound.ttf",200);
        textFont(font);
        
        text(spot1,pos1x,pos1y);
        text(spot2,pos2x,pos2y);
        text(spot3,pos3x,pos3y);
        text(spot4,pos4x,pos4y);
        text(spot5,pos5x,pos5y);
        text(spot6,pos6x,pos6y);
        text(spot7,pos7x,pos7y);
        text(spot8,pos8x,pos8y);
        text(spot9,pos9x,pos9y);
    }


    public void mousePressed(){
        System.out.println(mouseX +" "+mouseY);


    }




}




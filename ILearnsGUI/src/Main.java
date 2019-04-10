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

    char spot1 = ' ';
    char spot2 = ' ';
    char spot3 = ' ';
    char spot4 = ' ';
    char spot5 = ' ';
    char spot6 = ' ';
    char spot7 = ' ';
    char spot8 = ' ';
    char spot9 = ' ';



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

    public void draw() {
        PImage background;
        background = loadImage("black-and-white-board.jpg");
        image(background, 0, 0, displayWidth, displayHeight);
        PFont font = createFont("SqueakyChalkSound.ttf", 200);
        textFont(font);

//        text(spot1, pos1x, pos1y);
//        text(spot2, pos2x, pos2y);
//        text(spot3, pos3x, pos3y);
//        text(spot4, pos4x, pos4y);
//        text(spot5, pos5x, pos5y);
//        text(spot6, pos6x, pos6y);
//        text(spot7, pos7x, pos7y);
//        text(spot8, pos8x, pos8y);
//        text(spot9, pos9x, pos9y);

        BufferedReader reader;
        String line;
        reader = createReader("output.txt");

        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            line = null;
        }

        if (line != null) {
            char[] charArray = line.toCharArray();
            int x = charArray.length ;

            for (int i = 0; i < x; i = i+2) {
                char posi = charArray[i];
                switch (posi) {
                    case ('1'):
                        spot1 = charArray[i+1];
                        text(spot1, pos1x, pos1y);
                        break;
                    case ('2'):
                        spot2 = charArray[i+1];
                        text(spot2, pos2x, pos2y);
                        break;
                    case ('3'):
                        spot3 = charArray[i+1];
                        text(spot3, pos3x, pos3y);
                        break;
                    case ('4'):
                        spot4 = charArray[i+1];
                        text(spot4, pos4x, pos4y);
                        break;
                    case ('5'):
                        spot5 = charArray[i+1];
                        text(spot5, pos5x, pos5y);
                        break;
                    case ('6'):
                        spot6 = charArray[i+1];
                        text(spot6, pos6x, pos6y);
                        break;
                    case ('7'):
                        spot7 = charArray[i+1];
                        text(spot7, pos7x, pos7y);
                        break;
                    case ('8'):
                        spot8 = charArray[i+1];
                        text(spot8, pos8x, pos8y);
                        break;
                    case ('9'):
                        spot9 = charArray[i+1];
                        text(spot9, pos9x, pos9y);
                        break;

                }

            }


        }


    }



}




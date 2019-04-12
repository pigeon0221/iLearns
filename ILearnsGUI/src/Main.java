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

    boolean is1x = false;
    boolean is1o = false;
    boolean is2x = false;
    boolean is2o = false;
    boolean is3x = false;
    boolean is3o = false;
    boolean is4x = false;
    boolean is4o = false;
    boolean is5x = false;
    boolean is5o = false;
    boolean is6x = false;
    boolean is6o = false;
    boolean is7x = false;
    boolean is7o = false;
    boolean is8x = false;
    boolean is8o = false;
    boolean is9x = false;
    boolean is9o = false;

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
                        if(spot1 == 'X'){
                            is1x = true;
                        }
                        if(spot1 == 'O'){
                            is1o = true;
                        }
                        text(spot1, pos1x, pos1y);
                        break;
                    case ('2'):
                        spot2 = charArray[i+1];
                        if(spot2 == 'X'){
                            is2x = true;
                        }
                        if(spot2 == 'O'){
                            is2o = true;
                        }
                        text(spot2, pos2x, pos2y);
                        break;
                    case ('3'):
                        spot3 = charArray[i+1];
                        if(spot3 == 'X'){
                            is3x = true;
                        }
                        if(spot3 == 'O'){
                            is3o = true;
                        }
                        text(spot3, pos3x, pos3y);
                        break;
                    case ('4'):
                        spot4 = charArray[i+1];
                        if(spot4 == 'X'){
                            is4x = true;
                        }
                        if(spot4 == 'O'){
                            is4o = true;
                        }
                        text(spot4, pos4x, pos4y);
                        break;
                    case ('5'):
                        spot5 = charArray[i+1];
                        if(spot5 == 'X'){
                            is5x = true;
                        }
                        if(spot5 == 'O'){
                            is5o = true;
                        }
                        text(spot5, pos5x, pos5y);
                        break;
                    case ('6'):
                        spot6 = charArray[i+1];
                        if(spot6 == 'X'){
                            is6x = true;
                        }
                        if(spot6 == 'O'){
                            is6o = true;
                        }
                        text(spot6, pos6x, pos6y);
                        break;
                    case ('7'):
                        spot7 = charArray[i+1];
                        if(spot7 == 'X'){
                            is7x = true;
                        }
                        if(spot7 == 'O'){
                            is7o = true;
                        }
                        text(spot7, pos7x, pos7y);
                        break;
                    case ('8'):
                        spot8 = charArray[i+1];
                        if(spot8 == 'X'){
                            is8x = true;
                        }
                        if(spot8 == 'O'){
                            is8o = true;
                        }
                        text(spot8, pos8x, pos8y);
                        break;
                    case ('9'):
                        spot9 = charArray[i+1];
                        if(spot9 == 'X'){
                            is9x = true;
                        }
                        if(spot9 == 'O'){
                            is9o = true;
                        }
                        text(spot9, pos9x, pos9y);
                        break;

                }

            }


        }

        whoWins();



    }

    private void whoWins() {
        if(is1x && is2x && is3x){                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            text("X WINS",0,displayHeight-50,100);
        }
        if(is1o && is2o && is3o){
            // o wins draw line
            //rect(pos1x,pos1y-70,700,10);
            text("O WINS",0,displayHeight-50,100);
        }

        if(is4x && is5x && is6x){                   // second row
            // x wins draw line
            //rect(pos4x,pos4y-70,700,10);
            text("X WINS",0,displayHeight-50,100);
        }

        if(is4o && is5o && is6o){
            // o wins draw line
           // rect(pos4x,pos4y-70,700,10);
            text("O WINS",0,displayHeight-50,100);
        }

        if(is7x && is8x && is9x){                   // third row
            // x wins draw line
           // rect(pos7x,pos7y-70,700,10);
        }

        if(is7o && is8o && is9o){
            // o wins draw line
          //  rect(pos7x,pos7y-70,700,10);
        }

        if(is1x && is4x && is7x){                   // first column
            // x wins draw line
           // rect(pos1x,pos1y-70,10,700);
            pickWin('v', 1);
        }

        if(is1o && is4o && is7o){
            // o wins draw line
           // rect(pos1x+75,pos1y-150,10,700);
        }

        if(is2x && is5x && is8x){                   // second column
            // x wins draw line
        }

        if(is2o && is5o && is8o){
            // o wins draw line
        }
        if(is3x && is6x && is9x){                   // third column
            // x wins draw line
        }

        if(is3o && is6o && is9o){
            // o wins draw line
        }

        if(is1x && is5x && is9x){                   // negative diagonal
            // x wins draw line
        }

        if(is1o && is5o && is9o){
            // o wins draw line
        }

        if(is3x && is5x && is7x){                   // positive diagonal
            // x wins draw line
        }

        if(is3o && is5o && is7o){
            // o wins draw line
        }

    }
    private void pickWin(char c, int n){
        // c is direction v for vertical , h for horizontal, d for diagonal
        // n for which row or which column, 1 for neg slope diag and 2 for pos slope
        if(c == 'v'){
            if(n == 1){
                spot1 = 'W';
                spot4 = 'I';
                spot7 = 'N';
            }if(n == 2){
                spot2 = 'W';
                spot5 = 'I';
                spot8 = 'N';
            }if(n == 3){
                spot3 = 'W';
                spot6 = 'I';
                spot9 = 'N';
            }
        }if(c == 'h'){
            if(n == 1){
                spot1 = 'W';
                spot2 = 'I';
                spot3 = 'N';
            }if(n == 2){
                spot4 = 'W';
                spot5 = 'I';
                spot6 = 'N';
            }if(n == 3){
                spot7 = 'W';
                spot8 = 'I';
                spot9 = 'N';
            }
        }if(c == 'd'){
            if(n == 1){
                spot1 = 'W';
                spot5 = 'I';
                spot9 = 'N';
            }if(n == 2){
                spot7 = 'W';
                spot5 = 'I';
                spot3 = 'N';
            }

        }

    }


}




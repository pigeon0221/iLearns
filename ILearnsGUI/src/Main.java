import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main extends PApplet {

    int pos1x = 460;
    int pos1y = 250;
    int pos2x = 860;
    int pos2y = 250;
    int pos3x = 1260;
    int pos3y = 250;
    int pos4x = 460;
    int pos4y = 580;
    int pos5x = 860;
    int pos5y = 580;
    int pos6x = 1260;
    int pos6y = 580;
    int pos7x = 460;
    int pos7y = 900;
    int pos8x = 860;
    int pos8y = 900;
    int pos9x = 1260;
    int pos9y = 900;

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

    char lastWritten = 'X';

    boolean initialClear = false;


    public static void main(String[] args) {
        PApplet.main("Main");
        playMusic();

    }

    private static void playMusic() {
        try {
            FileInputStream fileInputStream = new FileInputStream("farewell_226883231_soundcloud.mp3");
            Player player = new Player(fileInputStream);
            player.play();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }


    }

    public void settings() {
        fullScreen();

    }

    public void setup() {
        fullScreen();


    }

    public void draw() {
        PImage background;
        background = loadImage("black-and-white-board.jpg");
        image(background, 0, 0, displayWidth, displayHeight);
        PFont font = createFont("SqueakyChalkSound.ttf", 200);
        PFont font2 = createFont("SqueakyChalkSound.ttf", 100);
        textFont(font2);
        text("Clear", 0, 100,100);
        textFont(font);
        if (initialClear == false) {
            String[] empty = {};
            saveStrings("output.txt", empty);
            initialClear = true;
        }

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
            int x = charArray.length;

            for (int i = 0; i < x; i = i + 2) {
                char posi = charArray[i];
                if (posi == '1') {
                    if (spot1 == ' ') {
                        text(lastWritten, pos1x, pos1y);
                        spot1 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot1, pos1x, pos1y);
                    }
                }
                if (posi == '2') {
                    if (spot2 == ' ') {
                        text(lastWritten, pos2x, pos2y);
                        spot2 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot2, pos2x, pos2y);
                    }
                }
                if (posi == '3') {
                    if (spot3 == ' ') {
                        text(lastWritten, pos3x, pos3y);
                        spot3 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot3, pos3x, pos3y);
                    }
                }
                if (posi == '4') {
                    if (spot4 == ' ') {
                        text(lastWritten, pos4x, pos4y);
                        spot4 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot4, pos4x, pos4y);
                    }
                }
                if (posi == '5') {
                    if (spot5 == ' ') {
                        text(lastWritten, pos5x, pos5y);
                        spot5 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot5, pos5x, pos5y);
                    }
                }
                if (posi == '6') {
                    if (spot6 == ' ') {
                        text(lastWritten, pos6x, pos6y);
                        spot6 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot6, pos6x, pos6y);
                    }
                }
                if (posi == '7') {
                    if (spot7 == ' ') {
                        text(lastWritten, pos7x, pos7y);
                        spot7 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot7, pos7x, pos7y);
                    }
                }
                if (posi == '8') {
                    if (spot8 == ' ') {
                        text(lastWritten, pos8x, pos8y);
                        spot8 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot8, pos8x, pos8y);
                    }
                }
                if (posi == '9') {
                    if (spot9 == ' ') {
                        text(lastWritten, pos9x, pos9y);
                        spot9 = lastWritten;
                        if (lastWritten == 'X') {
                            lastWritten = 'O';
                        } else {
                            lastWritten = 'X';
                        }
                    } else {
                        text(spot9, pos9x, pos9y);
                    }
                }
            }


        }
        whoWins();
    }
    public void mousePressed(){
        System.out.println(mouseX+" "+mouseY);
        if(mouseX<300 && mouseY<100){
            initialClear=false;
            spot1=' ';
            spot2=' ';
            spot3=' ';
            spot4=' ';
            spot5=' ';
            spot6=' ';
            spot7=' ';
            spot8=' ';
            spot9=' ';
            fill(255);
        }

    }

    private void whoWins() {
        if (spot1 == spot2 && spot2 == spot3 && spot1=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot1 == spot2 && spot2 == spot3 && spot1=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot4 == spot5 && spot5 == spot6 && spot4=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot4 == spot5 && spot5 == spot6 && spot4=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot7 == spot8 && spot8 == spot9 && spot7=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot7 == spot8 && spot8 == spot9 && spot7=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot1 == spot4 && spot4 == spot7 && spot7=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot1 == spot4 && spot4 == spot7 && spot7=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot2 == spot5 && spot5 == spot8 && spot2=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot2 == spot5 && spot5 == spot8 && spot2=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot3 == spot6 && spot6 == spot9 && spot3=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot3 == spot6 && spot6 == spot9 && spot3=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot1 == spot5 && spot5 == spot9 && spot1=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot1 == spot5 && spot5 == spot9 && spot1=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot3 == spot5 && spot5 == spot7 && spot3=='X') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("X WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }
        if (spot3 == spot5 && spot5 == spot7 && spot3=='O') {                   // first row
            // x wins draw line
            //rect(pos1x,pos1y-70,700,10);
            fill(0);
            rect(displayWidth / 2 - 450, displayHeight / 2 - 200, 950, 300);
            fill(255, 200, 123);
            text("O WINS", displayWidth / 2 - 400 + 50, displayHeight / 2 + 50, 100);
        }

    }




}




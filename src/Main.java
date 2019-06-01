import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


public class Main extends PApplet {
    private static Player player;
    private static Music music;
    private static boolean onNamePage = false;
    String input = "";
    String wordInput = "";
    private int exitButtonX;
    private int exitButtonY;
    private boolean begin = true;
    private boolean createLibrary = false;
    private boolean gamePage = false;
    private boolean selectLibrary = false;
    private PImage background;
    private PImage logo;
    private PImage playButton;
    private PImage pauseButton;
    private PImage menu;
    private PImage backButton;
    private PImage libraryName;
    private PImage libraryWords;
    private PImage wordBox;
    private PImage nextWord;
    private PImage lastWord;
    private PImage correct;
    private PImage incorrect;
    private PImage reset;


    private FileInputStream fileInputStream;
    private boolean fileCreated = false;
    private boolean fileSelected = false;
    private boolean createLibraryPage2 = false;
    private boolean onWordPage = false;
    private PrintWriter writer = null;
    private String fileName;
    private JFileChooser chooser = null;
    private ArrayList<String> gameWords = new ArrayList<>();
    private int level = 1;
    private boolean playedSound = false;


    {
        try {
            fileInputStream = new FileInputStream("Music/Kids_Music.mp3");
            player = new Player(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PApplet.main("Main");

    }

    private static void playMusic() {

        try {
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }

    }

    private static void stopMusic() {
       // music.pause();
        /*
        TODO: Make music stop when pause is clicked
         */
    }

    private static void playClickSound() {
        try {
            FileInputStream fileInputStream = new FileInputStream("Music/button.mp3");
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
        exitButtonX = displayWidth - 150;
        exitButtonY = 0;
    }

    public void setup() {
        background = loadImage("Images/cartoonB.png");
        playButton = loadImage("Images/playButton.png");
        pauseButton = loadImage("Images/pauseButton.png");
        logo = loadImage("Images/logo.png");
        menu = loadImage("Images/menu.png");
        backButton = loadImage("Images/backButton.png");
        libraryName = loadImage("Images/LibraryName.png");
        libraryWords = loadImage("Images/LibraryWords.png");
        wordBox = loadImage("Images/wordBox.png");
        nextWord = loadImage("Images/nextWordButton.png");
        lastWord = loadImage("Images/backWordButton.png");
        correct = loadImage("Images/goodjob.png");
        incorrect = loadImage("Images/incorrect.png");
        reset = loadImage("Images/reset.png");

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Python Setup/output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.close();

    }

    public void draw() {

        if (begin) {
            StartScreen();
        }
        if (selectLibrary) {
            selectLibrary();
        }
        if (createLibrary) {
            onNamePage = true;
            createLibrary();
        }
        if (createLibraryPage2) {
            onNamePage = false;
            onWordPage = true;
            createLibraryPage2();
        }
        if (onNamePage) {
            textSize(30);
            fill(255);
            text(input, 810, 570);

        }
        if (onWordPage) {
            textSize(30);
            fill(255);
            text(wordInput, 810, 570);

        }
        if (gamePage){
            playGame();
        }

    }

    private void playGame() {
        clear();
        drawBackgroundElements();
        fill(255);
        textSize(50);
        text("Level "+level,width/2-100,70);
        image(wordBox,200,height/2-200,1520,300);
        image(lastWord, 50, height/2-200, 100, 100);
        image(nextWord, 1770, height/2-200, 100, 100);
        image(reset,width/2-100,700,200,200);
        BufferedReader reader;
        String line;
        reader = createReader("Python Setup/output.txt");

        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            line = null;
        }
        if(line!=null){
            textSize(80);
            text(line,690,525);

            if(gameWords.get(level-1).length()==line.length() && gameWords.get(level-1).equals(line) && playedSound == false){
                image(correct,width/2-150,200,300,300);
//            try {
//                playedSound=true;
//                text(gameWords.get(level-1),690,525);
//                FileInputStream fileInputStream = new FileInputStream("Music/yayEffect.mp3");
//                Player player = new Player(fileInputStream);
//                player.play();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (JavaLayerException e) {
//                e.printStackTrace();
//            }
            }
            //TODO: Bug in method that plays sound before displaying full output
            if(gameWords.get(level-1).length()==line.length() && !gameWords.get(level-1).equals(line) && playedSound == false){
                image(incorrect,width/2-150,200,300,300);
//
            }
        }


    }

    private void createLibraryPage2() {
        clear();
        drawBackgroundElements();
        if(!fileCreated) {
            fileName = input + ".txt";
            try {
                writer = new PrintWriter("Libraries/" + fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            fileCreated=true;
            input="";
        }
        image(libraryWords, displayWidth / 2 - 200, displayHeight / 2 - 200, 400, 400);
    }

    public void keyPressed() {
        if (onNamePage) {
            System.out.println(keyCode);
            if (keyCode == 10 || keyCode == 16) {
                return;
            }
            if (key == 8) {
                if (input.length() == 0) {
                    return;
                }
                input = input.substring(0, input.length() - 1);
            } else {
                input = input + key;
            }
        }
        if (onWordPage) {
            System.out.println(keyCode);
            if (keyCode == 10 || keyCode == 16) {
                return;
            }
            if (key == 8) {
                if (wordInput.length() == 0) {
                    return;
                }
                wordInput = wordInput.substring(0, wordInput.length() - 1);
            } else {
                wordInput = wordInput + key;
            }
        }
    }

    public void StartScreen() {
        onNamePage=false;
        onWordPage=false;
        fileCreated=false;
        drawBackgroundElements();
        image(menu, displayWidth / 2 - 200, displayHeight / 2 - 200, 400, 400);

//        PFont mono;
//        mono = createFont("Fonts/SqueakyChalkSound.ttf",100);
//        textFont(mono);
//        fill(255);
//        text("iLearns",0,100);

    }

    private void drawBackgroundElements() {
        image(background, 0, 0, width, height);
        drawExitButton();
        image(logo, 0, 0, 400, 150);
        image(playButton, 0, 900, 80, 80);
        image(pauseButton, 80, 900, 80, 80);
        image(backButton, 1840, 900, 80, 80);

    }

    private void createLibrary() {
        clear();
        drawBackgroundElements();
        image(libraryName, displayWidth / 2 - 200, displayHeight / 2 - 200, 400, 400);
        
    }

    private void selectLibrary() {


            clear();
            drawBackgroundElements();
            if(!fileSelected) {
                chooser = new JFileChooser();
                int result = chooser.showSaveDialog(null);
                if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("Cancel was selected");
                    returnToHomePage();
                    return;
                }
                fileSelected=true;
            }

            File f = chooser.getSelectedFile();

           try(BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()))) {
            for(String line; (line = br.readLine()) != null; ) {
               gameWords.add(line);
            }
            // line is not visible here.
            } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
        startGame();

    }

    private void startGame() {
        fileSelected=false;
        begin = false;
        selectLibrary = false;
        createLibrary = false;
        createLibraryPage2 = false;
        gamePage=true;
    }

    public void drawExitButton() {
        fill(0);
        rect(exitButtonX, exitButtonY, 150, 100);
        PImage exit;
        exit = loadImage("Images/Exit.png");
        image(exit, exitButtonX, exitButtonY, 150, 100);
    }

    public void mousePressed() {
        System.out.println("Mouse Click at " + mouseX + " " + mouseY);
        playClickSound();
        if (overExit()) {
            exit();
        }
        if (overPause()) {
            System.out.println("Stopping Music");
            stopMusic();
        }
        if (overBackButton()) {
            returnToHomePage();
        }
        if (overPlay()) {
            System.out.println("Playing Music");
            //
            //TODO: Plays music when play button is clicked
            //
        }
        if(begin) {
            if (overSelectLibrary()) {
                begin = false;
                selectLibrary = true;
                createLibrary = false;
            }
            if (overCreateLibrary()) {
                begin = false;
                selectLibrary = false;
                createLibrary = true;
            }
        }
        if(createLibrary) {
            if (overNextButton()) {
                if (input == "") {
                    return;
                }
                begin = false;
                selectLibrary = false;
                createLibrary = false;
                createLibraryPage2 = true;
            }
        }
        if (onWordPage){
            if(overNextWord()){
                if(wordInput==""){
                    return;
                }
                writer.println(wordInput);
                wordInput="";
            }
            if(overSaveLibrary()){
                writer.close();
                returnToHomePage();
            }
        }
        if (gamePage){
            if(overNextLibraryWord()){
                if(level==gameWords.size()){
                    return;
                }
                else{
                    level+=1;
                    playedSound=false;
                    try {
                        PrintWriter writer = new PrintWriter("Python Setup/output.txt");
                        writer.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
            if(overPreviousLibraryWord()){
                if(level==1){
                    return;
                }
                else{
                    level-=1;
                    playedSound=false;
                    try {
                        PrintWriter writer = new PrintWriter("Python Setup/output.txt");
                        writer.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
            if(overResetButton()){
                try {
                    PrintWriter writer = new PrintWriter("Python Setup/output.txt");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean overResetButton() {
        return mouseX > 865 && mouseX < 1055 && mouseY > 700 && mouseY < 900;
    }

    private boolean overPreviousLibraryWord() {
        return mouseX > 50 && mouseX < 145 && mouseY >340  && mouseY < 435;
    }

    private boolean overNextLibraryWord() {
        return mouseX > 1770 && mouseX < 1875 && mouseY >340  && mouseY < 435;
    }

    private boolean overNextButton() {
        return mouseX > 890 && mouseX < 1035 && mouseY > 620 && mouseY < 680 && onNamePage;
    }


    private void returnToHomePage() {
        fileSelected=false;
        begin = true;
        selectLibrary = false;
        createLibrary = false;
        createLibraryPage2 = false;
        gamePage=false;
    }

    public boolean overExit() {
        return mouseX > displayWidth - 150 && mouseY < 100;
    }

    public boolean overPlay() {
        return mouseX < 80 && mouseY > 900;
    }

    public boolean overPause() {
        return mouseX > 80 && mouseX < 160 && mouseY > 900;
    }

    public boolean overSelectLibrary() {
        return mouseX > 818 && mouseX < 1097 && mouseY > 457 && mouseY < 500;
    }

    public boolean overCreateLibrary() {
        return mouseX > 808 && mouseX < 1108 && mouseY > 560 && mouseY < 610;
    }

    public boolean overBackButton() {
        return mouseX > 1840 && mouseY > 900;
    }
    public boolean overNextWord() {
        return mouseX > 800 && mouseX < 950 && mouseY >620  && mouseY < 690;
    }
    public boolean overSaveLibrary() {
        return mouseX > 970 && mouseX < 1120 && mouseY >620  && mouseY < 690;
    }

    public void loadGame() {
        BufferedReader reader;
        String line;
        reader = createReader("Python Setup/output.txt");


        clear();

        PImage img;
        img = loadImage("Images/cartoonB.png");
        image(img, 0, 0, width, height);
        fill(0);

        rect(exitButtonX, exitButtonY, 150, 100);
        PImage exit;
        exit = loadImage("Images/Exit.png");
        image(exit, exitButtonX, exitButtonY, 150, 100);
        PFont mono;
        mono = createFont("Fonts/SqueakyChalkSound.ttf", 100);
        textFont(mono);
        fill(255);
        text("iLearns", 0, 100);


        PImage dog;
        dog = loadImage("Images/dog.png");
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
            int x = 750;
            for (char c : charArray) {
                text(c, x, 480);
                x += 300;
            }

        }


    }

    static class Music extends Thread {

        FileInputStream fileInputStream;
        Player player;

        {
            try {
                fileInputStream = new FileInputStream("Music/Kids_Music.mp3");
                player = new Player(fileInputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }


        public void run() {
            try {
                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }


        }

        public void pause() {
            player.close();

        }

    }


}




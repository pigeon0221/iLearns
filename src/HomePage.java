import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class HomePage implements Screen {
    public boolean visibility = false;
    Pages pages = new Pages();
    Images images;
    private PApplet p;

    public HomePage(PApplet p) {
        this.p = p;
        images = new Images(this.p);
    }

    @Override
    public void mousePressed() {
        checkBackgroundButtons();
        checkButtons();
    }

    @Override
    public void keyPressed() {

    }

    @Override
    public void checkButtons() {
        if (overSelectLibrary()) {
            ArrayList<String> library = loadLibrary();
            HashMap<String,String> dictionary = loadDictionary();
            if (library != null) {
                startGame(library,dictionary);
            }
        }
        if (overCreateLibrary()) {
            visibility = false;
            Screen LibraryNamePage = new LibraryNamePage(p);
            pages.setPage("LibraryNamePage", LibraryNamePage);
            pages.getPage("LibraryNamePage").setVisibility(true);
        }
        if (overCreateDictionary()) {
            visibility = false;
            Screen DictionaryNamePage = new DictionaryNamePage(p);
            pages.setPage("DictionaryNamePage", DictionaryNamePage);
            pages.getPage("DictionaryNamePage").setVisibility(true);
        }
    }

    private HashMap<String, String> loadDictionary() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel was selected");
            return null;
        }
        File f = chooser.getSelectedFile();
        ArrayList<String> gameWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()))) {
            for (String line; (line = br.readLine()) != null; ) {
                gameWords.add(line.trim());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        HashMap<String, String> dictionary = new HashMap<>();
        for(int x = 0;x<gameWords.size();x+=2) {
            dictionary.put(gameWords.get(x),gameWords.get(x+1));
        }
        return dictionary;
    }


    private void startGame(ArrayList<String> library, HashMap<String, String> dictionary) {
        GamePage game = new GamePage(p, library,dictionary);
        game.setVisibility(true);
        visibility = false;
        pages.setPage("GamePage", game);
    }

    private ArrayList<String> loadLibrary() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("Cancel was selected");
            return null;
        }
        File f = chooser.getSelectedFile();
        ArrayList<String> gameWords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()))) {
            for (String line; (line = br.readLine()) != null; ) {
                gameWords.add(line.trim());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return gameWords;
    }

    @Override
    public void checkBackgroundButtons() {
        if (overExit()) {
            p.exit();
        }
        if (overPause()) {
            //
            //TODO: Stop music when play button is clicked
            //
        }
        if (overBackButton()) {
            //Do Nothing .. Already on HomePage

        }
        if (overPlay()) {
            //
            //TODO: Plays music when play button is clicked
            //
        }
    }

    @Override
    public boolean getVisibility() {
        return visibility;
    }

    @Override
    public void setVisibility(boolean state) {
        visibility = state;
    }

    @Override
    public PImage background() {
        return images.getImage("Background");
    }
    
    @Override
    public void create() {
        drawBackgroundElements();
        drawPageElements();
    }

    @Override
    public void drawPageElements() {
        p.image(images.getImage("Menu"), p.displayWidth / 2 - 200, p.displayHeight / 2 - 200, 400, 400);
    }

    @Override
    public void drawBackgroundElements() {
        p.image(background(), 0, 0, p.width, p.height);
        drawExitButton();
        p.image(images.getImage("Logo"), 0, 0, 400, 150);
        p.image(images.getImage("PlayButton"), 0, 900, 80, 80);
        p.image(images.getImage("PauseButton"), 80, 900, 80, 80);
        p.image(images.getImage("BackButton"), 1840, 900, 80, 80);
    }

    public void drawExitButton() {
        p.fill(0);
        p.rect(1770, 0, 150, 100);
        p.image(images.getImage("Exit"), 1770, 0, 150, 100);
    }

    public boolean overExit() {
        return p.mouseX > p.displayWidth - 150 && p.mouseY < 100;
    }

    public boolean overPlay() {
        return p.mouseX < 80 && p.mouseY > 900;
    }

    public boolean overPause() {
        return p.mouseX > 80 && p.mouseX < 160 && p.mouseY > 900;
    }

    public boolean overSelectLibrary() {
        return p.mouseX > 818 && p.mouseX < 1097 && p.mouseY > 457 && p.mouseY < 500;
    }

    public boolean overCreateLibrary() {
        return p.mouseX > 808 && p.mouseX < 1108 && p.mouseY > 560 && p.mouseY < 610;
    }

    public boolean overBackButton() {
        return p.mouseX > 1840 && p.mouseY > 900;
    }

    private boolean overCreateDictionary() {
        return p.mouseX > 800 && p.mouseX < 1120 && p.mouseY > 663 && p.mouseY < 700;
    }


}

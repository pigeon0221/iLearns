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
        if (overDownloadPDF()){
            CreatePDF c = new CreatePDF();
            c.Convert();
        }
    }

    private HashMap<String, String> loadDictionary() {
        JOptionPane.showMessageDialog(null,
                "Please select a dictionary.",
                "ILearns",
                JOptionPane.PLAIN_MESSAGE);
        File workingDirectory = new File(System.getProperty("user.dir")+File.separator+"Dictionary");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDirectory);
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
        JOptionPane.showMessageDialog(null,
                "Please select which library you would like to play.",
                "ILearns",
                JOptionPane.PLAIN_MESSAGE);
        File workingDirectory = new File(System.getProperty("user.dir")+File.separator+"Libraries");
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(workingDirectory);
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
        p.image(images.getImage("Menu"), Scaler.sw(760), Scaler.sh(340), Scaler.sw(400), Scaler.sh(400));
        p.image(images.getImage("PDFButton"),Scaler.sw(0), Scaler.sh(800), Scaler.sw(300), Scaler.sh(100));
    }

    @Override
    public void drawBackgroundElements() {
        p.image(background(), 0, 0, p.width, p.height);
        drawExitButton();
        p.image(images.getImage("Logo"), 0, Scaler.sh(10), Scaler.sw(400), Scaler.sh(125));
        p.image(images.getImage("PlayButton"), 0, Scaler.sh(900), Scaler.sw(80), Scaler.sh(80));
        p.image(images.getImage("PauseButton"), Scaler.sw(80), Scaler.sh(900), Scaler.sw(80), Scaler.sh(80));
        p.image(images.getImage("BackButton"), Scaler.sw(1840), Scaler.sh(900), Scaler.sw(80), Scaler.sh(80));
    }

    public void drawExitButton() {
        p.fill(0);
        p.rect(Scaler.sw(1770), 0, Scaler.sw(150), Scaler.sh(100));
        p.image(images.getImage("Exit"), Scaler.sw(1770), 0, Scaler.sw(150), Scaler.sh(100));
    }

    public boolean overExit() {
        return p.mouseX > Scaler.sw(1770) && p.mouseY < Scaler.sh(100);
    }

    public boolean overPlay() {
        return p.mouseX < Scaler.sw(80) && p.mouseY > Scaler.sh(900);
    }

    public boolean overPause() {
        return p.mouseX > Scaler.sw(80) && p.mouseX < Scaler.sw(160) && p.mouseY > Scaler.sh(900);
    }

    public boolean overSelectLibrary() {
        return p.mouseX > Scaler.sw(818) && p.mouseX < Scaler.sw(1097) && p.mouseY > Scaler.sh(457) && p.mouseY < Scaler.sh(500);
    }

    public boolean overCreateLibrary() {
        return p.mouseX > Scaler.sw(808) && p.mouseX < Scaler.sw(1108) && p.mouseY > Scaler.sh(560) && p.mouseY < Scaler.sh(610);
    }

    public boolean overBackButton() {
        return p.mouseX > Scaler.sw(1840) && p.mouseY > Scaler.sh(900);
    }

    private boolean overCreateDictionary() {
        return p.mouseX > Scaler.sw(800) && p.mouseX < Scaler.sw(1120) && p.mouseY > Scaler.sh(663) && p.mouseY < Scaler.sh(700);
    }

    private boolean overDownloadPDF(){ return p.mouseX > Scaler.sw(0) && p.mouseX < Scaler.sw(300) && p.mouseY < Scaler.sh(900)  && p.mouseY > Scaler.sh(800); }


}

import processing.core.PApplet;
import javax.swing.*;
import processing.core.PImage;

import java.io.*;
import java.util.ArrayList;

public class HomePage implements Screen {
    private PApplet p;
    Pages pages = new Pages();
    public boolean visibility = false;
    Images images;
    public HomePage(PApplet p) {
        this.p = p;
        images = new Images(this.p);
    }

    @Override
    public void mousePressed() {
        checkBackgroundButtons();
        checkButtons();
    }

    private void checkButtons() {
        if (overSelectLibrary()) {
            ArrayList<String> library = loadLibrary();
            if (library!=null){
                startGame(library);
            }
        }
        if (overCreateLibrary()) {

        }
    }

    private void startGame(ArrayList<String> library) {
        GamePage game = new GamePage(p,library);
        game.setVisibility(true);
        visibility = false;
        pages.setPage("GamePage",game);
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


    private void checkBackgroundButtons() {
        if (overExit()) {
            p.exit();
        }
        if (overPause()) {
            GameEffects.stopMusic();
        }
        if (overBackButton()) {

        }
        if (overPlay()) {
            //
            //TODO: Plays music when play button is clicked
            //
        }
    }
    

    @Override
    public void setVisibility(boolean state) {
        visibility = state;
    }

    @Override
    public boolean visibility() {
        return visibility;
    }

    @Override
    public PImage background() {
        return p.loadImage("Images/cartoonB.png");
    }

    @Override
    public void create(){
        drawBackgroundElements();
        drawPageElements();
    }

    private void drawPageElements() {
        p.image(images.getImage("Menu"), p.displayWidth / 2 - 200, p.displayHeight / 2 - 200, 400, 400);
    }

    private void drawBackgroundElements() {
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

}

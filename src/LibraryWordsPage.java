import processing.core.PApplet;
import processing.core.PImage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LibraryWordsPage implements Screen {
    public boolean visibility = false;
    Pages pages = new Pages();
    Images images;
    PrintWriter writer;
    String wordInput = "";
    private PApplet p;

    public LibraryWordsPage(PApplet p, String fileName) {
        this.p = p;
        images = new Images(this.p);
        try {
            writer = new PrintWriter("Libraries/" + fileName + ".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mousePressed() {
        checkBackgroundButtons();
        checkButtons();
    }

    @Override
    public void keyPressed() {
        if (p.keyCode == 10 || p.keyCode == 16) {
            return;
        }
        if (p.key == 8) {
            if (wordInput.length() == 0) {
                return;
            }
            wordInput = wordInput.substring(0, wordInput.length() - 1);
        } else {
            wordInput = wordInput + p.key;
        }
    }

    @Override
    public void checkButtons() {
        if (overNextWord()) {
            if (wordInput != "") {
                writer.println(wordInput);
                wordInput = "";
            }
        }
        if (overSaveLibrary()) {
            writer.close();
            visibility = false;
            pages.getPage("HomePage").setVisibility(true);
        }
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
            visibility = false;
            pages.getPage("HomePage").setVisibility(true);

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
        p.image(images.getImage("LibraryWordMenu"), p.displayWidth / 2 - 200, p.displayHeight / 2 - 200, 400, 400);
        p.fill(255);
        p.textSize(50);
        p.text(wordInput, 810, 570);
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

    public boolean overBackButton() {
        return p.mouseX > 1840 && p.mouseY > 900;
    }

    public boolean overNextWord() {
        return p.mouseX > 800 && p.mouseX < 950 && p.mouseY > 620 && p.mouseY < 690;
    }

    public boolean overSaveLibrary() {
        return p.mouseX > 970 && p.mouseX < 1120 && p.mouseY > 620 && p.mouseY < 690;
    }
}

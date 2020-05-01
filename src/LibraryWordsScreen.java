import processing.core.PApplet;
import processing.core.PImage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LibraryWordsScreen implements Screen {
    public boolean visibility = false;
    Screens screens = new Screens();
    Images images;
    PrintWriter writer;
    String wordInput = "";
    private PApplet p;

    public LibraryWordsScreen(PApplet p, String fileName) {
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
            screens.getScreen("HomeScreen").setVisibility(true);
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
            screens.getScreen("HomeScreen").setVisibility(true);

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
        drawScreenElements();
    }

    @Override
    public void drawScreenElements() {
        p.image(images.getImage("LibraryWordMenu"), Scaler.sw(760), Scaler.sh(340), Scaler.sw(400), Scaler.sh(400));
        p.fill(255);
        p.textSize(Scaler.sh(50));
        p.text(wordInput, Scaler.sw(810), Scaler.sh(570));
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

    public boolean overBackButton() {
        return p.mouseX > Scaler.sw(1840) && p.mouseY > Scaler.sh(900);
    }

    public boolean overNextWord() {
        return p.mouseX > Scaler.sw(800) && p.mouseX < Scaler.sw(950) && p.mouseY > Scaler.sh(620) && p.mouseY < Scaler.sh(690);
    }

    public boolean overSaveLibrary() {
        return p.mouseX > Scaler.sw(970) && p.mouseX < Scaler.sw(1120) && p.mouseY > Scaler.sh(620) && p.mouseY < Scaler.sh(690);
    }
}

import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePage implements TagScreen {
    public boolean visibility = false;
    Pages pages = new Pages();
    Images images;
    int level = 1;
    ArrayList<String> library = new ArrayList<>();
    HashMap<String, String> dictionary = new HashMap<>();
    private PApplet p;
    String scannerInput = "";

    public GamePage(PApplet p, ArrayList<String> lib, HashMap<String, String> dic) {
        this.p = p;
        images = new Images(this.p);
        library = lib;
        dictionary=dic;
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
        if (overNextLibraryWord()) {
            if (level == library.size()) {
                return;
            } else {
                level += 1;
               scannerInput="";
            }

        }
        if (overPreviousLibraryWord()) {
            if (level == 1) {
                return;
            } else {
                level -= 1;
                scannerInput="";
            }

        }
        if (overResetButton()) {
            scannerInput="";
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
    public void setVisibility(boolean state) {
        visibility = state;
    }

    @Override
    public boolean getVisibility() {
        return visibility;
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
        p.fill(255);
        p.textSize(Scaler.sh(50));
        p.text("Level " + level, Scaler.sw(860), Scaler.sh(70));
        p.image(images.getImage("WordBox"), Scaler.sw(200), Scaler.sh(340), Scaler.sw(1520), Scaler.sh(300));
        p.image(images.getImage("BackWordButton"), Scaler.sw(50), Scaler.sh(340), Scaler.sw(100), Scaler.sh(100));
        p.image(images.getImage("NextWordButton"), Scaler.sw(1770), Scaler.sh(340), Scaler.sw(100), Scaler.sh(100));
        p.image(images.getImage("ResetButton"), Scaler.sw(860), Scaler.sh(700), Scaler.sw(200), Scaler.sh(200));

        if (scannerInput != null) {
            p.textSize(Scaler.sh(80));
            p.text(scannerInput, Scaler.sw(690), Scaler.sh(525));

            //If the word is spelled correctly
            if (library.get(level - 1).length() == scannerInput.length() && library.get(level - 1).equals(scannerInput)) {
                p.image(images.getImage("Correct"), Scaler.sw(390), Scaler.sh(200), Scaler.sw(300), Scaler.sh(300));
            }
            //If the word is spelled incorrectly
            if (library.get(level - 1).length() == scannerInput.length() && !library.get(level - 1).equals(scannerInput)) {
                p.image(images.getImage("Incorrect"), Scaler.sw(390), Scaler.sh(200), Scaler.sw(300), Scaler.sh(300));
            }
        }
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

    @Override
    public void mouseOver() {

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

    private boolean overResetButton() {
        return p.mouseX > Scaler.sw(865) && p.mouseX < Scaler.sw(1055) && p.mouseY > Scaler.sh(700) && p.mouseY < Scaler.sh(900);
    }

    private boolean overPreviousLibraryWord() {
        return p.mouseX > Scaler.sw(50) && p.mouseX < Scaler.sw(145) && p.mouseY > Scaler.sh(340) && p.mouseY < Scaler.sh(435);
    }

    private boolean overNextLibraryWord() {
        return p.mouseX > Scaler.sw(1770) && p.mouseX < Scaler.sw(1875) && p.mouseY > Scaler.sh(340) && p.mouseY < Scaler.sh(435);
    }


    @Override
    public void scanTag(String tag) {
        scannerInput+=dictionary.get(tag);
    }
}

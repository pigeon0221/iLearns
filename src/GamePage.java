import processing.core.PApplet;
import processing.core.PImage;

import java.io.*;
import java.util.ArrayList;

public class GamePage implements Screen {
    public boolean visibility = false;
    Pages pages = new Pages();
    Images images;
    int level = 1;
    ArrayList<String> library = new ArrayList<>();
    private PApplet p;

    public GamePage(PApplet p, ArrayList<String> lib) {
        this.p = p;
        images = new Images(this.p);
        library = lib;
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
                try {
                    PrintWriter writer = new PrintWriter("Python/output.txt");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        if (overPreviousLibraryWord()) {
            if (level == 1) {
                return;
            } else {
                level -= 1;
                try {
                    PrintWriter writer = new PrintWriter("Python/output.txt");
                    writer.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
        if (overResetButton()) {
            try {
                PrintWriter writer = new PrintWriter("Python/output.txt");
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
        p.textSize(50);
        p.text("Level " + level, p.width / 2 - 100, 70);
        p.image(images.getImage("WordBox"), 200, p.height / 2 - 200, 1520, 300);
        p.image(images.getImage("BackWordButton"), 50, p.height / 2 - 200, 100, 100);
        p.image(images.getImage("NextWordButton"), 1770, p.height / 2 - 200, 100, 100);
        p.image(images.getImage("ResetButton"), p.width / 2 - 100, 700, 200, 200);
        String line;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("Python/output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            line = null;
        }
        if (line != null) {
            p.textSize(80);
            p.text(line, 690, 525);

            //If the word is spelled correctly
            if (library.get(level - 1).length() == line.length() && library.get(level - 1).equals(line)) {
                p.image(images.getImage("Correct"), p.width / 2 - 150, 200, 300, 300);
            }
            //If the word is spelled incorrectly
            if (library.get(level - 1).length() == line.length() && !library.get(level - 1).equals(line)) {
                p.image(images.getImage("Incorrect"), p.width / 2 - 150, 200, 300, 300);
            }
        }
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

    private boolean overResetButton() {
        return p.mouseX > 865 && p.mouseX < 1055 && p.mouseY > 700 && p.mouseY < 900;
    }

    private boolean overPreviousLibraryWord() {
        return p.mouseX > 50 && p.mouseX < 145 && p.mouseY > 340 && p.mouseY < 435;
    }

    private boolean overNextLibraryWord() {
        return p.mouseX > 1770 && p.mouseX < 1875 && p.mouseY > 340 && p.mouseY < 435;
    }


}

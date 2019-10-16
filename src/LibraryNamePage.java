import processing.core.PApplet;
import processing.core.PImage;

public class LibraryNamePage implements Screen {
    public boolean visibility = false;
    Pages pages = new Pages();
    Images images;
    String nameInput = "";
    private PApplet p;

    public LibraryNamePage(PApplet p) {
        this.p = p;
        images = new Images(this.p);
    }

    @Override
    public void mousePressed() {
        checkBackgroundButtons();
        checkButtons();
    }

    public void keyPressed() {
        if (p.keyCode == 10 || p.keyCode == 16) {
            return;
        }
        if (p.key == 8) {
            if (nameInput.length() == 0) {
                return;
            }
            nameInput = nameInput.substring(0, nameInput.length() - 1);
        } else {
            nameInput = nameInput + p.key;
        }
    }

    @Override
    public void checkButtons() {
        if (overNextButton()) {
            if (nameInput == "") {
                return;
            }
            else {
                Screen LibraryWordsPage = new LibraryWordsPage(p, nameInput);
                pages.setPage("LibraryWordsPage", LibraryWordsPage);
                visibility = false;
                pages.getPage("LibraryWordsPage").setVisibility(true);
            }
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
        p.image(images.getImage("LibraryNameMenu"), p.displayWidth / 2 - 200, p.displayHeight / 2 - 200, 400, 400);
        p.fill(255);
        p.textSize(50);
        p.text(nameInput, 810, 570);
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

    public boolean overNextButton() {
        return p.mouseX > 890 && p.mouseX < 1035 && p.mouseY > 620 && p.mouseY < 680;
    }
}
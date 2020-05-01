import processing.core.PApplet;
import processing.core.PImage;

public class DictionaryNameScreen implements Screen{
    public boolean visibility = false;
    Screens screens = new Screens();
    Images images;
    String nameInput = "";
    private PApplet p;

    public DictionaryNameScreen(PApplet p) {
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
                Screen DictionaryWordPage = new DictionaryWordScreen(p, nameInput);
                screens.addScreen("DictionaryWordPage", DictionaryWordPage);
                visibility = false;
                screens.getScreen("DictionaryWordPage").setVisibility(true);
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
        drawScreenElements();
    }

    @Override
    public void drawScreenElements() {
        p.image(images.getImage("DictionaryNameMenu"), Scaler.sw(760), Scaler.sh(340), Scaler.sw(400), Scaler.sh(400));
        p.fill(255);
        p.textSize(Scaler.sh(50));
        p.text(nameInput, Scaler.sw(810), Scaler.sh(570));
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

    public boolean overNextButton() {
        return p.mouseX > Scaler.sw(890) && p.mouseX < Scaler.sw(1035) && p.mouseY > Scaler.sh(620) && p.mouseY < Scaler.sh(680);
    }
}
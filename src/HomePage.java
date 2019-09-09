import processing.core.PApplet;
import javax.swing.*;
import processing.core.PImage;

public class HomePage implements Screen {
    private PApplet p;
    public boolean visibility = false;

    // Initialize the reference to the parent PApplet
    // Anytime we want to call a Processing function, we access it via the "p" variable
    public HomePage(PApplet p) {
        this.p = p;
    }

    @Override
    public void mousePressed() {
        visibility=false;
        Main.pages.get("GamePage").setVisibility(true);
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
    }

    private void drawBackgroundElements() {
        p.image(background(), 0, 0, p.width, p.height);
//        p.drawExitButton();
//        p.image(logo, 0, 0, 400, 150);
//        p.image(playButton, 0, 900, 80, 80);
//        p.image(pauseButton, 80, 900, 80, 80);
//        p.image(backButton, 1840, 900, 80, 80);
    }
}

import processing.core.PApplet;
import processing.core.PImage;

public class GamePage implements Screen {
    private PApplet p;
    public boolean visibility;

    public GamePage(PApplet p) {
        this.p = p;
    }

    @Override
    public void mousePressed() {
        visibility=false;
        Main.pages.get("HomePage").setVisibility(true);
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
        return p.loadImage("Images/goodjob.png");
    }

    @Override
    public void create() {
        drawBackgroundElements();
    }
    private void drawBackgroundElements() {
        p.image(background(), 0, 0, p.width, p.height);
    }
}

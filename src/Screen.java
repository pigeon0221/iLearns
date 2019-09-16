import processing.core.PImage;

public interface Screen {
    void mousePressed(); // What to do when the mouse is pressed
    void keyPressed(); // What to do when the key is pressed
    void checkButtons();
    void checkBackgroundButtons();
    void setVisibility(boolean state); // Are we able to see this screen
    boolean getVisibility(); // Are we able to see this screen
    PImage background();
    void create();
    void drawPageElements();
    void drawBackgroundElements();
}

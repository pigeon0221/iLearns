import processing.core.PImage;

public interface Screen {
    void mousePressed(); // What to do when the mouse is pressed
    void setVisibility(boolean state); // Are we able to see this screen
    boolean visibility(); // Are we able to see this screen
    PImage background();
    void create();
}

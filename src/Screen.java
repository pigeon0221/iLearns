import processing.core.PImage;

public interface Screen {
    void mousePressed(); // What to do when the mouse is pressed
    boolean viewable(boolean state); // Are we able to see this screen
    PImage background();
    void create();
}

import processing.core.PImage;
/*
Screen is a interface for defining new processing screens.
The main class calls the individually defined functions below.
 */
public interface Screen {
    void mousePressed(); // What to do when the mouse is pressed
    void keyPressed(); // What to do when the key is pressed
    void checkButtons(); // Checks if a button is pressed
    void checkBackgroundButtons(); // Checks background buttons
    void setVisibility(boolean state); // Sets the visibility of the screen. (True = user can see this screen)
    boolean getVisibility(); // Returns current visibility state
    PImage background(); // Returns an image to set as the screens background
    void create();  // All other functionality is included here
    void drawScreenElements(); // Code for specific screens
    void drawBackgroundElements(); // Code for screens background elements
}

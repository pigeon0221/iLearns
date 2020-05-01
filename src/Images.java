import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;
/*
Class for loading/displaying images in processing.
All images are loaded here and stored in a HashMap for easy access.
 */
public class Images {
    public static HashMap<String, PImage> images = new HashMap<>();
    private PApplet p;

    // Constructor to load images into HashMap
    public Images(PApplet p) {
        this.p = p;
        loadImages();
    }

    // Loads all individual images from Images folder.
    // Add new images here to access in other parts of the code.
    public void loadImages(){
        images.put("PlayButton",p.loadImage("Images/playButton.png"));
        images.put("PauseButton", p.loadImage("Images/pauseButton.png"));
        images.put("Logo", p.loadImage("Images/logo.png"));
        images.put("Menu", p.loadImage("Images/menu.png"));
        images.put("BackButton", p.loadImage("Images/backButton.png"));
        images.put("LibraryNameMenu",p.loadImage("Images/LibraryName.png"));
        images.put("DictionaryNameMenu",p.loadImage("Images/DictionaryName.png"));
        images.put("LibraryWordMenu", p.loadImage("Images/LibraryWords.png"));
        images.put("WordBox",p.loadImage("Images/wordBox.png"));
        images.put("NextWordButton", p.loadImage("Images/nextWordButton.png"));
        images.put("BackWordButton", p.loadImage("Images/backWordButton.png"));
        images.put("Correct",p.loadImage("Images/goodjob.png"));
        images.put("Incorrect", p.loadImage("Images/incorrect.png"));
        images.put("ResetButton",  p.loadImage("Images/reset.png"));
        images.put("Error",  p.loadImage("Images/ERROR.png"));
        images.put("Exit",  p.loadImage("Images/Exit.png"));
        images.put("Background",p.loadImage("Images/cartoonB.png"));
        images.put("ScanRFID",p.loadImage("Images/ScanRFID.png"));
        images.put("DictionaryWord",p.loadImage("Images/DictionaryWord.png"));
        images.put("PDFButton",p.loadImage("Images/PDFButton.png"));
    }

    // Returns an image given an image name.
    public PImage getImage(String imageName){
        return images.getOrDefault(imageName,images.get("Error"));
    }
}

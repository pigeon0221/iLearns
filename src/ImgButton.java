import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;

//TODO: Add parameter for the function that gets executed on click (Ex: Exit Button taking in p.exit())


public class ImgButton {
    private int x, y, w, h;
    private String name;
    private int clicks = 0;
    private String imgPath;
    private PImage img;
    private PShape thisButton;
    private PApplet p;
    private int stroke = 0;
    private boolean init = true;

    //Constructor
    public ImgButton(PApplet p, int x, int y, int w, int h, String name, PImage img /*String imgPath*/) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.name = name;
        this.p = p;
        this.img = img;
//        this.imgPath = imgPath;
    }

    //Check if the mouse is over the button
    private boolean mouseOverButton() {
        if((p.mouseX >= x && p.mouseX < x + w) && (p.mouseY >= y && p.mouseY < y + h)){
            System.out.println("The mouse is over " + name);
            return true;
        }
//        System.out.println("The mouse is off");
        return false;
    }

    //draws the button
    public void renderButton(){
        p.strokeWeight(4);
        if (init) {
            thisButton = p.createShape(p.RECT,x,y,w,h);
            thisButton.setFill(0);
//            img = p.loadImage(imgPath);
            img.resize(w, h);
            init = false;
        }
        thisButton.setStroke(stroke);
        p.shape(thisButton);
        p.image(img, x, y);
    }

    //Executes when the mouse moves
    void mouseOver(){
        if(this.mouseOverButton()){
            stroke = (p.color(255));
        }
        else{
            stroke = (p.color(0));
        }
        p.redraw();
    }

    //Executes when button is pressed
    void buttonPressed() {
        if(this.mouseOverButton()){
            System.out.println("The mouse is pressed on Button: " + name + " for: " + this.get_clicks() + " times");
        }
        p.redraw();
    }

    int get_clicks() {
        return ++clicks;
    }
}

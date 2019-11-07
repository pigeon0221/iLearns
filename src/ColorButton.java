import processing.core.PApplet;
import processing.core.PShape;

import java.awt.*;

import static processing.core.PConstants.RECT;

public class ColorButton {
    int x, y, w, h;
    String name;
    int clicks = 0;
    PShape thisButton;
    boolean buttonPressed = false;
    private PApplet p;
    boolean buttonRendered = false;
    int stroke = 0;
    int color = 0 ;

    //Constructor
    public ColorButton(PApplet parent, int new_x, int new_y, int new_w, int new_h, String my_name, int color) {
        x = new_x;
        y = new_y;
        w = new_w;
        h = new_h;
        name = my_name;
        p = parent;
        this.color =  color;

    }

    public void renderButton(){
        p.strokeWeight(8);
        thisButton = p.createShape(p.RECT,x,y,w,h);
        thisButton.setStroke(stroke);
        thisButton.setFill(color);
        p.shape(thisButton);
    }
    //check if the mouse is over the button
    private boolean mouseOverButton() {
        if((p.mouseX >= x && p.mouseX < x + w) &&
                (p.mouseY >= y && p.mouseY < y + h)){
            //println("The mouse is over " + name);
            return true;
        }
        //println("The mouse is off");
        return false;
    }

    //Updates all button fields every time draw() is run
    void buttonClick() {
        if(!buttonPressed){
            buttonPressed = true;
        }
        else{
            buttonPressed=false;
        }
        p.redraw();

    }

    void buttonPressed() {
        if(this.mouseOverButton()  ){
            System.out.println("The mouse is pressed on Button: " + name + " for: " + this.get_clicks() + " times");
            buttonClick();
        }

    }

    void mouseOver(){
        if(this.mouseOverButton() || buttonPressed){
                stroke = (p.color(200));
                p.redraw();
        }
        else{
            stroke = (p.color(255));
            p.redraw();
        }
    }




    int get_clicks() {
        return ++clicks;
    }
}

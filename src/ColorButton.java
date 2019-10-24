import processing.core.PApplet;
import processing.core.PShape;

import static processing.core.PConstants.RECT;

public class ColorButton {
    int x, y, w, h;
    String name;
    int clicks = 0;
    PShape thisButton;

    private PApplet p;

    //Constructor
    public ColorButton(PApplet p, int new_x, int new_y, int new_w, int new_h, String my_name) {
        x = new_x;
        y = new_y;
        w = new_w;
        h = new_h;
        name = my_name;
        this.p = p;

        //Create rectangle PShape
        thisButton = p.createShape(RECT,x,y,w,h);
        thisButton.setFill(p.color(0,255,0));
    }
}

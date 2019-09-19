import processing.core.PApplet;

import java.util.HashMap;

public class Scaler {
    private PApplet p;
    public Scaler(PApplet p) {
        this.p = p;
    }
    public int getWidth(){
        return p.displayWidth;
    }
    public int getHeight(){
        return p.displayHeight;
    }
}

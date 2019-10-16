import processing.core.PApplet;

import java.util.HashMap;

public class Scaler {
    private static int scaleH;
    private static int scaleW;
    public static int sw(int width){
        return (int) (width*scaleW);
    }
    public static int sh(int height){
        return (int) (height*scaleH);
    }
    public static void  setScalerWidth(int width)
    {
        scaleW=width/1920;
    }
    public static void setScalerHeight(int height)
    {
        scaleH=height/1080;
    }
}

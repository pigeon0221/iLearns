import processing.core.PApplet;

import java.util.HashMap;
/*
Static class for scaling all pixel values. 
 */
public class Scaler {
    private static float scaleH;
    private static float scaleW;
    public static int sw(int width){
        return (int) (width*scaleW);
    }
    public static int sh(int height){
        return (int) (height*scaleH);
    }
    public static void setScalerWidth(float width)
    {
        scaleW=width/1920;
        //System.out.println("sw: "+scaleW);
    }
    public static void setScalerHeight(float height)
    {
        scaleH=height/1080;
        //System.out.println("sh: "+scaleH);
    }
}

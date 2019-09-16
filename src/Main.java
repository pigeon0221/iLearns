import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class Main extends PApplet {
    private static Player player;
    private static boolean onNamePage = false;
    Pages pages = new Pages();
    private Screen currentPage;


    public static void main(String[] args) {
        PApplet.main("Main");
    }

    public void settings() {
        fullScreen();
        populatePages();
        setCurrentPage();
        currentPage.setVisibility(true);
    }

    private void setCurrentPage() {
        currentPage = pages.getPage("HomePage");
    }

    private void populatePages() {
        Screen HomePage = new HomePage(this);
        pages.setPage("HomePage", HomePage);
    }

    public void setup() {
        surface.setResizable(true);
        loadPrintWriter();
    }

    private void loadPrintWriter() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("Python/output.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.close();
    }

    public void draw() {
        if (!currentPage.getVisibility()) {
            for (Screen x : pages.getAllPages().values()) {
                if (x.getVisibility()) {
                    clear();
                    currentPage = x;
                    break;
                }
            }
        }
        currentPage.create();
    }

    public void keyPressed() {
        currentPage.keyPressed();
    }

    public void mousePressed() {
        GameEffects.playClickSound();
        currentPage.mousePressed();
    }
}
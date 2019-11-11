import com.fazecast.jSerialComm.SerialPort;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import processing.core.PApplet;
import processing.core.PImage;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Main extends PApplet {
    private static Player player;
    private static boolean onNamePage = false;
    Pages pages = new Pages();
    private Screen currentPage;


    public static void main(String[] args) {
        boolean USBConnected = true;
          SerialPort comPort[] = SerialPort.getCommPorts();
          if(comPort.length!=0){
              for (SerialPort s:comPort){
                  if(s.toString().contains("FT232R USB UART")){
                        USBConnected=true;
                        s.openPort();
                        PacketListener listener = new PacketListener();
                        s.addDataListener(listener);
                        break;
                  }
              }
          }
          if(!USBConnected){
              JOptionPane.showMessageDialog(null,
                      "NO USB Reader Connected, please connect and restart ILearns",
                      "ILearns: ERROR",
                      JOptionPane.WARNING_MESSAGE);
              System.exit(0);
          }
        PApplet.main("Main");
    }

    public void settings() {
        fullScreen();
        Scaler.setScalerWidth(parseFloat(displayWidth));
        Scaler.setScalerHeight(parseFloat(displayHeight));
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
        noLoop();
    }

    public void draw() {
        checkUSBConnection();
        currentPage=pages.getActivePage();
        currentPage.create();
    }

    private void checkUSBConnection() {
        boolean USBConnected = true;
        SerialPort comPort[] = SerialPort.getCommPorts();
        if(comPort.length!=0){
            for (SerialPort s:comPort){
                if(s.toString().contains("FT232R USB UART")){
                    USBConnected=true;
                    break;
                }
            }
        }
        if(!USBConnected){
            JOptionPane.showMessageDialog(null,
                    "USB DISCONNECTED, Please reconnect and restart ILearns",
                    "ILearns: ERROR",
                    JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    public void keyPressed() {
        currentPage.keyPressed();
    }

    public void mousePressed() {
        System.out.println("Mouse pressed at " + mouseX+","+mouseY);
        GameEffects.playClickSound();
        currentPage.mousePressed();
    }

    public void mouseReleased() {
        System.out.println("Mouse released at " + mouseX+","+mouseY);
        GameEffects.playClickSound();
    }
    public void mouseMoved(){
      //  System.out.println("Mouse Moved");
        currentPage.mouseOver();
    }
}
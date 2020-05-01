import com.fazecast.jSerialComm.SerialPort;
import processing.core.PApplet;
import javax.swing.JOptionPane;


/*
Main class for game. Main extends PApplet which is a processing applet. Processing is a java library for
creating visuals that the entire codebase is centered around.

PApplets contain 3 required methods:

        settings: Where settings are set before the code runs/draws.
        setup: Similar to setup but runs once settings is complete.
        draw: A continuously looping method for drawing screen elements.

The Main also includes the following non required methods from PApplet:

    keyPressed: A key listener for PApplets
    mousePressed: A mouse listener for PApplets.

The main class contains no code for the individual screen implementations but instead will determine what screen
is active and call it's local methods.
 */
public class Main extends PApplet {
    Screens screens = new Screens();    // Creates new Screens object
    private Screen currentScreen;       // Creates a screen called currentScreen which maps to the whichever screen is currently active.

    // Connects to the USB reader if it is connected and continues game. If not the game exits.
    public static void main(String[] args) {
        boolean USBConnected = true;
          SerialPort[] comPort = SerialPort.getCommPorts();
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

    /*
    Defines settings for the PApplet.
     */
    public void settings() {
        Scaler.setScalerWidth(parseFloat(displayWidth));
        Scaler.setScalerHeight(parseFloat(displayHeight));
        populateScreens();
        setCurrentScreen();
        currentScreen.setVisibility(true);
    }
    /*
    Setup for the PApplet.
     */
    public void setup() {
        surface.setResizable(true);
    }

    /*
    Draw code for the PApplet. Finds what page is currently active and calls it's local create method.
     */
    public void draw() {
        checkUSBConnection();
        if(!currentScreen.getVisibility()) {
            currentScreen = screens.getActiveScreen();
        }
        currentScreen.create();
    }
    /*
    Key listener. Calls the currently active screens keyPressed method.
     */
    public void keyPressed() {
        currentScreen.keyPressed();
    }

    /*
    Mouse listener. Calls the currently active screens mousePressed method.
     */
    public void mousePressed() {
        System.out.println(mouseX+","+mouseY);
        GameEffects.playClickSound();
        currentScreen.mousePressed();
    }

    /*
    Sets the currentScreen = HomeScreen
     */
    private void setCurrentScreen() {
        currentScreen = screens.getScreen("HomeScreen");
    }

    /*
    Creates the HomeScreen and adds it to screens.
     */
    private void populateScreens() {
        Screen HomeScreen = new HomeScreen(this);
        screens.addScreen("HomeScreen", HomeScreen);
    }

    /*
    Checks if the USB reader is connected to a serial port
     */
    private void checkUSBConnection() {
        boolean USBConnected = true;
        SerialPort[] comPort = SerialPort.getCommPorts();
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

}
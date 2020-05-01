import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

/*
Packet Listener for receiving inputs via the serial(USB) ports.
 */
final class PacketListener implements SerialPortPacketListener {
    Screens screens = new Screens();
    public PacketListener() {
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }
    /*
    Specifies packet size.
     */
    @Override
    public int getPacketSize() {
        return 16;
    }

    /*
    Listener triggered by tag being scanned.
     */
    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String scan="";
        for (int i = 1; i < newData.length - 2; ++i)
            scan+=((char) newData[i]);
        if(getCurrentScreenName().equals("DictionaryWordPage")){
            TagScreen screen = (TagScreen) screens.getScreen("DictionaryWordPage");
            screen.scanTag(scan);
        }
        if(getCurrentScreenName().equals("GamePage")){
            TagScreen screen = (TagScreen) screens.getScreen("GamePage");
            screen.scanTag(scan.replaceAll("\\r", ""));
        }
    }
    /*
    Returns current screen name.
     */
    public String getCurrentScreenName(){
        Screen x = screens.getActiveScreen();
        return(x.toString().split("@")[0]);
    }


}

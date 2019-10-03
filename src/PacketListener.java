import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;


final class PacketListener implements SerialPortPacketListener {
    Pages pages = new Pages();
    public PacketListener() {
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
    }

    @Override
    public int getPacketSize() {
        return 16;
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        byte[] newData = event.getReceivedData();
        String scan="";
        for (int i = 1; i < newData.length - 2; ++i)
            scan+=((char) newData[i]);
        if(getCurrentPageName().equals("DictionaryWordPage")){
            TagScreen screen = (TagScreen) pages.getPage("DictionaryWordPage");
            screen.scanTag(scan);
        }
        if(getCurrentPageName().equals("GamePage")){
            TagScreen screen = (TagScreen) pages.getPage("GamePage");
            screen.scanTag(scan.replaceAll("\\r", ""));
        }
    }
    public String getCurrentPageName(){
        Screen x = pages.getActivePage();
        return(x.toString().split("@")[0]);
    }


}

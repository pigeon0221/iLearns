import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;

final class PacketListener implements SerialPortPacketListener
{
    @Override
    public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_RECEIVED; }

    @Override
    public int getPacketSize() { return 16; }

    @Override
    public void serialEvent(SerialPortEvent event)
    {
        byte[] newData = event.getReceivedData();
        for (int i = 1; i < newData.length-2; ++i)
            System.out.print((char)newData[i]);
        System.out.println("\n");
    }
}
import serial
import time
from xbee import XBee
import sys



usb_location='/dev/ttyUSB'
location_count=0
while 1:
    try:
        serial_port = serial.Serial(usb_location+str(location_count), 9600)
        break
    except:
        location_count+=1
        if (location_count > 10):
            print("Error no USB Connected")
            sys.exit()

def print_data(data):
    """
    This method is called whenever data is received
    from the associated XBee device. Its first and
    only argument is the data contained within the
    frame.
    """
    input_Signal=str(data['rf_data'])

    if '35021CA912' in input_Signal:
        print('A')
    elif '35021C9C84' in input_Signal:
        print('B')



print("ILearns RFID Input:")
xbee = XBee(serial_port, callback=print_data)

while True:
    try:
        time.sleep(0.001)
    except KeyboardInterrupt:
        break

xbee.halt()
serial_port.close()
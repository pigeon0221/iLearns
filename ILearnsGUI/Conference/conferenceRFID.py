import serial
import sys
import platform
import serial.tools.list_ports as port_list

output_File_Clear = open('output.txt', 'w').close()
output_File = open('output.txt', 'a')
print(platform.system())
ser=None

if platform.system() == 'Linux':
    portNum = 0
    while 1:
        try:
            ser = serial.Serial('/dev/ttyUSB' + str(portNum), 9600)
            break
        except:
            portNum += 1
            if (portNum > 10):
                print("Error no USB Connected")  # Looping to find port with XBee connection
                sys.exit()

if platform.system() is 'Windows':

    ports= list(port_list.comports())
    for p in ports:
        if('USB' in p.description):
            ser = serial.Serial(p.device, 9600)


while True:
    data = ser.readline()
    if data:
        print("Recieved KEY = " + str(data, 'utf-8'));


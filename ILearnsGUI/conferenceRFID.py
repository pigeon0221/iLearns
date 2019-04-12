import serial
import sys
import platform
import serial.tools.list_ports as port_list

open('output.txt', 'w').close()
output_File = open('output.txt', 'w').close()
dictionary = open('dictionary.txt', 'a')
rfidValue={}
print(platform.system())
ser=None
marker = 'X'
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

mode = 1
clear = None
if(mode == 1):
    lines = None;
    list=[]
    with open('dictionary.txt') as f:
        lines = f.readlines()
    for l in lines:
        if(len(l[:len(l)-1])!=0):
            list.append(l[:len(l)-1])
    for x in range(0,len(list),2):
        rfidValue[list[x]]=list[x+1]
if(mode == 2):
    clear = int(input("Enter 1 to clear dictionary , Enter 2 to add new keys --- "))
while True:
    data = ser.readline()
    # READ Mode outputs information when an input is recieved
    # SETUP Mode allows the user to input new tags and there information.
    if data:
        if (mode == 1):
            output=open('output.txt', 'a')
            key = str(data, 'utf-8').split(':')[1]
            key=(key[:len(key)-1])
            print("Recieved KEY = " + key)
            print("This key = "+rfidValue[key])
            output.write(rfidValue[key]+marker)
            output.close()
            if(marker=='X'):
                marker='O'
            else:
                marker='X'
        if (mode == 2):
            if(clear==1):
                open('dictionary.txt', 'w').close()
            if(clear==2):
                dictionary=open('dictionary.txt', 'a')
                dictionary.write(str(data, 'utf-8').split(':')[1]+'\n')
                dictionary.close()
                dictionary = open('dictionary.txt', 'a')
                value=str(input("What do you want this to be?")+'\n')
                dictionary.write(value)
                dictionary.close()

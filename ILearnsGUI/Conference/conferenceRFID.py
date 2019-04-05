"""
Written by Alex Lazar
Reads XBee inputs and outputs respective info
Each TAG contains a pair <KEY><VALUE>
<KEY> is the HEX value stored in the TAG
<VALUE> is what the user inputs to be associated wih the TAG
Also allows for TAGS to be assigned to whatever the user wants
"""
import code

import serial
import time
from xbee import XBee
import sys
import platform
operatingSystem = platform.system();
usb_location = None
if(operatingSystem=="Linux"):
    location_count = 0  # Start at location 0 and loop through first 10 until we find a connection
    usb_location='/dev/ttyUSB'                  # Linux USB ports are organized by path
    while 1:
        try:
            ser = serial.Serial('/dev/ttyUSB'+str(location_count), 9600)
            break
        except:
            location_count += 1
            if (location_count > 10):
                print("Error no USB Connected")  # Looping to find port with XBee connection
                sys.exit()
else:
    import serial.tools.list_ports as port_list

    ports = list(port_list.comports())
    for p in ports:
        if("USB" in p.description):
            usb_location=p.device
output_File_Clear = open('TicTacToe.txt','w').close()
output_File = open('TicTacToe.txt','a')
serial_port = serial.Serial(usb_location, 9600)
print("Connected on port "+usb_location)
print("Hello! Welcome to ILearns Demo!")

#mode=int(input("Enter 1 for READ Mode , Enter 2 for SETUP --- "))
# READ Mode outputs information when an input is recieved
# SETUP Mode allows the user to input new tags and there information.
#
# if(mode == 1):
#     print("ILearns RFID Input:")
# if(mode  == 2):                 #Allows user to clear dictionary
#     clear=int(input("Enter 1 to erase all current TAGs in dictionary , Enter 0 to keep current TAGs - "))
#     if(clear == 1):
#         Make_Alphabet.clear_Dictionary()
#         print("Dictionary Cleared")
#     print("Scan TAG you would like to create")
def print_data(data):
    print(data)
#     """
#     This method is called whenever data is received
#     from the associated XBee device. Its first and
#     only argument is the data contained within the
#     frame.
#     """
#
#     input_Signal = str(data['rf_data'][2:12])           # Extracts hex key from TAG input
#     if(mode == 1):
#         if(input_Signal not in alphabet):
#             print("TAG not in dictionary")
#         print(alphabet[input_Signal])                   # Prints associated INFO to respective tag
#         output_File = open('output.txt', 'a')
#         output_File.write(alphabet[input_Signal])
#         output_File.close()
#     if(mode == 2):
#         Make_Alphabet.add_Letter(input_Signal,alphabet) # Adds to alphabet
#
#
#
#
#
xbee = XBee(serial_port, callback=print_data)
while True:
    try:
        time.sleep(0.001)                           # Monitors for inputs
    except KeyboardInterrupt:
        print(print_data())
        break

xbee.halt()
serial_port.close()

# def get_Info(code):                          #Used in EXE to return input from Java input
#     return alphabet[code]

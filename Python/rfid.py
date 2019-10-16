import serial
import Make_Alphabet
import sys
import os
import platform

alphabet = Make_Alphabet.get_Alphabet()  # Sets alphabet to a dictionary of <KEY> <INFO> returned by get_Alphabet
output_File_Clear = open('output.txt', 'w').close()
output_File = open('output.txt', 'a')
portNum = 0
print(platform.system())
if platform.system() == 'Linux' or platform.system() == 'Darwin':
    while 1:
        try:
            ser = serial.Serial('/dev/ttyUSB' + str(portNum), 9600)
            break
        except:
            portNum += 1
            if (portNum > 10):
                print("Error no USB Connected")  # Looping to find port with XBee connection
                sys.exit()
elif platform.system() is 'win64' or platform.system() is 'win32':

    """
    TODO: Write code to read from windows port
    """

print("Hello! Welcome to ILearns!")

mode = int(input("Enter 1 for READ Mode , Enter 2 for SETUP --- "))
print(alphabet)
while True:
    data = ser.readline() #.split(':')[1].rstrip("\n\r")
    if data:
        data = str(data)
        print("Recieved KEY = " + data)
        if (mode == 1):
            if (data not in alphabet):
                print("TAG not in dictionary")
            else:
                print(alphabet[data])  # Prints associated INFO to respective tag
                output_File = open('output.txt', 'a')
                output_File.write(alphabet[data])
                output_File.close()
        if (mode == 2):
            Make_Alphabet.add_Letter(data, alphabet)  # Adds to alphabet

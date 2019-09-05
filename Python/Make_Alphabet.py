"""
Written by Alex Lazar
Creates a dictionary for XBee input and its respective value
Each TAG contains a pair <KEY><VALUE>
<KEY> is the HEX value stored in the TAG
<VALUE> is what the user inputs to be associated wih the TAG
Dictionary is stored in a text file called "Alphabet_Codes.txt"
This file is read line by line and is in the format of

<KEY>
<VALUE>
<KEY>
<VALUE>

"""
from past.builtins import raw_input

File = open('Alphabet_Codes.txt','r+')          #Opens file containing KEYS
alphabet_File=File.read()                       #Stores contents in a string
File.close()
alphabet_Array=[]                               #This array will be used later to store the contents so they can easily be moved into the dictionary
alphabet={}                                     #Dictionary containing <KEY> <VALUE> pairs

def generate_Alphabet_Array():                  #Reads file line by line and stores <KEY> in even indexes and <VALUE> in odd indexes
    with open('Alphabet_Codes.txt') as fp:      #       <KEY>     <VALUE>     <KEY>   <VALUE>
        line = fp.readline()                    # Index  0          1           2       3
        cnt = 1
        while line:
            alphabet_Array.append(line.strip())
            line = fp.readline()
            cnt += 1


def add_Letter(data,current_alphabet):      #Allows user to input new TAGS
    if(data in current_alphabet):
       print("TAG already in alphabet")
       return
    else:
        File = open('Alphabet_Codes.txt', 'a')  # Opens file containing KEYS
        x = input("What would you like to assign this TAG too? - ")
        File.write(data+'\n')
        File.write(x+'\n')
        print("TAG Created!")
        print("Scan TAG you would like to create")
        File.close()
        return

def get_Alphabet():                                          # Creates and returns final dictionary of TAGS
    generate_Alphabet_Array()
    for i in range(0, len(alphabet_Array), 2):
        alphabet[alphabet_Array[i]]=alphabet_Array[i+1]
    return(alphabet)


def clear_Dictionary():                                     # Clears existing Dictionary
    open('Alphabet_Codes.txt',"w").close()

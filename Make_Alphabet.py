alphabet=dict([('b\'35021CA912\'','A'),('b\'35021C9C84\'','B')])

def add_Letter(data,current_alphabet):

    if(data in current_alphabet):
       print("Letter already in alphabet")
    else:
        x= input("What is the letter")
        return x

def get_Alphabet():
    return(alphabet)
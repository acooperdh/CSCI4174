# Drew Cooper B00811386 Assignment 6 RSA Algorithm 
# how an RSA algo works 
# 1. takes in 2 integers, p and q 
# 2. ensure that p and q are both prime numbers 
# 3. find and store the product of p and q 
# 4. find an integer, x, that is less then the product and does not share any factors with 
# the product of (p-1)(q-1). 
# 5. find an integer d such that e(d) mod (p-1)(q-1) = 1 
# then you know that the public key is (e,n) and the private key is (d, n)

import math
# determinePrime takes in an integer and returns True or False to determine if the number is prime or not 
def determinePrime(x):
    
    if (x % 2 == 0):
        return False
    elif (x == 0):
        return False
    else:
        #loops through all of the numbers from 2 - x and determines if any of them are factors of x. If there is a factor of x, then x is not prime. Else we know that x is prime 
        for i in range (2, x):
            if (x%i == 0):
                return False
    return True

def primeProduct(x, y):
    return x*y

def determineNTwo(x,y):
    return (x-1)*(y-1)

def primeGCD(x,y):
    return math.gcd(x,y)

#this checks to ensure that e and (p-1)(q-1) dont share factors 
def checkFactors(prime, e):
    larger = max(prime, e)
    for i in range (3, larger):
        if (prime % i == 0 & e % i == 0):
            return False
    return True

#determineE uses product of (p-1)*(q-1) in order to find a value e that is coprime with said value and is less then it. 
def determineE(smallerPrime, p, q):
    e = 2
    if (smallerPrime % 2 != 0):
        return e
    elif(smallerPrime % 3 != 0):
        e = 3
        return e
    else:
        e = 5 
        for i in range (5, smallerPrime, 2):
            if (e == p | e == q):
                continue
            elif(smallerPrime % i != 0):
                return i

# determines the value of D by finding them mod inverse of ed mod 
def determineD(e, modNum):
    temp = modNum + 1
    if (temp % e == 0):
        return temp / e
    else: 
        found = False
        while (found == False):
            #print('temp pre', temp)
            temp+= modNum
            if (temp%e == 0):
                found = True 
        return temp / e
    
def encryption (message, e, n):
    temp = message ** e 
    result = temp % n 
    return result 

def decryption (cipher, d, n):
    temp = cipher ** d
    result = temp % n
    return result 


def main():
    print("Welcome to Drew's RSA Algorithm Simulator\n")
    p = int(input("Please enter a prime number: "))
    q = int(input("Please enter another prime number that is not the same as the first: "))
    if (determinePrime(p) == False | determinePrime(q) == False):
        print("One of the numbers that you have entered is not prime. Please enter a prime number")
        print("\n")
    # this is used in modulus 
    product = primeProduct(p,q)
    n2 = determineNTwo(p,q)
    e = determineE(n2, p, q)
    gcd = primeGCD(p,q)
    d = int(determineD(e, n2))
    # print("This is the product of p and q:",product, "\n")
    # print('This is the product of p-1 * q-1:', n2)
    # print('this is the prime gcd of p, q', gcd)
    # print('this is the value of e ', e)
    # print('this is the value of d ', d)
    print("the public key is (",e,",",product,")")
    print("the private key is (",d,",",product,")")
    print('please enter a message (an int) you want to encrypt')
    message = int(input())
    ciphertext = encryption(message, e, product)
    print("This is your messaged encrypted: ", ciphertext)
    print("enter a message (an int) to decrypt: ")
    cipher = int(input())
    decrypted = decryption(cipher, d, product)
    print("this is your decrypted message: ", decrypted)

if __name__ == "__main__":
    main()
# Drew Cooper B00811386 CSCI 4174 Assignment 3 - ACL Simulation 
# ACLsim.py reads in 2 input files. The first one contains ACL statements that are used to set the rules of the ACL. The next is a list of ip addresses 
# where packets are being attempted to be sent to the server (packets are incoming). The output will be wether or not the packet was recieved or denyed 
from Rule import Rule
from IPPacket import IPPacket

def readIn(fileName):
    inputFile = open(fileName, "r")
    lango = []
    for x in inputFile:
        temp = x.split()
        tempRule = Rule(temp[1], temp[2], temp[3], temp[4])
        lango.append(tempRule)
        del tempRule
        del temp
    inputFile.close()
    return lango

def readInIPs(fileName):
    inputFile = open(fileName, "r")
    addrs = []
    t = []
    for x in inputFile: 
        addrs.append(IPPacket(x.strip("\n")))
    return addrs

def toString(ip, foo):
    if (foo == True):
        temp = str(ip)
        print("Packet from "+temp+ " permitted")
    else:
        temp = str(ip)
        print("Packet from "+temp+" denied")

def main():
    aclFile = input("Enter the name of the file you want to read in: ")
    aclRules = readIn(aclFile)
    numRules = len(aclRules)
    ipFile = input("Enter name of file containing the IP addresses you wish to test: ")
    ipAddys = readInIPs(ipFile)

    for x in range(len(ipAddys)):
        t = False
        for j in range(numRules):
            if (aclRules[j].determineOption(ipAddys[x]) == True):
                 toString(ipAddys[x].ip, True)
                 break
            elif (j == numRules - 1):
                toString(ipAddys[x].ip, False)
                break
    
            
    
    
    

if __name__ == "__main__":
    main()

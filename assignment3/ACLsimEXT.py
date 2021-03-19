# # Drew Cooper B00811386 CSCI 4174 Assignment 3 - ACL Simulation 
# # ACLsimEXT.py reads in 2 input files. The first one contains ACL statements that are used to set the rules of the ACL. The next is a list of ip addresses 
# # where packets are being attempted to be sent to the server (packets are incoming). The output will be wether or not the packet was recieved or denyed 
# from Rule import Rule
# from IPPacket import IPPacket

# def readIn(fileName):
#     inputFile = open(fileName, "r")
#     lango = []
#     for x in inputFile:
#         temp = []
#         temp = x.split()
#         tempRule = Rule(temp[1], temp[2], temp[3], temp[4])
#         lango.append(tempRule)
#         del tempRule
#         del temp
#     inputFile.close()
#     return lango

# def readInIPs(fileName):
#     inputFile = open(fileName, "r")
#     addrs = []
#     t = []
#     for x in inputFile: 
#         addrs.append(IPPacket(x.strip("\n")))
#     return addrs

# def toString(ip, foo):
#     if (foo):
#         temp = str(ip.ip)
#         print("Packet from "+temp+ "permitted")
#     else:
#         temp = str(ip.ip)
#         print("Packet from "+temp+" denied")

# def main():
#     print("Enter the name of the file you want to read in: ")
#     aclRules = readIn("acl.txt")
#     print("\n loading \n ")  
#     numRules = len(aclRules)
#     for i in range(numRules):
#         print(aclRules[i].printInfo())
#         print(aclRules[i].calcMask())
#     #print("Enter name of file containing the IP addresses you wish to test: ")
#     ipAddys = readInIPs("ips.txt")
#     for i in range(len(ipAddys)):
#         print(ipAddys[i].printInfo())

#     print("looping to test IP addresses against ACL rules ")
    
            
    
    
            
    
    
    

# if __name__ == "__main__":
#     main()

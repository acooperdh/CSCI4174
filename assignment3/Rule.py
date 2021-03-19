from IPPacket import IPPacket
class Rule: 
    def __init__(self, num, option, source, mask):
        self.num = num
        self.option = option
        self.source = source
        self.mask = mask
        self.sigfigs = 0
        self.sourceArr = source.split(".")
        self.maskArr = mask.split(".")

    def ruleType(self):
        if (self.option == "permit"):
            return "permitted"
        else: 
            return "denied"
    
    def sourceIP(self):
        return self.source

    def checkIP(self, ip):
        return self.sourceIP == ip

    def printInfo(self):
        return "access-list "+str(self.num)+" "+str(self.option)+" "+str(self.source)+" "+str(self.mask)

    def calcMask(self):
        self.sigfigs = 0
        for x in range(4):
            if(self.maskArr[x] != "0"):
                self.sigfigs+=1
        return self.sigfigs
    
    def determineOption(self, ip):
        if (self.ruleType() == "denied"):
            return self.deniedRule(ip)
        if (self.ruleType() == "permitted"):
            return self.permittedRule(ip)

    def deniedRule(self, ip):
        self.calcMask()
        allow = False
        temp = 0 
        for x in range(4 - self.sigfigs):
            if(self.sourceArr[x] == ip.ipArr[x]):
                allow = False
                temp +=1
        if (temp == self.sigfigs):
            ip.denied +=1
        return allow
            

    def permittedRule(self, ip):
        self.calcMask()
        allow = False
        for x in range(4 - self.sigfigs):
            if(self.sourceArr[x] == ip.ipArr[x]):
                allow = True
            else:
                allow = False
        if(ip.denied != 0):
            allow = False
        return allow

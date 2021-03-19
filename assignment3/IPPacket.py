class IPPacket: 
    def __init__(self, ip):
        self.ip = ip
        self.ipArr = ip.split(".")
        self.permitted = False
        self.denied = 0
    def printInfo(self):
        return self.ip 
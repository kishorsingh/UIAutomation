#Title           :changeMaxCMDFileSize.py
#Description     :Python script to change Max CMD file size using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :02/07/2016
#Version         :1.0
#Usage           :~/wlst.sh changeMaxCMDFileSize.py <ADMIN_SERVER> <USERNAME> <PASSWORD> <FILESIZE>
#Notes           :This python scripts changes Max CMD file size
#################################################################################################################

import sys
import os

from java.io import FileInputStream
print '*** Script invocation : Starting ***'
admin_server = sys.argv[1]
username =  sys.argv[2]
password =  sys.argv[3]
filesize = sys.argv[4]
print 'admin_server ' + admin_server
print 'username ' + username
print 'password ' + password
print 'filesize ' + filesize
print '##############################################################################################'
print 'Starting  to change Max CMD file size to ' +filesize
try:
    connect(username, password,admin_server)
    domainRuntime()
    soaBean = ObjectName('oracle.as.soainfra.config:Location=ESS_SOAServer_1,name=soa-infra,type=SoaInfraConfig,Application=soa-infra')
    cmdsFileSize = mbs.getAttribute(soaBean, 'MaxCMDSFileSize')
    print 'MaxCMDSFileSize: ',cmdsFileSize
    setCmdsFileSize = mbs.setAttribute(soaBean, Attribute('MaxCMDSFileSize',int(filesize)))
    disconnect()
    exit()

except:
    print 'Failed to change Max CMD file size: '
    traceback()
    pass

print '################################################################################################'

print ' Script invoked successfully '
exit()

print '*** Script Invocation : STOPPED ***'


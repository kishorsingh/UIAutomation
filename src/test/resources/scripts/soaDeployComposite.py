#Title           :soadeploy.py
#Description     :Python script to deploy SOA composite using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :17/06/2016
#Version         :1.0
#Usage           :~/wlst.sh pyscript.py soadeploy.py <SOA_SERVER> <USERNAME> <PASSWORD> <COMPOSITE_LOCATION>
#Notes           :This python scripts deploys all the composites listed below to a particular SOA server
#################################################################################################################

import sys
import os

from java.io import FileInputStream

# 


print '*** Script invocation : Starting ***'



soa_server = sys.argv[1]
username =  sys.argv[2]
password =  sys.argv[3]
composite_name = sys.argv[4]
print '##############################################################################################'
print '####################################### Composite Deployment #################################'
print '##############################################################################################'
print 'composite_name ' + composite_name
print 'soa_server ' + soa_server
print 'username ' + username
print 'password ' + password
print '##############################################################################################'
print 'Starting  to deploy: ' + composite_name
try:
        sca_deployComposite(soa_server,composite_name,true,username,password,true)

except:
    print 'Failed to deploy: ' + composite_name
    traceback()
    pass

print '################################################################################################'

print 'Composite deployed successfully'
exit()

print '*** Script Invocation : STOPPED ***'


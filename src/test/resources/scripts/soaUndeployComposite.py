#Title           :soaundeploy.py
#Description     :Python script to undeploy SOA composite using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :17/06/2016
#Version         :1.0
#Usage           :~/wlst.sh soaundeploy.py <SOA_SERVER> <USERNAME> <PASSWORD> <COMPOSITE_NAME> <VERSION>
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
composite_version = sys.argv[5]
print '##############################################################################################'
print '#				       Composite Undeployment 				    #'
print '##############################################################################################'
print '#		composite_name ' + composite_name					
print '#		soa_server ' + soa_server
print '#		username ' + username
print '#		password ' + password
print '#		composite_version ' + composite_version
print '##############################################################################################'
print 'Starting  to undeploy: ' + composite_name
try:
        sca_undeployComposite(soa_server,composite_name,composite_version,username,password)

except:
    print 'Failed to undeploy: ' + composite_name
    traceback()
    pass

print '################################################################################################'

print 'Composite undeployed successfully'
exit()

print '*** Script Invocation : STOPPED ***'


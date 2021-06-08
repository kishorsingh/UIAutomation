#Title           :soaexportcomposite.py
#Description     :Python script to export SOA composite using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :17/06/2016
#Version         :1.0
#Usage           :~/wlst.sh soaexportcomposite.py <SOA_SERVER> <UPDATE_TYPE> <SAR_FILE> <COMPOSITE_NAME> <VERSION>
#Notes           :This python scripts exports required verion of composite to particular location
################################################################################################################

import sys
import os

from java.io import FileInputStream

# 


print '*** Script invocation : Starting ***'


soa_server = sys.argv[1]
updateType = sys.argv[2]
sarFile = sys.argv[3]
compositeName = sys.argv[4]
revision = sys.argv[5]
partition = "partition='default'"
print '##############################################################################################'
print '####################################### Exporting Composite  #################################'
print '##############################################################################################'
print 'compositeName  ' + compositeName 
print 'soa_server ' + soa_server
print 'updateType ' + updateType
print 'sarFile ' + sarFile
print 'compositeName ' + compositeName
print 'revision ' + revision
print 'partition ' + partition
print '##############################################################################################'
print 'Starting  to export: ' + compositeName 
try:
        sca_exportComposite(soa_server,updateType,sarFile,compositeName,revision,partition='default')

except:
    print 'Failed to export: ' + compositeName 
    print ' '+traceback()
    pass

print '################################################################################################'

print 'Composite exported successfully'
exit()

print '*** Script Invocation : STOPPED ***'


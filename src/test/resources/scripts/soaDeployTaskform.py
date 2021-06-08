#Title           :soadeploytaskform.py
#Description     :Python script to deploy task form using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :17/06/2016
#Version         :1.0
#Usage           :~/wlst.sh soadeploytaskform.py <USERNAME> <PASSWORD> <TASK_FORM_NAME> <TASK_FORM_LOCATION> <ADMIN_SERVER>
#Notes           :This python scripts exports required verion of composite to particular location
################################################################################################################

import sys
import os

from java.io import FileInputStream

# 


print '*** Script invocation : Starting ***'



username =  sys.argv[1]
password =  sys.argv[2]
task_form_name = sys.argv[3]
task_form_path = sys.argv[4]
admin_server_url = sys.argv[5]
print '##############################################################################################'
print '####################################### Task Form Deployment #################################'
print '##############################################################################################'
#print 'task_form_path ' + task_form_path
#print 'soa_server ' + soa_server
print 'username ' + username
print 'password ' + password
print 'task_form_name ' + task_form_name
print '##############################################################################################'
print 'Starting  to deploy task form : ' + task_form_name
try:
	connect(username,password,admin_server_url, adminServerName='AdminServer' )
	deploy(task_form_name,task_form_path, targets='soa_server1')

except:
    print 'Failed to deploy task form : ' + task_form_name
    traceback()
    pass

print '################################################################################################'

print 'Task form deployed successfully'
exit()

print '*** Script Invocation : STOPPED ***'


#Title           :soaundeploytaskform.py
#Description     :Python script to undeploy task form using WLST.
#Author          :Ashwani Raj(ashwani.raj@oracle.com)
#Date            :17/06/2016
#Version         :1.0
#Usage           :~/wlst.sh sounadeploytaskform.py <USERNAME> <PASSWORD> <TASK_FORM_NAME> <ADMIN_SERVER>
#Notes           :This python scripts deploys required task form to server
################################################################################################################

import sys
import os

from java.io import FileInputStream

# 


print '*** Script invocation : Starting ***'


print 'sys.argv[1] '+sys.argv[1]
print 'sys.argv[2] '+sys.argv[2]
username =  sys.argv[1]
password =  sys.argv[2]
task_form_name = sys.argv[3]
admin_server_url = sys.argv[4]
print '##############################################################################################'
print '####################################### Task Form Undeployment ###############################'
print '##############################################################################################'
#print 'task_form_path ' + task_form_path
#print 'soa_server ' + soa_server
print 'username ' + username
print 'password ' + password
print 'task_form_name ' + task_form_name
print '##############################################################################################'
print 'Starting  to undeploy task form : ' + task_form_name
try:
	connect(username,password,admin_server_url, adminServerName='AdminServer' )
	undeploy(task_form_name)

except:
    print 'Failed to undeploy task form : ' + task_form_name
    traceback()
    pass

print '################################################################################################'

print 'Task form undeployed successfully'
exit()

print '*** Script Invocation : STOPPED ***'


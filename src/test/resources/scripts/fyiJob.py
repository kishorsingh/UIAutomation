#Title           :fyiJob.py
#Description     :Python script to change FyiDismissalJobFrequency and FyiTaskLifeTime using WLST
#Author          :Pragathi
#Date            :08/02/2018
#Version         :1.0
#Usage           :~/wlst.sh fyiJob.py <ADMIN_SERVER> <USERNAME> <PASSWORD>
#Notes           :This python script changes FyiDismissalJobFrequency and FyiTaskLifeTime
#################################################################################################################

import sys
import os

from java.io import FileInputStream
print '*** Script invocation : Starting ***'
admin_server = sys.argv[1]
username =  sys.argv[2]
password =  sys.argv[3]
print 'admin_server ' + admin_server
print 'username ' + username
print 'password ' + password
print '##############################################################################################'
print 'Starting to change FyiDismissalJobFrequency and FyiTaskLifeTime:  '
try:
   connect(username, password,admin_server)
   domainRuntime()
   soaBean = ObjectName('oracle.as.soainfra.config:Location=ESS_SOAServer_1,name=human-workflow,type=WorkflowConfig,Application=soa-infra')
   valueFyiJobFrequency = mbs.getAttribute(soaBean, 'FyiDismissalJobFrequency')
   valueFyiTaskLifetime = mbs.getAttribute(soaBean, 'FyiTaskLifeTime')
   print 'FyiDismissalJobFrequency: ',valueFyiJobFrequency
   print 'FyiTaskLifeTime: ',valueFyiTaskLifetime
   setvalueFyiJobFrequency = mbs.setAttribute(soaBean, Attribute('FyiDismissalJobFrequency',"0 0/1 * * * ?"))
   setvalueFyiTaskLifetime = mbs.setAttribute(soaBean, Attribute('FyiTaskLifeTime',"PT3M"))
   disconnect()
   exit()

except:
    print 'Failed to change FyiDismissalJobFrequency and FyiTaskLifeTime values'
    traceback()
    pass

print '################################################################################################'

print ' Script invoked successfully '
exit()

print '*** Script Invocation : STOPPED ***'


/* Copyright (c) 2018, Oracle and/or its affiliates.
All rights reserved.*/

/*
   DESCRIPTION
    <short description of component this file declares/defines>

   PRIVATE CLASSES
    <list of private classes defined - with one-line descriptions>

   NOTES
    <other useful comments, qualifications, etc.>

    MODIFIED   (MM/DD/YY)
 */

package com.oracle.fa.qa.selenium.component.bpm.common;

import com.jcraft.jsch.*;
import com.oracle.fa.qa.selenium.component.util.CryptoUtil;
import com.oracle.mcs.qa.selenium.framework.config.FrameworkContext;
import com.oracle.mcs.qa.selenium.framework.report.StepReport;

import java.io.File;


/**
 * SOACompositeAction is a class to execute a command on a given host machine. *
 * The command is built using {@link CommandBuilder}.
 *
 * @author ashwaraj
 *
 * Revisions: amranaya (7/27/2018) - Added copyCompositeFromRepository
 */

public class SOACompositeAction {

    /**
     * CommandBuilder
     */
    CommandBuilder builder = null;
    /**
     * CommandExecutor
     */
    CommandExecutor executor = null;
    /**
     * ExecutionResult
     */
    ExecutionResult result = null;
    /**
     * Current host name.
     */
    private String hostName = null;
    /**
     * Current username.
     */
    private String username = null;
    /**
     * Current password
     */
    private String password = null;
    /**
     * Script Location on remote host
     */
    private String scriptLoc = null;
    /**
     * Script Location on remote host
     */
    private String serverURL = null;
    /**
     * server port
     */
    private String port = null;
    /**
     * application username
     */
    private String appUsername = null;
    /**
     * application password
     */
    private String appPassword = null;

    public SOACompositeAction() {
        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        System.out.println("Hostname = " + hostName);
        System.out.println("Username = " + username);
        System.out.println("Password = " + password);
        System.out.println("ScriptLoc = " + scriptLoc);
        System.out.println("AppUsername = " + appUsername);
        System.out.println("AppPassword = " + appPassword);
    }

    /**
     * Deploy SOA composite on FMW Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc         wlst location on FMW host.
     * @param soaCompositeLoc SOA composite location on network.
     */
    public void soaDeployComposite(String wlstLoc, String soaCompositeLoc) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        String soaDeployScriptLoc = scriptLoc.concat("soaDeployComposite.py");

        if (wlstLoc == null || soaDeployScriptLoc == null || soaCompositeLoc == null || hostName == null
                || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaDeployScriptLoc).add(serverURL).add(appUsername).add(appPassword).add(soaCompositeLoc);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
    }

    /**
     * Undeploy SOA composite on FMW Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc             wlst location on FMW host.
     * @param soaCompositeName    SOA composite name to be undeployed.
     * @param soaCompositeVersion SOA composite version to be undeployed.
     */
    public void soaUnDeployComposite(String wlstLoc, String soaCompositeName, String soaCompositeVersion) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        String soaUnDeployScriptLoc = scriptLoc.concat("soaUndeployComposite.py");

        if (wlstLoc == null || soaUnDeployScriptLoc == null || soaCompositeName == null || soaCompositeVersion == null
                || hostName == null || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaUnDeployScriptLoc).add(serverURL).add(appUsername).add(appPassword).add(soaCompositeName).add(soaCompositeVersion);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
    }

    /**
     * Deploy SOA composite with custom task form on FMW Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc                 wlst location on FMW host.
     * @param soaCompositeLoc         SOA composite location on network.
     * @param taskFormName            Task form name to be deployed.
     * @param soaCompositeTaskFormLoc Task form location on network.
     */
    public void soaDeployCompositeWithTaskForm(String wlstLoc, String soaCompositeLoc, String taskFormName, String soaCompositeTaskFormLoc) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.adminport");

        serverURL = "t3://".concat(hostName).concat(":").concat(port);
        String soaDeployScriptLoc = scriptLoc.concat("soaDeployComposite.py");
        String soaDeployTaskFormScriptLoc = scriptLoc.concat("soaDeployTaskform.py");

        if (wlstLoc == null || soaDeployScriptLoc == null || soaCompositeLoc == null || soaCompositeTaskFormLoc == null
                || taskFormName == null || hostName == null || username == null || password == null || scriptLoc == null
                || appUsername == null || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaDeployTaskFormScriptLoc).add(appUsername).add(appPassword).add(taskFormName)
                .add(soaCompositeTaskFormLoc).add(serverURL);
        System.out.println(builder);
        executor = new CommandExecutor(hostName, username, password);
        result = executor.executeRemote(builder);
        System.out.println(result);

        soaDeployComposite(wlstLoc, soaCompositeLoc);
    }

    /**
     * Undeploy SOA composite with custom task form on FMW Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc             wlst location on FMW host.
     * @param soaCompositeName    SOA composite name to be undeployed.
     * @param soaCompositeVersion SOA composite version to be undeployed.
     * @param taskFormName        Task form name to be undeployed.
     */
    public void soaUnDeployCompositeWithTaskForm(String wlstLoc, String soaCompositeName, String soaCompositeVersion, String taskFormName) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.adminport");
        serverURL = serverURL = "t3://".concat(hostName).concat(":").concat(port);
        String soaUnDeployScriptLoc = scriptLoc.concat("soaUndeployComposite.py");
        String soaUnDeployTaskFormScriptLoc = scriptLoc.concat("soaUndeployTaskform.py");

        if (wlstLoc == null || soaUnDeployScriptLoc == null || soaCompositeName == null || soaCompositeVersion == null
                || hostName == null || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaUnDeployTaskFormScriptLoc).add(appUsername).add(appPassword).add(taskFormName)
                .add(serverURL);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);

        soaUnDeployComposite(wlstLoc, soaCompositeName, soaCompositeVersion);
    }

    /**
     * Deploy SOA composite on FA Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc        wlst location on FMW host.
     * @param faCompositeLoc SOA composite location on network.
     */
    public void faDeployComposite(String wlstLoc, String faCompositeLoc) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        String soaDeployScriptLoc = scriptLoc.concat("soaDeployComposite.py");

        if (wlstLoc == null || soaDeployScriptLoc == null || faCompositeLoc == null || hostName == null
                || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaDeployScriptLoc).add(serverURL).add(appUsername).add(appPassword).add(faCompositeLoc);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
    }

    /**
     * Undeploy SOA composite on FA Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc            wlst location on FMW host.
     * @param faCompositeName    SOA composite location on network.
     * @param faCompositeVersion SOA composite version.
     */
    public void faUnDeployComposite(String wlstLoc, String faCompositeName, String faCompositeVersion) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        String faUnDeployScriptLoc = scriptLoc.concat("soaUndeployComposite.py");

        if (wlstLoc == null || faUnDeployScriptLoc == null || faCompositeName == null || hostName == null
                || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(faUnDeployScriptLoc).add(serverURL).add(appUsername).add(appPassword).add(faCompositeName).add(faCompositeVersion);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
    }

    /**
     * Export SOA composite on FMW Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc             wlst location on FMW host.
     * @param updateType          Type of postdeployment changes to be exported:
     *                            <p>
     *                            all: Includes all postdeployment changes.
     *                            <p>
     *                            property: Includes only property postdeployment changes (binding component properties, composite properties such as audit level settings and payload validation status, and policy attachments).
     *                            <p>
     *                            runtime: Includes only runtime (rules dictionary and domain value maps (DVMs)) and metadata postdeployment changes.
     *                            <p>
     *                            none: Exports the original composite without any postdeployment changes (including property changes and runtime changes)
     * @param soaSarFile          Absolute path of a SAR file to generate (a .jar file that begins with sca_).
     * @param soaCompositeName    Name of the composite to export.
     * @param soaCompositeVersion Revision of the composite to export.
     */
    public void soaExportComposite(String wlstLoc, String updateType, String soaSarFile, String soaCompositeName, String soaCompositeVersion) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);
        String soaExportScriptLoc = scriptLoc.concat("soaExportComposite.py");

        if (wlstLoc == null || soaExportScriptLoc == null || updateType == null || soaSarFile == null
                || soaCompositeName == null || soaCompositeVersion == null || hostName == null || username == null
                || password == null || scriptLoc == null || appUsername == null || appPassword == null || port == null
                || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(soaExportScriptLoc).add(serverURL).add(updateType).add(soaSarFile).add(soaCompositeName).add(soaCompositeVersion);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        String[] input = new String[2];
        input[0] = appUsername;
        input[1] = appPassword;
        result = executor.executeRemote(builder, input);
        System.out.println(result);
    }

    /**
     * Export SOA composite on FA Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc            wlst location on FMW host.
     * @param updateType         Type of postdeployment changes to be exported:
     *                           <p>
     *                           all: Includes all postdeployment changes.
     *                           <p>
     *                           property: Includes only property postdeployment changes (binding component properties, composite properties such as audit level settings and payload validation status, and policy attachments).
     *                           <p>
     *                           runtime: Includes only runtime (rules dictionary and domain value maps (DVMs)) and metadata postdeployment changes.
     *                           <p>
     *                           none: Exports the original composite without any postdeployment changes (including property changes and runtime changes)
     * @param faSarFile          Absolute path of a SAR file to generate (a .jar file that begins with sca_).
     * @param faCompositeName    Name of the composite to export.
     * @param faCompositeVersion Revision of the composite to export.
     */
    public void faExportComposite(String wlstLoc, String updateType, String faSarFile, String faCompositeName, String faCompositeVersion) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        String faExportCompositeScriptLoc = scriptLoc.concat("soaExportComposite.py");

        if (wlstLoc == null || faExportCompositeScriptLoc == null || faCompositeName == null || hostName == null
                || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(faExportCompositeScriptLoc).add(serverURL).add(updateType).add(faSarFile).add(faCompositeName).add(faCompositeVersion);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        String[] input = new String[2];
        input[0] = appUsername;
        input[1] = appPassword;
        result = executor.executeRemote(builder, input);
        System.out.println(result);
    }

    /**
     * Copy required files on FA Host using CommandBuilder and CommandExecutor module.
     */
    public void copyFilesToRemoteHost() {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = "src/test/resources/scripts//*";
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        if (hostName == null || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        executor = new CommandExecutor(hostName, username, password);
        boolean doesFileExists = executor.existsFile("/u01/UI_SCRIPT_DIR");
        System.out.println("File exists on remote location " + doesFileExists);
        if (doesFileExists)
            executor.putFile(scriptLoc, "/u01/UI_SCRIPT_DIR");
        else
            System.out.println("ERROR : File could not be found on remote host");
    }

    /**
     * Make directory on FA Host using CommandBuilder and CommandExecutor module.
     */
    public void makeDirOnRemoteHost() {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        if (hostName == null || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        executor = new CommandExecutor(hostName, username, password);
        boolean doesDirExists = executor.existsDir("/u01/UI_SCRIPT_DIR");
        System.out.println("Dir exists on remote location " + doesDirExists);
        if (!doesDirExists)
        executor.mkdir("/u01/UI_SCRIPT_DIR");
    }

    /**
     * change Max CMD file size using WLST on FA Host using CommandBuilder and CommandExecutor module.
     *
     * @param wlstLoc wlst location on FMW host.
     * @param fileSize file size.
     */
    public int changeMaxCMDFileSize(String wlstLoc, String fileSize) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.adminport");
        serverURL = serverURL = "t3://".concat(hostName).concat(":").concat(port);
        String changeMaxCMDFileSizeScriptLoc = scriptLoc.concat("changeMaxCMDFileSize.py");

        if (wlstLoc == null || fileSize == null || hostName == null || username == null || password == null || scriptLoc == null
                || appUsername == null || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(changeMaxCMDFileSizeScriptLoc).add(serverURL).add(appUsername).add(appPassword).add(fileSize);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
        System.out.println(result.getExitStatus());
        return result.getExitStatus();
    }

    /**
     * Delete directory on FA Host using CommandBuilder and CommandExecutor module.
     */
    public void deleteDirOnRemoteHost() {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.soaport");
        serverURL = "http://".concat(hostName).concat(":").concat(port);

        if (hostName == null || username == null || password == null || scriptLoc == null || appUsername == null
                || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        executor = new CommandExecutor(hostName, username, password);
        executor.rmdir("/u01/UI_SCRIPT_DIR");
    }

    /**
     * Close all secure SSH sessions.
     */
    public void closeAllSession() {
        System.out.println("Cloasing all secure connections");
        CommandExecutor.closeAllSecureSessions();
    }


    public void copyCompositeFromRepository(String compositeName) throws Exception {
        String hostName = "slc15bue.us.oracle.com";
        String userName = "amranaya";
        String encryptedPwd = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nis.encrypted.pwd");
        final String KEY_FILE = "src/test/resources/scripts/pwd.key";
        String password = CryptoUtil.decrypt(encryptedPwd, new File(KEY_FILE));
        String copyFrom = "/scratch/akasturi/automation/fa/composite/"+compositeName+"";
        String copyTo = "src/test/resources/scripts";
        executor = new CommandExecutor(hostName, userName, password);
        executor.getFile(copyFrom,copyTo);
    }

    public int changeFyiValues(String wlstLoc ) {

        hostName = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.hostname");
        username = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nisusername");
        password = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.nispassword");
        scriptLoc = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.scriptLoc");
        appUsername = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.username");
        appPassword = FrameworkContext.getInstance().getTestConfigParams().getString("test.admin.password");
        port = FrameworkContext.getInstance().getTestConfigParams().getString("test.base.adminport");
        serverURL = serverURL = "t3://".concat(hostName).concat(":").concat(port);
        String fyiJobLoc = scriptLoc.concat("fyiJob.py");

        if (wlstLoc == null || hostName == null || username == null || password == null || scriptLoc == null
                || appUsername == null || appPassword == null || port == null || serverURL == null) {
            System.out.printf("Error : Missing required argument");
            StepReport.fail("Error : Missing required argument");
        }
        builder = new CommandBuilder();
        builder.add(wlstLoc).add(fyiJobLoc).add(serverURL).add(appUsername).add(appPassword);
        executor = new CommandExecutor(hostName, username, password);
        System.out.println(builder);
        result = executor.executeRemote(builder);
        System.out.println(result);
        System.out.println(result.getExitStatus());
        return result.getExitStatus();
    }


}
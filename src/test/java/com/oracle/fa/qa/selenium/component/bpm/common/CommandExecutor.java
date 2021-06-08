/* Copyright (c) 2018, Oracle and/or its affiliates.
All rights reserved.*/

/*
   DESCRIPTION
    CommandExecutor executes the command built using CommandBuilder

   PRIVATE CLASSES
    None

   NOTES
    None

    MODIFIED   (07/17/2018)
 */

package com.oracle.fa.qa.selenium.component.bpm.common;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * CommandExecutor is an utility to execute a command on a given host machine. *
 * The command is built using {@link CommandBuilder}.
 *
 * @author ashwaraj
 * @owner  ashwaraj
 *
 * Revisions: amranaya (7/27/2018) - Added getfile
 */
public class CommandExecutor {
    /**
     * Current host name.
     */
    private String hostName = null;

    /**
     * Current username.
     */
    private String username = null;

    /**
     * Current host name.
     */
    private String password = null;

    /**
     * Current session
     */
    private Session session = null;

    /**
     *
     */
    private boolean isRemote = false;

    /**
     * Logger object.
     */
    private Logger logger = Logger.getLogger("myLog");

    /**
     * Map of host names to corresponding secure session objects.
     */
    private static Hashtable<String, Session> mapHostSecureSession = new Hashtable<String, Session>();

    public static final String LINESEP = System.getProperty("line.separator");

    /**
     * Execute the command on remote host.
     *
     * @param builder Command builder encapsulating the command.
     * @param input   Strings having input redirection (if any).
     * @return An object of type {@link ExecutionResult}, encapsulating the result of command execution.
     */
    public ExecutionResult executeRemote(CommandBuilder builder, String input[]) {
        ExecutionResult result = null;

        try {
            ChannelExec channel = (ChannelExec) session.openChannel("exec");
            channel.setErrStream(System.err);

            InputStream in = channel.getInputStream();
            InputStream err = channel.getExtInputStream();

            channel.setCommand(builder.toString());
            channel.connect();

            String mesgOutput = "", mesgError = "";
            while (!channel.isClosed()) {
                if (input != null)
                    writeContent(channel.getOutputStream(), input);

                mesgOutput = mesgOutput + readContent(in);
                mesgError = mesgError + readContent(err);
            }
            result = new ExecutionResult(channel.getExitStatus(), mesgOutput, mesgError);
            in.close();
            err.close();
            channel.disconnect();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error executing remote command", e);
        }
        return result;
    }


    public ExecutionResult executeRemote(CommandBuilder builder){
        return executeRemote(builder,null);

    }
    /**
     * Write contents in <b>input</b> to output stream <b>outputStream</b>
     *
     * @param outputStream An output stream.
     * @param input        Array of stings.
     */
    public static void writeContent (OutputStream outputStream, String input[]) throws IOException
    {
        PrintWriter out = new PrintWriter (new OutputStreamWriter(outputStream));
        for (String currInput : input)
            out.println (currInput);
        out.close();
    }

    /**
     * Read content from a given input stream and return read content.
     *
     * @param inputStream Stream to be read input from
     * @return Content read from @{code inputStream}
     */
    public static String readContent (InputStream inputStream) throws IOException
    {
        BufferedReader in  = new BufferedReader(new InputStreamReader(inputStream));

        StringBuilder bufferOutput = new StringBuilder ();

        String currLine = null;
        while ((currLine = in.readLine()) != null)
            bufferOutput.append(currLine).append(LINESEP);

        return bufferOutput.toString();
    }


    /**
     * Create a command executor instance for executing commands in <b>hostName</b>.
     *
     * @param hostName Host name where the command has to be executed.
     * @param logger Logger object to be used.
     */
    public CommandExecutor (String hostName, Logger logger)
    {
        this.hostName = hostName;
        this.logger = logger;
        initSecureSession ();
    }

    public CommandExecutor (String hostName, String username, String password)
    {
        this.hostName = hostName;
        this.username = username;
        this.password = password;
        initSecureSession ();
    }

    /**
     * Initialize a secure session.
     *
     * If the host is NOT remote then there is no session initialization.
     * If the host is remote and not present in {@link #mapHostSecureSession},
     * initialize and add session to {@link #mapHostSecureSession}.
     */
    private void initSecureSession ()
    {

        if (mapHostSecureSession.containsKey(hostName))
        {
            session = mapHostSecureSession.get(hostName);
            return;
        }

        try
        {
            JSch jsch = new JSch();
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session = jsch.getSession(username, hostName, 22);
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
            System.out.println("session is created");
        }
        catch (JSchException e)
        {
            StringBuilder buffer = new StringBuilder ();
            buffer.append ("Error establishing secure session for remote command execution.")
                    .append ("   - Verify if NISUserName/NISPassword properties are set in Test.properties.")
                    .append ("   - Verify if values for NISUserName/NISPassword are accurate.");
        }
        mapHostSecureSession.put(hostName, session);
    }

    /**
     * Copy the file from local <b>pathSource</b> to <b>pathDestination</b> on remote host.
     *
     * @param pathSource       Path to local source file.
     * @param pathDestination  Path to remote destination file.
     */
    public void putFile (String pathSource, String pathDestination)
    {
        try
        {
            System.out.println("Remote copy initiated.");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put (pathSource, pathDestination, ChannelSftp.OVERWRITE);
            channel.disconnect();
            System.out.println("Remote copy completed");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IllegalStateException ("Error copying " + pathSource + " to " + pathDestination, e);
        }
    }

    /**
     * Create directory <b>pathDir</b>
     * @param pathDir
     */
    public void mkdir (String pathDir)
    {
        try
        {
            System.out.println("Remote directory creation initiated.");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.mkdir(pathDir);
            channel.disconnect();
            System.out.println("Remote directory creation completed.");
        }
        catch (Exception e)
        {
            throw new IllegalStateException ("Error creating directory " + pathDir, e);
        }
    }


    @SuppressWarnings("rawtypes")
    public boolean existsFile (String path)
    {
        try
        {
            System.out.println("Checking if script file exists");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            Vector result = sftpChannel.ls (path);
            channel.disconnect();

            return (result.size() == 0) ? false : true;
        }
        catch (Exception e)
        {
            throw new IllegalStateException ("Error listing " + path, e);
        }
    }

    @SuppressWarnings("rawtypes")
    public boolean existsDir (String path)
    {
        try
        {
            System.out.println("Checking if script dir exists");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd (path);
            channel.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            return false;
        }
        return true;
    }

    public void rmdir (String pathDir)
    {
        try
        {
            System.out.println("Remote directory deletion initiated.");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.cd(pathDir);
            Vector<ChannelSftp.LsEntry> fileAndFolderList = sftpChannel.ls(pathDir);
            for (ChannelSftp.LsEntry item : fileAndFolderList) {
                if (!item.getAttrs().isDir()) {
                    sftpChannel.rm(pathDir + "/" + item.getFilename());
                } else if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {
                    try {

                        sftpChannel.rmdir(pathDir + "/" + item.getFilename());
                    } catch (Exception e) {
                        throw new IllegalStateException ("Error listing directory " + pathDir, e);
                    }
                }
            }

            sftpChannel.rmdir(pathDir);


            channel.disconnect();
            System.out.println("Remote directory deletion completed.");
        }
        catch (Exception e)
        {
            throw new IllegalStateException ("Error deleting directory " + pathDir, e);
        }
    }
    /**
     * Close the SSH session
     * <b><font color="red">Note:</b>
     *   This will be called by the driver test suite. So do NOT include or call in your test case code.
     * </font>
     */
    public static void closeAllSecureSessions ()
    {
        for (Session currSession : mapHostSecureSession.values())
            currSession.disconnect();
    }

    public void getFile(String copyFrom, String copyTo)
    {
        try
        {
            System.out.println("File copy of "+copyFrom+" to "+copyTo+" initiated");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.get(copyFrom, copyTo);
            sftpChannel.exit();
            session.disconnect();
            System.out.println("File copy completed.");
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }
}

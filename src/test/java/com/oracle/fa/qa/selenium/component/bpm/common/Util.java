package com.oracle.fa.qa.selenium.component.bpm.common;

/* Copyright (c) 2018, Oracle and/or its affiliates.
All rights reserved.*/

/*
 DESCRIPTION
 This class provides generic utility functions.

 PRIVATE CLASSESNone<list of private classes defined - with one-line descriptions>

 NOTES
 <other useful comments, qualifications, etc.>

 */


import java.io.*;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import java.util.*;
import org.apache.commons.io.FileUtils;

/**
 * This class provides generic utility functions.
 *
 * @author ashwaraj
 * @owner ashwaraj
 */
public class Util
{
    /**
     * Black
     */
    public static final String COLOR_BLACK = "\u001B[30m";

    /**
     * Red
     */
    public static final String COLOR_RED = "\u001B[31m";

    /**
     * Green
     */
    public static final String COLOR_GREEN = "\u001B[32m";

    /**
     * Yellow
     */
    public static final String COLOR_YELLOW = "\u001B[33m";

    /**
     * Blue
     */
    public static final String COLOR_BLUE = "\u001B[34m";

    /**
     * Purple
     */
    public static final String COLOR_PURPLE = "\u001B[35m";

    /**
     * Cyan
     */
    public static final String COLOR_CYAN = "\u001B[36m";

    private Util()
    {

    }

    /**
     * Line separator that matches both Unix and Windows styles.
     */
    public static final String lineSepRegexWinUnix = "(\r)?\n";

    /**
     * Line separator
     */
    public static final String lineSep = System.getProperty("line.separator");

    /**
     * File separator
     */
    public static final String fileSep = File.separator;

    /**
     * Path separator
     */
    public static final String parthSep = File.pathSeparator;

    /**
     * Concatenate the Strings in the {@code list} using {@code delim}
     *
     * @param list Strings to be concatenated.
     * @param delim Delimiter
     * @return Resultant concatenated string.
     */
    public static String join(List<String> list, String delim)
    {
        return Util.join(list.toArray(new String[0]), delim);
    }

    /**
     * Concatenate the {@code num} using delimiter {@code delim}.
     *
     * @param num Array of numbers.
     * @param delim Delimiter
     * @return Resultant string after concatenation.
     */
    public static String join(int num[], String delim)
    {
        StringBuilder builder = new StringBuilder();
        int lastIndex = num.length - 1;
        for (int index = 0; index < lastIndex; ++index)
            builder.append(num[index]).append(delim);
        builder.append(num[lastIndex]);
        return builder.toString();
    }

    /**
     * Concatenate the Strings in the array {@code token} using {@code delim}
     *
     * @param token Strings to be concatenated.
     * @param delim Delimiter
     * @return Resultant concatenated string.
     */
    public static String join(String token[], String delim)
    {
        if (token == null || token.length == 0)
            return "";

        StringBuilder builder = new StringBuilder();
        int lastIndex = token.length - 1;
        for (int index = 0; index < lastIndex; ++index)
            builder.append(token[index]).append(delim);
        builder.append(token[lastIndex]);
        return builder.toString();
    }

    /**
     * Cut the string into two halves based on the first occurrence of the {@code delim}.
     * Trim both the halves and return them in an array.
     *
     * @param str String to be cut
     * @param delim Delimiter that determines where to cut.
     * @return Two halves cut near {@code delim} and trimmed.
     */
    public static String[] cut(String str, char delim)
    {
        int index = str.indexOf(delim);
        if (index == -1)
            return null;

        String firstHalf = str.substring(0, index).trim();
        String secondHalf = str.substring(index + 1).trim();

        return new String[] { firstHalf, secondHalf };
    }

    /**
     * Read lines from a file and return them.
     *
     * @param file File to read from.
     * @return A list of lines read.
     * @throws IOException
     */
    public static List<String> getLines(File file) throws IOException
    {
        List<String> listLine = new ArrayList<String>();
        BufferedReader in = new BufferedReader(new FileReader(file));
        String currLine = null;
        while ((currLine = in.readLine()) != null)
            listLine.add(currLine);
        in.close();
        return listLine;
    }

    /**
     * Return File content as string.
     *
     * @param file
     *           file to be read.
     * @return return the File content as string.
     */

    public static String readFile(File file)
    {

        String str = null;
        String fileData = "";

        try
        {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while ((str = in.readLine()) != null)
            {
                fileData += str + "\n";
            }
            in.close();
        }
        catch (IOException e)
        {
            throw new IllegalStateException(e);
        }

        return fileData;

    }

    /**
     * Sleeps for number of seconds specified.
     *
     * @param sleepTime number of seconds to sleep.
     */
    public static void sleep(int sleepTime)
    {
        try
        {
            Thread.sleep(1000 * sleepTime);
        }
        catch (Exception e)
        {
            throw new IllegalStateException ("Interuppted during sleep");
        }
    }

    /**
     * Current thread sleeps for {@code milli} seconds.
     *
     * @param sleepTime Time in milliseconds.
     */
    public static void sleepInMilli(long sleepTime)
    {
        try
        {
            Thread.sleep(sleepTime);
        }
        catch (InterruptedException e)
        {
            throw new IllegalStateException ("Interuppted during sleep");
        }
    }

    /**
     * @param filename Filename to load properties from
     * @return Load properties from a file and return them.
     * @throws IOException
     */
    public static Properties loadProperties(String filename) throws IOException
    {
        return loadProperties(new File(filename));
    }

    /**
     * @param file File to load properties from
     * @return Load properties from a file and return them.
     * @throws IOException
     */
    public static Properties loadProperties(File file) throws IOException
    {
        Properties prop = new Properties();
        FileReader in = new FileReader(file);
        prop.load(in);
        in.close();
        return prop;
    }

    /**
     * Recursively delete contents of file/directory - {@code file}. Inclusive of {@code file}.
     *
     * @param file The file/directory whose contents needs to be deleted.
     * @return true, if deletion is successful.
     * @throws IOException
     */
    public static void recurDelete (File file) throws IOException
    {
        FileUtils.deleteDirectory(file);
    }

    /**
     * {@code arg[index]} is expected to have the option switch for which value
     * needs to be retrieved. If {@code (index+1)} is NOT within the argument boundary
     * an exception is thrown, else the switch value is returned.
     *
     * @param arg Array of arguments.
     * @param index Index of the switch
     * @return Value of the switch.
     */
    public static String getSwitchValue(String arg[], int index)
    {
        if (index + 1 >= arg.length)
            throw new IllegalArgumentException("Option " + arg[index] + " needs a value");
        return arg[index + 1];
    }

    /**
     * Convert a 2 dimensional array to String;
     *
     * @param arr
     * @return String representation of 2 dimentional array.
     */
    public static String toString(int arr[][])
    {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < arr.length; ++i)
            buffer.append(Arrays.toString(arr[i])).append(Util.lineSep);
        return buffer.toString();
    }

    /**
     * Convert an array of Strings with integral values to an integer array.
     *
     * @param token
     * @return
     */
    public static int[] toIntArray(String token[])
    {
        int value[] = new int[token.length];
        for (int i = 0; i < value.length; ++i)
            value[i] = new Integer(token[i]);
        return value;
    }

    /**
     * Convert an array of Strings with integral values to an integer array.
     *
     * @param token
     * @return
     */
    public static boolean[] toBooleanArray(String token[])
    {
        boolean value[] = new boolean[token.length];
        for (int i = 0; i < value.length; ++i)
        {
            if (token[i].equals("1"))
                value[i] = true;
            else if (token[i].equals("0"))
                value[i] = false;
            else
                throw new IllegalArgumentException(token[i] + " is not a valud boolean eqivalent. Should be (1|0)");
        }
        return value;
    }

    /**
     * @param value Array of integers
     * @return Summation of integers in <b>value</b>
     */
    public static int summation(int value[])
    {
        int sum = 0;
        for (int currValue : value)
            sum += currValue;
        return sum;
    }

    /**
     * Prints the message on the console.
     *
     * <br>
     * Example:
     *
     * <pre>
     * {@code
     * Util.print("hello world")                 //Prints in black color
     * Util.print("hello world", Util.COLOR_RED) //Prints in red color
     * }
     * </pre>
     *
     * Supported Colors:
     * <ul>
     * <li>COLOR_BLACK
     * <li>COLOR_RED
     * <li>COLOR_GREEN
     * <li>COLOR_YELLOW
     * <li>COLOR_BLUE
     * <li>COLOR_PURPLE
     * <li>COLOR_CYAN
     * </ul>
     *
     * @param message Message to be displayed.
     * @param option Display options
     */
    public static void print(String message, String... option)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(getFormattedDate());
        builder.append("] ");
        builder.append(message);

        //if (!TestProperties.ConsoleLogColors)
        System.out.println(builder.toString());

     /* if (option.length == 0)
         System.out.print(builder.toString());
      else
      {
         System.out.print(option[0] + builder.toString());
         System.out.println(COLOR_RESET);
      }
      */
    }

    public static void print(Logger logger, String message, String... option)
    {
        logger.info(message);
        print (message, option);
    }

    /**
     * @return Format <b>date</b> as per access-log format and return the resultant date.
     */
    public static String getFormattedDate (Date date)
    {
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        return ft.format(date);
    }

    /**
     * @return Current date as per access-log format.
     */
    public static String getFormattedDate ()
    {
        return getFormattedDate(new Date());
    }

    /**
     * Cut the {@code original} array by including elements from {@code beginIndex} to {@code endIndex}, both inclusive.
     *
     * @param original Original array
     * @param beginIndex Index to start slicing. (inclusive)
     * @param endIndex Index to end slicing. (inclusive)
     * @return The sliced array.
     */
    public static int[] slice(int original[], int beginIndex, int endIndex)
    {
        return Arrays.copyOfRange(original, beginIndex, endIndex + 1);
    }

    /**
     * Cut the {@code original} array by including elements from {@code beginIndex} to {@code endIndex}, both inclusive.
     *
     * @param original Original array
     * @param beginIndex Index to start slicing. (inclusive)
     * @param endIndex Index to end slicing. (inclusive)
     * @return The sliced array.
     */
    public static int[][] slice(int original[][], int beginIndex, int endIndex)
    {
        int arr[][] = new int[original.length][];

        for (int i = 0; i < arr.length; ++i)
            arr[i] = slice(original[i], beginIndex, endIndex);

        return arr;
    }



    /**
     * Writes strings in <b>token</b> separated by new line in <b>file</b>.
     *
     * @param content Array of strings
     * @param file File to write the strings into.
     */
    public static void writeToFile (Logger logger, File file, String content[]) //throws IOException
    {
        try
        {
            if (logger != null)
                logger.info(Arrays.toString(content));

            PrintWriter out = new PrintWriter(new FileWriter(file));
            for (String currToken : content)
                out.println(currToken);
            out.close();
        }
        catch (Exception e)
        {

        }
    }

    public static void writeToFile (File file, String content[]) //throws IOException
    {
        writeToFile (null, file, content);
    }

    public static void writeToFile (Logger logger, File file, String content)// throws IOException
    {
        writeToFile (logger, file, new String[] {content});
    }

    public static void writeToFile (File file, String content)// throws IOException
    {
        writeToFile (null, file, new String[] {content});
    }

    public static void writeToFile (Logger logger, String filePath, String content)// throws IOException
    {
        writeToFile (logger, new File(filePath), new String[] {content});
    }

    public static void writeToFile (String filePath, String content)// throws IOException
    {
        writeToFile (null, new File(filePath), new String[] {content});
    }

    public static void writeToFile(String filePath, String content[])// throws IOException
    {
        writeToFile (null, new File(filePath), content);
    }

    /**
     * If {@code logger} is specified, log message into the logger else log message using STDOUT.
     * The actual class and method which is calling this log method is inferred ( using a generated
     * Throwable or existing exception) and is used for logging.
     *
     * This is just a convenient method for logging exception and the caller is expected to take
     * appropriate action on the exception. Hence, this function throws back the exception.
     *
     * @param mesg Message to be logged.
     * @param e Exception to be logged.
     * @param logger Logger to log the exception into.
     * @throws Exception Throws the exception logged.
     */
    public static void logError(String mesg, Exception e, Logger logger) throws Exception
    {
        if (mesg == null)
            mesg = "Error occured during test run";

        if (e == null)
            e = new Exception(mesg);

        System.out.println(mesg);
        if (logger == null)
        {
            e.printStackTrace();
        }
        else
        {
            StackTraceElement stack[] = e.getStackTrace();
            String sourceClass = "Unknown class";
            String sourceMethod = "Unknown method";
            if (stack.length > 1)
            {
                sourceClass = stack[1].getClassName();
                sourceMethod = stack[1].getMethodName();
            }
            logger.logp(Level.SEVERE, sourceClass, sourceMethod, mesg, e);
            System.out.println("Refer logs for details");
        }

        throw e;
    }

    /**
     * Calls logError (mesg, null, logger);
     */
    public static void logError(String mesg, Logger logger) throws Exception
    {
        logError(mesg, null, logger);
    }

    /**
     * Calls logError ("Error: ", e, logger);
     */
    public static void logError(Exception e, Logger logger) throws Exception
    {
        logError("Error: ", e, logger);
    }

    /**
     * Calls logError (mesg, e, null);
     */
    public static void logError(String mesg, Exception e) throws Exception
    {
        logError(mesg, e, null);
    }

    /**
     * Calls logError (mesg, null, null);
     */
    public static void logError(String mesg) throws Exception
    {
        logError(mesg, null, null);
    }

    /**
     * Calls logError ("Error: ", e, null);
     */
    public static void logError(Exception e) throws Exception
    {
        logError("Error: ", e, null);
    }

    /**
     * To delete a file
     *
     * @param filePath String path of file to be deleted
     */
    public static boolean deleteFile(String filePath)
    {
        File file = new File(filePath);
        if (file.exists())
        {

            return file.delete();
        }

        return false;
    }


    /**
     * Log error message {@code mesg} and exit.
     *
     * @param mesg Error message
     */
    public static void die(String mesg)
    {
        die(mesg, null);
    }

    /**
     * Log error message {@code mesg}, exception {@code e} and exit.
     *
     * @param mesg Error message
     * @param e Exception
     */
    public static void die (String mesg, Throwable e)
    {
        if (mesg == null)
            mesg = "Error occured during test run";

        if (e == null)
            e = new Exception(mesg);

        System.out.println (mesg);
        e.printStackTrace();
        System.exit(1);
    }


    /**
     * To get the Freee ports.
     * @param rangeMin
     * @param rangeMax
     * @param count
     * @return
     */
    public static int[] getFreePorts (int rangeMin, int rangeMax, int count)
    {
        int currPortCount = 0;

        int port[] = new int [count];

        for (int currPort = rangeMin; currPortCount < count && currPort <= rangeMax; ++currPort)
        {
            if (isPortFree(currPort))
                port[currPortCount++] = currPort;
        }

        if (currPortCount < count)
            throw new IllegalStateException ("Could not find " + count + " free ports to allocate within range " +
                    rangeMin + "-" + rangeMax + ".");

        return port;
    }


    /**
     * Check if the current <b>port</b> is free ( Not bound by any process ).
     *
     * @param port The port that needs to be checked if free.
     * @return true if the port is free, false otherwise.
     */
    public static boolean isPortFree (int port)
    {
        ServerSocket socket = null;
        try
        {
            socket = new ServerSocket (port);
            socket.close();
        }
        catch (IOException e)
        {
            return false;
        }
        return true;
    }

    /**
     * Get the IP address of clent host name
     *
     * @author mseelam
     *
     * @return
     */
    public static String getIPAddress(String hostName)
    {
        String ip = null;
        try
        {
            InetAddress thisIp = InetAddress.getByName(hostName);
            ip = thisIp.getHostAddress();
            System.out.println("IP:" + ip);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getNetworkInterfaceName(String ipAddress)
    {
        try
        {
            InetAddress localHost = InetAddress.getByName(ipAddress);
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            for (InterfaceAddress address : networkInterface.getInterfaceAddresses())
            {
                if (address.getAddress().getHostAddress().equalsIgnoreCase(ipAddress))
                {
                    System.out.println(address.toString() +
                            "Network prefix:" + address.getNetworkPrefixLength() +
                            "Network name:" + networkInterface.getDisplayName() +
                            "getHostAddress:" + localHost.getHostAddress());
                    return networkInterface.getDisplayName();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNetworkPrefixLength(String hostName)
    {
        int length = 0;
        try
        {
            InetAddress localHost = InetAddress.getByName(hostName);
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
            for (InterfaceAddress address : networkInterface.getInterfaceAddresses())
            {
                if (address.getAddress().getHostName().equalsIgnoreCase(hostName))
                {
                    System.out.println(address.toString() +
                            "Network prefix:" + address.getNetworkPrefixLength() +
                            "Network name:" + networkInterface.getDisplayName() +
                            "getHostAddress:" + localHost.getHostAddress());
                    return address.getNetworkPrefixLength();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * Delete Folders and subfolders including files.
     * @param directory
     */
    public static void deleteDirectory(File directory, String... exceptions)
    {
        if (directory.exists())
        {
            File[] files = directory.listFiles();
            if (null != files)
            {
                for (int i = 0; i < files.length; i++)
                {
                    if (files[i].isDirectory())
                    {
                        System.out.println("Deleting:" + files[i].getAbsolutePath());
                        deleteDirectory(files[i]);
                    }
                    else
                    {
                        System.out.println(files[i].getName());
                        files[i].delete();
                    }
                }
            }
        }
    }

    public static String getStacktrace(Exception e)
    {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public static String getNormalizePath (String first, String... more)
    {
        if (first == null)
            return null;
        return Paths.get(first, more).normalize().toFile().getPath().replace("\\", "/");
    }
}

/* Copyright (c) 2018, Oracle and/or its affiliates.
All rights reserved.*/

/*
   DESCRIPTION
    CommandBuilder builds the command for execution

   PRIVATE CLASSES
    None

   NOTES
    None

   MODIFIED   (07/17/2018)

*/

/**
 *  @author  ashwaraj
 *
 */
package com.oracle.fa.qa.selenium.component.bpm.common;

import java.util.*;

/**
 * CommandBuilder is used to build arguments for a command.
 *
 * Once all the arguments are added, use the {@link #toString()} method
 * to get the String representation of the command.
 *
 * @author ashwaraj
 * @owner ashwaraj
 */
public class CommandBuilder
{
    /**
     * Delimiter between a property key and value.
     */
    public static final String PROPERTY_DELIMITER = "=";

    /**
     * Delimiter between a switch name and value.
     */
    public static final String SWITCH_DELIMITER = " ";

    /**
     * Delimiter between multiple command options.
     */
    public static final String COMMAND_DELIMITER = " ";

    /**
     * List of command arguments.
     */
    private List<String> listArg = new ArrayList<String> ();

    private HashMap<String, String > map = new HashMap<String, String>();

    /**
     * Creates a new command builder instance
     */
    public CommandBuilder()
    {
    }

    /**
     * Add <b>builder</b> to this command.
     *
     * @param builder Command builder to be added.
     * @return Resultant command builder object.
     */
    public CommandBuilder add (CommandBuilder builder)
    {
        listArg.addAll(builder.getArgList());
        return this;
    }

    /**
     * Add options which are provided in the format
     * {@code add ("key1", "value1", "key2", "value2",...,"keyN", "valueN"); }
     *
     * @param option Options to be added to the command.
     * @return Resultant command builder object.
     */
    public CommandBuilder add (String... option)
    {
        for (String currOption : option)
            listArg.add(currOption);
        return this;
    }

    /**
     * Use the {@code delimiter} to delimit {@code option} and {@code value}.
     * Add this to the command.
     *
     * @param option Command option.
     * @param value Command value.
     * @param delimiter Delimiter between option and value.
     * @return Resultant command builder object.
     */
    public CommandBuilder add (String option, String value, String delimiter)
    {
        map.put(option, value);
        return add(option + delimiter + value);
    }

    /**
     * Invoke {@code  add (option, value, SWITCH_DELIMITER); }
     * <br>See {@link #add(String, String, String)}
     */
    public CommandBuilder addSwitch(String name, String value)
    {
        map.put(name, value);
        return add (name, value, SWITCH_DELIMITER);
    }

    /**
     * Invoke {@code add (key ,value, PROPERTY_DELIMITER); }
     * <br>See {@link #add(String, String, String)}
     */
    public CommandBuilder addProperty (String key, String value)
    {
        map.put(key, value);
        return add(key, value, PROPERTY_DELIMITER);
    }

    /**
     * Adds properties which are provided in the format
     * {@code addProperty ("key1", "value1", "key2", "value2",...,keyN, valueN); }
     *
     * @param property Add properties
     * @return Resultant command builder object.
     */
    public CommandBuilder addProperty (String... property)
    {
        /* If the length is zero. Nothing is to be added. Variable argument must include ZERO or more*/
        if (property.length == 0)
            return this;

        if (property.length % 2 != 0)
            throw new IllegalArgumentException("Every property name argument must be followed by a value argument");

        for (int i = 0; i < property.length; i += 2)
        {
            map.put(property[i], property[i+1]);
            add(property[i], property[i+1], PROPERTY_DELIMITER);
        }

        return this;
    }

    /**
     * @return Returns the list of command arguments.
     */
    public List<String> getArgList ()
    {
        return listArg;
    }

    /**
     * @return Returns the array of command arguments.
     */
    public String[] getArgArray ()
    {
        return listArg.toArray(new String[0]);
    }

    /**
     * @return Returns the resultant command.
     */
    @Override
    public String toString()
    {
        return Util.join (listArg, COMMAND_DELIMITER);
    }

    public HashMap<String, String> getMap()
    {
        //System.out.println("Madhu:" + map + "Kiran");
      /*HashMap<String, String> map = new HashMap<String, String>();
      int size = listArg.size();
      for (int i=0; i<size; i+=2)
      {
         map.put(listArg.get(i), listArg.get(i+1));
      }*/
        //map.remove("configuration");
        return map;
    }
}


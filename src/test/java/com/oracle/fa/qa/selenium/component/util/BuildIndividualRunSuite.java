package com.oracle.fa.qa.selenium.component.util;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Creates a xml file based on the Suite and TestCases selected by the user
 * Created by Amrathraj Nayak on 4/25/18.
 */

public class BuildIndividualRunSuite {

    //public static void buidSuiteXML(){
    public static void main(String[] args){

        String testsString = args[0];
        String[] tests = testsString.split(",");
        //String suite = getSuiteName(args[0], tests);

        List<String> IndividualTests;
        IndividualTests = Arrays.asList(tests);
        writeData(IndividualTests);

    }

    /*public static String getSuiteName(String suite, String[] tests){
        if(suite.equalsIgnoreCase("FIN")) return "com.oracle.fa.qa.selenium.component.fin.test.";
        else if(suite.equalsIgnoreCase("HCM")) return "com.oracle.fa.qa.selenium.component.hcm.test.";
        else if(suite.equalsIgnoreCase("PRC")) return "com.oracle.fa.qa.selenium.component.prc.test.";
        else if(suite.equalsIgnoreCase("CRM")) return "com.oracle.fa.qa.selenium.component.crm.test.";
        else if(suite.contains("BPM")) return "com.oracle.fa.qa.selenium.component.bpm.test.";
        else return null;
    }*/

    public static void writeData(List<String> testCases) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element suiteElement = doc.createElement("suite");
            suiteElement.setAttribute("name","FA Test");
            suiteElement.setAttribute("verbose","1");
            suiteElement.setAttribute("parallel","none");

            //append root element to document
            doc.appendChild(suiteElement);

            Element testElement = doc.createElement("test");
            testElement.setAttribute("name","FA_Individual_Test");
            testElement.setAttribute("verbose","1");
            testElement.setAttribute("preserve-order","true");

            //append root element to document
            suiteElement.appendChild(testElement);

            Element classesElement = doc.createElement("classes");
            testElement.appendChild(classesElement);

            //Iterator for classes
            Iterator<String> iterator = testCases.iterator();
            while(iterator.hasNext()){
                String suite;
                String testCase = iterator.next();
                if(testCase.startsWith("FIN")) suite = "com.oracle.fa.qa.selenium.component.fin.test.";
                else if(testCase.startsWith("HCM")) suite = "com.oracle.fa.qa.selenium.component.hcm.test.";
                else if(testCase.startsWith("PRC")) suite = "com.oracle.fa.qa.selenium.component.prc.test.";
                else if(testCase.startsWith("CRM")) suite = "com.oracle.fa.qa.selenium.component.crm.test.";
                else suite = "com.oracle.fa.qa.selenium.component.bpm.test.";

                Element classElement = doc.createElement("class");
                classElement.setAttribute("name",suite+testCase);
                classesElement.appendChild(classElement);
            }

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMImplementation domImpl = doc.getImplementation();
            DocumentType doctype = domImpl.createDocumentType("doctype",
                    null,
                    "http://testng.org/testng-1.0.dtd");
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("src/test/resources/tests/test_Individual.xml"));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




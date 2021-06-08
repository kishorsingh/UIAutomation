package com.oracle.fa.qa.selenium.component.util;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Creates a xml file based on the Failed Testcases in a run
 * Created by Amrathraj Nayak on 4/25/18.
 */

public class BuildRerunFailedSuite {

    //public static void buidSuiteXML(){
    public static void main(String[] args){

        ArrayList<String> failedTests = readData();
        writeData(failedTests);

    }

    public static ArrayList readData(){

        ArrayList<String> failedTests = new ArrayList();

        File f = new File("build/test-results/runTests");
        ArrayList<File> files = new ArrayList(Arrays.asList(f.listFiles()));

        //Iterate though the list of files under test-output/FA Test
        System.out.println("FAILED TESTCASES IN THIS RUN ARE:");
        Iterator<File> iterator = files.iterator();
        while(iterator.hasNext()){

            try {
                File file = iterator.next();

                if(file.isFile()) {

                    File xmlFile = new File(file.getPath());

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(xmlFile);

                    doc.getDocumentElement().normalize();

                    NodeList nList = doc.getElementsByTagName("failure");

                    for (int temp = 0; temp < nList.getLength(); temp++) {

                        Node nNode = nList.item(temp);
                        Node pNode = nNode.getParentNode();
                        Element eElement = (Element) pNode;
                        System.out.println(eElement.getAttribute("classname"));
                        failedTests.add(eElement.getAttribute("classname"));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return failedTests;
    }


    public static void writeData(ArrayList<String> failedTests) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element suiteElement = doc.createElement("suite");
            suiteElement.setAttribute("name","FA Test");
            suiteElement.setAttribute("verbose","1");
            suiteElement.setAttribute("parallel","tests");
            suiteElement.setAttribute("thread-count","12");

            //append root element to document
            doc.appendChild(suiteElement);

            Element parameterElement = doc.createElement("parameter");
            parameterElement.setAttribute("name","environment");
            parameterElement.setAttribute("value","FA");

            suiteElement.appendChild(parameterElement);

            Iterator<String> iterator = failedTests.iterator();


            boolean finSuiteCreated=false;
            boolean hcmSuiteCreated=false;
            boolean prcSuiteCreated=false;
            boolean crmSuiteCreated=false;

            Element finClassesElement = doc.createElement("classes");
            Element finTestElement = doc.createElement("test");
            finTestElement.setAttribute("name", "FIN_ReRun_Failed");
            finTestElement.setAttribute("verbose", "1");
            finTestElement.setAttribute("preserve-order", "true");

            Element hcmClassesElement = doc.createElement("classes");
            Element hcmTestElement = doc.createElement("test");
            hcmTestElement.setAttribute("name", "HCM_ReRun_Failed");
            hcmTestElement.setAttribute("verbose", "1");
            hcmTestElement.setAttribute("preserve-order", "true");

            Element prcClassesElement = doc.createElement("classes");
            Element prcTestElement = doc.createElement("test");
            prcTestElement.setAttribute("name", "PRC_ReRun_Failed");
            prcTestElement.setAttribute("verbose", "1");
            prcTestElement.setAttribute("preserve-order", "true");

            Element crmClassesElement = doc.createElement("classes");
            Element crmTestElement = doc.createElement("test");
            crmTestElement.setAttribute("name", "CRM_ReRun_Failed");
            crmTestElement.setAttribute("verbose", "1");
            crmTestElement.setAttribute("preserve-order", "true");

            while(iterator.hasNext()){

                String testCase = iterator.next();

                if(testCase.contains("FIN")) {

                    if (!finSuiteCreated) {

                        suiteElement.appendChild(finTestElement);
                        finTestElement.appendChild(finClassesElement);
                        finSuiteCreated=true;
                    }

                    Element classElement = doc.createElement("class");
                    classElement.setAttribute("name",testCase);
                    finClassesElement.appendChild(classElement);


                }

                if(testCase.contains("HCM")) {

                    if (!hcmSuiteCreated) {

                        suiteElement.appendChild(hcmTestElement);
                        hcmTestElement.appendChild(hcmClassesElement);
                        hcmSuiteCreated=true;
                    }

                    Element classElement = doc.createElement("class");
                    classElement.setAttribute("name",testCase);
                    hcmClassesElement.appendChild(classElement);


                }

                if(testCase.contains("PRC")) {

                    if (!prcSuiteCreated) {

                        suiteElement.appendChild(prcTestElement);
                        prcTestElement.appendChild(prcClassesElement);
                        prcSuiteCreated=true;
                    }

                    Element classElement = doc.createElement("class");
                    classElement.setAttribute("name",testCase);
                    prcClassesElement.appendChild(classElement);


                }

                if(testCase.contains("CRM")) {

                    if (!crmSuiteCreated) {

                        suiteElement.appendChild(crmTestElement);
                        crmTestElement.appendChild(crmClassesElement);
                        crmSuiteCreated=true;
                    }

                    Element classElement = doc.createElement("class");
                    classElement.setAttribute("name",testCase);
                    crmClassesElement.appendChild(classElement);


                }


            }

            /*Element testElement = doc.createElement("test");
            testElement.setAttribute("name","FA_ReRun_Failed");
            testElement.setAttribute("verbose","1");
            testElement.setAttribute("preserve-order","true");

            //append root element to document
            suiteElement.appendChild(testElement);

            String a = testElement.getAttribute("name");
            System.out.println("Attribute name is:"+a);

            //Element classesElement = doc.createElement("classes");
            testElement.appendChild(classesElement);

            //Iterator for classes
            /*Iterator<String> iterator = failedTests.iterator();
            while(iterator.hasNext()){

                Element classElement = doc.createElement("class");
                classElement.setAttribute("name",iterator.next());
                classesElement.appendChild(classElement);
            }*/

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
            StreamResult file = new StreamResult(new File("src/test/resources/tests/test_ReRun.xml"));

            //write data
            //transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}




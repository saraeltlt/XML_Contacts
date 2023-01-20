package com.example.mycontactsxml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsXML {

    public static Document ListToDoc(List<Contact> contactList) throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element root = doc.createElement("contacts");
        for (Contact contact : contactList) {
            root.appendChild( ContactsXML.createElement(doc, contact));
        }
        doc.appendChild(root);
        return doc;
    }

    public static List<Contact> docToList(Document contacts) {
        List<Contact> contactList = new ArrayList<>();

        NodeList list = contacts.getElementsByTagName("contact");
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                contactList.add(createObj(element));
            }
        }
        return contactList;
    }
    public static Document FileToDoc(File file) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder= factory.newDocumentBuilder();
        return builder.parse(file);
    }

    public static void docToFile(Document doc, File dest) throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource src = new DOMSource(doc);
        StreamResult output = new StreamResult(new FileOutputStream(dest, false));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(src, output);
    }

    private static Contact createObj(Element e) {
        Contact contact = new Contact();
        contact.setName(e.getElementsByTagName("name").item(0).getTextContent());
        contact.setPhone(e.getElementsByTagName("phone").item(0).getTextContent());
        contact.setAddress(e.getElementsByTagName("address").item(0).getTextContent());
        contact.setEmail(e.getElementsByTagName("email").item(0).getTextContent());
        return contact;
    }

    private static Element createContactElement(Document doc, String name, String value) {
        Element root = doc.createElement(name);
        root.appendChild(doc.createTextNode(value));
        return root;
    }
    private static Element createElement(Document doc, Contact contact) {
        Element contactElement = doc.createElement("contact");
        contactElement.appendChild(createContactElement(doc, "name", contact.getName()));
        contactElement.appendChild(createContactElement(doc, "phone", contact.getPhone()));
        contactElement.appendChild(createContactElement(doc, "address", contact.getAddress()));
        contactElement.appendChild(createContactElement(doc, "email", contact.getEmail()));
        return contactElement;
    }





}

package com.example.mycontactsxml;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContactsFunctions {
    private static List<Contact> contactsList = new ArrayList<>();
    private static int current = 0;
    static boolean  foundFlag;


    public static void prev() {
        if (current > 0)
            current--;
    }
    public static void next() {
        if (current + 1 < contactsList.size())
            current++;
    }
    public static void search(String name) {
        if (contactsList.size() > 0) {
            current = 0;
            while (current + 1 < contactsList.size() && !contactsList.get(current).getName().equals(name)) {
                foundFlag=false;
                current++;
            }
            if (contactsList.get(current).getName().equals(name)){
                foundFlag=true;
            }
        }
    }
    public static boolean isFoundFlag() {
        return foundFlag;
    }

    public static void setFoundFlag(boolean foundFlag) {
        ContactsFunctions.foundFlag = foundFlag;
    }

    public static void insert(Contact contact) {
        contactsList.add(contact);
        current = contactsList.size() - 1;
    }

    public static void update(Contact updatedContact) {
        if (contactsList.size() > 0)
            contactsList.set(current, updatedContact);
    }

    public static void delete() {
        if (!contactsList.isEmpty()) {
            contactsList.remove(current);
            if (current > 0)
                current--;
        }
    }
    public static void save() throws IOException, ParserConfigurationException, TransformerException {
        File file = new File("myContacts.xml");
        ContactsXML.docToFile(ContactsXML.ListToDoc(contactsList), file);
    }


    public static void getAllContacts() throws IOException, ParserConfigurationException,SAXException {
        File file = new File("myContacts.xml");
        if (file.length()!=0)
        contactsList = ContactsXML.docToList(ContactsXML.FileToDoc(file));

    }
    public static Contact getCurrentContact() {
        return contactsList.get(current);
    }

    public static int getContactsSize() {
        return contactsList.size();
    }
    public static String getCurrent() {
        if (contactsList.isEmpty()) {
            return "0";
        } else {
            return String.valueOf(current + 1);
        }
    }



}

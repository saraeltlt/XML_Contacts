package com.example.mycontactsxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyContactsController implements Initializable {
    @FXML
    Button prevBtn;
    @FXML
    Button nextBtn;
    @FXML
    Button searchBtn;
    @FXML
    Button insertBtn;
    @FXML
    Button updateBtn;
    @FXML
    Button deleteBtn;
    @FXML
    Button saveBtn;
    @FXML
    TextField nameText;
    @FXML
    TextField phoneText;
    @FXML
    TextField addressText;
    @FXML
    TextField emailText;
    @FXML
    Label infoLabel;

    private boolean statusFlag=true;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoLabel.setVisible(false);
        editableText(false);
        disableBtn(false);
    }
    @FXML
    private void prev() {
        infoLabel.setVisible(false);
        ContactsFunctions.prev();
        updateMyContactsView();
    }
    @FXML
    private void next() {
        infoLabel.setVisible(false);
        ContactsFunctions.next();
        updateMyContactsView();
    }
    @FXML
    private void search() {
        infoLabel.setVisible(false);
        if (statusFlag) {
            statusFlag = false;
            clearFields();
            disableBtn(true);
            searchBtn.setDisable(false);
            nameText.setEditable(true);
        } else {
            statusFlag = true;
            nameText.setEditable(false);
            disableBtn(false);
            ContactsFunctions.search(nameText.getText());
            if (ContactsFunctions.isFoundFlag()==false)
            {
                clearFields();
                infoLabel.setVisible(true);
                infoLabel.setText("CONTACT NOT FOUND!");
            }
            else {
                updateMyContactsView();
            }
        }
    }
    @FXML
    private void insert() {
        infoLabel.setVisible(false);
        if (statusFlag) {
            statusFlag = false;
            clearFields();
            disableBtn(true);
            insertBtn.setDisable(false);
            editableText(true);

        } else {
            statusFlag = true;
            disableBtn(false);
            editableText(false);
            if (!nameText.getText().isEmpty()) {
                Contact contact = new Contact(nameText.getText(), phoneText.getText(), addressText.getText(), emailText.getText());
                ContactsFunctions.insert(contact);
            }
            updateMyContactsView();
        }

    }
    @FXML
    private void update() {
        infoLabel.setVisible(false);
        if (statusFlag) {
            statusFlag = false;
            disableBtn(true);
            updateBtn.setDisable(false);
            editableText(true);
        } else {
            statusFlag = true;
            disableBtn(false);
            editableText(false);
            if (!nameText.getText().isEmpty()) {
                Contact contact = new Contact(nameText.getText(), phoneText.getText(), addressText.getText(), emailText.getText());
                ContactsFunctions.update(contact);
            }
            updateMyContactsView();
        }

    }

    @FXML
    private void delete() {
        infoLabel.setVisible(false);
        ContactsFunctions.delete();
        updateMyContactsView();
    }
    @FXML
    private void save() {
        infoLabel.setVisible(false);
        try {
            ContactsFunctions.save();
            clearFields();
            infoLabel.setVisible(true);
            infoLabel.setText("SAVED SUCCESSFULLY !");
        } catch (IOException |ParserConfigurationException |TransformerException e) {
            e.printStackTrace();
        }
    }



    private void updateMyContactsView() {
        if (ContactsFunctions.getContactsSize() > 0) {
            nameText.setText(ContactsFunctions.getCurrentContact().getName());
            phoneText.setText(ContactsFunctions.getCurrentContact().getPhone());
            addressText.setText(ContactsFunctions.getCurrentContact().getAddress());
            emailText.setText(ContactsFunctions.getCurrentContact().getEmail());
        } else {
            clearFields();
        }
    }
    private void clearFields() {
        nameText.clear();
        emailText.clear();
        phoneText.clear();
        addressText.clear();
    }
    private void disableBtn(boolean disable) {
        prevBtn.setDisable(disable);
        nextBtn.setDisable(disable);
        searchBtn.setDisable(disable);
        insertBtn.setDisable(disable);
        updateBtn.setDisable(disable);
        deleteBtn.setDisable(disable);
        saveBtn.setDisable(disable);
    }
    private void editableText(boolean disable) {
        nameText.setEditable(disable);
        phoneText.setEditable(disable);
        addressText.setEditable(disable);
        emailText.setEditable(disable);
    }

}
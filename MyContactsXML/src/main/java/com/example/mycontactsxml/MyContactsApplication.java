package com.example.mycontactsxml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MyContactsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            ContactsFunctions.getAllContacts();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(MyContactsApplication.class.getResource("MyContactsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 984, 553);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
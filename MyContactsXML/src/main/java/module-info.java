module com.example.mycontactsxml {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.example.mycontactsxml to javafx.fxml;
    exports com.example.mycontactsxml;
}
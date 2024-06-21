module com.projetofinal322 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    

    opens com.projetofinal322 to javafx.fxml;
    exports com.projetofinal322;
}

module com.projetofinal322 {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens com.projetofinal322 to javafx.fxml;
    opens com.projetofinal322.arquivos_gui to javafx.fxml;

    exports com.projetofinal322;
}

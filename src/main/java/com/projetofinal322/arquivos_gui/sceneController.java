package com.projetofinal322.arquivos_gui;

import java.io.IOException;
import java.util.LinkedList;

import com.projetofinal322.arquivos_leitura.LerEspacos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.Jogo;

public class sceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane anchorPane1;

    public void trocarParaCena2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/com/projetofinal322/fxml/scene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package com.projetofinal322;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/projetofinal322/scenes/scene1.fxml"));
            stage.setTitle("Jogo da Vida Universitário");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/projetofinal322/images/icon.png")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        launch(args);
    }

}
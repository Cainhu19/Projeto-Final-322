package arquivos_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class gameController implements Initializable {

    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView imgTabuleiro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgTabuleiro.fitHeightProperty().bind(hBox.heightProperty());

    }

}

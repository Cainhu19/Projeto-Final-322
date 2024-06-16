package arquivos_gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class gameController implements Initializable {

    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private Canvas gameCanvas;

    @FXML
    private ImageView imgTabuleiro;

    private void iniciaCanvas() {
        gameCanvas.widthProperty().bind(gameAnchor.widthProperty());
        gameCanvas.heightProperty().bind(gameAnchor.heightProperty());
        imgTabuleiro.fitHeightProperty().bind(gameCanvas.heightProperty());
        imgTabuleiro.fitWidthProperty().bind(gameCanvas.widthProperty());

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        iniciaCanvas();
    }

}

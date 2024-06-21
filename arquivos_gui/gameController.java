package arquivos_gui;

import java.net.URL;
import java.security.Key;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import logica.Jogador;
import logica.Jogo;

public class gameController implements Initializable {
    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private ImageView imgTabuleiro;

    @FXML
    private Label labelInfos;

    @FXML
    private Label labelTerminal;

    @FXML
    private TextField fieldTerminal;

    private String acao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Jogo jogo = Jogo.getInstance(Jogo.getJogadores());
        escolhaDeAcao(jogo);
    }

    public void escolhaDeAcao(Jogo jogo) {
        acao = "";
        String texto = Jogo.getJogadores().get(jogo.getJogadorAtual()).getNome() + " escolha uma ação";
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).getGrupo() != null) {
            texto += "\n2. Jogar o dado especial do seu grupo";
        }
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).possuiDadoComprado()) {
            texto += "\n3. Jogar o dado comprado na loja";
        }
        labelTerminal.setText(texto);

        if (acao != "") {
            labelTerminal.setText("viado de bosta");
        }
    }
}

package com.projetofinal322.arquivos_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import com.projetofinal322.logica.Jogo;
import com.projetofinal322.logica.Jogador;

public class gameController implements Initializable {
    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private ImageView imgTabuleiro;

    @FXML
    private Label labelInfos;

    @FXML
    private Label labelTerminal;

    private int turno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turno = 0;
        Jogo jogo = Jogo.getInstance(Jogo.getJogadores());
        labelTerminal.setFocusTraversable(true);
        gameAnchor.setOnKeyPressed(this::handleGameAnchor);
        escolhaDeAcao(jogo);
    }

    public void escolhaDeAcao(Jogo jogo) {
        String texto = Jogo.getJogadores().get(jogo.getJogadorAtual()).getNome() + " escolha uma ação";
        texto += "\n1. Jogar um D10 comum";
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).getGrupo() != null) {
            texto += "\n2. Jogar o dado especial do seu grupo";
        }
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).possuiDadoComprado()) {
            texto += "\n3. Jogar o dado comprado na loja";
        }
        texto += "\nL. Abrir a loja";
        labelTerminal.setText(texto);

    }

    private void handleGameAnchor(KeyEvent keyEvent) {
        Jogo jogo = Jogo.getInstance(Jogo.getJogadores());
        Jogador jogadorAtual = Jogo.getJogadores().get(jogo.getJogadorAtual());
        String texto = "";
        if (turno == 0) {
            switch (keyEvent.getCode().toString()) {
                case "DIGIT1":
                    texto = jogo.jogarDado(jogadorAtual, 0);
                    labelTerminal.setText(texto);
                    turno++;
                    break;
                case "DIGIT2":
                    if (jogadorAtual.getGrupo() != null) {
                        texto = jogo.jogarDado(jogadorAtual, 1);
                        labelTerminal.setText(texto);
                        turno++;
                    }
                    break;
                case "DIGIT3":
                    if (jogadorAtual.possuiDadoComprado()) {
                        texto = jogo.jogarDado(jogadorAtual, 2);
                        labelTerminal.setText(texto);
                        turno++;
                    }
                    break;
                default:
                    break;
            }

        }
    }
}

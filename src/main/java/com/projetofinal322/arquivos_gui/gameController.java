package com.projetofinal322.arquivos_gui;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import com.projetofinal322.arquivos_leitura.LerEspacos;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import com.projetofinal322.logica.Entrada;
import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.Jogo;

public class gameController implements Initializable {
    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private HBox hBox;

    @FXML
    private ImageView imgTabuleiro;

    @FXML
    private Label label;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgTabuleiro.fitHeightProperty().bind(hBox.heightProperty());
        Jogador j1 = new Jogador("Caio");
        Jogador j2 = new Jogador("Henrique");
        Jogador j3 = new Jogador("Giulliano");
        LinkedList<Jogador> jogadores = new LinkedList<>();
        jogadores.add(j1);
        jogadores.add(j2);
        jogadores.add(j3);
        try {
            LerEspacos leitor = new LerEspacos();
            leitor.lerArquivo("src/main/resources/com/arquivos_xml/tabuleiro.xml");

            // Caso haja exceções na leitura do tabuleiro, será impressa uma stack trace
            // para localização do erro
        } catch (Exception e) {
            e.printStackTrace();
        }

        Jogo jogo = Jogo.getInstance(jogadores);
        inciaJogo(jogo);
    }

    private void inciaJogo(Jogo jogo) {
        new Thread(() -> {
            loopDeJogo(jogo);
        }).start();
    }

    /**
     * Método principal que controla o loop do jogo.
     */
    private void loopDeJogo(Jogo jogo) {
        String texto;
        LinkedList<Jogador> jogadores = Jogo.getJogadores();
        int jogadorAtual = 0;
        int[] fim = new int[] { 12, 12 };
        boolean continuarJogo = true;
        while (continuarJogo) {
            Jogador jogador = jogadores.get(jogadorAtual);
            if (jogador.getPerdeuProxRodada()) {
                System.out.println(jogador.getNome() + " não pode jogar essa rodada.");
                jogador.setPerdeuProxRodada(false);
                jogadorAtual = (jogadorAtual + 1) % jogadores.size();
                continue;
            }

            // System.out.printf("%s, escolha uma ação:\n", jogador.getNome());
            texto = jogador.getNome() + " escolha uma ação: \n";
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String texto = jogador.getNome() + " escolha uma ação: \n";
                    label.setText(texto);
                }
            });
            System.out.println("1. Jogar um D10 comum");
            if (jogador.getGrupo() != null) {
                System.out.println("2. Jogar o dado especial do seu grupo");
            }
            if (jogador.possuiDadoComprado()) {
                System.out.println("3. Jogar o dado comprado na loja");
            }
            System.out.println("L. Abrir a loja");
            String escolha = Entrada.respostaString();

            switch (escolha.toLowerCase()) {
                case "1":
                    jogo.jogarDado(jogador, jogador.resultadoDado(0));
                    break;
                case "2":
                    if (jogador.getGrupo() != null) {
                        jogo.jogarDado(jogador, jogador.resultadoDado(1));
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case "3":
                    if (jogador.possuiDadoComprado()) {
                        jogo.jogarDado(jogador, jogador.resultadoDado(2));
                    } else {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                    break;
                case "l":
                    jogo.lojaAberta(jogador);
                    jogadorAtual = (jogadorAtual - 1) % jogadores.size(); // Ajuste para manter o mesmo jogador atual
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            if (jogador.getPosicao().equals(fim)) {
                System.out.println("Você chegou ao fim do tabuleiro!");
                jogadorAtual = (jogadorAtual + 1) % jogadores.size();
                jogadores.remove(jogadorAtual);
                continue;
            }

            System.out.println("Digite 'sair' para sair");
            if (Entrada.continuarJogo()) {
                jogadores.remove(jogadorAtual);
                if (jogadores.size() <= 1) {
                    continuarJogo = false;
                    break;
                }
            } else {
                jogadorAtual = (jogadorAtual + 1) % jogadores.size();
            }
        }
        Entrada.fecharScanner();
    }
}

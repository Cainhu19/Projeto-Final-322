package com.projetofinal322.arquivos_gui;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.projetofinal322.arquivos_leitura.LerEspacos;
import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.Jogo;

public class scene1Controller {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane anchorPane1;

    @FXML
    private Button button;

    @FXML
    private TextField textField;

    /**
     * 
     * Função ativida ao clicar no botão
     * e que resgata os nomes escritos em textField, e caso eles estejam de
     * acordo
     * com as regras, inicia o jogo
     */
    public void trocarParaCena2(ActionEvent event) throws IOException {
        String nomes = textField.getText();
        if (nomes.length() != 0) {
            String[] nomesSeparados = nomes.split(",");
            LinkedList<Jogador> jogadores = new LinkedList<Jogador>();
            for (String string : nomesSeparados) {
                string = string.trim();
                Jogador jogador = new Jogador(string);
                jogadores.add(jogador);
            }

            if (jogadores.size() > 1 && jogadores.size() <= 6) {
                Collections.shuffle(jogadores, new Random());
                try {
                    LerEspacos leitor = new LerEspacos();
                    leitor.lerArquivo("src\\main\\resources\\com\\arquivos_xml\\tabuleiro.xml");

                    // Caso haja exceções na leitura do tabuleiro, será impressa uma stack trace
                    // para localização do erro
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Jogo.getInstance(jogadores);
                root = FXMLLoader.load(getClass().getResource("/com/projetofinal322/scenes/scene2.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        }
    }
}

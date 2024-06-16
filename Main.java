import java.util.LinkedList;

import arquivos_leitura.LerEspacos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logica.*;

public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
        Jogador j1 = new Jogador("Caio");
        Jogador j2 = new Jogador("Henrique");
        Jogador j3 = new Jogador("Giulliano");
        LinkedList<Jogador> jogadores = new LinkedList<>();
        jogadores.add(j1);
        jogadores.add(j2);
        jogadores.add(j3);
        try {
            LerEspacos leitor = new LerEspacos();
            leitor.lerArquivo("arquivos_xml/tabuleiro.xml");

            // Caso haja exceções na leitura do tabuleiro, será impressa uma stack trace
            // para localização do erro
        } catch (Exception e) {
            e.printStackTrace();
        }

        Jogo jogo = Jogo.getInstance(jogadores);
        jogo.loopDeJogo();
    }

    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("arquivos_gui/scene1.fxml"));
            stage.setTitle("Jogo da Vida Universitário");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
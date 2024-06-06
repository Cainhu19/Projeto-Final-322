package logica.tabuleiro;

import java.util.LinkedList;

public class Tabuleiro {
    private static Tabuleiro tabuleiroInstance;
    private LinkedList<Caminho> caminhos;

    private Tabuleiro() {
        this.caminhos= new LinkedList<Caminho>();
    }

    public static Tabuleiro getInstance() {
        if (tabuleiroInstance == null) {
            tabuleiroInstance = new Tabuleiro();
        }

        return tabuleiroInstance;
    }

    public void adicionarCaminho(Caminho caminho) {
        caminhos.add(caminho);
    }
}

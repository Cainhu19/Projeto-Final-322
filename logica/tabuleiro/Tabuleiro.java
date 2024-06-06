package logica.tabuleiro;

import java.util.LinkedList;

public class Tabuleiro {
    // TODO: singleton aqui
    private static Tabuleiro tabuleiroInstance;
    private LinkedList<Espaco> espacos;

    private Tabuleiro(int n, LinkedList<Espaco> espacos) {
        this.espacos = new LinkedList<Espaco>();
        this.espacos = espacos;
    }

    public static Tabuleiro getInstance(int n, LinkedList<Espaco> espacos) {
        if (tabuleiroInstance == null) {
            tabuleiroInstance = new Tabuleiro(n, espacos);
        }

        return tabuleiroInstance;
    }
}

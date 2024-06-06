package logica;

import java.util.LinkedList;

import logica.tabuleiro.Espaco;
import logica.tabuleiro.Tabuleiro;

public class Jogo {
    private static Jogo jogoInstance;
    private LinkedList<Jogador> jogadores;
    private Tabuleiro tabuleiro;

    private Jogo(LinkedList<Jogador> jogadores, int nEspacos, LinkedList<Espaco> espacos) {
        this.jogadores = jogadores;
        this.tabuleiro = Tabuleiro.getInstance(nEspacos, espacos);
    }

    public static Jogo getInstance(LinkedList<Jogador> jogadores, int nEspacos, LinkedList<Espaco> espacos) {
        if (jogoInstance == null) {
            jogoInstance = new Jogo(jogadores, nEspacos, espacos);
        }

        return jogoInstance;
    }

    public void loopDeJogo() {

        while (true) {
            for (Jogador jogador : jogadores) {

            }
        }
    }
}

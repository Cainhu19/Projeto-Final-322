package logica;

import java.util.LinkedList;
import java.util.Scanner;

import logica.tabuleiro.Tabuleiro;

public class Jogo {
    private static Jogo jogoInstance;
    private LinkedList<Jogador> jogadores;
    private Tabuleiro tabuleiro;

    private Jogo(LinkedList<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.tabuleiro = Tabuleiro.getInstance();
    }

    public static Jogo getInstance(LinkedList<Jogador> jogadores) {
        if (jogoInstance == null) {
            jogoInstance = new Jogo(jogadores);
        }

        return jogoInstance;
    }

    public void loopDeJogo() {

        while (true) {
            for (Jogador jogador : jogadores) {
                System.err.printf("Jogador %s joga o dado: %d\n", jogador.getNome(), jogador.resultadoDado(0));
                Scanner sc = new Scanner(System.in);

                System.err.println("Digite para sair");
                sc.next();
            }
        }
    }
}

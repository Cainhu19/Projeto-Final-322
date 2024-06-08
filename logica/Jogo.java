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
        Scanner sc = new Scanner(System.in);
        int jogadorAtual = 0;
        boolean continuarJogo = true;
        while (continuarJogo) {
            Jogador jogador = jogadores.get(jogadorAtual);
            int resultadoDado = jogador.resultadoDado(0);
            System.out.printf("Jogador %s joga o dado: %d\n", jogador.getNome(), resultadoDado);

            tabuleiro.moverJogador(jogador, resultadoDado, sc);

            System.out.println("Digite 'sair' para sair");
            if (sc.next().equals("sair")) {
                jogadores.remove(jogadorAtual);
                if (jogadores.size() <= 1) {
                    continuarJogo = false;
                    break;
                }
            } else {
                jogadorAtual = (jogadorAtual + 1) % jogadores.size();
            }
        }
        sc.close();
    }
}

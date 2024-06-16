package logica;

import java.util.LinkedList;

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

    public static LinkedList<Jogador> getJogadores() {
        return jogoInstance.jogadores;
    }

    public void loopDeJogo() {
        int jogadorAtual = 0;
        int[] fim = new int[]{12, 12};
        boolean continuarJogo = true;
        while (continuarJogo) {
            Jogador jogador = jogadores.get(jogadorAtual);
            if (jogador.getPerdeuProxRodada()) {
                System.out.println(jogador.getNome() + " não pode jogar essa rodada.");
                jogador.setPerdeuProxRodada(false);
                jogadorAtual = (jogadorAtual + 1) % jogadores.size();
            } else {
                int resultadoDado = jogador.resultadoDado(0);
                System.out.printf("Jogador %s joga o dado: %d\n", jogador.getNome(), resultadoDado);
                tabuleiro.moverJogador(jogador, resultadoDado);
                if (jogador.getPosicao().equals(fim)) {
                    System.out.println("Você chegou ao fim do tabuleiro!");
                    jogadores.remove(jogadorAtual);
                    jogadorAtual = (jogadorAtual + 1) % jogadores.size();
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
        }
        Entrada.fecharScanner();
    }
}
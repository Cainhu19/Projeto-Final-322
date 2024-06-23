package com.projetofinal322.logica;

import java.util.LinkedList;

import com.projetofinal322.logica.tabuleiro.Tabuleiro;

public class Jogo {
    private static Jogo jogoInstance;
    private LinkedList<Jogador> jogadores;
    private Tabuleiro tabuleiro;
    private int jogadorAtual;

    private Jogo(LinkedList<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.tabuleiro = Tabuleiro.getInstance();
        this.jogadorAtual = 0;
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

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(int jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    /**
     * Método para lidar com a interação da loja do jogo.
     * 
     * @param jogador jogador atual que está vendo a loja.
     */
    public void lojaAberta(Jogador jogador) {
        boolean lojaAberta = true;
        Loja.mostrarDadosDisponiveis();
        System.out.println("Escolha um dado para comprar (digite o número) ou 0 para fechar a loja:");
        while (lojaAberta) {
            int dadoEscolhidoLoja = Entrada.respostaInt();
            if (dadoEscolhidoLoja > 0 && dadoEscolhidoLoja <= Loja.getDadosAVenda().length) {
                Loja.compra(jogador, dadoEscolhidoLoja - 1);
            } else if (dadoEscolhidoLoja == 0) {
                lojaAberta = false;
                System.out.println("Fechando a loja...");
            } else {
                System.out.println("Escolha inválida. Tente novamente.");
            }
        }
    }

    /**
     * Método para lidar com a jogada de um dado por parte do jogador.
     * 
     * @param jogador  jogador que jogou o dado.
     * @param tipoDado tipo do Dado que será lançado pelo jogador (Normal, do grupo
     *                 ou comprado).
     */
    public int jogarDado(Jogador jogador, int tipoDado) {
        return jogador.resultadoDado(tipoDado);
    }

    /**
     * Método principal que controla o loop do jogo.
     */
    // public void loopDeJogo() {
    // int jogadorAtual = 0;
    // int[] fim = new int[] { 12, 12 };
    // boolean continuarJogo = true;
    // while (continuarJogo) {
    // Jogador jogador = jogadores.get(jogadorAtual);
    // if (jogador.getPerdeuProxRodada()) {
    // System.out.println(jogador.getNome() + " não pode jogar essa rodada.");
    // jogador.setPerdeuProxRodada(false);
    // jogadorAtual = (jogadorAtual + 1) % jogadores.size();
    // continue;
    // }

    // System.out.printf("%s, escolha uma ação:\n", jogador.getNome());
    // System.out.println("1. Jogar um D10 comum");
    // if (jogador.getGrupo() != null) {
    // System.out.println("2. Jogar o dado especial do seu grupo");
    // }
    // if (jogador.possuiDadoComprado()) {
    // System.out.println("3. Jogar o dado comprado na loja");
    // }
    // System.out.println("L. Abrir a loja");
    // String escolha = Entrada.respostaString();

    // switch (escolha.toLowerCase()) {
    // case "1":
    // jogarDado(jogador, jogador.resultadoDado(0));
    // break;
    // case "2":
    // if (jogador.getGrupo() != null) {
    // jogarDado(jogador, jogador.resultadoDado(1));
    // } else {
    // System.out.println("Opção inválida. Tente novamente.");
    // }
    // break;
    // case "3":
    // if (jogador.possuiDadoComprado()) {
    // jogarDado(jogador, jogador.resultadoDado(2));
    // } else {
    // System.out.println("Opção inválida. Tente novamente.");
    // }
    // break;
    // case "l":
    // lojaAberta(jogador);
    // jogadorAtual = (jogadorAtual - 1) % jogadores.size(); // Ajuste para manter o
    // mesmo jogador atual
    // break;
    // default:
    // System.out.println("Opção inválida. Tente novamente.");
    // break;
    // }

    // if (jogador.getPosicao().equals(fim)) {
    // System.out.println("Você chegou ao fim do tabuleiro!");
    // jogadorAtual = (jogadorAtual + 1) % jogadores.size();
    // jogadores.remove(jogadorAtual);
    // continue;
    // }

    // System.out.println("Digite 'sair' para sair");
    // if (Entrada.continuarJogo()) {
    // jogadores.remove(jogadorAtual);
    // if (jogadores.size() <= 1) {
    // continuarJogo = false;
    // break;
    // }
    // } else {
    // jogadorAtual = (jogadorAtual + 1) % jogadores.size();
    // }
    // }
    // Entrada.fecharScanner();
    // }
}
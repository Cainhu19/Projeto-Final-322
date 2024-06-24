package com.projetofinal322.logica;

import java.util.LinkedList;

import com.projetofinal322.logica.tabuleiro.Tabuleiro;

public class Jogo {
    private static Jogo jogoInstance;
    private LinkedList<Jogador> jogadores;
    private LinkedList<Jogador> jogadoresQueAcabaram;
    private Tabuleiro tabuleiro;
    private int jogadorAtual;

    private Jogo(LinkedList<Jogador> jogadores) {
        this.jogadores = jogadores;
        this.jogadoresQueAcabaram = new LinkedList<>();
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

    public LinkedList<Jogador> getJogadoresQueAcabaram() {
        return jogadoresQueAcabaram;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadores(LinkedList<Jogador> jogadores) {
        this.jogadores = jogadores;
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
}
package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

public class EspacoDinheiro extends Espaco {
    private int quantidade;

    public EspacoDinheiro(String descricao, int quantidade) {
        super(descricao);
        this.quantidade = quantidade;
    }

    @Override
    public void acao(Jogador jogador) { // Usado para ganhar dinheiro, perder dinheiro e limpar dívida quando quantidade = 0 
        imprimirDescricao(descricao);
        if (quantidade > 0) {
            System.out.printf("(+%d na sua conta)\n", quantidade);
        } else if (quantidade < 0) {
            System.out.printf("(%d na sua conta)\n", quantidade);
        } else if (jogador.getDinheiro() < 0) {
            System.out.println("Suas dívidas foram quitadas! Seu saldo é agora de 0.\n");
            jogador.setDinheiro(0);
        }
        jogador.ajustarDinheiro(quantidade);
    }
}

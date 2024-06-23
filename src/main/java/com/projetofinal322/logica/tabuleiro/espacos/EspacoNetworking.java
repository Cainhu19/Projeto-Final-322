package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

public class EspacoNetworking extends Espaco {
    private int bonusNetworking;

    public EspacoNetworking(String descricao, int bonusNetworking) {
        super(descricao);
        this.bonusNetworking = bonusNetworking;
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(imprimirDescricao());
        System.out.printf("(+%d pontos de networking)\n", bonusNetworking);
        jogador.adicionarPontosNetworking(bonusNetworking);
    }
}

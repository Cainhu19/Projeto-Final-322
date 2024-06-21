package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

public class EspacoRemuneracao extends Espaco {
    public EspacoRemuneracao() {
        super("CASA DE REMUNERAÇÃO");
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(descricao);
        if (jogador.getFonteDeRenda() == null) {
            System.out.println("Você não possui fonte de renda, portanto não ganha nada.");
            return;
        }
        System.out.printf("Você recebeu %d.\n", jogador.getFonteDeRenda().getRenda());
        jogador.ajustarDinheiro(jogador.getFonteDeRenda().getRenda());
    }
}

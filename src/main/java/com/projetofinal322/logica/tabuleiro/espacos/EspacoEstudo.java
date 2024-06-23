package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

public class EspacoEstudo extends Espaco {
    public EspacoEstudo(String descricao) {
        super(descricao);
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(imprimirDescricao());
        jogador.incrementarVezesEstudo();
    }
}

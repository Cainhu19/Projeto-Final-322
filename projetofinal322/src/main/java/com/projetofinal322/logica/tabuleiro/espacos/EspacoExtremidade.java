package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

public class EspacoExtremidade extends Espaco {
    public EspacoExtremidade(String descricao) {
        super(descricao);
    }

    @Override
    public void acao(Jogador jogador) {}    // Não faz nada.
}
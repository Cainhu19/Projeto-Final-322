package com.projetofinal322.logica.tabuleiro;

import com.projetofinal322.logica.Jogador;

/**
 * Classe abstrata da qual todos os espaços do tabuleiro herdam.
 */
public abstract class Espaco {
    protected String descricao;

    protected Espaco(String descricao) {
        this.descricao = descricao;
    }

    public abstract void acao(Jogador jogador);

    public String imprimirDescricao() {
        if (descricao == null) {
            return "";
        }
        return descricao;
    }
}

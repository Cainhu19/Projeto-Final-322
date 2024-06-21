package com.projetofinal322.logica.dados_do_jogo;


/**
 * Classe abstrata que estabelece contrato que todos os dados do jogo devem seguir.
 */
public abstract class Dado {
    protected int[] valores;

    public abstract int rodar(int vezes);
}

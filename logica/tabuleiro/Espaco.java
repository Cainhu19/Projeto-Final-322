package logica.tabuleiro;

import logica.Jogador;

/**
 * Classe abstrata da qual todos os espaços do tabuleiro herdam.
 * //TODO: confirmar essa docstring
 */
public abstract class Espaco {
    protected String descricao;

    protected Espaco(String descricao) {
        this.descricao = descricao;
    }

    public abstract void acao(Jogador jogador);

    public void imprimirDescricao(String descricao) {
        if (descricao == null) { return; }
        System.out.println(descricao);
    }
}

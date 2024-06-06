package logica.tabuleiro;

import logica.Jogador;

public abstract class Espaco {
    private String descricao;

    public String imprimeDescricao() { return descricao; }

    public abstract void acao(Jogador jogador);
}

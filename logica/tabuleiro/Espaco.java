package logica.tabuleiro;

import logica.Jogador;

public abstract class Espaco {
    protected String descricao;

    protected Espaco(String descricao) {
        this.descricao = descricao;
    }

    public String imprimeDescricao() {
        return descricao;
    }

    public abstract void acao(Jogador jogador);

    public void imprimirDescricao(String descricao) {
        if (descricao == null) { return; }
        System.out.println(descricao);
    }
}

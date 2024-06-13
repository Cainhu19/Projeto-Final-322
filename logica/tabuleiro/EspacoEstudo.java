package logica.tabuleiro;

import logica.Jogador;

public class EspacoEstudo extends Espaco {
    public EspacoEstudo(String descricao) {
        super(descricao);
    }

    @Override
    public void acao(Jogador jogador) {
        imprimirDescricao(descricao);
        jogador.incrementarVezesEstudo();
    }
}

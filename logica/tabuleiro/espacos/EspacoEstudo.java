package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

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

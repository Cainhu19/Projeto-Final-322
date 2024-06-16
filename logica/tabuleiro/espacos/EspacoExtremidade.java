package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

public class EspacoExtremidade extends Espaco {
    protected EspacoExtremidade(String descricao) {
        super(descricao);
    }

    @Override
    public void acao(Jogador jogador) {}    // Não faz nada.
}
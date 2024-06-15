package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

public class EspacoPerdeRodada extends Espaco {
    public EspacoPerdeRodada(String descricao) {
        super(descricao);
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(descricao);
        jogador.setPerdeuProxRodada(true);
    }
}

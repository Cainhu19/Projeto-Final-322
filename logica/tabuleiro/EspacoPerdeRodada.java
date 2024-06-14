package logica.tabuleiro;

import logica.*;

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

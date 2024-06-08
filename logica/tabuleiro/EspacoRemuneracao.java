package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao extends Espaco {
    private String descricao;

    public EspacoRemuneracao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void acao(Jogador jogador) {
        if (jogador.getFonteDeRenda() != null) {
            jogador.adicionarDinheiro(jogador.getFonteDeRenda().getRenda());
        }
    }
}

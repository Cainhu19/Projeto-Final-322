package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao extends Espaco {
    private String descricao;

    public EspacoRemuneracao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public void acao(Jogador jogador) {
        jogador.adicionarDinheiro(jogador.getFonteDeRenda().getRenda());
    }
}

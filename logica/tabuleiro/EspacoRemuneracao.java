package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao extends Espaco {
    @Override
    public void acao(Jogador jogador) {
        jogador.adicionarDinheiro(jogador.getFonteDeRenda().getRenda());
    }
}

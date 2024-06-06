package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao implements Espaco {
    @Override
    public void acao(Jogador jogador) {
        jogador.adicionarDinheiro(jogador.getFonteDeRenda().getRenda());
    }
}

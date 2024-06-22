package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

public class EspacoNetworking extends Espaco {
    private int bonusNetworking;

    public EspacoNetworking(String descricao, int bonusNetworking) {
        super(descricao);
        this.bonusNetworking = bonusNetworking;
    }

    @Override
    public void acao(Jogador jogador) {
        imprimirDescricao(descricao);
        System.out.printf("(+%d pontos de networking)\n", bonusNetworking);
        jogador.adicionarPontosNetworking(bonusNetworking);
    }
}

package logica.tabuleiro;

import logica.Jogador;

public class EspacoNetworking extends Espaco {
    private int bonusNetworking;

    public EspacoNetworking(String descricao, int bonusNetworking) {
        super(descricao);
        this.bonusNetworking = bonusNetworking;
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.printf("%s\n(+%d pontos de networking)\n", descricao, bonusNetworking);
        jogador.adicionarPontosNetworking(bonusNetworking);
    }
}

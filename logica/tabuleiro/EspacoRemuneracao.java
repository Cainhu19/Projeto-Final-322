package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao extends Espaco {
    public EspacoRemuneracao() {
        super("CASA DE REMUNERAÇÃO");
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(descricao);
        System.out.printf("Você recebeu %d.\n", jogador.getFonteDeRenda().getRenda());
        jogador.ajustarDinheiro(jogador.getFonteDeRenda().getRenda());
    }
}

package logica.tabuleiro;

import logica.Jogador;

public class EspacoRemuneracao extends Espaco {
    public EspacoRemuneracao() {
        super("CASA DE REMUNERAÇÃO");
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(descricao);
        if (jogador.getFonteDeRenda() != null) {
            jogador.ajustarDinheiro(jogador.getFonteDeRenda().getRenda());
            System.out.printf("Você recebeu %d.\n", jogador.getFonteDeRenda().getRenda());
        } else {
            System.out.println("Você não tem nenhuma fonte de renda, portanto não ganha nada.");
        }
    }
}

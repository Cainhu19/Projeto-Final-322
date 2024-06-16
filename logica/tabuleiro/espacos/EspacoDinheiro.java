package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

public class EspacoDinheiro extends Espaco {
    private int quantidade;

    public EspacoDinheiro(String descricao, int quantidade) {
        super(descricao);
        this.quantidade = quantidade;
    }

    @Override
    public void acao(Jogador jogador) { // Usado tanto para ganhar quanto gastar dinheiro
        imprimirDescricao(descricao);
        if (quantidade > 0) {
            System.out.printf("(+%d na sua conta)\n", quantidade);
        } else {
            System.out.printf("(%d na sua conta)\n", quantidade);
        }
        jogador.ajustarDinheiro(quantidade);
    }
}

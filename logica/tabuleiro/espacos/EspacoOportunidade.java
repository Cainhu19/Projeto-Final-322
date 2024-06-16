package logica.tabuleiro.espacos;

import logica.Jogador;
import logica.tabuleiro.Espaco;

public class EspacoOportunidade extends Espaco {
    private int pontosOportunidade;

    public EspacoOportunidade(String descricao, int pontosOportunidade) {
        super(descricao);
        this.pontosOportunidade = pontosOportunidade;
    }

    @Override
    public void acao(Jogador jogador) {
        imprimeDescricao();
        if (pontosOportunidade > 0) {
            System.out.printf("(+%d pontos de oportunidade)", pontosOportunidade);
        } else {
            System.out.printf("(%d pontos de oportunidade)", pontosOportunidade);
        }
        jogador.ajustarPontosOportunidade(pontosOportunidade);
    }
    
}

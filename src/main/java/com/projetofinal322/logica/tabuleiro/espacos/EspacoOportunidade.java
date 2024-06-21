package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.Espaco;

/**
 * Classe de espaços que diretamente dão pontos de oportunidade ao jogador ou que dão pontos se o jogador estiver em um grupo.
 */
public class EspacoOportunidade extends Espaco {
    private int pontosOportunidade;
    private boolean receberPontosSeEstiverEmGrupo;

    public EspacoOportunidade(String descricao, int pontosOportunidade, boolean receberPontosSeEstiverEmGrupo) {
        super(descricao);
        this.pontosOportunidade = pontosOportunidade;
        this.receberPontosSeEstiverEmGrupo = receberPontosSeEstiverEmGrupo;
    }

    @Override
    public void acao(Jogador jogador) {
        imprimirDescricao(descricao);
        // Tipo P_OPORTUNIDADES_SE_ESTIVER_EM_GRUPO: se o espaço requer que o jogador esteja em um grupo para receber pontos
        if (receberPontosSeEstiverEmGrupo) {
            if (jogador.getGrupo() == null) {
                System.out.printf("%s não está em um grupo.\n", jogador.getNome());
            } else {
                System.out.printf("(+%d pontos de oportunidade)\n", pontosOportunidade);
                jogador.ajustarPontosOportunidade(pontosOportunidade);
            }
        // Tipo P_OPORTUNIDADES: se o espaço dá (ou tira) pontos diretamente
        } else {
            if (pontosOportunidade > 0) {
                System.out.printf("(+%d pontos de oportunidade)\n", pontosOportunidade);
            } else {
                System.out.printf("(%d pontos de oportunidade)\n", pontosOportunidade);
            }
            jogador.ajustarPontosOportunidade(pontosOportunidade);
        }
    }
    
}

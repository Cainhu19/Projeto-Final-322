package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.*;
import com.projetofinal322.logica.tabuleiro.Espaco;

/**
 * Classe de espaços em que o jogador recebe uma oferta de grupo se ele tiver parado em casas de estudo um determinado número de vezes.
 */
public class EspacoEstudoParaOferta extends Espaco {
    private Grupo grupo;
    private int vezesEstudo;

    public EspacoEstudoParaOferta(String descricao, Grupo grupo, int vezesEstudo) {
        super(descricao);
        this.grupo = grupo;
        this.vezesEstudo = vezesEstudo;
    }

    @Override
    public void acao(Jogador jogador) {
        if (jogador.getVezesEstudo() < vezesEstudo) {
            System.out.println("Você não estudou o suficiente para poder aceitar essa proposta.");
        } else {
            if (grupo.isOcupado()) {
                if (jogador.getGrupo() != null && !jogador.getGrupo().equals(grupo)) {
                    System.out.printf("Um jogador já faz parte de %s.\n", grupo.getNome());
                } else {
                    System.out.printf("Você já faz parte de %s!\n", grupo.getNome());
                }
            } else {
                imprimirDescricao(descricao);
                if (Entrada.respostaString().equals("s")) {
                    GerenciadorDeGrupos.ocupar(grupo, jogador);
                    jogador.adicionarPontosNetworking(grupo.getBonusNetworking());
                    System.out.printf("Novo grupo: %s. (+%d pontos de networking)\n", grupo.getNome(),
                            grupo.getBonusNetworking());
                }
            }
        }
    }
}

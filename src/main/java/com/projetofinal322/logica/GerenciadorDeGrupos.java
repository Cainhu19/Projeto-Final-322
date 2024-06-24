package com.projetofinal322.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que gerencia os grupos (Ligas/Entidades) disponíveis no jogo.
 * Garante que cada grupo seja ocupado por apenas um jogador por vez.
 */
public class GerenciadorDeGrupos {
    private static Map<Grupo, Jogador> grupos;
    static {
        grupos = new HashMap<>();
        for (Grupo grupo : Grupo.values()) {
            grupos.put(grupo, null);
        }
    }

    /**
     * Verifica se um grupo está livre,
     * ou seja disponível para ser ocupado
     * 
     * @param grupo grupo a ser verificado
     * @return true caso o grupo esteja disponível, false caso contrário.
     */
    public static boolean disponivel(Grupo grupo) {
        if (grupos.get(grupo) != null) {
            return false;
        }
        return true;
    }

    /**
     * Ocupa um grupo escolhido por certo jogador,
     * assim apenas um jogador pode fazer parte daquele grupo
     * 
     * @param grupo   grupo a ser ocupado.
     * @param jogador jogador que vai ocupar o grupo.
     */
    public static void ocupar(Grupo grupo, Jogador jogador) {
        if (jogador.getGrupo() != null && !jogador.getGrupo().equals(grupo)) {
            liberar(grupo);
        }
        grupos.put(grupo, jogador);
        grupo.setOcupado(true);
        jogador.setGrupo(grupo);
    }

    /**
     * Libera um grupo que estava ocupado.
     *
     * @param grupo grupo a ser liberado.
     */
    public static void liberar(Grupo grupo) {
        Jogador jogador = grupos.get(grupo);
        if (jogador != null) {
            jogador.setGrupo(null);
            grupos.put(grupo, null);
            grupo.setOcupado(false);
        }
    }

    /**
     * Retorna uma lista dos grupos que estão disponíveis.
     * 
     * @return lista dos grupos disponíveis.
     */
    public static List<Grupo> getGruposDisponiveis() {
        List<Grupo> gruposDisponiveis = new ArrayList<>();
        for (Map.Entry<Grupo, Jogador> entry : grupos.entrySet()) {
            if (entry.getValue() == null) {
                gruposDisponiveis.add(entry.getKey());
            }
        }
        return gruposDisponiveis;
    }
}

package com.projetofinal322.logica.resultados;

import com.projetofinal322.logica.Jogo;
import com.projetofinal322.logica.Jogador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que gerencia se o jogo acabou e, se sim, o que acontece depois.
 */
public class FimDeJogo {
    private static Map<Jogador, Double> classificacao;

    public static Map<Jogador, Double> getClassificacao() {
        return classificacao;
    }

    /**
     * Retorna true se o jogo não possui mais jogadores em sua lista, ou seja, se o jogo acabou.
    */
    public static boolean jogoAcabou() {
        return Jogo.getJogadores().size() == 0;
    }

    /**
     * Gerencia o que acontece após o fim de um jogo.
     */
    public static void posJogo(Jogo jogo) {
        // Associando a cada jogador sua pontuação em uma lista de pares e ordenando essa lista.
        Map<Jogador, Double> pontuacoes = new HashMap<>();
        for (Jogador j : jogo.getJogadoresQueAcabaram()) {
            pontuacoes.put(j, j.calcularPontuacao());
        }

        List<Map.Entry<Jogador, Double>> lista = new ArrayList<>(pontuacoes.entrySet());

        Collections.sort(lista, new Comparator<Map.Entry<Jogador, Double>>() {
            @Override
            public int compare(Map.Entry<Jogador, Double> par1, Map.Entry<Jogador, Double> par2) {
                // Ordenação decrescente
                return par2.getValue().compareTo(par1.getValue());
            }
        });

        Map<Jogador, Double> mapOrdenado = new LinkedHashMap<>();
        for (Map.Entry<Jogador, Double> entrada : lista) {
            mapOrdenado.put(entrada.getKey(), entrada.getValue());
        }

        classificacao = mapOrdenado;

        //TODO: mapOrdenado aqui é uma lista de pares (jogador, pontos) começando com o 1o lugar, implementar na GUI de alguma forma
    }
}

package com.projetofinal322.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que gerencia as fontes de renda disponíveis no jogo.
 * Garante que cada fonte de renda seja ocupada por apenas um jogador por vez.
 */
public class GerenciadorDeFontes { 
    private static Map<FonteDeRenda, Jogador> fontes;

    // Inicializador estático para garantir que o mapa seja inicializado quando a classe for carregada
    static {
        fontes = new HashMap<>();
        for (FonteDeRenda fonte : FonteDeRenda.values()) {
            fontes.put(fonte, null);
        }
    }

    /**
     * Verifica se uma fonte de renda está disponível ou não para ser ocupada.
     * A bolsa-auxílio é uma exceção: sempre estará disponível para todos jogadores.
     * 
     * @param fonteDeRenda a fonte de renda sendo verificada.
     * @return true se a fonte não está ocupada, false se já está ocupada.
     */
    public static boolean disponivel(FonteDeRenda fonteDeRenda) {
        if (!fonteDeRenda.equals(FonteDeRenda.BOLSA_AUXILIO) && fontes.get(fonteDeRenda) != null) {
            return false; // Ocupação já está ocupada
        }
        return true;
    }

    /**
     * Faz com que um jogador ocupe uma fonte de renda.
     * 
     * @param fonteDeRenda fonte de renda a ser ocupada.
     * @param jogador jogador que ocupará a fonte.
     */
    public static void ocupar(FonteDeRenda fonteDeRenda, Jogador jogador) {
        if (jogador.getFonteDeRenda() != null && !jogador.getFonteDeRenda().equals(fonteDeRenda) && 
            !jogador.getFonteDeRenda().equals(FonteDeRenda.BOLSA_AUXILIO)) {
            liberar(jogador.getFonteDeRenda());
        }
        fontes.put(fonteDeRenda, jogador);
        fonteDeRenda.setOcupada(true);
        jogador.setFonteDeRenda(fonteDeRenda);
    }

    /**
     * Libera uma fonte de renda, tornando-a disponível para outros jogadores.
     * 
     * @param fonteDeRenda fonte de renda a ser liberada.
     */
    public static void liberar(FonteDeRenda fonteDeRenda) {
        Jogador jogador = fontes.get(fonteDeRenda);
        if (jogador != null) {
            jogador.setFonteDeRenda(FonteDeRenda.BOLSA_AUXILIO);
            fontes.put(fonteDeRenda, null);
            fonteDeRenda.setOcupada(false);
        }
    }

    /**
     * Retorna uma lista das fontes de renda que estão disponíveis.
     * 
     * @return lista das fontes de renda disponíveis.
     */
    public static List<FonteDeRenda> getFontesDisponiveis() {
        List<FonteDeRenda> fontesDisponiveis = new ArrayList<>();
        for (Map.Entry<FonteDeRenda, Jogador> entry : fontes.entrySet()) {
            if (entry.getValue() == null) {
                fontesDisponiveis.add(entry.getKey());
            }
        }
        return fontesDisponiveis;
    }
}


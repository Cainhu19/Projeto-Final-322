package logica;

import java.util.HashMap;
import java.util.Map;

import logica.grupos.Grupo;

/**
 * Classe que gerencia os grupos(Ligas/Entidades) disponíveis no jogo.
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

    public static boolean disponivel(Grupo grupo) {
        if (grupos.get(grupo) != null) {
            return false;
        }
        return true;
    }

    public static void ocupar(Grupo grupo, Jogador jogador) {
        if (jogador.getGrupo() != null && !jogador.getGrupo().equals(grupo)) {
            liberar(grupo);
        }
        grupos.put(grupo, jogador);
        grupo.setOcupado(true);
        jogador.setGrupo(grupo);
    }

    public static void liberar(Grupo grupo) {
        Jogador jogador = grupos.get(grupo);
        if (jogador != null) {
            jogador.setGrupo(null);
            grupos.put(grupo, null);
            grupo.setOcupado(false);
        }
    }

}

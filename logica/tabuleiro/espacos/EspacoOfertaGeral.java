package logica.tabuleiro.espacos;

import java.util.List;
import java.util.Random;

import logica.*;
import logica.tabuleiro.Espaco;

public class EspacoOfertaGeral extends Espaco {
    private boolean ehOfertaGrupo; // true: sorteia grupo para o jogador escolher, false: sorteia fonte de renda

    public EspacoOfertaGeral(String descricao, boolean ehOfertaGrupo) {
        super(descricao);
        this.ehOfertaGrupo = ehOfertaGrupo;
    }

    @Override
    public void acao(Jogador jogador) {
        imprimirDescricao(descricao);
        if (Entrada.respostaString().equals("s")) {
            Random rand = new Random();
            if (ehOfertaGrupo) {
                List<Grupo> gruposDisponiveis = GerenciadorDeGrupos.getGruposDisponiveis();
                Grupo grupo = gruposDisponiveis.get(rand.nextInt(gruposDisponiveis.size()));
                GerenciadorDeGrupos.ocupar(grupo, jogador);
                jogador.adicionarPontosNetworking(grupo.getBonusNetworking());
                System.out.printf("Novo grupo: %s. (+%d pontos de networking)\n", grupo.getNome(),
                        grupo.getBonusNetworking());
            } else {
                List<FonteDeRenda> fontesDisponiveis = GerenciadorDeFontes.getFontesDisponiveis();
                FonteDeRenda fonteDeRenda = fontesDisponiveis.get(rand.nextInt(fontesDisponiveis.size()));
                GerenciadorDeFontes.ocupar(fonteDeRenda, jogador);
                System.out.printf("Nova fonte de renda: %s.\n", fonteDeRenda.getNome());
            }
        }
    }
}
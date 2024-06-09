package logica.tabuleiro;

import logica.Entrada;
import logica.Jogador;
import logica.grupos_e_ocupacoes.Grupo;
import logica.grupos_e_ocupacoes.Ocupacao;

public class EspacoOportunidade extends Espaco {
    private Grupo grupo;
    private Ocupacao ocupacao;

    // Citar uso de polimorfismo nos construtores
    public EspacoOportunidade(Grupo grupo) {
        this.grupo = grupo;
    }

    public EspacoOportunidade(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    // TODO: revisar isso aqui (principalmente pras ocupações)
    @Override
    public void acao(Jogador jogador) {
        // Oportunidade de entrar num grupo
        if (grupo != null && !grupo.isOcupado()) {
            System.out.printf("Oportunidade de grupo: %s. Deseja entrar? (s/n)\n", grupo.getNome());
            if (Entrada.respotaString().equals("s")) {
                if (jogador.getGrupo() != null) {
                    if (jogador.getGrupo().equals(grupo)) { System.out.println("Você já faz parte desse grupo!"); return;}
                    System.out.printf("Você já faz parte do grupo %s. Você vai sair do grupo atual.\n", jogador.getGrupo().getNome());
                    jogador.getGrupo().setOcupado(false);
                }
                jogador.setGrupo(grupo);
                grupo.setOcupado(true);
                jogador.adicionarPontosNetworking(grupo.getBonusNetworking());
                System.out.printf("Novo grupo: %s. Você ganhou %d pontos de networking!\n", grupo.getNome(), grupo.getBonusNetworking());
            }

        // Oportunidade de ter uma ocupação
        } else if (ocupacao != null && !ocupacao.isOcupada()) {
            System.out.printf("Oportunidade de ocupação: %s (remuneração: %d). Deseja aceitar? (s/n)\n", 
                        ocupacao.getNome(), ocupacao.getRenda());
            if (Entrada.respotaString().equals("s")) {
                if (jogador.getOcupacao() != null) {
                    if (jogador.getOcupacao().equals(ocupacao)) { System.out.println("Você já tem essa ocupação!"); return;}
                    System.out.printf("Você já tem uma ocupação: %s. Você vai sair da ocupação atual.\n", jogador.getOcupacao().getNome());
                    jogador.getOcupacao().setOcupada(false);
                }
                jogador.setOcupacao(ocupacao);
                ocupacao.setOcupada(true);
                System.out.printf("Nova ocupação: %s.\n", ocupacao.getNome());
            }
        }
    }
}
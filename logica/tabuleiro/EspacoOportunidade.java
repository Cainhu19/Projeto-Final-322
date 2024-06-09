package logica.tabuleiro;

import logica.Entrada;
import logica.Jogador;
import logica.grupos_e_ocupacoes.Grupo;
import logica.grupos_e_ocupacoes.Ocupacao;

public class EspacoOportunidade extends Espaco {
    private Grupo grupo;
    private Ocupacao ocupacao;

    // Citar uso de polimorfismo nos construtores
    public EspacoOportunidade(String descricao, Grupo grupo) {
        super(descricao);
        this.grupo = grupo;
    }

    public EspacoOportunidade(String descricao, Ocupacao ocupacao) {
        super(descricao);
        this.ocupacao = ocupacao;
    }

    // TODO: revisar esse método
    @Override
    public void acao(Jogador jogador) {
        // Oportunidade de entrar num grupo
        if (grupo != null && !grupo.isOcupado()) {
            System.out.println(descricao);
            if (Entrada.respostaString().equals("s")) {
                if (jogador.getGrupo() != null) {
                    if (jogador.getGrupo().equals(grupo)) { System.out.println("Você já faz parte desse grupo!"); return; }
                    System.out.printf("Você já faz parte do grupo %s. Você vai sair do grupo atual.\n", jogador.getGrupo().getNome());
                    jogador.getGrupo().setOcupado(false);
                }
                jogador.setGrupo(grupo);
                grupo.setOcupado(true);
                jogador.adicionarPontosNetworking(grupo.getBonusNetworking());
                System.out.printf("Novo grupo: %s. (+%d pontos de networking)\n", grupo.getNome(), grupo.getBonusNetworking());
            } else if (Entrada.respostaString().equals("n")) { 
                System.out.println("Você não entrou no grupo."); 
            }

        // Oportunidade de ter uma ocupação (mudar pra fonte de renda?)
        } else if (ocupacao != null && !ocupacao.isOcupada()) {
            System.out.println(descricao); 
            if (Entrada.respostaString().equals("s")) {
                if (jogador.getOcupacao() != null) {
                    if (jogador.getOcupacao().equals(ocupacao)) { System.out.println("Você já tem essa fonte de renda!"); return;}
                    System.out.printf("Você já tem uma fonte de renda: %s. Você deixará de ter essa fonte.\n", jogador.getOcupacao().getNome());
                    jogador.getOcupacao().setOcupada(false);
                }
                jogador.setOcupacao(ocupacao);
                ocupacao.setOcupada(true);
                System.out.printf("Nova fonte de renda: %s. (remuneração: %d)\n", ocupacao.getNome(), ocupacao.getRemuneracao());
            } else if (Entrada.respostaString().equals("n")) { 
                System.out.println("Você recusou a proposta."); 
            }
        }
    }
}
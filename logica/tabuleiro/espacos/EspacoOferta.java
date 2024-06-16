package logica.tabuleiro.espacos;

import logica.*;
import logica.tabuleiro.Espaco;

/**
 * Classe de espaços em que o jogador recebe uma oferta, seja ela de fonte de renda ou de um grupo.
 */
public class EspacoOferta extends Espaco {
    private Grupo grupo;
    private GrupoConjunto grupoConjunto;
    private FonteDeRenda fonteDeRenda;

    //TODO: Citar uso de polimorfismo nos construtores
    // Construtor para a oferta de uma fonte de renda
    public EspacoOferta(String descricao, FonteDeRenda fonteDeRenda) {
        super(descricao);
        this.grupo = null;
        this.grupoConjunto = null;
        this.fonteDeRenda = fonteDeRenda;
    }

    // Construtor para a oferta de um grupo
    public EspacoOferta(String descricao, Grupo grupo) {
        super(descricao);
        this.grupo = grupo;
        this.grupoConjunto = null;
        this.fonteDeRenda = null;
    }

    // Construtor para a oferta de um conjunto de grupos
    public EspacoOferta(String descricao, GrupoConjunto conjunto) {
        super(descricao);
        this.grupo = null;
        this.grupoConjunto = conjunto;
        this.fonteDeRenda = null;
    }

    //TODO: revisar esse método
    @Override
    public void acao(Jogador jogador) {
        // Oportunidade de ter uma fonte de renda/ocupação
        if (fonteDeRenda != null) {
            if (!GerenciadorDeFontes.disponivel(fonteDeRenda)) {
                if (jogador.getFonteDeRenda() != null && !jogador.getFonteDeRenda().equals(fonteDeRenda)) {
                    System.out.printf("A fonte de renda %s já está sendo ocupada por um jogador.\n",
                            fonteDeRenda.getNome());
                } else {
                    System.out.printf("%s já é sua fonte de renda!\n", fonteDeRenda.getNome());
                }
            } else {
                imprimirDescricao(descricao);
                if (Entrada.respostaString().equals("s")) {
                    if (fonteDeRenda.equals(FonteDeRenda.BOLSA_AUXILIO)) {
                        if (jogador.getFonteDeRenda() != null) {
                            GerenciadorDeFontes.liberar(jogador.getFonteDeRenda());
                        } else {
                            jogador.setFonteDeRenda(fonteDeRenda);
                        }
                    } else {
                        GerenciadorDeFontes.ocupar(fonteDeRenda, jogador);
                    }
                    System.out.printf("Nova fonte de renda: %s.\n", fonteDeRenda.getNome());
                }
            }

        // Oportunidade de entrar num grupo
        } else if (grupo != null) {
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

        // Oportunidade de entrar em um grupo que faz parte de um conjunto (Atlética, C.A., Liga Acadêmica)
        } else if (grupoConjunto != null) {
            Grupo primeiroGrupo = grupoConjunto.getGrupos().get(0);
            Grupo segundoGrupo = grupoConjunto.getGrupos().get(1);
            if (!GerenciadorDeGrupos.disponivel(primeiroGrupo) && !GerenciadorDeGrupos.disponivel(segundoGrupo)) {
                if (jogador.getGrupo() != null && !jogador.getGrupo().equals(primeiroGrupo) || !jogador.getGrupo().equals(segundoGrupo)) {
                    System.out.printf("Dois jogadores já ocupam %s.\n", grupoConjunto.getNome());
                } else {
                    System.out.printf("Você já faz parte de %s! (%s)\n", grupoConjunto.getNome(), jogador.getGrupo().getNome());
                }
            } else {
                imprimeDescricao();
                if (Entrada.respostaString().equals("s")) {
                    if (GerenciadorDeGrupos.disponivel(primeiroGrupo)) {
                        GerenciadorDeGrupos.ocupar(primeiroGrupo, jogador);
                        jogador.adicionarPontosNetworking(primeiroGrupo.getBonusNetworking());
                        System.out.printf("Novo grupo: %s. (+%d pontos de networking)\n", primeiroGrupo.getNome(),
                        primeiroGrupo.getBonusNetworking());
                    } else {
                        GerenciadorDeGrupos.ocupar(segundoGrupo, jogador);
                        jogador.adicionarPontosNetworking(segundoGrupo.getBonusNetworking());
                        System.out.printf("Novo grupo: %s. (+%d pontos de networking)\n", segundoGrupo.getNome(),
                        segundoGrupo.getBonusNetworking());
                    }
                }
            }
        }
    }
}
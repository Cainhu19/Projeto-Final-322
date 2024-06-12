package logica.tabuleiro;

import logica.Entrada;
import logica.FonteDeRenda;
import logica.GerenciadorDeFontes;
import logica.GerenciadorDeGrupos;
import logica.Grupo;
import logica.Jogador;

public class EspacoOportunidade extends Espaco {
    private Grupo grupo;
    private FonteDeRenda fonteDeRenda;

    // Citar uso de polimorfismo nos construtores
    public EspacoOportunidade(String descricao, Grupo grupo) {
        super(descricao);
        this.grupo = grupo;
    }

    public EspacoOportunidade(String descricao, FonteDeRenda fonteDeRenda) {
        super(descricao);
        this.fonteDeRenda = fonteDeRenda;
    }

    // TODO: revisar esse método
    @Override
    public void acao(Jogador jogador) {
        // Oportunidade de ter uma fonte de renda/ocupação
        if (fonteDeRenda != null) {
            if (!GerenciadorDeFontes.disponivel(fonteDeRenda)) {
                if (!jogador.getFonteDeRenda().equals(fonteDeRenda)) {
                    System.out.printf("A fonte de renda %s já está sendo ocupada por um jogador.\n",
                            fonteDeRenda.getNome());
                } else {
                    System.out.printf("%s já é sua fonte de renda!\n", fonteDeRenda.getNome());
                }
            } else {
                System.out.println(descricao);
                if (Entrada.respostaString().equals("s")) {
                    if (fonteDeRenda.equals(FonteDeRenda.BOLSA_AUXILIO)) {
                        jogador.setFonteDeRenda(fonteDeRenda);
                    } else {
                        GerenciadorDeFontes.ocupar(fonteDeRenda, jogador);
                    }
                    System.out.printf("Nova fonte de renda: %s.\n", fonteDeRenda.getNome());
                }
            }

            // Oportunidade de entrar num grupo
        } else if (grupo != null) {
            if (grupo.isOcupado()) {
                if (!jogador.getGrupo().equals(grupo)) {
                    System.out.printf("Um jogador já faz parte de %s.\n", grupo.getNome());
                } else {
                    System.out.printf("Você já faz parte de %s!\n", grupo.getNome());
                }
            } else {
                System.out.println(descricao);
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
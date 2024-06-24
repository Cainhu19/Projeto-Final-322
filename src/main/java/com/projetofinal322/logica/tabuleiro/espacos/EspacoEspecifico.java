package com.projetofinal322.logica.tabuleiro.espacos;

import com.projetofinal322.logica.tabuleiro.Espaco;
import com.projetofinal322.logica.tabuleiro.Tabuleiro;
import com.projetofinal322.logica.*;

/**
 * Classe de espaços específicos que ocorrem apenas uma vez (ou duas, se suas
 * ações forem idênticas) no tabuleiro.
 * A ação de cada espaço é identificada com um parâmetro String id usado no
 * método de ação em um switch.
 */
public class EspacoEspecifico extends Espaco {
    private final String id;

    public EspacoEspecifico(String descricao, String id) {
        super(descricao);
        this.id = id;
    }

    @Override
    public void acao(Jogador jogador) {
        System.out.println(imprimirDescricao());
        switch (id) {
            case "PAGAR_COM_OUTRO_JOGADOR":
                // Espaço 25 (escolha um jogador para rachar uma pizza de 120 com você)
                Jogador jogadorEscolhidoParaRachar = escolherOutroJogador(jogador);
                jogador.ajustarDinheiro(-60);
                jogadorEscolhidoParaRachar.ajustarDinheiro(-60);
                System.out.println("Cada um gastou 60 com a pizza.");
                break;

            case "DINHEIRO_EM_FUNCAO_DE_DADO":
                // Espaço 28 (venceu aposta com os amigos, cada jogador te deve 10 vezes o valor
                // de um 1d10 rodado)
                int qtdDinheiro = jogador.resultadoDado(0) * 10;
                int vezes = 0;
                for (int i = 0; i < Jogo.getJogadores().size(); i++) {
                    if (!Jogo.getJogadores().get(i).equals(jogador)) {
                        jogador.ajustarDinheiro(qtdDinheiro);
                        Jogo.getJogadores().get(i).ajustarDinheiro(-qtdDinheiro);
                        System.out.printf("%s perdeu %d.\n", Jogo.getJogadores().get(i).getNome(), qtdDinheiro);
                        vezes++;
                    }
                }
                System.out.printf("Você ganhou %d no total!\n", qtdDinheiro * vezes);
                break;

            case "PAGAR_PARA_P_NETWORKING":
                // Espaço 31 (pague 120 reais para ir na viagem de férias com 80 pessoas do seu
                // curso)
                if (Entrada.respostaString().equals("s")) {
                    jogador.ajustarDinheiro(-120);
                    jogador.adicionarPontosNetworking(80);
                    System.out.println("Você conseguiu 80 pontos de networking com a viagem!");
                }
                break;

            case "DINHEIRO_SE_TIVER_RENDA_ESPECIFICA":
                // Espaço 38a (ações de pequenas empresas estão em alta, receba 4000 se tiver um
                // empreendimento)
                if (jogador.getFonteDeRenda().equals(FonteDeRenda.EMPREENDIMENTO)) {
                    jogador.ajustarDinheiro(4000);
                    System.out.println("Você recebeu 4000 na sua conta!");
                }
                break;

            case "PAGAR_PARA_ESTUDO":
                // Espaço 37b (pague um curso online de 150 para estudar um conteúdo de nicho)
                if (Entrada.respostaString().equals("s")) {
                    jogador.ajustarDinheiro(-150);
                    jogador.incrementarVezesEstudo();
                    System.out.println("Você pagou o curso. (-150)");
                }
                break;

            case "RECEBER_OFERTA_EMPREGO_SE_P_NETWORKING_SUFICIENTE":
                // Espaço 39b (se tiver networking >= 150, um colega te indica para uma vaga de
                // emprego onde ele trabalha)
                if (jogador.getPontosNetworking() >= 150) {
                    if (!GerenciadorDeFontes.disponivel(FonteDeRenda.EMPREGO)) {
                        if (jogador.getFonteDeRenda() != null
                                && !jogador.getFonteDeRenda().equals(FonteDeRenda.EMPREGO)) {
                            System.out.printf("A fonte de renda %s já está sendo ocupada por um jogador.\n",
                                    FonteDeRenda.EMPREGO.getNome());
                        } else {
                            System.out.printf("%s já é sua fonte de renda!\n", FonteDeRenda.EMPREGO.getNome());
                        }
                    } else {
                        if (Entrada.respostaString().equals("s")) {
                            if (FonteDeRenda.EMPREGO.equals(FonteDeRenda.BOLSA_AUXILIO)) {
                                if (jogador.getFonteDeRenda() != null) {
                                    GerenciadorDeFontes.liberar(jogador.getFonteDeRenda());
                                } else {
                                    jogador.setFonteDeRenda(FonteDeRenda.EMPREGO);
                                }
                            } else {
                                GerenciadorDeFontes.ocupar(FonteDeRenda.EMPREGO, jogador);
                            }
                            System.out.printf("Nova fonte de renda: %s.\n", FonteDeRenda.EMPREGO.getNome());
                        }
                    }
                }
                break;

            case "DINHEIRO_POR_P_NETWORKING":
                // Espaço 45b (é seu aniversário! ganhe 10 de presente para cada 10 pontos de
                // networking)
                int bonusDinheiro = (jogador.getPontosNetworking() / 10) * 10;
                jogador.ajustarDinheiro(bonusDinheiro);
                System.out.printf("Você recebeu %d na sua conta!\n", bonusDinheiro);
                break;

            case "CAMPEONATO_ATLETICA":
                // Espaço 46b (campeonato da Atlética)
                for (int i = 0; i < Jogo.getJogadores().size(); i++) {
                    Jogador jogadorExaminado = Jogo.getJogadores().get(i);
                    if (jogadorExaminado.getGrupo().equals(Grupo.ATLETICA_ESPORTES)
                            || jogadorExaminado.getGrupo().equals(Grupo.ATLETICA_EVENTOS)) {
                        jogadorExaminado.ajustarPontosOportunidade(50);
                        System.out.printf("%s ganhou 50 pontos de oportunidade!\n", jogadorExaminado.getNome());
                    }
                }
                break;

            case "VOLTAR_SE_NAO_TIVER_ESTUDO":
                // Espaço 49b (se não tiver estudado, você foi mal em uma prova e deve voltar
                // até o espaço 36b para estudar mais)
                if (jogador.getVezesEstudo() <= 0) {
                    jogador.setPosicao(new int[] { 5, 0 });
                    System.out.println("Você voltou para o começo da bifurcação.");
                }
                break;

            case "ROLAR_DADO_DE_NOVO":
                // Espaços 50a e 64 (tempo com a família revigorou suas energias, role o dado de
                // novo / motivação súbita, role o dado de novo)
                int resultadoDois = jogador.resultadoDado(0);
                System.out.printf("Jogador %s joga o dado: %d\n", jogador.getNome(), resultadoDois);
                Jogo.getInstance(Jogo.getJogadores()).getTabuleiro().moverJogador(jogador, resultadoDois);
                break;

            case "CASA_DO_AZAR":
                // Espaço 56 (casa do azar: rode 1d10 e se tirar 1 ou 2 você terá que sair da
                // sua entidade estudantil)
                if (jogador.getGrupo() == null) {
                    System.out.println("Você não está em nenhum grupo!");
                    break;
                }
                int resultadoDado = jogador.resultadoDado(0);
                if (resultadoDado == 1 || resultadoDado == 2) {
                    System.out.printf("Você tirou %d no dado e precisou sair de %s.\n", resultadoDado,
                            jogador.getGrupo().getNome());
                    GerenciadorDeGrupos.liberar(jogador.getGrupo());
                    break;
                }
                System.out.printf("Você tirou %d no dado e pode continuar no seu grupo.\n", resultadoDado);
                break;

            case "GANHAR_P_NETWORKING_COM_MAIOR_DADO":
                // Espaço 57 (desafie um jogador a uma competição de dança em uma festa; quem
                // ganhar vai impressionar 30 pessoas (ganha o maior dado))
                Jogador jogadorDesafiado = escolherOutroJogador(jogador);
                int resultadoPrimeiroDado = jogador.resultadoDado(0);
                System.out.printf("%s tirou %d no dado.\n", jogador.getNome(), resultadoPrimeiroDado);
                int resultadoSegundoDado = jogadorDesafiado.resultadoDado(0);
                System.out.printf("%s tirou %d no dado.\n", jogadorDesafiado.getNome(), resultadoSegundoDado);
                if (resultadoPrimeiroDado > resultadoSegundoDado) {
                    jogador.adicionarPontosNetworking(30);
                    System.out.printf("%s ganhou 30 pontos de networking!\n", jogador.getNome());
                    break;
                } else if (resultadoPrimeiroDado < resultadoSegundoDado) {
                    jogadorDesafiado.adicionarPontosNetworking(30);
                    System.out.printf("%s ganhou 30 pontos de networking!\n", jogador.getNome());
                    break;
                }
                jogador.adicionarPontosNetworking(15);
                jogadorDesafiado.adicionarPontosNetworking(15);
                System.out.println("Ambos dançaram muito bem e ganharam 15 pontos de networking cada!");
                break;

            case "OPORTUNIDADE_DE_INTERCAMBIO":
                // Espaço 62 (bolsa de intercâmbio no Canadá: você pode ir se tiver estudado 2x
                // e pagar 1000 pela burocracia imigratória)
                if (jogador.getVezesEstudo() >= 2) {
                    if (Entrada.respostaString().equals("s")) {
                        jogador.ajustarDinheiro(-1000);
                        jogador.setPosicao(new int[] { 13, 0 });
                        System.out.println("Você agora está em um intercâmbio no Canadá!");
                        break;
                    }
                }
                System.out.println("Você não tem os requisitos necessários para conseguir a bolsa.");
                break;

            case "PERDER_RENDA":
                // Espaço 67 (perca sua fonte de renda (se não for bolsa-auxílio))
                if (jogador.getFonteDeRenda() != null
                        && !jogador.getFonteDeRenda().equals(FonteDeRenda.BOLSA_AUXILIO)) {
                    GerenciadorDeFontes.liberar(jogador.getFonteDeRenda());
                    System.out.println("Você perdeu sua fonte de renda e recebeu uma bolsa-auxílio como compensação.");
                }
                break;

            case "PROMOCAO_LOJA":
                // Espaço 69 (promoção relâmpago na Lojinha do Destino: tudo com 50% de desconto
                // (para quem parar aqui e apenas quando parar aqui))
                Loja.promocaoRelampago(jogador);
                break;

            case "PERDER_GRUPO":
                // Espaço 73 (por falta de tempo, você precisou sair da sua entidade estudantil)
                if (jogador.getGrupo() != null) {
                    GerenciadorDeGrupos.liberar(jogador.getGrupo());
                    System.out.println("Você saiu da entidade.");
                }
                break;

            case "ESCOLHER_JOGADOR_PARA_VOLTAR":
                // Espaço 76 (emprestou anotações erradas a um jogador e ele precisou voltar 5
                // casas para estudar o conteúdo corretamente)
                Jogador jogadorComAnotacoes = escolherOutroJogador(jogador);
                Tabuleiro.getInstance().moverJogador(jogadorComAnotacoes, -5);
                System.out.printf("%s voltou 5 casas.\n", jogadorComAnotacoes.getNome());
                break;

            case "DINHEIRO_DE_JOGADOR":
                // Espaço 77 (você está em apuros financeiros, peça 300 emprestados de um
                // jogador)
                Jogador jogadorQueEmpresta = escolherOutroJogador(jogador);
                if (jogadorQueEmpresta.getDinheiro() < 300) {
                    System.out.printf("%s ajudou %s como pôde, emprestando todo o seu dinheiro (%d) por uma boa causa.\n",
                            jogadorQueEmpresta.getNome(), jogador.getNome(), jogadorQueEmpresta.getDinheiro());
                    jogador.ajustarDinheiro(jogadorQueEmpresta.getDinheiro());
                    jogadorQueEmpresta.ajustarDinheiro(-jogadorQueEmpresta.getDinheiro());
                } else {
                    System.out.printf("%s ajudou %s emprestando 300.\n", jogadorQueEmpresta.getNome(), jogador.getNome());
                    jogador.ajustarDinheiro(300);
                    jogadorQueEmpresta.ajustarDinheiro(-300);
                }
                break;

            case "SAIR_GRUPO_GANHAR_P_OPORTUNIDADES":
                /**
                 * Espaço 96b (se você está em uma entidade estudantil, você acaba de sair dela
                 * deixando um ótimo legado,
                 * ganhando +250 pontos de oportunidade por isso)
                 */
                if (jogador.getGrupo() != null) {
                    GerenciadorDeGrupos.liberar(jogador.getGrupo());
                    jogador.ajustarPontosOportunidade(250);
                    System.out.println("Você saiu da entidade e ganhou 250 pontos de oportunidade.");
                    break;
                }
                System.out.println("Você não faz parte de nenhuma entidade.");
                break;

            case "TENTAR_ROUBAR_P_OPORTUNIDADES":
                // Espaço 100b (tente copiar os passos de uma pessoa e se destacar mais que ela:
                // se conseguir, ganhe 100 pontos de oportunidade da pessoa, se não, perca 100
                // pontos para ela (decidido em dado))
                Jogador jogadorCopiado = escolherOutroJogador(jogador);
                int resultadoDadoUm = jogador.resultadoDado(0);
                System.out.printf("%s tirou %d no dado.\n", jogador.getNome(), resultadoDadoUm);
                int resultadoDadoDois = jogadorCopiado.resultadoDado(0);
                System.out.printf("%s tirou %d no dado.\n", jogadorCopiado.getNome(), resultadoDadoDois);
                if (resultadoDadoUm > resultadoDadoDois) {
                    jogador.ajustarPontosOportunidade(100);
                    jogadorCopiado.ajustarPontosOportunidade(-100);
                    System.out.printf("%s copiou o que %s fazia e ganhou 100 pontos de oportunidades de %s!\n",
                            jogador.getNome(), jogadorCopiado.getNome(), jogadorCopiado.getNome());
                    break;
                } else if (resultadoDadoUm < resultadoDadoDois) {
                    jogador.ajustarPontosOportunidade(-100);
                    jogadorCopiado.ajustarPontosOportunidade(100);
                    System.out.printf(
                            "%s passou vergonha tentando imitar %s e perdeu 100 pontos de oportunidades para %s!\n",
                            jogador.getNome(), jogadorCopiado.getNome(), jogadorCopiado.getNome());
                    break;
                }
                jogador.ajustarPontosOportunidade(-50);
                jogadorCopiado.ajustarPontosOportunidade(-50);
                System.out.println("Os dois jogadores se envolveram em uma polêmica e perderam 50 pontos de oportunidades cada!");
                break;

            case "CASA_DA_CARIDADE":
                // Espaço 102 (CASA DA CARIDADE)
                Tabuleiro.getInstance().incrementarQtdJogadoresCaridade();
                int qtdJogadoresCaridade = Tabuleiro.getInstance().getQtdJogadoresCaridade();
                if (qtdJogadoresCaridade < Jogo.getJogadores().size()) {
                    jogador.ajustarDinheiro(-200);
                    System.out.println("Você doou 200 para a caridade.");
                    break;
                }
                int qtdAcumulada = (qtdJogadoresCaridade - 1) * 200;
                jogador.ajustarDinheiro(qtdAcumulada);
                System.out.printf("Você recebeu %d pela generosidade dos outros jogadores!\n", qtdAcumulada);
                break;

            case "ESTUDO_PARA_P_OPORTUNIDADES":
                // Espaço 109 (se tiver estudado 5x, se forme com honras acadêmicas e receba
                // +400 pontos de oportunidades acadêmicas futuras)
                if (jogador.getVezesEstudo() >= 5) {
                    jogador.ajustarPontosOportunidade(400);
                    System.out.println("Você recebeu 400 pontos de oportunidade!");
                    break;
                }
                System.out.println("Você não estudou o suficiente para se formar com honras acadêmicas.");
                break;

            case "P_NETWORKING_EM_FUNCAO_DE_DADO":
                // Espaço 110 (cole grau com número de pessoas igual a 6 vezes o número de um
                // 1d10)
                int qtdPessoas = 6 * jogador.resultadoDado(0);
                System.out.printf("Você tirou %d no dado.\n", qtdPessoas / 6);
                jogador.adicionarPontosNetworking(qtdPessoas);
                System.out.printf("Você ganhou %d pontos de networking!\n", qtdPessoas);
                break;

            default:
                throw new IllegalArgumentException("ID não reconhecido de espaço específico: " + id);
        }
    }

    private Jogador escolherOutroJogador(Jogador jogador) {
        int qtdJogadores = Jogo.getJogadores().size();
        Jogador jogadorEscolhido = null;
        for (int i = 0; i < qtdJogadores; i++) {
            if (!Jogo.getJogadores().get(i).equals(jogador)) {
                System.out.printf("%d - %s\n", i + 1, Jogo.getJogadores().get(i).getNome());
            }
        }
        int escolha = Entrada.respostaInt();
        if (escolha >= 1 && escolha <= qtdJogadores && escolha != Jogo.getJogadores().indexOf(jogador) + 1) {
            jogadorEscolhido = Jogo.getJogadores().get(escolha - 1);
            System.out.printf("Jogador escolhido: %s\n", jogadorEscolhido.getNome());
        }
        return jogadorEscolhido;
    }
}

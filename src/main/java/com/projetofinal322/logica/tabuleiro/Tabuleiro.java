package com.projetofinal322.logica.tabuleiro;

import java.util.LinkedList;

import com.projetofinal322.logica.Entrada;
import com.projetofinal322.logica.FonteDeRenda;
import com.projetofinal322.logica.Jogador;
import com.projetofinal322.logica.tabuleiro.espacos.EspacoRemuneracao;

/**
 * Classe que representa o tabuleiro do jogo. Ele é tratado como Singleton.
 */
public class Tabuleiro {
    private static Tabuleiro tabuleiroInstance;
    private LinkedList<Caminho> caminhos;
    private int qtdJogadoresCaridade; // armazena quantos jogadores passaram pela casa de caridade

    private Tabuleiro() {
        this.caminhos = new LinkedList<Caminho>();
        this.qtdJogadoresCaridade = 0;
    }

    public static Tabuleiro getInstance() {
        if (tabuleiroInstance == null) {
            tabuleiroInstance = new Tabuleiro();
        }
        return tabuleiroInstance;
    }

    public LinkedList<Caminho> getCaminhos() {
        return caminhos;
    }

    public int getNumeroCaminhos() {
        return caminhos.size();
    }

    public int getQtdJogadoresCaridade() {
        return qtdJogadoresCaridade;
    }

    public void incrementarQtdJogadoresCaridade() {
        qtdJogadoresCaridade++;
    }

    /**
     * Verifica se o jogador entrou em uma bifurcação. Se for múltiplo de 3 + 1 (1,
     * 4, 7...) e não for o intercâmbio retorna true.
     * 
     * @param caminho índice do caminho.
     */
    private boolean jogadorEntrouEmBifurcacao(int caminho) {
        return (caminho - 1) % 3 == 0 && caminho != 13; // Exclui o caminho equivalente ao intercâmbio
    }

    /**
     * Permite que o jogador escolha qual caminho seguir quando encontra uma
     * bifurcação.
     * 
     * @param jogador jogador que entrou na bifurcação.
     * @param caminho caminho que determina qual bifurcação do tabuleiro o jogador
     *                entrou.
     * @param scan    scanner que lê a entrada, ou seja, a escolha do jogador.
     * @return
     */
    private int jogadorEscolheBifurcacao(Jogador jogador, int caminho) {
        System.out.println("Você chegou em uma bifurcação. Qual caminho deseja seguir? (digite 1 ou 2)");
        if (caminho == 1) {
            System.out.println("1. Focar em dinheiro");
            System.out.println("2. Focar em socialização e oportunidades");
        }
        int escolha = Entrada.respostaInt();
        switch (escolha) {
            case 1:
                if (caminho == 1) {
                    jogador.setMultiplicadorDinheiro(0.002);
                }
                return caminho;
            case 2:
                if (caminho == 1) {
                    jogador.setMultiplicadorOportunidade(2);
                    jogador.setMultiplicadorNetworking(0.7);
                }
                return caminho + 1;
            // alguma coisa no caso default (tratar exceção de escolha inválida??)
        }
        return caminho;
    }

    /**
     * Verifica se tem espaços obrigatórios em determinado intervalo de um caminho.
     * Se tiver, executa a ação de cada um.
     * 
     * @param jogador       jogador sobre o qual a ação vai ser executada.
     * @param caminho       caminho atual do jogador.
     * @param espacoInicial espaço inicial do intervalo.
     * @param limite        limite do intervalo.
     */
    private void executarEspacosObrigatorios(Jogador jogador, int caminho, int espacoInicial, int limite) {
        for (int i = espacoInicial; i < limite; i++) {
            Espaco espacoIntermediario = caminhos.get(caminho).getEspacos().get(i);
            // Verifica se o espaço é um espaço de remuneração ou a casa da caridade
            if (espacoIntermediario instanceof EspacoRemuneracao || caminho == 12 && i == 1) {
                espacoIntermediario.acao(jogador);
            }
        }
    }

    /**
     * Move o jogador no tabuleiro baseado na quantidade de espaços determinada.
     * 
     * @param jogador    jogador que vai se mover.
     * @param quantidade quantos espaços serão percorridos (positivo: jogador
     *                   avança; negativo: jogador volta)
     */
    public void moverJogador(Jogador jogador, int quantidade) {
        if (quantidade == 0) { // Caso o jogador ande 0 espaços (fica parado / perde uma rodada)
            System.out.println("Rodada perdida. Na próxima, já poderá voltar a andar.");
            return;
        }
        int caminhoAtual = jogador.getPosicao()[0];
        int espacoAtual = jogador.getPosicao()[1];
        int espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        int novoEspaco = espacoAtual + quantidade; // novo espaço do jogador após se mover

        if (quantidade > 0 && jogador.getGrupo() != null) {
            jogador.ajustarPontosOportunidade(quantidade);
        }
        // Jogador avança um caminho
        int caminhosAvancados = 0;
        while (caminhoAtual < caminhos.size() && novoEspaco >= espacosCaminhoAtual) {
            // Verifica e executa espaços obrigatórios entre o espaço atual e o fim do
            // caminho
            if (caminhosAvancados > 0) {
                executarEspacosObrigatorios(jogador, caminhoAtual, 0, espacosCaminhoAtual);
            } else {
                executarEspacosObrigatorios(jogador, caminhoAtual, espacoAtual + 1, espacosCaminhoAtual);
            }
            novoEspaco -= espacosCaminhoAtual;
            caminhoAtual++;
            // Verifica se entrou no caminho do intercâmbio a partir do último caminho do
            // tabuleiro normal e corrige a posição para o fim do tabuleiro
            if (caminhoAtual == 13) {
                caminhoAtual--;
                novoEspaco = caminhos.get(caminhoAtual).getNumeroEspacos() - 1;
                espacoAtual = novoEspaco;
                break;
            }
            // Verifica todos os espaços entre espacoAtual - 1 e espacosCaminhoAtual para
            // ver se tem um espaço obrigatório
            // True se ele entrar numa bifurcação pela primeira vez (vai sempre entrar pela
            // bifurcação A primeiro)
            if (jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)
                    &&
                    !jogador.getBifurcacoesPercorridas().contains(caminhoAtual + 1)) {
                caminhoAtual = jogadorEscolheBifurcacao(jogador, caminhoAtual);
                jogador.adicionarBifurcacaoPercorrida(caminhoAtual);
            }
            // True se caminhoAtual igualar ao da bifurcação A mas jogador decidiu percorrer
            // a B anteriormente (e vice-versa)
            if (jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)
                    ||
                    (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
                caminhoAtual++;
            }
            // Verifica se saiu da primeira bifurcação sem uma fonte de renda
            if (caminhoAtual == 3 && jogador.getFonteDeRenda() == null) {
                jogador.setFonteDeRenda(FonteDeRenda.BOLSA_AUXILIO);
                System.out.printf("A universidade decidiu lhe dar uma bolsa-auxílio! (%d de remuneração)\n",
                        FonteDeRenda.BOLSA_AUXILIO.getRenda());
            }
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            if (caminhoAtual >= caminhos.size()) { // Verifica se chegou no final do tabuleiro (último espaço do
                                                   // intercâmbio)
                caminhoAtual = 9;
                novoEspaco = 4;
                espacoAtual = novoEspaco;
                caminhosAvancados++;
            }
        }

        // Jogador volta um caminho
        while (caminhoAtual >= 0 && novoEspaco < 0) {
            caminhoAtual--;
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            // Verifica se o jogador entrou na bifurcação errada: se entrou, volta mais um
            // caminho (ou seja, B -> A ou A -> caminho normal)
            if ((jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual))
                    ||
                    (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
                caminhoAtual--;
            }
            if (caminhoAtual < 0) { // Verifica se voltou pro início do tabuleiro (não sei se vai ser possível mas
                                    // sla)
                caminhoAtual = 0;
                novoEspaco = 0;
                break;
            }
            novoEspaco += espacosCaminhoAtual;
        }
        jogador.setPosicao(new int[] { caminhoAtual, novoEspaco });
        if (quantidade > 0) { // Só executa a ação do espaço se o jogador tiver avançado no tabuleiro, não
                              // voltado
            if (caminhosAvancados > 0) {
                executarEspacosObrigatorios(jogador, caminhoAtual, 0, novoEspaco);
            } else {
                executarEspacosObrigatorios(jogador, caminhoAtual, espacoAtual + 1, novoEspaco);
            }
            caminhos.get(caminhoAtual).getEspacos().get(novoEspaco).acao(jogador);
        }
    }

    /**
     * Adiciona um caminho à lista de caminhos
     * 
     * @param caminho caminho a ser adicionado.
     */
    public void adicionarCaminho(Caminho caminho) {
        caminhos.add(caminho);
    }
}

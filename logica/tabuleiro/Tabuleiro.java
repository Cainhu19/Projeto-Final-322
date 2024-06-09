package logica.tabuleiro;

import java.util.LinkedList;
import java.util.Scanner;

import logica.Entrada;
import logica.Jogador;

public class Tabuleiro {
    private static Tabuleiro tabuleiroInstance;
    private LinkedList<Caminho> caminhos;

    private Tabuleiro() {
        this.caminhos = new LinkedList<Caminho>();
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

    // TODO: ajustar a lógica dos três métodos de movimentação abaixo pq acho que
    // ficou bem gambiarra
    /**
     * Verifica se o jogador precisa mudar de caminho.
     * 
     * @param jogador
     */
    private boolean checaFimCaminho(Jogador jogador) {
        if (jogador.getPosicao()[1] == caminhos.get(jogador.getPosicao()[0]).getNumeroEspacos() - 1) {
            return true;
        }
        return false;
    }

    /**
     * Permite que o jogador escolha qual caminho seguir quando encontra uma
     * bifurcação.
     * 
     * @param jogador jogador que entrou na bifurcação.
     * @return
     */
    private Caminho jogadorMudaCaminho(Jogador jogador) {
        System.out.println("Você chegou em uma bifurcação. Qual caminho deseja seguir?");
        if (jogador.getPosicao()[0] == 0) {
            System.out.println("1. Universidade particular");
            System.out.println("2. Universidade pública");

        }
        if (jogador.getPosicao()[0] == 3) {
            System.out.println("1. Caminho do networking");
            System.out.println("2. Caminho do aprimoramento profissional");
        }
        // adicionar outras bifurcações
        if (jogador.getPosicao()[0] == 0 || jogador.getPosicao()[0] == 3) {
            int escolha = Entrada.escolhaBifurcacao();
            jogador.setPosicao(1, 0);
            switch (escolha) {
                case 1:
                    jogador.setPosicao(0, jogador.getPosicao()[0] + 1);
                    return caminhos.get(jogador.getPosicao()[0]);
                case 2:
                    jogador.setPosicao(0, jogador.getPosicao()[0] + 2);
                    return caminhos.get(jogador.getPosicao()[0]);
                // alguma coisa no caso default (tratar exceção de escolha inválida??)
            }
        }
        jogador.setPosicao(0, jogador.getPosicao()[0] + 1);
        return caminhos.get(jogador.getPosicao()[0]);
    }

    /**
     * Move o jogador no tabuleiro baseado na quantidade de espaços determinada.
     * 
     * @param jogador    jogador que vai se mover.
     * @param quantidade quantos espaços serão percorridos (positivo: jogador
     *                   avança; negativo: jogador volta)
     * @param scan       scanner que lê a entrada do jogador.
     */
    public void moverJogador(Jogador jogador, int quantidade) {
        Caminho caminhoAtual = caminhos.get(jogador.getPosicao()[0]);
        Espaco espacoAtual = caminhoAtual.getEspacos().get(jogador.getPosicao()[1]);

        for (int i = 0; i < quantidade; i++) {
            if (checaFimCaminho(jogador)) {
                caminhoAtual = jogadorMudaCaminho(jogador);
                espacoAtual = caminhoAtual.getEspacos().get(0);

            } else {
                jogador.setPosicao(1, jogador.getPosicao()[1] + 1);
                espacoAtual = caminhoAtual.getEspacos().get(jogador.getPosicao()[1]);
            }
            jogador.setPosicao(2, jogador.getPosicao()[2] + 1);
        }

        // int espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        // int novoEspaco = espacoAtual + quantidade;

        // Jogador avança um caminho
        // while (caminhoAtual < caminhos.size() && novoEspaco >= espacosCaminhoAtual) {
        // novoEspaco -= espacosCaminhoAtual;
        // caminhoAtual++;
        // // True se ele entrar numa bifurcação pela primeira vez (vai sempre entrar
        // pela
        // // bifurcação A primeiro)
        // if (jogadorEntrouEmBifurcacao(caminhoAtual)
        // && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)) {
        // caminhoAtual = jogadorEscolheBifurcacao(jogador, caminhoAtual, sc);
        // jogador.adicionarBifurcacaoPercorrida(caminhoAtual);
        // }
        // // True se caminhoAtual igualar ao da bifurcação B mas jogador decidiu
        // percorrer
        // // a A
        // if (caminhoAtual % 3 == 2 &&
        // jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1)) {
        // caminhoAtual++;
        // }
        // espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        // if (caminhoAtual >= caminhos.size()) { // Verifica se chegou no final do
        // tabuleiro
        // caminhoAtual = caminhos.size() - 1;
        // novoEspaco = espacosCaminhoAtual - 1;
        // break;
        // }
        // }

        // // Jogador volta um caminho
        // while (caminhoAtual >= 0 && novoEspaco < 0) {
        // caminhoAtual--;
        // // Verifica se o jogador entrou na bifurcação errada: se entrou, volta mais
        // um
        // // caminho (ou seja, B -> A ou A -> caminho normal)
        // if ((jogadorEntrouEmBifurcacao(caminhoAtual) &&
        // !jogador.getBifurcacoesPercorridas().contains(caminhoAtual))
        // ||
        // (caminhoAtual % 3 == 2 &&
        // jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
        // caminhoAtual--;
        // }
        // espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        // if (caminhoAtual < 0) { // Verifica se voltou pro início do tabuleiro (não
        // sei se vai ser possível mas
        // // sla)
        // caminhoAtual = 0;
        // novoEspaco = 0;
        // break;
        // }
        // novoEspaco += espacosCaminhoAtual;
        // }
        // jogador.setPosicao(new int[] { caminhoAtual, novoEspaco });
        // caminhos.get(caminhoAtual).getEspacos().get(novoEspaco).acao(jogador);
    }

    public void adicionarCaminho(Caminho caminho) {
        caminhos.add(caminho);
    }
}

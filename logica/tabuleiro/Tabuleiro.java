package logica.tabuleiro;

import java.util.LinkedList;
import java.util.Scanner;

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

    public LinkedList<Caminho> getCaminhos() { return caminhos; }
    public int getNumeroCaminhos() { return caminhos.size(); }

    // TODO: ajustar a lógica dos três métodos de movimentação abaixo pq acho que ficou bem gambiarra
    /**
     * Verifica se o jogador entrou em uma bifurcação. Se for múltiplo de 3 - 1 (1, 4, 7...) retorna true. 
     * 
     * @param caminho índice do caminho.
     */
    private boolean jogadorEntrouEmBifurcacao(int caminho) {
        return (caminho - 1) % 3 == 0;
    }

    /**
     * Permite que o jogador escolha qual caminho seguir quando encontra uma bifurcação.
     * 
     * @param jogador jogador que entrou na bifurcação.
     * @param caminho caminho que determina qual bifurcação do tabuleiro o jogador entrou.
     * @param scan scanner que lê a entrada, ou seja, a escolha do jogador.
     * @return
     */
    private int jogadorEscolheBifurcacao(Jogador jogador, int caminho, Scanner sc) {
        System.out.println("Você chegou em uma bifurcação. Qual caminho deseja seguir?");
        if (caminho == 1) {
            System.out.println("1. Universidade particular");
            System.out.println("2. Universidade pública");
        }
        if (caminho == 4) {
            System.out.println("1. Caminho do networking");
            System.out.println("2. Caminho do aprimoramento profissional");
        }
        // adicionar outras bifurcações
        int escolha = sc.nextInt();
        switch(escolha) {
            case 1:
                return caminho;
            case 2:
                return caminho + 1;
            // alguma coisa no caso default (tratar exceção de escolha inválida??)
        }
        return caminho;
    }
    
    /**
     * Move o jogador no tabuleiro baseado na quantidade de espaços determinada.
     * 
     * @param jogador jogador que vai se mover.
     * @param quantidade quantos espaços serão percorridos (positivo: jogador avança; negativo: jogador volta)
     * @param scan scanner que lê a entrada do jogador.
     */
    public void moverJogador(Jogador jogador, int quantidade, Scanner sc) {
        int caminhoAtual = jogador.getPosicao()[0];
        int espacoAtual = jogador.getPosicao()[1];
        int espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        int novoEspaco = espacoAtual + quantidade;
        
        // Jogador avança um caminho
        while (caminhoAtual < caminhos.size() && novoEspaco >= espacosCaminhoAtual) {
            novoEspaco -= espacosCaminhoAtual;
            caminhoAtual++;
            // True se ele entrar numa bifurcação pela primeira vez (vai sempre entrar pela bifurcação A primeiro)
            if (jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)) {
                caminhoAtual = jogadorEscolheBifurcacao(jogador, caminhoAtual, sc);
                jogador.adicionarBifurcacaoPercorrida(caminhoAtual);
            }
            // True se caminhoAtual igualar ao da bifurcação B mas jogador decidiu percorrer a A
            if (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1)) {
                caminhoAtual++;
            }
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            if (caminhoAtual >= caminhos.size()) {  // Verifica se chegou no final do tabuleiro
                caminhoAtual = caminhos.size() - 1;
                novoEspaco = espacosCaminhoAtual - 1;
                break;
            }
        }
        
        // Jogador volta um caminho
        while (caminhoAtual >= 0 && novoEspaco < 0) {
            caminhoAtual--;
            // Verifica se o jogador entrou na bifurcação errada: se entrou, volta mais um caminho (ou seja, B -> A ou A -> caminho normal)
            if ((jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)) || 
            (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
                caminhoAtual--;
            }
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            if (caminhoAtual < 0) { // Verifica se voltou pro início do tabuleiro (não sei se vai ser possível mas sla)
                caminhoAtual = 0;
                novoEspaco = 0;
                break;
            }
            novoEspaco += espacosCaminhoAtual;
        }
        jogador.setPosicao(new int[]{caminhoAtual, novoEspaco});
        caminhos.get(caminhoAtual).getEspacos().get(novoEspaco).acao(jogador);    
    }

    public void adicionarCaminho(Caminho caminho) {
        caminhos.add(caminho);
    }
}

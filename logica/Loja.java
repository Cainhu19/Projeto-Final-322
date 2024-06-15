package logica;

import logica.dados_do_jogo.*;

/**
 * Classe que gerencia a loja de dados especiais do jogo.
 */
public class Loja {
    private static Dado[] dadosAVenda;
    private static int[] precosDosDados;

    public static Dado[] getDadosAVenda() {
        return dadosAVenda;
    }

    public static int[] getPrecosDosDados() {
        return precosDosDados;
    }

    /**
     * Avisa ao jogador j que ele não pode comprar um dado da loja -- porque ele já possui um dado comprado e precisa usá-lo antes.
     */
    private static void avisoVendaProibida(Jogador j) {
        //TODO: mostrar alguma coisa na interface
    }
    /**
     * Entrega o dado comprado ao jogador que o comprou, o cobrando o preço apropriado. 
     */
    private static void posVenda(Jogador j, Dado d, int preco) {
        Dado[] novosDados = j.getDados();
        novosDados[2] = d;
        j.setDados(novosDados);
        j.setDinheiro(j.getDinheiro() - preco);
    } 

    /**
     * Realiza a ação da compra de um dado por um jogador j, ou a impede se necessário.
     * 
     * @param j jogador que está realizando a compra.
     * @param escolha o índice do dado escolhido nas arrays de dados e preços.
     */
    public static void compra(Jogador j, int escolha) {
        if (j.possuiDadoComprado()) {
            avisoVendaProibida(j);
        } else {
            //TODO: fazer o jogador confirmar a compra
            posVenda(j, dadosAVenda[escolha], precosDosDados[escolha]);
        }
    }
}

package com.projetofinal322.logica;

import com.projetofinal322.logica.dados_do_jogo.*;

/**
 * Classe que gerencia a loja de dados especiais do jogo.
 */
public class Loja {
    private static Dado[] dadosAVenda = new Dado[5];
    private static int[] precosDosDados;

    // Inicializador estático para garantir que os dados e seus preços sejam configurados ao carregar a classe
    static {
        dadosAVenda = new Dado[] {
            new D6(1, 1, 1, 1, 1, 1), // D6 com 6 faces de 1
            new D6(6, 6, 6, 6, 6, 6), // D6 com 6 faces de 6
            new D2(0, 1), // Moeda
            new D8(0, 1, 1, 2, 3, 5, 8, 13), // Dado Fibonacci
            new D4(2, 4, 6, 8) // D4 com faces 2, 4, 6 e 8
        };
        precosDosDados = new int[] {1500, 3000, 1500, 2100, 2000};
    }

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
        System.out.println("Você já possui um dado comprado da loja e não pode ter outro.");
    }

    /**
     * Entrega o dado comprado ao jogador que o comprou, o cobrando o preço apropriado. 
     */
    private static void posVenda(Jogador j, Dado d, int preco) {
        Dado[] novosDados = j.getDados();
        novosDados[2] = d;
        j.setDados(novosDados);
        j.setDinheiro(j.getDinheiro() - preco);
        System.out.println("Compra realizada com sucesso.");
    }

    /**
     * Retorna uma descrição do dado com base no índice fornecido.
     * 
     * @param indice índice do dado na lista de dados à venda.
     * @return descrição do dado especificado.
     */
    private static String descricaoDado(int indice) {
        switch (indice) {
            case 0:
                return "D6 com apenas faces 1";
            case 1:
                return "D6 com apenas faces 6";
            case 2:
                return "Moeda com faces 0 e 1";
            case 3:
                return "D8 Fibonacci: [0, 1, 1, 2, 3, 5, 8, 13]";
            case 4:
                return "D4 com faces 2, 4, 6, 8";
            default:
                return "Dado desconhecido";
        }
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
        } else if (j.getDinheiro() < precosDosDados[escolha]) {
            System.out.println("Você não tem dinheiro o suficiente para comprar o dado escolhido.");
        } else {
            System.out.printf("Dado sendo comprado: %s. Confirmar compra? (s/n)\n", descricaoDado(escolha));
            if (Entrada.respostaString().equals("s")) {
                posVenda(j, dadosAVenda[escolha], precosDosDados[escolha]);
            } else {
                System.out.println("Compra cancelada.");
            }
        }
    }

    /**
     * Mostra os dados disponíveis na loja junto com seus preços.
     */
    public static void mostrarDadosDisponiveis() {
        System.out.println("Dados disponíveis na loja:");
        for (int i = 0; i < dadosAVenda.length; i++) {
            if (dadosAVenda[i] == null) {
                continue;
            }
            System.out.printf("%d- %s (preço: %d)\n", i + 1, descricaoDado(i), precosDosDados[i]);
        }
    }

    /**
     * Divide o preço de todos os dados da loja pela metade.
     */
    private static void lojaEmPromocao() {
        for (int i = 0; i < 5; i++) {
            precosDosDados[i] /= 2;
        }
    }

    /**
     * Multiplica o preço de todos os dados da loja por dois.
     */
    private static void fimDaPromocao() {
        for (int i = 0; i < 5; i++) {
            precosDosDados[i] *= 2;
        }
    }

    /**
     * Gerencia a ação do espaço da promoção relâmpago da loja.
     */
    public static void promocaoRelampago(Jogador j) {
        lojaEmPromocao();
        mostrarDadosDisponiveis();
        System.out.println("Você deseja comprar algum dado? Se sim, digite o número do dado desejado; se não, digite 0.");
        System.out.println("Atenção: a promoção acaba no fim de sua rodada!");
        int escolha = Entrada.respostaInt();
        if (escolha != 0) {
            compra(j, escolha); //TODO: isso aqui pressupõe entrada bem formatada, na GUI pode haver um jeito melhor

        }
        System.out.println("Fim da promoção relâmpago da Lojinha do Destino!");
        fimDaPromocao();
    }
}
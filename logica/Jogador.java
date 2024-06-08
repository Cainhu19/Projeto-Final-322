package logica;

import logica.dados_do_jogo.*;
import logica.grupos_e_ocupacoes.*;

import java.util.LinkedList;
import java.util.Random;

/**
 * Classe que gerencia o jogador no jogo.
 * 
 * Possui atributos de nome, grupo, ocupação, dinheiro e pontos de oportunidade
 * e networking.
 */
public class Jogador {
    private final String nome;
    private TipoUniversidade tipoUniversidade;
    private FonteDeRenda fonteDeRenda;
    private Dado[] dados; // limite de três dados: o normal, o do grupo e um da loja.
    private Grupo grupo;
    private Ocupacao ocupacao;
    private int dinheiro;
    private int pontosOportunidade;
    private int pontosNetworking;
    private int[] posicao;
    private LinkedList<Integer> bifurcacoesPercorridas;

    public Jogador(String nome) {
        this.nome = nome;
        this.tipoUniversidade = null;
        this.fonteDeRenda = null;
        this.dados = new Dado[3];
        dados[0] = new D10(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        dados[1] = null;
        dados[2] = null;
        this.grupo = null;
        this.ocupacao = null;
        this.dinheiro = 0;
        this.pontosOportunidade = 0;
        this.pontosNetworking = 0;
        this.posicao = new int[3];
        posicao[0] = 0; // Caminho
        posicao[1] = 0; // Espaço (dentro do caminho)
        posicao[2] = 0; // Posicao absoluta
        this.bifurcacoesPercorridas = new LinkedList<Integer>();
    }

    public String getNome() {
        return nome;
    }

    public TipoUniversidade getTipoUniversidade() {
        return tipoUniversidade;
    }

    public FonteDeRenda getFonteDeRenda() {
        return fonteDeRenda;
    }

    public Dado[] getDados() {
        return dados;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Ocupacao getOcupacao() {
        return ocupacao;
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public int getPontosOportunidade() {
        return pontosOportunidade;
    }

    public int getPontosNetworking() {
        return pontosNetworking;
    }

    public int[] getPosicao() {
        return posicao;
    }

    public LinkedList<Integer> getBifurcacoesPercorridas() {
        return bifurcacoesPercorridas;
    }

    public void setTipoUniversidade(TipoUniversidade tipo) {
        this.tipoUniversidade = tipo;
    }

    public void setFonteDeRenda(FonteDeRenda fonte) {
        this.fonteDeRenda = fonte;
    }

    public void setDados(Dado[] dados) {
        this.dados = dados;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
        this.dados[1] = grupo.getDado();
    }

    public void setOcupacao(Ocupacao ocupacao) {
        this.ocupacao = ocupacao;
    }

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setPontosOportunidade(int pontosOportunidade) {
        this.pontosOportunidade = pontosOportunidade;
    }

    public void setPontosNetworking(int pontosNetworking) {
        this.pontosNetworking = pontosNetworking;
    }

    public void setPosicao(int i, int pos) {
        this.posicao[i] = pos;
    }

    /**
     * Retorna o resultado da ação de rodar o dado normal (1d10).
     */
    private int resultadoDadoNormal() {
        return dados[0].rodar(1);
    }

    /**
     * Retorna o resultado da ação de rodar o dado especial do grupo do jogador.
     */
    private int resultadoDadoEspecial() {
        return grupo.getDado().rodar(grupo.getQtdVezesDado());
    }

    /**
     * Retorna o resultado da ação de rodar o dado especial comprado do jogador.
     */
    private int resultadoDadoComprado() {
        return dados[2].rodar(1);
    }

    /**
     * Retorna verdadeiro se o jogador possui um dado comprado e falso caso
     * contrário.
     */
    public boolean possuiDadoComprado() {
        return dados[2] != null;
    }

    /**
     * Roda o dado de acordo com a escolha do jogador e retorna o resultado.
     * 
     * @param escolha indica qual dado o jogador quer utilizar (1: dado comprado, 2:
     *                dado especial, nenhum dos dois: dado normal).
     */
    public int resultadoDado(int escolha) {
        if (escolha == 1 && possuiDadoComprado())
            return resultadoDadoComprado();
        else if (escolha == 2)
            return resultadoDadoEspecial();
        else
            return resultadoDadoNormal();
    }

    /**
     * Calcula a pontuação do jogador ao decorrer do jogo.
     */
    public int calcularPontuacao() {
        return (dinheiro / 1000) + pontosOportunidade + (pontosNetworking / 3);
    }

    /**
     * Adiciona um valor à quantidade de dinheiro atual do jogador.
     * 
     * @param valor a quantidade de dinheiro a ser adicionada.
     */
    public void adicionarDinheiro(int valor) {
        this.dinheiro += valor;
    }

    /**
     * Remove certa quantidade dinheiro do jogador.
     * 
     * @param valor a quantidade de dinheiro a ser removida.
     */
    public void removerDinheiro(int valor) {
        this.dinheiro -= valor;
    }

    // TODO: ver se o método tá certo até agr pelo menos e ajustar a chance de
    // sortear cada fonte
    /**
     * Determina qual fonte de renda será recebida pelo jogador no início do jogo de
     * acordo com o tipo de sua universidade.
     */
    public void sortearFonteDeRendaInicial() {
        Random rand = new Random();
        if (tipoUniversidade == TipoUniversidade.PARTICULAR) {
            FonteDeRenda[] fontesParticular = { FonteDeRenda.HERDEIRO_PARTICULAR, FonteDeRenda.BOLSA_AUXILIO,
                    FonteDeRenda.TIOS, FonteDeRenda.PAIS,
                    FonteDeRenda.EMPREGO, FonteDeRenda.MEIO_PERIODO, FonteDeRenda.ESTAGIO,
                    FonteDeRenda.EMPREENDIMENTO };
            fonteDeRenda = fontesParticular[rand.nextInt(8)];
        } else {
            FonteDeRenda[] fontesPublica = { FonteDeRenda.HERDEIRO_PUBLICA, FonteDeRenda.BOLSA_AUXILIO,
                    FonteDeRenda.EMPREGO, FonteDeRenda.MEIO_PERIODO,
                    FonteDeRenda.ESTAGIO, FonteDeRenda.EMPREENDIMENTO };
            fonteDeRenda = fontesPublica[rand.nextInt(6)];
        }
    }

    public void adicionarBifurcacaoPercorrida(int bifurcacao) {
        bifurcacoesPercorridas.add(bifurcacao);
    }
}

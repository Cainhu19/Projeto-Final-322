package com.projetofinal322.logica;

import com.projetofinal322.logica.dados_do_jogo.*;

import java.util.LinkedList;

/**
 * Classe que gerencia o jogador no jogo.
 * 
 * Possui atributos de nome, grupo, ocupação, dinheiro e pontos de oportunidade
 * e networking.
 */
public class Jogador {
    private final String nome;
    private FonteDeRenda fonteDeRenda;
    private Dado[] dados; // limite de três dados: o normal, o do grupo e um da loja.
    private Grupo grupo;
    private int dinheiro;
    private int pontosOportunidade;
    private int pontosNetworking;
    private double multiplicadorDinheiro;
    private double multiplicadorOportunidade;
    private double multiplicadorNetworking;
    private int[] posicao;
    private LinkedList<Integer> bifurcacoesPercorridas;
    private int vezesEstudo;
    private boolean perdeuProxRodada;

    public Jogador(String nome) {
        this.nome = nome;
        this.fonteDeRenda = null;
        this.dados = new Dado[3];
        dados[0] = new D10(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        dados[1] = null;
        dados[2] = null;
        this.grupo = null;
        this.dinheiro = 500;
        this.pontosOportunidade = 0;
        this.pontosNetworking = 0;
        this.multiplicadorDinheiro = 0.001;
        this.multiplicadorOportunidade = 1;
        this.multiplicadorNetworking = 0.3;
        this.posicao = new int[2];
        posicao[0] = 0; // Caminho
        posicao[1] = 0; // Espaço (dentro do caminho)
        this.bifurcacoesPercorridas = new LinkedList<Integer>();
        this.vezesEstudo = 0;
        this.perdeuProxRodada = false;
    }

    public String getNome() {
        return nome;
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

    public int getDinheiro() {
        return dinheiro;
    }

    public int getPontosOportunidade() {
        return pontosOportunidade;
    }

    public int getPontosNetworking() {
        return pontosNetworking;
    }

    public double getMultiplicadorDinheiro() {
        return multiplicadorDinheiro;
    }

    public double getMultiplicadorOportunidade() {
        return multiplicadorOportunidade;
    }

    public double getMultiplicadorNetworking() {
        return multiplicadorNetworking;
    }

    public int[] getPosicao() {
        return posicao;
    }

    public LinkedList<Integer> getBifurcacoesPercorridas() {
        return bifurcacoesPercorridas;
    }

    public int getVezesEstudo() {
        return vezesEstudo;
    }

    public boolean getPerdeuProxRodada() {
        return perdeuProxRodada;
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

    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public void setPontosOportunidade(int pontosOportunidade) {
        this.pontosOportunidade = pontosOportunidade;
    }

    public void setPontosNetworking(int pontosNetworking) {
        this.pontosNetworking = pontosNetworking;
    }
    public void setMultiplicadorDinheiro(double multiplicadorDinheiro) {
        this.multiplicadorDinheiro = multiplicadorDinheiro;
    }

    public void setMultiplicadorOportunidade(double multiplicadorOportunidade) {
        this.multiplicadorOportunidade = multiplicadorOportunidade;
    }

    public void setMultiplicadorNetworking(double multiplicadorNetworking) {
        this.multiplicadorNetworking = multiplicadorNetworking;
    }

    public void setPosicao(int[] pos) {
        this.posicao = pos;
    }

    public void setPerdeuProxRodada(boolean perdeuProxRodada) {
        this.perdeuProxRodada = perdeuProxRodada;
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
        dados[2] = null; // dado usado sai do inventário do jogador
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
    public double calcularPontuacao() {
        return dinheiro * multiplicadorDinheiro + pontosOportunidade * multiplicadorOportunidade + pontosNetworking * multiplicadorNetworking;
    }

    /**
     * Ajusta a quantidade de dinheiro atual do jogador. Pode ser usado para
     * adicionar (valor positivo)
     * ou remover (valor negativo) dinheiro.
     * 
     * @param valor a quantidade de dinheiro a ser adicionada (se for positivo) ou
     *              removida (negativo).
     */
    public void ajustarDinheiro(int valor) {
        this.dinheiro += valor;
    }

    public void adicionarBifurcacaoPercorrida(int bifurcacao) {
        bifurcacoesPercorridas.add(bifurcacao);
    }

    public void adicionarPontosNetworking(int pontos) {
        pontosNetworking += pontos;
    }

    public void ajustarPontosOportunidade(int pontos) {
        pontosOportunidade += pontos;
    }

    public void incrementarVezesEstudo() {
        vezesEstudo++;
    }
}

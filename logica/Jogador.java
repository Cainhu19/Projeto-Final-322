package logica;

import logica.dados_do_jogo.*;
import logica.grupos_e_ocupacoes.*;

/**
 * Classe que gerencia o jogador no jogo.
 * 
 * Possui atributos de nome, grupo, ocupação, dinheiro e pontos de oportunidade e networking.
 */
public class Jogador {
    private final String nome;
    private Dado[] dados;  // limite de três dados: o normal, o do grupo e um da loja.
    private Grupo grupo;
    private Ocupacao ocupacao;
    private int dinheiro;
    private int pontosOportunidade;
    private int pontosNetworking;

    public Jogador(String nome) {
        this.nome = nome;
        this.dados = new Dado[3];
        dados[0] = new D10(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        dados[1] = null;
        dados[2] = null;
        this.grupo = null;
        this.ocupacao = null;
        this.dinheiro = 0;
        this.pontosOportunidade = 0;
        this.pontosNetworking = 0;
    }

    public String getNome() { return nome; }
    public Dado[] getDados() { return dados; }
    public Grupo getGrupo() { return grupo; }
    public Ocupacao getOcupacao() { return ocupacao; }
    public int getDinheiro() { return dinheiro; }
    public int getPontosOportunidade() { return pontosOportunidade; }
    public int getPontosNetworking() { return pontosNetworking; }

    //TODO: ver se esse setter do grupo ta ok
    public void setDados(Dado[] dados) { this.dados = dados; }
    public void setGrupo(Grupo grupo) { this.grupo = grupo; this.dados[1] = grupo.getDado(); }
    public void setOcupacao(Ocupacao ocupacao) { this.ocupacao = ocupacao; }
    public void setDinheiro(int dinheiro) { this.dinheiro = dinheiro; }
    public void setPontosOportunidade(int pontosOportunidade) { this.pontosOportunidade = pontosOportunidade; }
    public void setPontosNetworking(int pontosNetworking) { this.pontosNetworking = pontosNetworking; }
    
    /**
     * Retorna o resultado da ação de rodar o dado normal (1d10).
     */
    public int resultadoDadoNormal() {
        return dados[0].rodar(1);
    }

    /**
     * Retorna o resultado da ação de rodar o dado especial do grupo do jogador.
     */
    private int resultadoDadoEspecial() {
        return grupo.getDado().rodar(grupo.getQtdVezesDado());
    }

    /**
     * Retorna verdadeiro se o jogador possui um dado comprado e falso caso contrário.
     */
    public boolean possuiDadoComprado() {
        return dados[2] != null;
    }
}

package logica;

import logica.grupos_e_ocupacoes.*;

/**
 * Classe que gerencia o jogador no jogo.
 * 
 * Possui atributos de nome, grupo, ocupação, dinheiro e pontos de oportunidade e networking.
 */
public class Jogador {
    private final String nome;
    private Grupo grupo;
    private Ocupacao ocupacao;
    private int dinheiro;
    private int pontosOportunidade;
    private int pontosNetworking;

    public Jogador(String nome) {
        this.nome = nome;
        this.grupo = null;
        this.ocupacao = null;
        this.dinheiro = 0;
        this.pontosOportunidade = 0;
        this.pontosNetworking = 0;
    }

    public String getNome() { return nome; }
    public Grupo getGrupo() { return grupo; }
    public Ocupacao getOcupacao() { return ocupacao; }
    public int getDinheiro() { return dinheiro; }
    public int getPontosOportunidade() { return pontosOportunidade; }
    public int getPontosNetworking() { return pontosNetworking; }

    public void setGrupo(Grupo grupo) { this.grupo = grupo; }
    public void setOcupacao(Ocupacao ocupacao) { this.ocupacao = ocupacao; }
    public void setDinheiro(int dinheiro) { this.dinheiro = dinheiro; }
    public void setPontosOportunidade(int pontosOportunidade) { this.pontosOportunidade = pontosOportunidade; }
    public void setPontosNetworking(int pontosNetworking) { this.pontosNetworking = pontosNetworking; }
    
    
}

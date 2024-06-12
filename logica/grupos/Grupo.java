package logica.grupos;

import logica.dados_do_jogo.*;

/**
 * Classe que representa os grupos (entidades, ligas etc.) dos quais um jogador pode fazer parte.
 * 
 * Possui atributos de nome, o dado especial que vem de bônus, 
 * a quantidade de vezes que esse dado especial é rodado caso for escolhido
 * e um valor booleano para indicar se um jogador já possui a instância do grupo.
 */
public class Grupo {
    private final String nome;
    private final Dado dado;
    private final int qtdVezesDado;
    private final int bonusNetworking;
    private boolean ocupado;
    
    public Grupo(String nome, Dado dado, int qtdVezesDado) {
    	this.nome = nome;
    	this.dado = dado;
    	this.qtdVezesDado = qtdVezesDado;
        this.bonusNetworking = 30; // mudar de acordo com cada grupo dps
    	this.ocupado = false;
    }
    
    public String getNome() { return nome; }
    public Dado getDado() { return dado; }
    public int getQtdVezesDado() { return qtdVezesDado; }
    public int getBonusNetworking() { return bonusNetworking; }
    public boolean isOcupado() { return ocupado; }

    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }
}

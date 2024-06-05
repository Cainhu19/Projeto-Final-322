package logica.grupos_e_ocupacoes;

import logica.dados.*;

// Representa os grupos (entidades, ligas, etc) que um jogador pode fazer parte.
public class Grupo {
    private final String nome;
    private final Dado dado;
    private final int qtdDados;
    private boolean ocupado;
    
    public Grupo(String nome, Dado dado, int qtdDados) {
    	this.nome = nome;
    	this.dado = dado;
    	this.qtdDados = qtdDados;
    	this.ocupado = false;
    }
    
    public String getNome() {
    	return nome;
    }
    
    public Dado getDado() {
    	return dado;
    }
    
    public int qtdDados() {
    	return qtdDados;
    }
    
    public boolean isOcupado() {
    	return ocupado;
    }
}

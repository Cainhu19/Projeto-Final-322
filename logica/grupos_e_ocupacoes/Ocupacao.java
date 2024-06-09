package logica.grupos_e_ocupacoes;

import logica.FonteDeRenda;

/**
 * Classe que representa as ocupações (emprego, estágio etc.) que um jogador pode ter.
 * 
 * Possui atributos de nome, renda que o jogador recebe a cada espaço de remuneração
 * e um valor booleano para indicar se um jogador já possui a instância da ocupação.
 */
public class Ocupacao {
    private final String nome;
    private final FonteDeRenda fonteDeRenda;
    private boolean ocupada;
    
    public Ocupacao(String nome, FonteDeRenda fonteDeRenda) {
    	this.nome = nome;
    	this.fonteDeRenda = fonteDeRenda;
    	this.ocupada = false;
    }
    
    public String getNome() { return nome; }
    public FonteDeRenda getFonteDeRenda() { return fonteDeRenda; }
    public boolean isOcupada() { return ocupada; }

    public void setOcupada(boolean ocupada) { this.ocupada = ocupada; }

    public int getRenda() {
        return fonteDeRenda.getRenda();
    }
}

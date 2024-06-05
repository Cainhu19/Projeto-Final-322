package logica.grupos_e_ocupacoes;

/**
 * Classe que representa as ocupações (emprego, estágio etc.) que um jogador pode ter.
 * 
 * Possui atributos de nome, renda que o jogador recebe a cada espaço de remuneração
 * e um valor booleano para indicar se um jogador já possui a instância da ocupação.
 */
public class Ocupacao {
    private final String nome;
    private final int renda;
    private boolean ocupada;
    
    public Ocupacao(String nome, int renda) {
    	this.nome = nome;
    	this.renda = renda;
    	this.ocupada = false;
    }
    
    public String getNome() {
    	return nome;
    }
    
    public int getRenda() {
    	return renda;
    }
    
    public boolean isOcupada() {
    	return ocupada;
    }
}

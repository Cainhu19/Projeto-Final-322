package logica.grupos_e_ocupacoes;

// Representa as ocupações que um jogador pode ter.
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

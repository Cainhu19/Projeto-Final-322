import java.util.LinkedList;

import logica.Jogador;
import logica.Jogo;
import logica.tabuleiro.Espaco;

public class Main {
    public static void main(String args[]) {
        Jogador j1 = new Jogador("Caio");
        Jogador j2 = new Jogador("Henrique");
        Jogador j3 = new Jogador("Giulliano");
        LinkedList<Jogador> jogadores = new LinkedList<>();
        jogadores.add(j1);
        jogadores.add(j2);
        jogadores.add(j3);
        LinkedList<Espaco> espacos = new LinkedList<>();
        
        Jogo jogo = Jogo.getInstance(jogadores, 12, null);
    }
}
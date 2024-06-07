import java.util.LinkedList;

import logica.Jogador;
import logica.Jogo;
import logica.tabuleiro.Caminho;
import logica.tabuleiro.Espaco;
import logica.tabuleiro.EspacoRemuneracao;

public class Main {
    public static void main(String args[]) {
        Jogador j1 = new Jogador("Caio");
        Jogador j2 = new Jogador("Henrique");
        Jogador j3 = new Jogador("Giulliano");
        LinkedList<Jogador> jogadores = new LinkedList<>();
        jogadores.add(j1);
        jogadores.add(j2);
        jogadores.add(j3);

        Caminho c1 = new Caminho();

        Espaco e1 = new EspacoRemuneracao("Inicio");
        Espaco e2 = new EspacoRemuneracao("Formatura do Médio");
        Espaco e3 = new EspacoRemuneracao("Preparações da Faculdade");

        c1.adicionarEspaco(e1);
        c1.adicionarEspaco(e2);
        c1.adicionarEspaco(e3);

        Jogo jogo = Jogo.getInstance(jogadores);
    }
}
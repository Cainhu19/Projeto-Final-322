import java.util.LinkedList;

import logica.Jogador;
import logica.Jogo;
import logica.tabuleiro.Caminho;
import logica.tabuleiro.Espaco;
import logica.tabuleiro.EspacoRemuneracao;
import logica.tabuleiro.Tabuleiro;

public class Main {
    public static void main(String args[]) {
        Jogador j1 = new Jogador("Caio");
        Jogador j2 = new Jogador("Henrique");
        Jogador j3 = new Jogador("Giulliano");
        LinkedList<Jogador> jogadores = new LinkedList<>();
        jogadores.add(j1);
        jogadores.add(j2);
        jogadores.add(j3);

        Caminho c0 = new Caminho();
        Caminho bifurcacaoA0 = new Caminho();
        Caminho bifurcacaoB0 = new Caminho();
        Caminho c1 = new Caminho();
        // adicionar resto dos caminhos

        // TODO: criar novos tipos de espaço <<<
        Espaco e0 = new EspacoRemuneracao("Inicio");
        Espaco e1 = new EspacoRemuneracao("Formatura do Médio");
        Espaco e2 = new EspacoRemuneracao("Preparações da Faculdade");
        Espaco e3a = new EspacoRemuneracao("Início aulas");
        Espaco e4a = new EspacoRemuneracao("Festa bio");
        Espaco e5a = new EspacoRemuneracao("rifa");
        Espaco e6a = new EspacoRemuneracao("calouros");
        Espaco e3b = new EspacoRemuneracao("representante");
        Espaco e4b = new EspacoRemuneracao("bolsa");
        Espaco e5b = new EspacoRemuneracao("seletivo liga");
        Espaco e6b = new EspacoRemuneracao("atletica");
        Espaco e15 = new EspacoRemuneracao("emprego");
        Espaco e16 = new EspacoRemuneracao("mat");
        Espaco e17 = new EspacoRemuneracao("youtube");

        c0.adicionarEspaco(e0);
        c0.adicionarEspaco(e1);
        c0.adicionarEspaco(e2);

        bifurcacaoA0.adicionarEspaco(e3a);
        bifurcacaoA0.adicionarEspaco(e4a);
        bifurcacaoA0.adicionarEspaco(e5a);
        bifurcacaoA0.adicionarEspaco(e6a);

        bifurcacaoB0.adicionarEspaco(e3b);
        bifurcacaoB0.adicionarEspaco(e4b);
        bifurcacaoB0.adicionarEspaco(e5b);
        bifurcacaoB0.adicionarEspaco(e6b);

        c1.adicionarEspaco(e15);
        c1.adicionarEspaco(e16);
        c1.adicionarEspaco(e17);

        Tabuleiro tabuleiro = Tabuleiro.getInstance();
        tabuleiro.adicionarCaminho(c0);
        tabuleiro.adicionarCaminho(bifurcacaoA0);
        tabuleiro.adicionarCaminho(bifurcacaoB0);
        tabuleiro.adicionarCaminho(c1);

        Jogo jogo = Jogo.getInstance(jogadores);
        jogo.loopDeJogo();
    }
}
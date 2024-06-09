import java.util.LinkedList;

import logica.FonteDeRenda;
import logica.Jogador;
import logica.Jogo;
import logica.grupos_e_ocupacoes.*;
import logica.tabuleiro.Caminho;
import logica.tabuleiro.Espaco;
import logica.tabuleiro.EspacoOportunidade;
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
        Caminho bifurcacaoA1 = new Caminho();
        Caminho bifurcacaoB1 = new Caminho();
        // adicionar resto dos caminhos

        // TODO: criar novos tipos de espaço <<<
        Espaco e0 = new EspacoRemuneracao("Inicio");
        Espaco e1 = new EspacoRemuneracao("Formatura do Médio");
        Espaco e2 = new EspacoRemuneracao("Preparações da Faculdade");
        Espaco e3a = new EspacoRemuneracao("Início aulas");
        Espaco e4a = new EspacoRemuneracao("Festa bio");
        Espaco e5a = new EspacoRemuneracao("rifa");
        Espaco e6a = new EspacoOportunidade(LigaPesquisasAcademicas.getInstance());
        Espaco e7a = new EspacoRemuneracao("calouros");
        Espaco e8a = new EspacoOportunidade(AtleticaEsportes.getInstance());
        Espaco e9a = new EspacoOportunidade(new Ocupacao("Estágio", FonteDeRenda.ESTAGIO));
        Espaco e10a = new EspacoOportunidade(new Ocupacao("Emprego", FonteDeRenda.EMPREGO));
        Espaco e3b = new EspacoOportunidade(RepresentacaoDiscente.getInstance());
        Espaco e4b = new EspacoOportunidade(new Ocupacao("Emprego", FonteDeRenda.EMPREGO));
        Espaco e5b = new EspacoOportunidade(LigaPesquisasAcademicas.getInstance());
        Espaco e6b = new EspacoOportunidade(AtleticaEsportes.getInstance());
        Espaco e15 = new EspacoOportunidade(new Ocupacao("Emprego", FonteDeRenda.EMPREGO));
        Espaco e16 = new EspacoRemuneracao("mat");
        Espaco e17 = new EspacoRemuneracao("youtube");

        c0.adicionarEspaco(e0);
        c0.adicionarEspaco(e1);
        c0.adicionarEspaco(e2);

        bifurcacaoA0.adicionarEspaco(e3a);
        bifurcacaoA0.adicionarEspaco(e4a);
        bifurcacaoA0.adicionarEspaco(e5a);
        bifurcacaoA0.adicionarEspaco(e6a);
        bifurcacaoA0.adicionarEspaco(e7a);
        bifurcacaoA0.adicionarEspaco(e8a);
        bifurcacaoA0.adicionarEspaco(e9a);
        bifurcacaoA0.adicionarEspaco(e10a);

        bifurcacaoB0.adicionarEspaco(e3b);
        bifurcacaoB0.adicionarEspaco(e4b);
        bifurcacaoB0.adicionarEspaco(e5b);
        bifurcacaoB0.adicionarEspaco(e6b);

        c1.adicionarEspaco(e15);
        c1.adicionarEspaco(e16);
        c1.adicionarEspaco(e17);

        // Só pra testar se os espaços de remuneração funcionam
        for (int i = 0; i < 10; i++) {
            bifurcacaoA1.adicionarEspaco(new EspacoRemuneracao("dinheiro!!"));
            bifurcacaoB1.adicionarEspaco(new EspacoRemuneracao("dinheiro!!"));
        }

        Tabuleiro tabuleiro = Tabuleiro.getInstance();
        tabuleiro.adicionarCaminho(c0);
        tabuleiro.adicionarCaminho(bifurcacaoA0);
        tabuleiro.adicionarCaminho(bifurcacaoB0);
        tabuleiro.adicionarCaminho(c1);
        tabuleiro.adicionarCaminho(bifurcacaoA1);
        tabuleiro.adicionarCaminho(bifurcacaoB1);
        
        Jogo jogo = Jogo.getInstance(jogadores);
        jogo.loopDeJogo();
    }
}
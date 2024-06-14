import java.util.LinkedList;

import arquivos_leitura.LerEspacos;
import logica.*;
// import logica.tabuleiro.Caminho;
// import logica.tabuleiro.Espaco;
// import logica.tabuleiro.EspacoDinheiro;
// import logica.tabuleiro.EspacoEstudo;
// import logica.tabuleiro.EspacoNetworking;
// import logica.tabuleiro.EspacoOportunidade;
// import logica.tabuleiro.EspacoRemuneracao;
// import logica.tabuleiro.Tabuleiro;

public class Main {
        public static void main(String args[]) {
                Jogador j1 = new Jogador("Caio");
                Jogador j2 = new Jogador("Henrique");
                Jogador j3 = new Jogador("Giulliano");
                LinkedList<Jogador> jogadores = new LinkedList<>();
                jogadores.add(j1);
                jogadores.add(j2);
                jogadores.add(j3);

                // Caminho c0 = new Caminho();
                // Caminho bifurcacaoA0 = new Caminho();
                // Caminho bifurcacaoB0 = new Caminho();
                // Caminho c1 = new Caminho();
                // Caminho bifurcacaoA1 = new Caminho();
                // Caminho bifurcacaoB1 = new Caminho();
                // // adicionar resto dos caminhos
                // // TODO: criar novos tipos de espaço <<<
                // Espaco e0 = new EspacoRemuneracao();
                // Espaco e1 = new EspacoRemuneracao();
                // Espaco e2 = new EspacoRemuneracao();
                // Espaco e3a = new EspacoDinheiro("Início das aulas, gaste 200 com materiais novos", 200);
                // Espaco e4a = new EspacoNetworking("Festa da Biologia, conversou com 25 pessoas", 25);
                // Espaco e5a = new EspacoDinheiro("Ganhou uma rifa da Veterinária", 150);
                // Espaco e6a = new EspacoEstudo("Ficou estudando na biblioteca");
                // Espaco e7a = new EspacoNetworking("Foi na recepção dos calouros do curso, conheceu 30 pessoas", 30);
                // Espaco e8a = new EspacoOportunidade("Você pode entrar na Atlética do seu curso. Deseja entrar? (s/n)",
                // Grupo.ATLETICA_ESPORTES);
                // Espaco e9a = new EspacoOportunidade("Você recebeu uma proposta de estágio com vale-transporte do lado da suacasa. Deseja aceitar? (s/n)",
                // FonteDeRenda.ESTAGIO);
                // Espaco e10a = new EspacoOportunidade("Você foi aceito para receber uma bolsa-auxílio da sua universidade. Deseja recebê-la? (s/n)",
                // FonteDeRenda.BOLSA_AUXILIO);
                // Espaco e3b = new EspacoOportunidade("Surgiu uma oportunidade de ser representante discente. Deseja aproveitá-la? (s/n)",
                // Grupo.REP_DISCENTE);
                // Espaco e4b = new EspacoOportunidade("Você foi aceito para receber uma bolsa-auxílio da sua universidade. Deseja recebê-la? (s/n)",
                // FonteDeRenda.BOLSA_AUXILIO);
                // Espaco e5b = new EspacoOportunidade("Você pensa em participar do seletivo de uma Liga Acadêmica. Deseja participar? (s/n)",
                // Grupo.LIGA_PESQUISAS);
                // Espaco e6b = new EspacoOportunidade("Te chamaram para entrar na Atlética. Deseja entrar? (s/n)",
                // Grupo.ATLETICA_ESPORTES);
                // Espaco e15 = new EspacoOportunidade(
                // "Seu primo de segundo grau lhe ofereceu um emprego. Aceita a vaga? (s/n)",
                // FonteDeRenda.EMPREGO);
                // Espaco e16 = new EspacoNetworking("Seu grupo de estudo de matemática de 15 pessoas se aproximou de você.", 15);
                // Espaco e17 = new EspacoEstudo("Estudou com vídeos no YouTube.");
                // c0.adicionarEspaco(e0);
                // c0.adicionarEspaco(e1);
                // c0.adicionarEspaco(e2);

                // bifurcacaoA0.adicionarEspaco(e3a);
                // bifurcacaoA0.adicionarEspaco(e4a);
                // bifurcacaoA0.adicionarEspaco(e5a);
                // bifurcacaoA0.adicionarEspaco(e6a);
                // bifurcacaoA0.adicionarEspaco(e7a);
                // bifurcacaoA0.adicionarEspaco(e8a);
                // bifurcacaoA0.adicionarEspaco(e9a);
                // bifurcacaoA0.adicionarEspaco(e10a);

                // bifurcacaoB0.adicionarEspaco(e3b);
                // bifurcacaoB0.adicionarEspaco(e4b);
                // bifurcacaoB0.adicionarEspaco(e5b);
                // bifurcacaoB0.adicionarEspaco(e6b);

                // c1.adicionarEspaco(e15);
                // c1.adicionarEspaco(e16);
                // c1.adicionarEspaco(e17);

                // // Só pra testar se os espaços de remuneração funcionam
                // for (int i = 0; i < 10; i++) {
                // bifurcacaoA1.adicionarEspaco(new EspacoRemuneracao());
                // bifurcacaoB1.adicionarEspaco(new EspacoRemuneracao());
                // }

                // Tabuleiro tabuleiro = Tabuleiro.getInstance();
                // tabuleiro.adicionarCaminho(c0);
                // tabuleiro.adicionarCaminho(bifurcacaoA0);
                // tabuleiro.adicionarCaminho(bifurcacaoB0);
                // tabuleiro.adicionarCaminho(c1);
                // tabuleiro.adicionarCaminho(bifurcacaoA1);
                // tabuleiro.adicionarCaminho(bifurcacaoB1);

                try {
                    LerEspacos leitor = new LerEspacos();
                    leitor.lerArquivo("arquivos_xml/tabuleiro.xml");

                // Caso haja exceções na leitura do tabuleiro, será impressa uma stack trace para localização do erro
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Jogo jogo = Jogo.getInstance(jogadores);
                jogo.loopDeJogo();
        }
}
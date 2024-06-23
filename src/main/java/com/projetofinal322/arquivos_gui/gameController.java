package com.projetofinal322.arquivos_gui;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import com.projetofinal322.logica.Jogo;
import com.projetofinal322.logica.tabuleiro.Caminho;
import com.projetofinal322.logica.tabuleiro.Espaco;
import com.projetofinal322.logica.tabuleiro.espacos.EspacoRemuneracao;
import com.projetofinal322.logica.Entrada;
import com.projetofinal322.logica.FonteDeRenda;
import com.projetofinal322.logica.Jogador;

public class gameController implements Initializable {
    @FXML
    private AnchorPane gameAnchor;

    @FXML
    private ImageView imgTabuleiro;

    @FXML
    private Label labelInfos;

    @FXML
    private Label labelTerminal;

    private int turno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        turno = 0;
        Jogo jogo = Jogo.getInstance(Jogo.getJogadores());
        labelTerminal.setFocusTraversable(true);
        gameAnchor.setOnKeyPressed(this::handleGameAnchor);
        escolhaDeAcao(jogo);
    }

    public void escolhaDeAcao(Jogo jogo) {
        String descricaoJogador = "Nome: " + Jogo.getJogadores().get(jogo.getJogadorAtual()).getNome() + "\nDinheiro: "
                + Jogo.getJogadores().get(jogo.getJogadorAtual()).getDinheiro();
        String texto = Jogo.getJogadores().get(jogo.getJogadorAtual()).getNome() + " escolha uma ação";
        texto += "\n1. Jogar um D10 comum";
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).getGrupo() != null) {
            texto += "\n2. Jogar o dado especial do seu grupo";
        }
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).possuiDadoComprado()) {
            texto += "\n3. Jogar o dado comprado na loja";
        }
        texto += "\nL. Abrir a loja";
        texto += "\nM. Abrir o manual";
        labelTerminal.setText(texto);

        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).getGrupo() != null) {
            descricaoJogador += "\n" + Jogo.getJogadores().get(jogo.getJogadorAtual()).getGrupo().getNome();
        }
        if (Jogo.getJogadores().get(jogo.getJogadorAtual()).getFonteDeRenda() != null) {
            descricaoJogador += "\n" + Jogo.getJogadores().get(jogo.getJogadorAtual()).getFonteDeRenda().getNome();
        }

        labelInfos.setText(descricaoJogador);
    }

    private void handleGameAnchor(KeyEvent keyEvent) {
        Jogo jogo = Jogo.getInstance(Jogo.getJogadores());
        Jogador jogadorAtual = Jogo.getJogadores().get(jogo.getJogadorAtual());
        String texto = jogadorAtual.getNome() + " jogou o dado" + "\nResultado: ";
        int resultadoDado;
        if (turno == 0) {
            switch (keyEvent.getCode().toString()) {
                case "DIGIT1":
                    resultadoDado = jogo.jogarDado(jogadorAtual, 0);
                    texto += resultadoDado;
                    labelTerminal.setText(texto);
                    texto += moverJogador(jogadorAtual, resultadoDado) + "\n1. para passar a vez";
                    labelTerminal.setText(texto);

                    turno++;
                    break;
                case "DIGIT2":
                    if (jogadorAtual.getGrupo() != null) {
                        resultadoDado = jogo.jogarDado(jogadorAtual, 1);
                        texto += resultadoDado;
                        texto += moverJogador(jogadorAtual, resultadoDado) + "\n1. para passar a vez";
                        labelTerminal.setText(texto);
                        turno++;
                    }
                    break;
                case "DIGIT3":
                    if (jogadorAtual.possuiDadoComprado()) {
                        resultadoDado = jogo.jogarDado(jogadorAtual, 2);
                        texto += resultadoDado;
                        texto += moverJogador(jogadorAtual, resultadoDado) + "\n1. para passar a vez";
                        labelTerminal.setText(texto);
                        turno++;
                    }
                    break;
                case "L":
                    jogo.lojaAberta(jogadorAtual);
                    break;
                case "M":
                    abreManual();
                    break;
                default:
                    break;
            }

        } else if (turno == 1) {
            switch (keyEvent.getCode().toString()) {
                case "L":
                    jogo.lojaAberta(jogadorAtual);
                    break;
                case "M":
                    abreManual();
                    break;
                case "DIGIT1":
                    turno = 0;
                    jogo.setJogadorAtual((jogo.getJogadorAtual() + 1) % Jogo.getJogadores().size());
                    escolhaDeAcao(jogo);
                default:
                    break;
            }
        }
    }

    private void abreManual() {

        try {
            // URL url = getClass().getResource("./resources\\com\\manual\\index.html");
            // File file = new File(url.toURI());
            // String absolutePath = file.getAbsolutePath();
            // File htmlFile = new File(absolutePath);
            // Desktop.getDesktop().browse(htmlFile.toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Move o jogador no tabuleiro baseado na quantidade de espaços determinada.
     * 
     * @param jogador    jogador que vai se mover.
     * @param quantidade quantos espaços serão percorridos (positivo: jogador
     *                   avança; negativo: jogador volta)
     */
    public String moverJogador(Jogador jogador, int quantidade) {
        LinkedList<Caminho> caminhos = Jogo.getInstance(Jogo.getJogadores()).getTabuleiro().getCaminhos();
        if (quantidade == 0) { // Caso o jogador ande 0 espaços (fica parado / perde uma rodada)
            return "Rodada perdida. Na próxima, já poderá voltar a andar.";
        }
        int caminhoAtual = jogador.getPosicao()[0];
        int espacoAtual = jogador.getPosicao()[1];
        int espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
        int novoEspaco = espacoAtual + quantidade; // novo espaço do jogador após se mover
        String descricaoDoEspaco = "\n";

        if (quantidade > 0 && jogador.getGrupo() != null) {
            jogador.ajustarPontosOportunidade(quantidade);
        }
        // Jogador avança um caminho
        int caminhosAvancados = 0;
        while (caminhoAtual < caminhos.size() && novoEspaco >= espacosCaminhoAtual) {
            // Verifica e executa espaços obrigatórios entre o espaço atual e o fim do
            // caminho
            if (caminhosAvancados > 0) {
                descricaoDoEspaco += executarEspacosObrigatorios(jogador, caminhoAtual, 0, espacosCaminhoAtual);
            } else {
                descricaoDoEspaco += executarEspacosObrigatorios(jogador, caminhoAtual, espacoAtual + 1,
                        espacosCaminhoAtual);
            }
            novoEspaco -= espacosCaminhoAtual;
            caminhoAtual++;
            // Verifica se entrou no caminho do intercâmbio a partir do último caminho do
            // tabuleiro normal e corrige a posição para o fim do tabuleiro
            if (caminhoAtual == 13) {
                caminhoAtual--;
                novoEspaco = caminhos.get(caminhoAtual).getNumeroEspacos() - 1;
                espacoAtual = novoEspaco;
                break;
            }
            // Verifica todos os espaços entre espacoAtual - 1 e espacosCaminhoAtual para
            // ver se tem um espaço obrigatório
            // True se ele entrar numa bifurcação pela primeira vez (vai sempre entrar pela
            // bifurcação A primeiro)
            if (jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)
                    &&
                    !jogador.getBifurcacoesPercorridas().contains(caminhoAtual + 1)) {
                caminhoAtual = jogadorEscolheBifurcacao(jogador, caminhoAtual);
                jogador.adicionarBifurcacaoPercorrida(caminhoAtual);
            }
            // True se caminhoAtual igualar ao da bifurcação A mas jogador decidiu percorrer
            // a B anteriormente (e vice-versa)
            if (jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual)
                    ||
                    (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
                caminhoAtual++;
            }
            // Verifica se saiu da primeira bifurcação sem uma fonte de renda
            if (caminhoAtual == 3 && jogador.getFonteDeRenda() == null) {
                jogador.setFonteDeRenda(FonteDeRenda.BOLSA_AUXILIO);
                System.out.printf("A universidade decidiu lhe dar uma bolsa-auxílio! (%d de remuneração)\n",
                        FonteDeRenda.BOLSA_AUXILIO.getRenda());
            }
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            if (caminhoAtual >= caminhos.size()) { // Verifica se chegou no final do tabuleiro (último espaço do
                                                   // intercâmbio)
                caminhoAtual = 9;
                novoEspaco = 4;
                espacoAtual = novoEspaco;
                caminhosAvancados++;
            }
        }

        // Jogador volta um caminho
        while (caminhoAtual >= 0 && novoEspaco < 0) {
            caminhoAtual--;
            espacosCaminhoAtual = caminhos.get(caminhoAtual).getNumeroEspacos();
            // Verifica se o jogador entrou na bifurcação errada: se entrou, volta mais um
            // caminho (ou seja, B -> A ou A -> caminho normal)
            if ((jogadorEntrouEmBifurcacao(caminhoAtual) && !jogador.getBifurcacoesPercorridas().contains(caminhoAtual))
                    ||
                    (caminhoAtual % 3 == 2 && jogador.getBifurcacoesPercorridas().contains(caminhoAtual - 1))) {
                caminhoAtual--;
            }
            if (caminhoAtual < 0) { // Verifica se voltou pro início do tabuleiro
                caminhoAtual = 0;
                novoEspaco = 0;
                break;
            }
            novoEspaco += espacosCaminhoAtual;
        }
        jogador.setPosicao(new int[] { caminhoAtual, novoEspaco });
        if (quantidade > 0) { // Só executa a ação do espaço se o jogador tiver avançado no tabuleiro, não
                              // voltado
            if (caminhosAvancados > 0) {
                executarEspacosObrigatorios(jogador, caminhoAtual, 0, novoEspaco);
            } else {
                executarEspacosObrigatorios(jogador, caminhoAtual, espacoAtual + 1, novoEspaco);
            }
            caminhos.get(caminhoAtual).getEspacos().get(novoEspaco).acao(jogador);
            descricaoDoEspaco += caminhos.get(caminhoAtual).getEspacos().get(novoEspaco).imprimirDescricao();
        }
        return descricaoDoEspaco;
    }

    /**
     * Verifica se tem espaços obrigatórios em determinado intervalo de um caminho.
     * Se tiver, executa a ação de cada um.
     * 
     * @param jogador       jogador sobre o qual a ação vai ser executada.
     * @param caminho       caminho atual do jogador.
     * @param espacoInicial espaço inicial do intervalo.
     * @param limite        limite do intervalo.
     */
    private String executarEspacosObrigatorios(Jogador jogador, int caminho, int espacoInicial, int limite) {
        LinkedList<Caminho> caminhos = Jogo.getInstance(Jogo.getJogadores()).getTabuleiro().getCaminhos();
        String descricao = "";
        for (int i = espacoInicial; i < limite; i++) {
            Espaco espacoIntermediario = caminhos.get(caminho).getEspacos().get(i);
            // Verifica se o espaço é um espaço de remuneração ou a casa da caridade
            if (espacoIntermediario instanceof EspacoRemuneracao || caminho == 12 && i == 1) {
                espacoIntermediario.acao(jogador);
                descricao += espacoIntermediario.imprimirDescricao();
            }
        }
        return descricao;
    }

    /**
     * Verifica se o jogador entrou em uma bifurcação. Se for múltiplo de 3 + 1 (1,
     * 4, 7...) e não for o intercâmbio retorna true.
     * 
     * @param caminho índice do caminho.
     */
    private boolean jogadorEntrouEmBifurcacao(int caminho) {
        return (caminho - 1) % 3 == 0 && caminho != 13; // Exclui o caminho equivalente ao intercâmbio
    }

    /**
     * Permite que o jogador escolha qual caminho seguir quando encontra uma
     * bifurcação.
     * 
     * @param jogador jogador que entrou na bifurcação.
     * @param caminho caminho que determina qual bifurcação do tabuleiro o jogador
     *                entrou.
     * @param scan    scanner que lê a entrada, ou seja, a escolha do jogador.
     * @return
     */
    private int jogadorEscolheBifurcacao(Jogador jogador, int caminho) {
        System.out.println("Você chegou em uma bifurcação. ");

        if (caminho == 1) {
            System.out.println("1. Focar em dinheiro");
            System.out.println("2. Focar em socialização e oportunidades");
        }
        int escolha = Entrada.respostaInt();
        switch (escolha) {
            case 0:
                // Os multiplicadores por padrão já são os do caminho 1, então não há essa
                // condicional aqui
                return caminho;
            case 1:
                if (caminho == 1) {
                    jogador.setMultiplicadorDinheiro(1.5);
                    jogador.setMultiplicadorOportunidade(3.5);
                    jogador.setMultiplicadorNetworking(5);
                }
                return caminho + 1;
            // alguma coisa no caso default (tratar exceção de escolha inválida??)
        }
        return caminho;
    }

}

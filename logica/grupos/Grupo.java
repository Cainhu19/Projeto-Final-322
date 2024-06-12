package logica.grupos;

import logica.dados_do_jogo.*;

/**
 * Classe que representa os grupos (entidades, ligas etc.) dos quais um jogador
 * pode fazer parte.
 * 
 * Possui atributos de nome, o dado especial que vem de bônus,
 * a quantidade de vezes que esse dado especial é rodado caso for escolhido
 * e um valor booleano para indicar se um jogador já possui a instância do
 * grupo.
 */
public enum Grupo {
    ATLETICA_ESPORTES("Atlética, esportes", new D4(1, 2, 3, 4), 3),
    ATLETICA_EVENTOS("Atlética, eventos", new D4(1, 2, 3, 4), 2),
    C_A_DIRETORIA("Centro acadêmico, diretoria", new D6(1, 2, 3, 4, 5, 6), 2),
    C_A_MARKETING("Centro acadêmico, marketing", new D6(1, 2, 3, 4, 5, 6), 1),
    EMPRESA_JUNIOR("Empresa Júnior", new D6(0, 1, 2, 3, 9, 10), 1),
    INICIACAO_CIENTIFICA("Iniciação científica", new D8(1, 2, 3, 4, 5, 6, 7, 8), 1),
    LIGA_MULTIDISCIPLINAR("Liga multidisciplinar", new D6(3, 4, 5, 6, 7, 8), 1),
    LIGA_PESQUISAS("Liga de pesquisas acadêmicas", new D6(0, 1, 2, 3, 4, 5), 1),
    PROJETO_EXNTENSAO("Projeto de extensão", new D4(1, 2, 3, 4), 1),
    REP_DISCENTE("Representação discente", new D6(4, 4, 4, 5, 5, 5), 1);

    private final String nome;
    private final Dado dado;
    private final int qtdVezesDado;
    private final int bonusNetworking;
    private boolean ocupado;

    private Grupo(String nome, Dado dado, int qtdVezesDado) {
        this.nome = nome;
        this.dado = dado;
        this.qtdVezesDado = qtdVezesDado;
        this.bonusNetworking = 30; // mudar de acordo com cada grupo dps
        this.ocupado = false;
    }

    public String getNome() {
        return nome;
    }

    public Dado getDado() {
        return dado;
    }

    public int getQtdVezesDado() {
        return qtdVezesDado;
    }

    public int getBonusNetworking() {
        return bonusNetworking;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}

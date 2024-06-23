package com.projetofinal322.logica;

/**
 * Enum que representa as fontes de renda / ocupações que um jogador pode ter.
 */
public enum FonteDeRenda {
    BOLSA_AUXILIO("Bolsa-Auxílio", 500),
    ASSISTENCIA_A_DOCENCIA("Assistência à docência", 600),
    FREELANCER("Freelancer", 650),
    ESTAGIO("Estágio", 700),
    MEIO_PERIODO("Meio Período", 800),
    EMPREENDIMENTO("Empreendimento", 1000),
    EMPREGO("Emprego", 1500);

    private final String nome;
    private final int renda;
    private boolean ocupada;

    private FonteDeRenda(String nome, int renda) {
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

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }
}

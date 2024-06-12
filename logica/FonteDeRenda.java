package logica;

public enum FonteDeRenda {
    BOLSA_AUXILIO("Bolsa-Auxílio", 900),
    EMPREGO("Emprego", 1400),
    MEIO_PERIODO("Meio Período", 700),
    ESTAGIO("Estágio", 600),
    EMPREENDIMENTO("Empreendimento", 800);

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

package logica;

public enum FonteDeRenda {
    HERDEIRO(1500),
    TIOS(1000),
    PAIS(1000),
    BOLSA_AUXILIO(900),
    EMPREGO(1400),
    MEIO_PERIODO(700),
    ESTAGIO(600),
    EMPREENDIMENTO(800);

    private final int renda;

    private FonteDeRenda(int renda) {
        this.renda = renda;
    }

    public int getRenda() {
        return renda;
    }
}

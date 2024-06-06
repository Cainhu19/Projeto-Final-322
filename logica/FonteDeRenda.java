package logica;

public enum FonteDeRenda {
    HERDEIRO_PARTICULAR(1500),
    HERDEIRO_PUBLICA(1100),
    BOLSA_AUXILIO(900),
    TIOS(1000),
    PAIS(1000),
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

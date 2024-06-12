package logica.grupos;

import logica.dados_do_jogo.D8;

public class IniciacaoCientifica extends Grupo {
    private static IniciacaoCientifica instance;

    private IniciacaoCientifica() {
        super("Iniciação Científica", new D8(1, 2, 3, 4, 5, 6, 7, 8), 1);
    }

    public static IniciacaoCientifica getInstance() {
        if (instance == null) {
            instance = new IniciacaoCientifica();
        }
        return instance;
    }
}

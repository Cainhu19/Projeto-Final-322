package logica.grupos_e_ocupacoes;

import logica.dados_do_jogo.D6;

public class RepresentacaoDiscente extends Grupo {
    private static RepresentacaoDiscente instance;

    private RepresentacaoDiscente() {
        super("Representação Discente", new D6(4, 4, 4, 5, 5, 5), 1);
    }

    public static RepresentacaoDiscente getInstance() {
        if (instance == null) {
            instance = new RepresentacaoDiscente();
        }
        return instance;
    }
}

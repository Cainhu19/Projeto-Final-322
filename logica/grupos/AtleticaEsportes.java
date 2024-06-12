package logica.grupos;

import logica.dados_do_jogo.D4;

public class AtleticaEsportes extends Grupo {
    private static AtleticaEsportes instance;

    private AtleticaEsportes() {
        super("Atlética (Esportes)", new D4(1, 2, 3, 4), 3);
    }

    public static AtleticaEsportes getInstance() {
        if (instance == null) {
            instance = new AtleticaEsportes();
        }
        return instance;
    }
}
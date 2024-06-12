package logica.grupos;

import logica.dados_do_jogo.D6;

public class LigaMultidisciplinar extends Grupo {
    private static LigaMultidisciplinar instance;

    private LigaMultidisciplinar() {
        super("Liga Multidisciplinar", new D6(3, 4, 5, 6, 7, 8), 1);
    }

    public static LigaMultidisciplinar getInstance() {
        if (instance == null) {
            instance = new LigaMultidisciplinar();
        }
        return instance;
    }
}
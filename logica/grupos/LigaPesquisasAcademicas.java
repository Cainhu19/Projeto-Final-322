package logica.grupos;

import logica.dados_do_jogo.D6;

public class LigaPesquisasAcademicas extends Grupo {
    private static LigaPesquisasAcademicas instance;

    private LigaPesquisasAcademicas() {
        super("Liga de Pesquisas Acadêmicas", new D6(0, 1, 2, 3, 4, 5), 1);
    }

    public static LigaPesquisasAcademicas getInstance() {
        if (instance == null) {
            instance = new LigaPesquisasAcademicas();
        }
        return instance;
    }
}
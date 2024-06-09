package logica.grupos_e_ocupacoes;

import logica.dados_do_jogo.D4;

public class ProjetoExtensao extends Grupo {
    private static ProjetoExtensao instance;

    private ProjetoExtensao() {
        super("Projeto de Extensão", new D4(1, 2, 3, 4), 1);
    }

    public static ProjetoExtensao getInstance() {
        if (instance == null) {
            instance = new ProjetoExtensao();
        }
        return instance;
    }
}

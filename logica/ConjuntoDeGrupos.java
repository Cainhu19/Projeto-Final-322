package logica;

import java.util.Arrays;
import java.util.List;

public enum ConjuntoDeGrupos {
    ATLETICA("Atlética", Arrays.asList(Grupo.ATLETICA_ESPORTES, Grupo.ATLETICA_EVENTOS)),
    C_A("Centro Acadêmico", Arrays.asList(Grupo.C_A_DIRETORIA, Grupo.C_A_MARKETING)),
    LIGA_ACADEMICA("Liga Acadêmica", Arrays.asList(Grupo.LIGA_MULTIDISCIPLINAR, Grupo.LIGA_PESQUISAS));

    private final String nome;
    private final List<Grupo> grupos;

    private ConjuntoDeGrupos(String nome, List<Grupo> grupos) {
        this.nome = nome;
        this.grupos = grupos;
    }

    public String getNome() {
        return nome;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }
}

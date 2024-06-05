package logica.dados;

/**
 * Classe abstrata que estabelece contrato que todos os dados do jogo devem
 * seguir.
 */
public abstract class Dado {
    protected int[] valores;

    public abstract int rodar();
}

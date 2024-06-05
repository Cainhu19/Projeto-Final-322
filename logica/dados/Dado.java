package logica.dados;

/**
 * Classe abstrata que estabelece contrato que todos os dados do jogo devem seguir.
 */
public abstract class Dado {
    public int[] valores;
    public abstract void rodar(int vezes);
}

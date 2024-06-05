package logica.dados_do_jogo;

import java.util.Random;

/**
 * Classe do "dado" especial com duas faces (no jogo, é a moeda comprada na loja).
 * 
 * Suas faces podem ter valores que divergem dos valores 1-2 usuais.
 */
public class D2 {
    private int[] valores;

    public D2(int v0, int v1) {
        valores = new int[2];
        valores[0] = v0;
        valores[1] = v1;
    }

    /**
     * Roda o dado e retorna o resultado de uma de suas faces.
     * 
     * @param vezes número de vezes que o dado é rodado.
     */
    public int rodar(int vezes) {
        Random rand = new Random();
        int resultado = 0;
        for (int i = 0; i < vezes; i++) {
            resultado += valores[rand.nextInt(2)];
        }

        return valores[resultado];
    }
}

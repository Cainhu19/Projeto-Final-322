package logica.dados_do_jogo;

import java.util.Random;

/**
 * Classe do dado normal do jogo com dez faces, um decaedro.
 * 
 * Suas faces podem ter valores que divergem dos valores 1-2-3-4-5-6-7-8-9-10 usuais.
 */
public class D10 extends Dado {
    private int[] valores;

    public D10(int v0, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        valores = new int[10];
        valores[0] = v0;
        valores[1] = v1;
        valores[2] = v2;
        valores[3] = v3;
        valores[4] = v4;
        valores[5] = v5;
        valores[6] = v6;
        valores[7] = v7;
        valores[8] = v8;
        valores[9] = v9;
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
            resultado += valores[rand.nextInt(10)];
        }
        return resultado;
    }
}

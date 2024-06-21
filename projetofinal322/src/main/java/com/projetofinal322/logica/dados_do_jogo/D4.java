package com.projetofinal322.logica.dados_do_jogo;

import java.util.Random;

/**
 * Classe do dado especial com quatro faces, um tetraedro.
 * 
 * Suas faces podem ter valores que divergem dos valores 1-2-3-4 usuais.
 */
public class D4 extends Dado {
    private int[] valores;

    public D4(int v0, int v1, int v2, int v3) {
        valores = new int[4];
        valores[0] = v0;
        valores[1] = v1;
        valores[2] = v2;
        valores[3] = v3;
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
            resultado += valores[rand.nextInt(4)];
        }
        return resultado;
    }
}

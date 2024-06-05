package logica.dados;

import java.util.Random;

public class D8 extends Dado {
    private int[] valores;

    public D8(int v0, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
        valores = new int[8];
        valores[0] = v0;
        valores[1] = v1;
        valores[2] = v2;
        valores[3] = v3;
        valores[4] = v4;
        valores[5] = v5;
        valores[6] = v6;
        valores[7] = v7;
    }

    public int rodar() {
        Random rand = new Random();
        int resultado = rand.nextInt(6);

        return valores[resultado];
    }

}

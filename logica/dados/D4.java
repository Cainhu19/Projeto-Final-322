package logica.dados;

import java.util.Random;

public class D4 extends Dado {
    private int[] valores;

    public D4(int v0, int v1, int v2, int v3) {
        valores = new int[4];
        valores[0] = v0;
        valores[1] = v1;
        valores[2] = v2;
        valores[3] = v3;
    }

    public int rodar() {
        Random rand = new Random();
        int resultado = rand.nextInt(6);

        return valores[resultado];
    }

}

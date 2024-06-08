package logica;

import java.util.Scanner;

public class Entrada {

    public static int escolhaBifurcacao() {
        Scanner sc = new Scanner(System.in);
        int res = sc.nextInt();
        sc.close();
        return res;
    }

}

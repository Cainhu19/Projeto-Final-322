package com.projetofinal322.logica;

import java.util.Scanner;

public class Entrada {
    public static Scanner sc = new Scanner(System.in);

    public static int respostaInt() {
        while (true) {
            if (sc.hasNextInt()) {
                return sc.nextInt();
            } else {
                sc.nextLine();
            }
        }
    }
    
    public static String respostaString() {
        return sc.next();
    }

    public static boolean continuarJogo() {
        return sc.next().equals("sair");
    }

    public static void fecharScanner() {
        sc.close();
    }
}

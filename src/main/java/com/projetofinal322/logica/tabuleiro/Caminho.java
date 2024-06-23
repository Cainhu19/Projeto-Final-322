package com.projetofinal322.logica.tabuleiro;

import java.util.LinkedList;

/**
 * Classe que representa um caminho no tabuleiro, isto é, um conjunto de espaços.
 */
public class Caminho {
    private LinkedList<Espaco> espacos;

    public Caminho() {
        this.espacos = new LinkedList<Espaco>();
    }

    public LinkedList<Espaco> getEspacos() { return espacos; }
    public int getNumeroEspacos() { return espacos.size(); }

    public void adicionarEspaco(Espaco espaco) {
        espacos.add(espaco);
    }
}

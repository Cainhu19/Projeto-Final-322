package logica.tabuleiro;

import java.util.LinkedList;

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

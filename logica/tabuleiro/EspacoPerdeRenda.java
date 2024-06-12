package logica.tabuleiro;

import logica.FonteDeRenda;
import logica.GerenciadorDeFontes;
import logica.Jogador;

public class EspacoPerdeRenda extends Espaco {

    public EspacoPerdeRenda(String descricao) {
        super(descricao);
    }

    /*
     * 
     */
    @Override
    public void acao(Jogador jogador) {
        if (jogador.getFonteDeRenda() == null) {
            System.err.println("Não possui renda para perder\n");
            return;
        } else if (jogador.getFonteDeRenda().equals(FonteDeRenda.BOLSA_AUXILIO)) {
            System.err.println("Jogador possui Bolsa-Auxilio e não pode perder sua renda\n");
            return;
        }
        GerenciadorDeFontes.liberar(jogador.getFonteDeRenda());
        System.err.println("Jogador perdeu a renda e agora tem apenas a Bolsa");
    }

}

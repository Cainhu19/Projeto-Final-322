package logica.tabuleiro.espacos;

import logica.FonteDeRenda;
import logica.GerenciadorDeFontes;
import logica.Jogador;
import logica.tabuleiro.Espaco;

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
            System.out.println("Não possui renda para perder");
            return;
        } else if (jogador.getFonteDeRenda().equals(FonteDeRenda.BOLSA_AUXILIO)) {
            System.out.println("Jogador possui Bolsa-Auxilio e não pode perder sua renda");
            return;
        }
        GerenciadorDeFontes.liberar(jogador.getFonteDeRenda());
        System.out.println("Jogador perdeu a renda e agora tem apenas a Bolsa");
    }
}

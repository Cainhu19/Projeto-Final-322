package logica.tabuleiro.espacos;

import logica.tabuleiro.Espaco;
import logica.Jogador;

/**
 * Classe de espaços específicos que ocorrem apenas uma vez no tabuleiro.
 * A ação de cada espaço é identificada com um parâmetro String id usado no método de ação em um switch. 
 */
public class EspacoEspecifico extends Espaco {
    private String id;

    public EspacoEspecifico(String descricao, String id) {
        super(descricao);
        this.id = id;
    }

    //TODO: escrever em comentários qual espaço tem qual id para melhor legibilidade
    @Override
    public void acao(Jogador jogador) {
        switch(id) {
            case "PERDER_RENDA":
                //
            case "PERDER_GRUPO":
                // 
            case "DINHEIRO_EM_FUNCAO_DE_DADO":
                // 
            case "DINHEIRO_SE_TIVER_RENDA_ESPECIFICA":
                //
            case "DINHEIRO_POR_P_NETWORKING":
                //
            case "ESTUDO_PARA_P_OPORTUNIDADES":
                //
            case "PAGAR_COM_OUTRO_JOGADOR":
                //
            case "PAGAR_PARA_P_NETWORKING":
                //
            case "PAGAR_PARA_ESTUDO":
                //
            case "RECEBER_OFERTA_EMPREGO_SE_P_NETWORKING_SUFICIENTE":
                //
            case "CAMPEONATO_ATLETICA":
                //
            case "VOLTAR_SE_NAO_TIVER_ESTUDO":
                //
            case "CASA_DO_AZAR":
                //
            case "CASA_DA_CARIDADE":
                //
            case "GANHAR_P_NETWORKING_COM_MAIOR_DADO":
                //
            case "P_NETWORKING_EM_FUNCAO_DE_DADO":
                //
            case "OPORTUNIDADE_DE_INTERCAMBIO":
                //
            case "PROMOCAO_LOJA":
                //
            case "ESCOLHER_JOGADOR_PARA_VOLTAR":
                //
            case "SAIR_GRUPO_GANHAR_P_OPORTUNIDADES":
                //
            default:
                throw new IllegalArgumentException("ID não reconhecido de espaço específico: " + id);
        }
    }
}

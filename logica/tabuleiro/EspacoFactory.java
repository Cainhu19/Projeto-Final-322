package logica.tabuleiro;

import org.w3c.dom.Element;

import logica.FonteDeRenda;
import logica.Grupo;

/**
 * Classe que gerencia criação de espaços conforme seu tipo e ação a ser feita.
 */
public class EspacoFactory {
    public static Espaco criarEspaco(Element espacoElement) {
        String tipo = espacoElement.getAttribute("tipo");
        String desc = espacoElement.getElementsByTagName("desc").item(0).getTextContent();

        switch (tipo) {
            case "REMUNERACAO":
                return new EspacoRemuneracao();
            case "DINHEIRO":
                int qtd = Integer.parseInt(espacoElement.getElementsByTagName("qtd").item(0).getTextContent());
                return new EspacoDinheiro(desc, qtd);
            case "RECEBER_OFERTA_RENDA":
                FonteDeRenda fonteDeRenda = FonteDeRenda.valueOf(espacoElement.getElementsByTagName("fonteDeRenda").item(0).getTextContent());
                return new EspacoOferta(desc, fonteDeRenda);
            // case "RECEBER_OFERTA_RENDA_GERAL":
                
            case "RECEBER_OFERTA_GRUPO":
                Grupo grupo = Grupo.valueOf(espacoElement.getElementsByTagName("grupo").item(0).getTextContent());
                return new EspacoOferta(desc, grupo);
            // case "RECEBER_OFERTA_GRUPO_GERAL"
                
            // case "RECEBER_OFERTA_GRUPO_CONJUNTO"
                
            case "GANHAR_P_NETWORKING":
                int bonusNetworking = Integer.parseInt(espacoElement.getElementsByTagName("bonusNetworking").item(0).getTextContent());
                return new EspacoNetworking(desc, bonusNetworking);
            case "ESTUDO":
                return new EspacoEstudo(desc);
            // case "PERDER_RODADA":
                // return new EspacoPerdeRodada();
            
            // case "DINHEIRO_EM_FUNCAO_DE_DADO":

            // case "DINHEIRO_SE_TIVER_RENDA_ESPECIFICA":

            // case "PAGAR_COM_OUTRO_JOGADOR":

            // case "ESTUDO_PARA_IC"

            // case "GANHAR_P_OPORTUNIDADES_SE_ESTIVER_EM_GRUPO"

            // case "PAGAR_PARA_P_NETWORKING"

            // case "ROLAR_DADO_DE_NOVO"
                
            // case "EXTREMIDADE":

            default:
                throw new IllegalArgumentException("Tipo não reconhecido de espaço: " + tipo);
        }
    }
}

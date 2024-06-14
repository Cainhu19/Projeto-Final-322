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
            case "DINHEIRO":
                int qtd = Integer.parseInt(espacoElement.getElementsByTagName("qtd").item(0).getTextContent());
                return new EspacoDinheiro(desc, qtd);
            case "RECEBER_OFERTA_RENDA":
                FonteDeRenda fonteDeRenda = FonteDeRenda.valueOf(espacoElement.getElementsByTagName("fonteDeRenda").item(0).getTextContent());
                return new EspacoOferta(desc, fonteDeRenda);
            // case "RECEBER_OFERTA_RENDA_GERAL":
                //TODO
            case "RECEBER_OFERTA_GRUPO":
                Grupo grupo = Grupo.valueOf(espacoElement.getElementsByTagName("grupo").item(0).getTextContent());
                return new EspacoOferta(desc, grupo);
            // case "RECEBER_OFERTA_GRUPO_GERAL"
                //TODO
            // case "RECEBER_OFERTA_GRUPO_CONJUNTO"
                //TODO
            case "GANHAR_P_NETWORKING":
                int bonusNetworking = Integer.parseInt(espacoElement.getElementsByTagName("bonusNetworking").item(0).getTextContent());
                return new EspacoNetworking(desc, bonusNetworking);
            case "ESTUDO":
                return new EspacoEstudo(desc);

            // case "EXTREMIDADE":
                //TODO

            default:
                throw new IllegalArgumentException("Tipo não reconhecido de espaço: " + tipo);
        }
    }
}

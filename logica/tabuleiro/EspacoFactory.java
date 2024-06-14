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

        // Criação de espaço verificando caso a caso.
        // No switch, os casos estão quase ordenados por recorrência da maior para a menor, às vezes agrupados fora de ordem para melhor .legibilidade
        switch (tipo) {
            case "GANHAR_P_NETWORKING": // 31 espaços
                int bonusNetworking = Integer.parseInt(espacoElement.getElementsByTagName("bonusNetworking").item(0).getTextContent());
                return new EspacoNetworking(desc, bonusNetworking);

            case "DINHEIRO": // 21 espaços
                int qtd = Integer.parseInt(espacoElement.getElementsByTagName("qtd").item(0).getTextContent());
                return new EspacoDinheiro(desc, qtd);
            
            case "ESTUDO": // 19 espaços
                return new EspacoEstudo(desc);
            
            case "RECEBER_OFERTA_RENDA_ESPECIFICA": // 14 espaços
                FonteDeRenda fonteDeRenda = FonteDeRenda.valueOf(espacoElement.getElementsByTagName("fonteDeRenda").item(0).getTextContent());
                return new EspacoOferta(desc, fonteDeRenda);

            // case "RECEBER_OFERTA_RENDA_GERAL": // 2 espaços

            case "REMUNERACAO": // 8 espaços
                return new EspacoRemuneracao();
            
            // case "RECEBER_OFERTA_GRUPO_CONJUNTO" // 8 espaços
                
            case "RECEBER_OFERTA_GRUPO_ESPECIFICO": // 7 espaços
                Grupo grupo = Grupo.valueOf(espacoElement.getElementsByTagName("grupo").item(0).getTextContent());
                return new EspacoOferta(desc, grupo);
            
            // case "RECEBER_OFERTA_GRUPO_GERAL" // 6 espaços
                                
            case "PERDER_RODADA": // 4 espaços
                return new EspacoPerdeRodada(desc);
            
            // case "PERDER_RENDA": // 1 espaço

            // case "PERDER_GRUPO": // 1 espaço

            // case "P_OPORTUNIDADES": // 3 espaços, todos no intercâmbio

            // case "LIMPAR_DIVIDA": // 2 espaços

            // case "ESTUDO_PARA_IC": // 2 espaços

            // case "P_OPORTUNIDADES_SE_ESTIVER_EM_GRUPO": // 2 espaços

            // case "ROLAR_DADO_DE_NOVO": // 2 espaços

            // case "EXTREMIDADE": // 2 espaços
            
            // case "DINHEIRO_EM_FUNCAO_DE_DADO": // 1 espaço

            // case "DINHEIRO_SE_TIVER_RENDA_ESPECIFICA": // 1 espaço

            // case "DINHEIRO_POR_P_NETWORKING" // 1 espaço

            // case "ESTUDO_PARA_P_OPORTUNIDADES" // 1 espaço

            // case "PAGAR_COM_OUTRO_JOGADOR": // 1 espaço

            // case "PAGAR_PARA_P_NETWORKING": // 1 espaço

            // case "PAGAR_PARA_ESTUDO": // 1 espaço

            // case "RECEBER_OFERTA_EMPREGO_SE_P_NETWORKING_SUFICIENTE": // 1 espaço

            // case "CAMPEONATO_ATLETICA": // 1 espaço

            // case "ESPACO_49B": // 1 espaço

            // case "CASA_DO_AZAR": // 1 espaço

            // case "CASA_DA_CARIDADE": // 1 espaço

            // case "GANHAR_P_NETWORKING_COM_MAIOR_DADO": // 1 espaço

            // case "P_NETWORKING_EM_FUNCAO_DE_DADO": // 1 espaço

            // case "OPORTUNIDADE_DE_INTERCAMBIO": // 1 espaço

            // case "PROMOCAO_LOJA": // 1 espaço

            // case "ESCOLHER_JOGADOR_PARA_VOLTAR": // 1 espaço

            // case "ESPACO_96B" // 1 espaço
                
            default:
                throw new IllegalArgumentException("Tipo não reconhecido de espaço: " + tipo);
        }
    }
}

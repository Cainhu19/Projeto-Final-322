package logica.tabuleiro.espacos;

import org.w3c.dom.Element;

import logica.ConjuntoDeGrupos;
import logica.FonteDeRenda;
import logica.Grupo;
import logica.tabuleiro.*;

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

            case "ESPECIFICO": // 20 espaços
                String id = espacoElement.getAttribute("id");
                return new EspacoEspecifico(desc, id);
            
            case "ESTUDO": // 19 espaços
                return new EspacoEstudo(desc);
            
            case "RECEBER_OFERTA_RENDA_ESPECIFICA": // 14 espaços
                FonteDeRenda fonteDeRenda = FonteDeRenda.valueOf(espacoElement.getElementsByTagName("fonteDeRenda").item(0).getTextContent());
                return new EspacoOferta(desc, fonteDeRenda);

            case "RECEBER_OFERTA_RENDA_GERAL": // 2 espaços
                return new EspacoOfertaGeral(desc, false);

            case "REMUNERACAO": // 8 espaços
                return new EspacoRemuneracao();
            
            case "RECEBER_OFERTA_GRUPO_CONJUNTO": // 8 espaços
                ConjuntoDeGrupos conjunto = ConjuntoDeGrupos.valueOf(espacoElement.getElementsByTagName("conjunto").item(0).getTextContent());
                return new EspacoOferta(desc, conjunto);

            case "RECEBER_OFERTA_GRUPO_ESPECIFICO": // 7 espaços
                Grupo grupo = Grupo.valueOf(espacoElement.getElementsByTagName("grupo").item(0).getTextContent());
                return new EspacoOferta(desc, grupo);
            
            case "RECEBER_OFERTA_GRUPO_GERAL": // 6 espaços
                return new EspacoOfertaGeral(desc, true);
                                
            case "PERDER_RODADA": // 4 espaços
                return new EspacoPerdeRodada(desc);
            
            case "P_OPORTUNIDADES": // 3 espaços, todos no intercâmbio
                int pontosOportunidade = Integer.parseInt(espacoElement.getElementsByTagName("qtd").item(0).getTextContent());
                return new EspacoOportunidade(desc, pontosOportunidade);

            // case "LIMPAR_DIVIDA": // 2 espaços

            // case "ESTUDO_PARA_IC": // 2 espaços

            // case "P_OPORTUNIDADES_SE_ESTIVER_EM_GRUPO": // 2 espaços

            // case "ROLAR_DADO_DE_NOVO": // 2 espaços

            case "EXTREMIDADE": // 2 espaços
                return new EspacoExtremidade(desc);
                
            default:
                throw new IllegalArgumentException("Tipo não reconhecido de espaço: " + tipo);
        }
    }
}

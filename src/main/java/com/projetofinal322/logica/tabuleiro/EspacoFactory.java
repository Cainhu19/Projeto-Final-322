package com.projetofinal322.logica.tabuleiro;

import org.w3c.dom.Element;

import com.projetofinal322.logica.*;
import com.projetofinal322.logica.tabuleiro.espacos.*;


/**
 * Classe que gerencia criação de espaços conforme seu tipo e ação a ser feita.
 */
public class EspacoFactory {
    public static Espaco criarEspaco(Element espacoElement) {
        String tipo = espacoElement.getAttribute("tipo");
        String desc = espacoElement.getElementsByTagName("desc").item(0).getTextContent();

        // Criação de espaço verificando caso a caso.
        // No switch, os casos estão quase ordenados por recorrência da maior para a menor, às vezes agrupados fora de ordem para melhor legibilidade.
        switch (tipo) {
            case "GANHAR_P_NETWORKING": // 31 espaços
                int bonusNetworking = Integer.parseInt(espacoElement.getElementsByTagName("bonusNetworking").item(0).getTextContent());
                return new EspacoNetworking(desc, bonusNetworking);

            case "DINHEIRO": // 23 espaços
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
                GrupoConjunto conjunto = GrupoConjunto.valueOf(espacoElement.getElementsByTagName("conjunto").item(0).getTextContent());
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
                return new EspacoOportunidade(desc, pontosOportunidade, false);

            case "P_OPORTUNIDADES_SE_ESTIVER_EM_GRUPO": // 2 espaços
                int pontosOportunidadeSeEstiverEmGrupo = Integer.parseInt(espacoElement.getElementsByTagName("qtd").item(0).getTextContent());
                return new EspacoOportunidade(desc, pontosOportunidadeSeEstiverEmGrupo, true);

            case "ESTUDO_PARA_IC": // 2 espaços
                Grupo grupoOfertado = Grupo.valueOf(espacoElement.getElementsByTagName("grupo").item(0).getTextContent());
                int vezesEstudos = Integer.parseInt(espacoElement.getElementsByTagName("vezesEstudos").item(0).getTextContent());
                return new EspacoEstudoParaOferta(desc, grupoOfertado, vezesEstudos);

            case "EXTREMIDADE": // 2 espaços
                return new EspacoExtremidade(desc);
                
            default:
                throw new IllegalArgumentException("Tipo não reconhecido de espaço: " + tipo);
        }
    }
}

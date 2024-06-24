package com.projetofinal322.logica.resultados;

import com.projetofinal322.logica.Jogador;

import java.util.Map;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe responsável por criar um arquivo .txt para salvar o resultado final de um jogo.
 */
public class GravacaoResultados {
    public static void gravarResultados() {
        LocalDateTime agora = LocalDateTime.now();
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH-mm-ss");
        String dataEHoraFormatadas = agora.format(formato);

        Map<Jogador, Double> classificacao = FimDeJogo.getClassificacao();

        String path = "JVU-" + dataEHoraFormatadas + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(path, true))) {
            writer.println("JOGO DA VIDA UNIVERSITÁRIO");
            writer.println("Resultados do jogo finalizado em " + dataEHoraFormatadas);
            writer.println();

            int i = 1;

            for (Map.Entry<Jogador, Double> par : classificacao.entrySet()) {
                Jogador jogador = par.getKey();
                Double pontuacao = par.getValue();

                String ranking = i++ + "º lugar [" + pontuacao + "] - " + jogador.getNome();
                String dinheiroFinal = "Dinheiro = " + jogador.getDinheiro() + " [" + jogador.getMultiplicadorDinheiro() + "]";
                String oportunidadesFinal = "Pontos de oportunidades = " + jogador.getPontosOportunidade() + " ["  + jogador.getMultiplicadorOportunidade() + "]";
                String networkingFinal = "Pontos de networking = " + jogador.getPontosNetworking() + " ["  + jogador.getMultiplicadorNetworking() + "]";
                String fonteDeRendaFinal = "Fonte de renda final: " + jogador.getFonteDeRenda().getNome();
                String grupoFinal = "Grupo final: " + jogador.getGrupo().getNome();

                writer.println(ranking);
                writer.println(dinheiroFinal);
                writer.println(oportunidadesFinal);
                writer.println(networkingFinal);
                writer.println();
                writer.println(fonteDeRendaFinal);
                writer.println(grupoFinal);
                writer.println();
            }
            writer.println();
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo " + path + ": " + e.getMessage());
        }
    }
}

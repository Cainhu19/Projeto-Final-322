package arquivos_leitura;

import logica.tabuleiro.*;

import java.io.File;
import java.util.LinkedList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Realiza a inicialização do tabuleiro ao ler o arquivo XML dos espaços.
 */
public class LerEspacos implements LerArquivo {
    /**
     * Método mestre de leitura arquivo do tabuleiro.
     * @param pathTabuleiro caminho até o arquivo XML que contém outros arquivos XML com conjuntos de 3 caminhos cada.
     */
    @Override
    public void lerArquivo(String pathTabuleiro) {
        LinkedList<String> arquivosCaminhos = getArquivosCaminho(pathTabuleiro);

        for (String arq : arquivosCaminhos) 
            lerArquivoCaminhos(arq);
    }

    /**
     * Método para extrair e retornar os arquivos XML dos conjuntos de caminhos contidos no arquivo do tabuleiro inteiro.
     * @param pathTabuleiro caminho até o arquivo do tabuleiro que contém os arquivos XML com conjuntos de 3 caminhos cada.
     * @return uma lista ligada com os caminhos, em ordem, do tabuleiro lidos.
     */
    private LinkedList<String> getArquivosCaminho(String pathTabuleiro) {
        LinkedList<String> arquivosCaminho = new LinkedList<>();

        try {
            File file = new File(pathTabuleiro);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("arqCaminhos");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element arquivoCaminhoElement = (Element) nodeList.item(i);
                String pathArquivoCaminhos = arquivoCaminhoElement.getTextContent();
                arquivosCaminho.add(pathArquivoCaminhos);
            }

        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo do tabuleiro: " + e.getMessage());
            e.printStackTrace();
        }
        
        return arquivosCaminho;
    }

    /**
     * Lê cada arquivo de conjunto de caminhos, delegando a criação dos espaços para EspacoFactory.
     * @param path caminho até o arquivo de conjunto de caminhos a ser lido.
     */
    private void lerArquivoCaminhos(String path) {
        Tabuleiro t = Tabuleiro.getInstance();

        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("caminho");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element caminho = (Element) nodeList.item(i);
                Caminho c = new Caminho();

                NodeList espacosNodeList = caminho.getElementsByTagName("espaco");
                for (int j = 0; j < espacosNodeList.getLength(); j++) {
                    Element espacoElement = (Element) espacosNodeList.item(j);
                    Espaco espaco = EspacoFactory.criarEspaco(espacoElement);
                    c.adicionarEspaco(espaco);
                }

                t.adicionarCaminho(c);

            }

        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo de caminhos e espaços: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
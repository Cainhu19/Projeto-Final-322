package arquivos_leitura;

import logica.tabuleiro.*;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Realiza a inicialização do tabuleiro ao ler o arquivo XML de jogadores.
 */
public class LerEspacos implements I_Arquivo {
    @Override
    public void lerArquivo(String path) {
        Tabuleiro t = Tabuleiro.getInstance();

        try {
            File file = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Caminho");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element caminho = (Element) nodeList.item(i);
                Caminho c = new Caminho();

                NodeList espacosNodeList = caminho.getElementsByTagName("Espaco");
                for (int j = 0; j < espacosNodeList.getLength(); j++) {
                    Element espaco = (Element) espacosNodeList.item(j);
                    String desc = espaco.getElementsByTagName("desc").item(0).getTextContent();
                    String tipo = espaco.getElementsByTagName("tipo").item(0).getTextContent();
                    switch (tipo) {
                        case "DINHEIRO":
                            int quantidade = Integer.parseInt(espaco.getElementsByTagName("quantidade").item(0).getTextContent());
                            EspacoDinheiro espDin = new EspacoDinheiro(desc, quantidade);
                            c.adicionarEspaco(espDin);
                            break;
                        case "NETWORKING":
                            int bonusNetworking = Integer.parseInt(espaco.getElementsByTagName("bonusNetworking").item(0).getTextContent());
                            EspacoNetworking espNet = new EspacoNetworking(desc, bonusNetworking);
                            c.adicionarEspaco(espNet);
                            break;
                    
                        // default é o tipo EXTREMIDADE, i.e. início e fim do tabuleiro
                        default: 
                            break;
                    }
                }

                t.adicionarCaminho(c);

            }

        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo de espaços: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
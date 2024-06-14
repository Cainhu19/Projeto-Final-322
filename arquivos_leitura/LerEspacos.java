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
                    Element espacoElement = (Element) espacosNodeList.item(j);
                    Espaco espaco = EspacoFactory.criarEspaco(espacoElement);
                    c.adicionarEspaco(espaco);
                }

                t.adicionarCaminho(c);

            }

        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo de espaços: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
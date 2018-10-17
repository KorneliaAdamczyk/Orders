import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        System.out.println("Podaj ścieżkę do pliku w formacie xml lub csv");
        XmlToDatabase xmlToDatabase = new XmlToDatabase();
        Download download = new Download (xmlToDatabase);
        download.downloadingFile();
    }
}

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.SQLException;

public class Application {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException, XPathExpressionException, ClassNotFoundException, IllegalAccessException, InstantiationException {

        System.out.println("Podaj ścieżkę do pliku w formacie xml lub csv");
        Download download = new Download ();
        download.downloadingFile( );

    }
}

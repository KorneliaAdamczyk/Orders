import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class Download {

    private XmlToDatabase xmlToDatabase;

    public Download (final XmlToDatabase xmlToDatabase){
        this.xmlToDatabase = xmlToDatabase;
    }

    public void downloadingFile () throws IOException, ParserConfigurationException, SAXException {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();

        switch (Files.probeContentType(Paths.get(text))) {
            case "text/xml":
                System.out.println("to jest xml");
                xmlToDatabase.parsingXml(text);
                break;
            case "text/csv":
                System.out.println("to jest csv");
                break;
            default:
                System.out.println("zły format pliku");
        }
    }
}

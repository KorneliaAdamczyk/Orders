import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class Download {
    Data data;

    public void downloadingFile () throws IOException, ParserConfigurationException, SAXException, SQLException, XPathExpressionException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Scanner input = new Scanner(System.in);
        String text = input.nextLine();

        switch (Files.probeContentType(Paths.get(text))) {

            case "text/xml":
                System.out.println("to jest xml");
                data=new XmlToDatabase();
                data.connectDatabase();
                data.insertData(text);
                break;
            case "text/csv":
                System.out.println("to jest csv");
                break;
            default:
                System.out.println("zły format pliku");
        }
    }
}

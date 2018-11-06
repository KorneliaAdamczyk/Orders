import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.sql.SQLException;

public interface Data {

    void connectDatabase() throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException;
    void insertData (String text) throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException;
}

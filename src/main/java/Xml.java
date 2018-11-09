import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.List;


public class Xml implements Data{

    private Connection conn;
    
    public void connectDatabase () throws SQLException {
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "");
            conn.createStatement().execute("DROP TABLE IF EXISTS orders");
            conn.createStatement().execute("CREATE TABLE orders(clientId varchar(6),requestId long, name varchar (255), quantity int, price decimal(8,2))");
        }catch(ClassNotFoundException ex){
                System.out.println( "ERROR: Class not found: " + ex.getMessage() );
        conn.close();
            }

        }

    private static String getAttrValue(Node node,String attrName) {
        if ( ! node.hasAttributes() ) return "";
        NamedNodeMap nmap = node.getAttributes();
        if ( nmap == null ) return "";
        Node n = nmap.getNamedItem(attrName);
        if ( n == null ) return "";
        return n.getNodeValue();
    }

    private static String getTextContent(Node parentNode,String childName) {
        NodeList nlist = parentNode.getChildNodes();
        for (int i = 0 ; i < nlist.getLength() ; i++) {
            Node n = nlist.item(i);
            String name = n.getNodeName();
            if ( name != null && name.equals(childName) )
                return n.getTextContent();
        }
        return "";
    }

    public void insertData (String text) throws SQLException, XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(text);
        doc.getDocumentElement().normalize();
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nlist = (NodeList) xpath.evaluate("/requests/request", doc, XPathConstants.NODESET);
        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders(\n" + " clientId, requestId, name, quantity, price)\n" + "VALUES(?, ?, ?, ?, ?)\n");

            for (int i = 0; i < nlist.getLength(); i++) {
                Node node = nlist.item(i);
                List<String> columns = Arrays.asList(getAttrValue(node, "request"), getTextContent(node, "clientId"), getTextContent(node, "requestId"), getTextContent(node, "name"), getTextContent(node, "quantity"), getTextContent(node, "price"));
                for (int n = 1; n < columns.size(); n++) {
                    stmt.setString(n, columns.get(n));
                }
                stmt.execute();
            }
            Statement s1 = conn.createStatement();
            ResultSet quantityOrders = s1.executeQuery("SELECT COUNT (clientId) FROM orders");
            while (quantityOrders.next()) {
                int rowcount = quantityOrders.getInt(1);
                System.out.println("łączna ilość zamówień wynosi = " + rowcount + "\n");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    }

import com.sun.xml.internal.bind.marshaller.DataWriter;
import org.h2.store.DataReader;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Csv implements Data {

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

    public void insertData (String text) throws IOException, ClassNotFoundException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(text));
        String line;

        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/testdb", "sa", "");


        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO orders(\n" + " clientId, requestId, name, quantity, price)\n" + "VALUES ('" + arr[0] + "','" + arr[1] + "','" + arr[2] + "'" +
                    ",'" + arr[3] + "','" + arr[4]  + "') ");
            stmt.execute();
        }
    }
}

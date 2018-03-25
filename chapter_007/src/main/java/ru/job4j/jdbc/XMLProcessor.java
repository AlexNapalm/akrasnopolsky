package ru.job4j.jdbc;

import javax.xml.stream.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;

public class XMLProcessor {
    /**
     * URL to the database.
     */
    private String url;
    /**
     * Username for database.
     */
    private String username;
    /**
     * Password for username.
     */
    private String password;
    /**
     * Given number of values.
     */
    private int number;

    public XMLProcessor(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if database has the table. If it does not exist, method will create it.
     */
    public void initTable() {
        try (Connection conn = DriverManager.getConnection(this.url, this.username, this.password)) {
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE if not EXISTS test (field int)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fills table with 1, 2, 3, 4, ..., N values.
     * If table already has value, the method cleans it up.
     */
    public void fillTable(int number) {
        setNumber(number);
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement("DELETE FROM test where 1=1");
            ps.addBatch();
            ps.executeBatch();
            ps = conn.prepareStatement("INSERT into test VALUES (?)");
            for (int i = 0; i < this.number; i++) {
                ps.setInt(1, i + 1);
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Creates XML file from the values taken from the database.
     */
    public void createXML() {
        String file = "1.xml";
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try (Connection conn =
                     DriverManager.getConnection(this.url, this.username, this.password);
            FileWriter fw =
                new FileWriter(file)) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM test");
            XMLStreamWriter writer = factory.createXMLStreamWriter(fw);
            writer.writeStartDocument();
            writer.writeStartElement("entries");
            while (rs.next()) {
                writer.writeStartElement("entry");
                writer.writeStartElement("field");
                writer.writeCharacters(Integer.toString(rs.getInt("field")));
                writer.writeEndElement();
                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();
            writer.flush();
            writer.close();
        } catch (SQLException | IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforms 1.xml file to necessary format.
     */
    public void transformXML() {
        String xml = "1.xml";
        String xsl = "stylesheet.xsl";
        String result = "2.xml";

        StreamSource inputXML = new StreamSource(new File(xml));
        StreamSource inputXSL = new StreamSource(new File(xsl));
        StreamResult outputXML = new StreamResult(new File(result));

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer(inputXSL);
            transformer.transform(inputXML, outputXML);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses 2.xml file and calculates sum of all the fields.
     */
    private long parseXML() {
        String file = "2.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        long sum = 0;
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(file));
            while (reader.hasNext()) {
                reader.next();
                if (reader.isStartElement() && reader.getLocalName().equals("entry")) {
                    sum = sum + Long.valueOf(reader.getAttributeValue(0));
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return sum;
    }

    public void start() {
        initTable();
        fillTable(15);
        createXML();
        transformXML();
        System.out.println(String.format("Sum of values: %s", parseXML()));
    }
}

package ru.job4j.orders;

import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;

public class Parser {
    /**
     * Main container for all elements, parsed from the file.
     */
    private TreeMap<String, OrderBook> booksMap = new TreeMap<>();

    /**
     * Parsing method.
     * @throws FileNotFoundException
     * @throws XMLStreamException
     */
    public void startParsing() throws FileNotFoundException, XMLStreamException {
        String path = "chapter_005\\src\\main\\java\\ru\\job4j\\orders\\orders.xml";
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser = factory.createXMLStreamReader(new FileInputStream(path));

        while (parser.hasNext()) {
            int event = parser.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (parser.getLocalName().equals("AddOrder")) {
                    String book = parser.getAttributeValue(null, "book");
                    String operation = parser.getAttributeValue(null, "operation");
                    double price = Double.valueOf(parser.getAttributeValue(null, "price"));
                    int volume = Integer.valueOf(parser.getAttributeValue(null, "volume"));
                    int id = Integer.valueOf(parser.getAttributeValue(null, "orderId"));

                    OrderBook orderBook = booksMap.get(book);
                    if (orderBook == null) {
                        orderBook = new OrderBook();
                        booksMap.put(book, orderBook);
                    }
                    Order order = new Order(operation, price, volume, id);
                    orderBook.addOrder(id, order);

                } else if (parser.getLocalName().equals("DeleteOrder")) {
                    String book = parser.getAttributeValue(null, "book");
                    int id = Integer.valueOf(parser.getAttributeValue(null, "orderId"));
                    OrderBook orderBook = booksMap.get(book);
                    orderBook.deleteOrder(id);
                }
            }
        }
    }

    /**
     * prints all necessary data.
     */
    private void print() {
        for (String book : booksMap.keySet()) {
            System.out.println("Order Book: " + book);
            System.out.println("BID\t\t\tASK");
            System.out.println("Volume@Price - Volume@Price");
            OrderBook orderBook = booksMap.get(book);
            orderBook.print();
            System.out.println();
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        Parser parser = new Parser();

        System.out.println("start program");
        long start = System.currentTimeMillis();

        parser.startParsing();
        parser.print();

        long finish = System.currentTimeMillis() - start;
        System.out.println("program finished");
        System.out.println(finish + " millis");
    }
}

package com.sainsburys.scraper;

import java.io.IOException;
import java.math.BigDecimal;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sainsburys.model.Item;
import com.sainsburys.model.Items;

/**
 * Scraps web page to extract list of items and their property (title, price,
 * description, page size in kb) to use the class call its 'getItems' method and
 * a URL to scrap as an argument.
 *
 * @author Mehran
 */
public class WebScraper {

    /**
     * Set of elements that we need to look up.
     */
    public static final String PRODUCT_QUERY = ".product";

    public static final String PRICE_QUERY = ".pricePerUnit";

    public static final String TITLE_QUERY = ".productInfo h3 a";

    public static final String PRODUCT_INFO_QUERY = "h3.productDataItemHeader";

    public static final String PRODUCT_TEXT_QUERY = "div.productText";

    public static final String INFO_TO_RETRIEVE = "Description";

    // This is never going to change -  
    // but this will make it easy to understand why this number is being used in places.
    private static final int BYTES_IN_A_KIBIBYTE = 1024;

    /**
     * Get the HTML document and extracts list of items.
     *
     * @param url the URL to scrap
     * @return A list of items, which are found on the given URL
     * @throws IOException when a connection to the web page fails
     */
    public Items getItems(String url) throws IOException {
        Items itemList = new Items();
        // get HTML document
        Document doc = getDocument(url, 10000);

        //look for list of elements that match '.product' 
        Elements els = doc.select(PRODUCT_QUERY);

        // for each element in the list extract price, description, title, and 
        //product page size
        for (Element el : els) {
            Item item = getItem(el);
            itemList.addItem(item);
        }

        return itemList;

    }

    /**
     * Fetch content from the given URL, and parse them into Documents.
     *
     * @param url the URL to fetch
     * @param timeout number of milliseconds (thousandths of a second) before
     * timing out connects or reads. A timeout of zero is treated as an infinite
     * timeout.
     * @return A HTML Document.
     * @throws IOException
     */
    private Document getDocument(String url, int timeout) throws IOException {
        //get HTML document
        Connection conn = Jsoup.connect(url).timeout(timeout);
        Document doc = conn.get();
        return doc;
    }

    /**
     * Extracts item's properties (price description, title, page size) from the
     * given element.
     *
     * @param element An Element where the item information is available
     * @return an item with all of its properties (price description, title,
     * page size)
     * @throws IOException when a connection to the webpage fails
     */
    private Item getItem(Element element) throws IOException {

        Element elPrice = getChild(element, PRICE_QUERY);
        String priceText = elPrice.text();
        String plainPrice = trimPrice(priceText);

        Element elTitle = getChild(element, TITLE_QUERY);
        String title = elTitle.text();

        String size = getSize(element);

        String description = getDescription(element);

        Item item = new Item(title, size, new BigDecimal(plainPrice), description);
        return item;
    }

    /**
     * Find first element that match the Selector CSS query, with this element
     * as the starting context. Matched element may include this element, or any
     * of its children.
     *
     * @param element An Element where the item information is available
     * @param query A Selector CSS-like query
     * @return The first matched element, or null if contents is empty.
     */
    private Element getChild(Element element, String query) {
        Element child = element.select(query).first();
        return child;
    }

    /**
     * Trims the given price text to drop '£' and '/unit'.
     *
     * @param priceText A price string to be trimmed
     * @return The plain price with no symbol.
     */
    private String trimPrice(String priceText) {
        priceText = priceText.replaceAll("£", "");
        priceText = priceText.replaceAll("/unit", "");
        return priceText;
    }

    /**
     * Retrieves the size of the HTML page of individual product.
     *
     * @param item The item we want to retrieve page size
     * @return size of the page in 2 decimal palaces in kb
     * @throws IOException when a connection to the page fails
     */
    private String getSize(Element item) throws IOException {
        String size;
        Connection conn = getSubPage(item);
        float contentLength = (float) conn.get().toString().length();
        size = Math.round(contentLength / BYTES_IN_A_KIBIBYTE) + "kb";

        return size;
    }

    /**
     * Retrieves the description of the given item from the item's web page.
     *
     * @param item The item we want to retrieve description
     * @return description of the item
     * @throws IOException when a connection to the webpage fails
     */
    private String getDescription(Element item) throws IOException {
        String description;

        Connection conn = getSubPage(item);
        Document doc = conn.get();
        Element elm = null;
        // get the list of product data from the information tab on the web page
        Elements elms = doc.select(PRODUCT_INFO_QUERY);
        for (int i = 0; i < elms.size(); i++) {
            elm = elms.get(i);
            // check if the header text equals 'Description'
            if (INFO_TO_RETRIEVE.equals(elm.text())) {
                // if yes get the 'div.productText' at the same index as 'Description'
                elm = doc.select(PRODUCT_TEXT_QUERY).get(i);
                break;
            }
        }
        if (elm == null) {
            return null;
        } else {
            description = elm.text();
        }

        return description;
    }

    /**
     * Creates a new Connection to a specific item URL. Use to fetch and parse a
     * HTML page.
     *
     * @param item The item we want to stablish connection to its URL
     * @return the connection
     */
    private Connection getSubPage(Element item) {
        Element title = getChild(item, TITLE_QUERY);
        String url = title.attr("href");
        Connection conn = Jsoup.connect(url);
        return conn;
    }
}

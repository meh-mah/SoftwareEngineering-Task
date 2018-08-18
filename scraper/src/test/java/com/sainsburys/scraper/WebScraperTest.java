package com.sainsburys.scraper;

import com.sainsburys.model.Items;
import java.io.IOException;
import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to assert that the WebScraper works.
 *
 * @author Mehran
 */
public class WebScraperTest {

    private WebScraper scraper;
    private String url;

    /**
     * Set up URL to scrap and instantiate WebScraper class.
     *
     * @throws IOException incase of connection failure
     */
    @Before
    public void setup() throws IOException {
        scraper = new WebScraper();

        url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/"
                + "CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518"
                + "&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137"
                + "&searchTerm=&categoryId=185749&listId="
                + "&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137"
                + "&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20"
                + "&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";

    }

    /**
     * To test if IllegalArgumentException is thrown when URL is null
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullURL() throws Exception {
        Items items = scraper.getItems(null);
    }

    /**
     * Assert list in the Items object is empty and total price is set to Zero
     * when wrong URL is scraped and no item could be found
     *
     * @throws IOException
     */
    @Test
    public void testGetNullList() throws IOException {
        String invalidUrl = "http://www.google.com";
        Items items = scraper.getItems(invalidUrl);
        assertTrue(items.getItemList().isEmpty());
        assertEquals(items.getTotal(), new BigDecimal("0"));
    }

    /**
     * Assert list in the Items object is valid and not empty when correct URL
     * is scraped
     *
     * @throws IOException
     */
    @Test
    public void testGetItems() throws IOException {
        Items items = scraper.getItems(url);
        assertEquals(items.getTotal(), new BigDecimal("28.90"));
        assertEquals(items.getItemList().size(), 16);
        items.getItemList().stream().map((item) -> {
            assertNotNull(item);
            return item;
        }).map((item) -> {
            assertNotNull(item.getTitle());
            return item;
        }).map((item) -> {
            assertNotNull(item.getSize());
            return item;
        }).map((item) -> {
            assertNotNull(item.getPrice());
            return item;
        }).map((item) -> {
            assertNotNull(item.getDescription());
            return item;
        }).forEachOrdered((item) -> {
            assertTrue(!item.getDescription().isEmpty());
        });

    }

}

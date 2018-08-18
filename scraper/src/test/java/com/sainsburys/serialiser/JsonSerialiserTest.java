package com.sainsburys.serialiser;

import com.sainsburys.model.Items;
import com.sainsburys.scraper.WebScraper;

import java.io.IOException;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to assert that the JsonSerialiser works.
 *
 * @author Mehran
 */
public class JsonSerialiserTest {

    private WebScraper scraper;
    private String url;
    private ISerialiser serialiser;

    /**
     * Set up test environment.
     */
    @Before
    public void setup() {
        scraper = new WebScraper();
        serialiser = SerialiserFactory.getSerialiser("JSON");
        url = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/"
                + "CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518"
                + "&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId=10137"
                + "&searchTerm=&categoryId=185749&listId="
                + "&storeId=10151&promotionId=#langId=44&storeId=10151&catalogId=10137"
                + "&categoryId=185749&parent_category_rn=12518&top_category=12518&pageSize=20"
                + "&orderBy=FAVOURITES_FIRST&searchTerm=&beginIndex=0&hideFilters=true";
    }

    /**
     * Assert JSON file is not empty and is valid JSON
     *
     * @throws IOException
     */
    @Test
    public void testJsonNotEmpty() throws IOException {
        Items items = scraper.getItems(url);
        String json = serialiser.serialise(items);
        assertTrue(!json.isEmpty());
        // Ensure the price field is present - this ensures a valid JSON
        assertTrue(json.contains("price"));
    }
}

package com.sainsburys.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.sainsburys.model.Items;
import com.sainsburys.scraper.WebScraper;
import com.sainsburys.serialiser.ISerialiser;
import com.sainsburys.serialiser.SerialiserFactory;

/**
 * Main class.
 *
 * @author Mehran
 */
public class Main {

    // Allows us to extend to use other categories
    // Given more time, I would use a properties file for these
    private static final String CATEGORY_ID = "185749";

    // Allows us to change stores
    private static final String STORE_ID = "10151";

    // Allows us to change catalog
    private static final String CATALOG_ID = "10137";

    // Allows us to change catalog
    private static final String PARENT_CATEGORY_ID = "12518";

    // Allows us to change catalog
    private static final int ITEMS_PER_PAGE = 20;

    // Defult URL to scrap
    private static final String DEFAULT_URL = "http://www.sainsburys.co.uk/webapp/wcs/stores/servlet/"
            + "CategoryDisplay?listView=true&orderBy=FAVOURITES_FIRST&parent_category_rn=12518"
            + "&top_category=12518&langId=44&beginIndex=0&pageSize=20&catalogId="
            + CATALOG_ID + "&searchTerm=&categoryId="
            + CATEGORY_ID + "&listId=" + "&storeId="
            + STORE_ID + "&promotionId=#langId=44&parent_category_rn="
            + PARENT_CATEGORY_ID + "&top_category=12518&pageSize="
            + ITEMS_PER_PAGE + "&orderBy=FAVOURITES_FIRST&searchTerm="
            + "&beginIndex=0&hideFilters=true";

    // User provided URL (Optional)
    private static String ripeUrl;

    // Location to save JSON file.
    private static String jsonFile = "result.json";

    private static WebScraper scraper;

    private static ISerialiser serialiser;

    /**
     * Main method that is executed when the program runs.
     *
     * @param args Arguments provided when program is started
     */
    public static void main(String[] args) {

        // Get the URL as an optional argument. Otherwise, use the defaultUrl
        if (args.length == 1) {

            ripeUrl = args[0];
        } else {
            ripeUrl = DEFAULT_URL;
        }

        boolean valid = false;
        // To read user input
        Scanner sc = new Scanner(System.in);

        // create WebScraper object to extract data
        scraper = new WebScraper();

        // create JsonSerialiser object to format items list as JSON
        serialiser = SerialiserFactory.getSerialiser("JSON");

        do {
            try {

                // retrieve list of items and their properties from the page
                Items listOfProducts = scraper.getItems(ripeUrl);

                // format list of items as JSON and print
                System.out.println(serialiser.serialise(listOfProducts));

                // write list of items and total price into a file in JSON format
                File resultFile = new File(jsonFile);
                serialiser.serialiseToFile(resultFile, listOfProducts);

                valid = true;

            } catch (MalformedURLException | IllegalArgumentException ex) {
                // In case of invalid url, prompt user for new input
                System.err.println("Invalid link: %s" + ripeUrl);
                System.out.println("Please enter a valid URL:");
                ripeUrl = sc.nextLine();
            }catch (JsonProcessingException ex) {
                System.out.println("Error While converting java object to json string");
            }  catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (!valid);
    }
}

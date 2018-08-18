package com.sainsburys.model;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Test class to assert that the Items works.
 *
 * @author Mehran
 */
public class ItemsTest {

    private Item item1;
    private Item item2;
    private Item item3;
    private Items items;

    /**
     * Setup test data
     */
    @Before
    public void setup() {
        items = new Items();
        item1 = new Item("peach", "33.52Kb", new BigDecimal("1.99"),
                "Description peach");
        item2 = new Item("peach2", "33.52Kb", new BigDecimal("1.54"),
                "Description peach2");
        item3 = new Item("peach3", "33.52Kb", new BigDecimal("3.20"),
                "Description peach3");
    }

    /**
     * Test if NullPointerException is thrown in case of adding null Item to the
     * List
     */
    @Test(expected = NullPointerException.class)
    public void testGetTotalNull() {
        items.addItem(null);
    }

    /**
     * Assert total price of items is set to Zero when Items instantiated
     */
    @Test
    public void testGetTotalNoItems() {
        assertEquals(items.getTotal(), new BigDecimal("0"));
    }

    /**
     * Assert total price of items is set correctly after adding first Item to
     * the List
     */
    @Test
    public void testGetTotalOneItem() {
        items.addItem(item1);
        assertEquals(items.getTotal(), new BigDecimal("1.99"));
    }

    /**
     * Assert total price of items is set correctly when adding several Items to
     * the list
     */
    @Test
    public void testGetTotalSeveralItems() {
        items.addItem(item1);
        items.addItem(item2);
        items.addItem(item3);

        assertEquals(items.getTotal(), new BigDecimal("6.73"));
    }

}

package com.sainsburys.model;

import java.math.BigDecimal;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class to assert that the Item works.
 *
 * @author Mehran
 */
public class ItemTest {

    private Item item;

    /**
     * Setup test data
     */
    @Before
    public void setup() {
        item = new Item("peach", "33.52Kb", new BigDecimal("1.99"), "Description peach");
    }

    /**
     * Assert Accessor methods of the Item class
     */
    @Test
    public void testItemAccessor() {
        assertEquals(item.getTitle(), "peach");
        assertEquals(item.getSize(), "33.52Kb");
        assertEquals(item.getPrice(), new BigDecimal("1.99"));
        assertEquals(item.getDescription(), "Description peach");
    }

}

package com.sainsburys.model;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represent a list of Sainsbury's products (Items) and their total price.
 *
 * @author Mehran
 */
public class Items implements IModel {

    @JsonProperty("results")
    private final List<Item> itemList;

    /**
     * Sum of all items price.
     */
    @JsonProperty("total")
    private BigDecimal total = new BigDecimal("0");

    public Items() {
        this.itemList = new LinkedList<>();
    }

    /**
     * Add item to list.
     *
     * @param item Item to add
     */
    public void addItem(Item item) {
        itemList.add(item);
        total = total.add(item.getPrice());
    }

    /**
     * Get list of all items.
     *
     * @return List of items
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * Get total count.
     *
     * @return Total count
     */
    public BigDecimal getTotal() {
        return total;
    }
}

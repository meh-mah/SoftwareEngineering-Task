package com.sainsburys.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Item class that models each Sainsbury's product.
 *
 * @author Mehran
 */
@JsonPropertyOrder({"title", "size", "unit_price", "description"})
public class Item implements IModel {

    /**
     * Item Title.
     */
    private String title;

    /**
     * Item HTML page size.
     */
    private String size;

    /**
     * Item unit price.
     */
    @JsonProperty("unit_price")
    private BigDecimal price;

    /**
     * Item Description.
     */
    private String description;

    /**
     * Constructor.
     *
     * @param title Title of item
     * @param size Size of item
     * @param price Price of item
     * @param description Description
     */
    public Item(String title, String size, BigDecimal price, String description) {
        this.title = title;
        this.size = size;
        this.price = price;
        this.description = description;
    }

    /**
     * Set title of item.
     *
     * @param title Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get title of item.
     *
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set size attribute for item.
     *
     * @param size Size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Get size attribute of item.
     *
     * @return Size attribute
     */
    public String getSize() {
        return size;
    }

    /**
     * Set price of item.
     *
     * @param price Price of item
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Get price of item.
     *
     * @return Price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set description of item.
     *
     * @param description Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get description of the item.
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

}

package com.pucgo.cliserver.tocofome_admin.model;

import java.math.BigDecimal;

public class Item {

    public Item() {
    }

    public Item(Long id, String product, String description, String imageUrl, BigDecimal price) {
        this.id = id;
        this.product = product;
        this.description = description;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    private Long id;

    private String product;

    private String description;

    private String imageUrl;

    private BigDecimal price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

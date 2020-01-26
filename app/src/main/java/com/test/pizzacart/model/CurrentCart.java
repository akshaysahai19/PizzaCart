package com.test.pizzacart.model;

public class CurrentCart {

    private String title;
    private long quantity;
    private long price;
    private String Id;

    public CurrentCart(String title, long quantity, long price, String id) {
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}

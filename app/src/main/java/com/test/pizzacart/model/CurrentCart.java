package com.test.pizzacart.model;

public class CurrentCart {

    private String pizzaType;
    private long quantity;
    private long price;
    private long id;

    public CurrentCart(String pizzaType, long quantity, long price, long id) {
        this.pizzaType = pizzaType;
        this.quantity = quantity;
        this.price = price;
        this.id = id;
    }

    public String getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(String pizzaType) {
        this.pizzaType = pizzaType;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

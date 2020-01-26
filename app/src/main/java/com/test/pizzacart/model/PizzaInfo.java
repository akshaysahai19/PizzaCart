package com.test.pizzacart.model;

import java.io.Serializable;
import java.util.List;

public class PizzaInfo implements Serializable {

    private String name;
    private String description;
    private boolean isVeg;
    private long defaultCrust;
    private List<Crusts> crusts;

    public PizzaInfo(String name, String description, boolean isVeg, long defaultCrust, List<Crusts> crusts) {
        this.name = name;
        this.description = description;
        this.isVeg = isVeg;
        this.defaultCrust = defaultCrust;
        this.crusts = crusts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVeg() {
        return isVeg;
    }

    public void setVeg(boolean veg) {
        isVeg = veg;
    }

    public long getDefaultCrust() {
        return defaultCrust;
    }

    public void setDefaultCrust(long defaultCrust) {
        this.defaultCrust = defaultCrust;
    }

    public List<Crusts> getCrusts() {
        return crusts;
    }

    public void setCrusts(List<Crusts> crusts) {
        this.crusts = crusts;
    }


}

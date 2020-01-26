package com.test.pizzacart.model;

import java.io.Serializable;
import java.util.List;

public class Crusts implements Serializable {

    private long defaultSize;
    private long id;
    private String name;
    private List<Sizes> sizes;

    public Crusts(long defaultSize, long id, String name, List<Sizes> sizes) {
        this.defaultSize = defaultSize;
        this.id = id;
        this.name = name;
        this.sizes = sizes;
    }

    public long getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(long defaultSize) {
        this.defaultSize = defaultSize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sizes> getSizes() {
        return sizes;
    }

    public void setSizes(List<Sizes> sizes) {
        this.sizes = sizes;
    }
}
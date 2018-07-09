package com.issp.mobileprogrammingapp.entity;

public class Indicator {
    private String id;
    private String name;

    public Indicator() {
    }

    public Indicator(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

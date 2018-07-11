package com.ipss.worldbank.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DataChart implements Serializable{
    private String name;
    private String country;
    private String indicator;
    private List<Point> pointList;

    public DataChart(String country, String indicator, List<Point> pointList) {
        this.country = country;
        this.indicator = indicator;
        this.pointList = pointList;
    }

    public DataChart(String name, String country, String indicator, List<Point> pointList) {
        this.name = name;
        this.country = country;
        this.indicator = indicator;
        this.pointList = pointList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList(LinkedList<Point> pointList) {
        this.pointList = pointList;
    }
}

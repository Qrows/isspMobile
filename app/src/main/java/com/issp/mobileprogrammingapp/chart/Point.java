package com.ipss.worldbank.chart;

public class Point {
    private int year;
    private double value;

    public Point(int year, double value) {
        this.year = year;
        this.value = value;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

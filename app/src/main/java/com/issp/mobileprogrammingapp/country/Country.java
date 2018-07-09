package com.ipss.worldbank.country;

import android.graphics.Bitmap;

public class Country {
    private String name;
    private String iso;
    private Bitmap flag;

    public Country(String name, String iso) {
        this.name = name;
        this.iso = iso;
    }

    public Country() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public Bitmap getFlag() {
        return flag;
    }

    public void setFlag(Bitmap flag) {
        this.flag = flag;
    }
}

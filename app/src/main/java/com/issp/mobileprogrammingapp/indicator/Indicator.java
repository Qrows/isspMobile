package com.ipss.worldbank.indicator;

public class Indicator {
    private String name;
    private String source;
    private String organizationName;
    private String sourceDescription;

    public Indicator(String name, String source, String organizationName, String sourceDescription) {
        this.name = name;
        this.source = source;
        this.organizationName = organizationName;
        this.sourceDescription = sourceDescription;
    }

    public Indicator() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }
}

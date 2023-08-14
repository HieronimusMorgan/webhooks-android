package com.morg.webhook.model;

public class Fields {
    private String name;
    private String value;
    private boolean inline;

    public Fields() {
    }

    public Fields(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isInline() {
        return inline;
    }

    public void setInline(boolean inline) {
        this.inline = inline;
    }

    @Override
    public String toString() {
        return "{" +
                "\"name\" : \"" + name + "\"" +
                ", \"value\" : \"" + value + "\" " +
                ", \"inline\" : \"" + inline + "\"}";
    }
}

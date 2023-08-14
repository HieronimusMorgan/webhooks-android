package com.morg.webhook.model;

import java.util.List;

public class Embeds {
    private String title;
    private String description;
    private String timestamp;
    private List<Fields> fields;

    public Embeds() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "{" +
                "\"title\" : \"" + title + '\"' +
                ", \"description\" : \"" + description + "\"" +
                ", \"timestamp\" : \"" + timestamp + "\"" +
                ", \"fields\" : \"" + fields + "\"}";
    }
}

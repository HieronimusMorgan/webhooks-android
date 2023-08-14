package com.morg.webhook.model;

import java.util.List;

public class Webhooks {
    private String content;
    private List<Embeds> embeds;

    public Webhooks() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Embeds> getEmbeds() {
        return embeds;
    }

    public void setEmbeds(List<Embeds> embeds) {
        this.embeds = embeds;
    }

    @Override
    public String toString() {
        return "{" +
                "\"content\": \"" + content + '\"' +
                ", \"embeds:\"" + embeds + "\"}";
    }
}

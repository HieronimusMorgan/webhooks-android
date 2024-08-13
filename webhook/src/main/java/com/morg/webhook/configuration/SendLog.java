package com.morg.webhook.configuration;

import android.content.Context;

import com.morg.webhook.model.Fields;

public class SendLog {
    private final Context context;
    private final String webhooksUrl;

    public SendLog(Context context, String webhooksUrl) {
        this.context = context;
        this.webhooksUrl = webhooksUrl;
    }

    public void sendLogError(Throwable throwable) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Error")
                .setDescription(throwable.getMessage())
                .sendLog();
    }

    public void sendLogInfo(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Info")
                .setDescription(message)
                .sendLog();
    }

    public void sendLogWarning(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Warning")
                .setDescription(message)
                .sendLog();
    }

    public void sendLogDebug(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Debug")
                .setDescription(message)
                .sendLog();
    }

    public void sendLogVerbose(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Verbose")
                .setDescription(message)
                .sendLog();
    }

    public void sendLogAssert(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Assert")
                .setDescription(message)
                .sendLog();
    }

    public void sendLogError(String tag, Throwable throwable) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Error")
                .setDescription(throwable.getMessage())
                .addField(new Fields("TAG", tag))
                .sendLog();
    }

    public void sendLogInfo(String tag, String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Info")
                .setDescription(message)
                .addField(new Fields("TAG", tag))
                .sendLog();
    }

    public void sendLogWarning(String tag, String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Warning")
                .setDescription(message)
                .addField(new Fields("TAG", tag))
                .sendLog();
    }

    public void sendLogDebug(String tag, String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Debug")
                .setDescription(message)
                .addField(new Fields("TAG", tag))
                .sendLog();
    }

    public void sendLogVerbose(String tag, String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Verbose")
                .setDescription(message)
                .addField(new Fields("TAG", tag))
                .sendLog();
    }

    public void sendLogAssert(String tag, String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .setTitle("Log Assert")
                .setDescription(message)
                .addField(new Fields("TAG", tag))
                .sendLog();
    }
}


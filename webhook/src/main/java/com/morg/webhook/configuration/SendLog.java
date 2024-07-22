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
                .addField(new Fields("Log Verbose", message))
                .sendLog();
    }

    public void sendLogAssert(String message) {
        new WebhooksConfiguration(context, webhooksUrl)
                .addField(new Fields("Log Assert", message))
                .sendLog();
    }
}

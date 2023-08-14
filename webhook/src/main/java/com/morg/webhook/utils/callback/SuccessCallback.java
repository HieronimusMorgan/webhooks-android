package com.morg.webhook.utils.callback;

public interface SuccessCallback<T> {
    void onSuccess(T object);
}
package com.morg.webhook.utils.callback;

/**
 * Created by LENOVO on 8/14/2018.
 */

public interface ZipCallback {
    void compressSuccess(Object message);

    void compressFailed(Object message);
}

package com.morg.webhook.data;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface WebhooksServiceAPI {
    @Multipart
    @POST("{fullUrl}")
    Call<String> sendWebhooksRequestBody(@Path(value = "fullUrl", encoded = true) String fullUrl,
                                         @Part("payload_json") okhttp3.RequestBody name,
                                         @Part MultipartBody.Part file);
    @Multipart
    @POST("{fullUrl}")
    Call<String> sendWebhooksRequestBodyLog(@Path(value = "fullUrl", encoded = true) String fullUrl,
                                         @Part("payload_json") okhttp3.RequestBody name);
}

package com.morg.webhook.data;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitHttpsCall {
    protected Retrofit client;
    protected OkHttpClient okHttpClient;

    public RetrofitHttpsCall(String urlApi) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.addHeader("Cache-Control", "no-cache");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        okHttpClient = httpClient.readTimeout(500, TimeUnit.SECONDS)
                .connectTimeout(500, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        ObjectMapper jacksonMapper = new ObjectMapper();
        jacksonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        client = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                .client(okHttpClient)
                .build();
    }

    public static RetrofitHttpsCall getInstance(String ipServer) {
        return new RetrofitHttpsCall(ipServer);
    }

    public <T> T create(final Class<T> service) {
        return client.create(service);
    }
}

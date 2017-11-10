package com.example.androidapp.api.retrofit;

import android.support.annotation.CallSuper;
import android.text.TextUtils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class AbstractApiProvider<T> {

    private Class<T> apiClass;
    private T api;

    AbstractApiProvider(Class<T> apiClass) {
        this.apiClass = apiClass;
    }

    private T buildApi() {
        String baseEndpoint = getBaseEndpoint();
        if (TextUtils.isEmpty(baseEndpoint)) {
            throw new IllegalStateException("API endpoint can't be empty");
        }
        OkHttpClient.Builder okHttpClientBuilder = initHttpClient(baseEndpoint);
        Retrofit retrofit = initRetrofit(okHttpClientBuilder.build(), baseEndpoint).build();
        return retrofit.create(apiClass);
    }

    protected abstract String getBaseEndpoint();

    @CallSuper
    protected OkHttpClient.Builder initHttpClient(String endpoint) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);
        return builder;
    }

    @CallSuper
    protected Retrofit.Builder initRetrofit(OkHttpClient okHttpClient, String baseEndpoint) {
        return new Retrofit.Builder()
                .baseUrl(baseEndpoint)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public T getApi() {
        if (api == null) {
            api = buildApi();
        }
        return api;
    }

}

package com.example.androidapp.api.retrofit;

import com.example.androidapp.api.BuildConfig;
import com.example.androidapp.api.service.StackOverflowService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StackOverflowApiProvider extends AbstractApiProvider<StackOverflowService> {

    @Inject
    public StackOverflowApiProvider() {
        super(StackOverflowService.class);
    }

    @Override
    protected String getBaseEndpoint() {
        return BuildConfig.BASE_ENDPOINT;
    }
}

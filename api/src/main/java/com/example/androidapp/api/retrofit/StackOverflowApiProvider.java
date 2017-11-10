package com.example.androidapp.api.retrofit;

import com.example.androidapp.api.service.StackOverflowService;

public class StackOverflowApiProvider extends AbstractApiProvider<StackOverflowService> {

    private final static String GIT_HUB_ENDPOINT = "";

    public StackOverflowApiProvider() {
        super(StackOverflowService.class);
    }

    @Override
    protected String getBaseEndpoint() {
        return GIT_HUB_ENDPOINT;
    }
}

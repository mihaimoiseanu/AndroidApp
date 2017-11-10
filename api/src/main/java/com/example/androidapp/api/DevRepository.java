package com.example.androidapp.api;

import com.example.androidapp.api.caching.DiskCaching;
import com.example.androidapp.api.caching.ExpiringLruCache;
import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;

import java.util.List;

public class DevRepository {

    private final ExpiringLruCache<Integer, Developer> memory;
    private final DiskCaching diskCaching;
    private final StackOverflowApiProvider stackOverflowApiProvider;

    public DevRepository(ExpiringLruCache<Integer, Developer> memory, DiskCaching diskCaching, StackOverflowApiProvider stackOverflowApiProvider) {
        this.memory = memory;
        this.diskCaching = diskCaching;
        this.stackOverflowApiProvider = stackOverflowApiProvider;
    }


    public Developer getDev() {
        return null;
    }

    public List<Developer> getDevs() {
        return null;
    }
}

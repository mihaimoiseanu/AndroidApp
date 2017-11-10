package com.example.androidapp.api;

import com.example.androidapp.api.caching.disk.DevDatabase;
import com.example.androidapp.api.caching.memory.ExpiringLruCache;
import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;

import java.util.List;

public class DevRepository {

    private final ExpiringLruCache<Integer, Developer> memory;
    private final DevDatabase devDatabase;
    private final StackOverflowApiProvider stackOverflowApiProvider;

    public DevRepository(ExpiringLruCache<Integer, Developer> memory, DevDatabase devDatabase, StackOverflowApiProvider stackOverflowApiProvider) {
        this.memory = memory;
        this.devDatabase = devDatabase;
        this.stackOverflowApiProvider = stackOverflowApiProvider;
    }


    public Developer getDev() {
        return null;
    }

    public List<Developer> getDevs() {
        return null;
    }
}

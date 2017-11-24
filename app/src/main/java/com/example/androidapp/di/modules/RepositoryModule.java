package com.example.androidapp.di.modules;

import com.example.androidapp.api.storage.memory.ExpiringLruCache;
import com.example.androidapp.commons.models.Developer;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ExpiringLruCache<String, List<Developer>> provideExpiringCache() {
        return new ExpiringLruCache<>(15, 1000 * 60 * 10);
    }
}

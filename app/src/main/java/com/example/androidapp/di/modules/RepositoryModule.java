package com.example.androidapp.di.modules;

import com.example.androidapp.api.DevRepository;
import com.example.androidapp.api.caching.DiskCaching;
import com.example.androidapp.api.caching.ExpiringLruCache;
import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    //here we provide classes that belongs to the repository
    //such as api providers, interceptors, cache

    @Provides
    @Singleton
    public StackOverflowApiProvider provideGitHubApiProvider() {
        return new StackOverflowApiProvider();
    }

    @Provides
    @Singleton
    DiskCaching provideDiskCaching() {
        return new DiskCaching();
    }

    @Provides
    @Singleton
    ExpiringLruCache provideExpiringCache() {
        return new ExpiringLruCache<Integer, Developer>(15, 1000 * 60 * 10);
    }

    @Provides
    @Singleton
    DevRepository provideDevRepository(StackOverflowApiProvider stackOverflowApiProvider,
                                       DiskCaching diskCaching,
                                       ExpiringLruCache<Integer, Developer> expiringLruCache) {
        return new DevRepository(expiringLruCache, diskCaching, stackOverflowApiProvider);
    }

}

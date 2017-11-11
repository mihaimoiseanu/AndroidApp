package com.example.androidapp.di.modules;

import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.storage.DevRepository;
import com.example.androidapp.storage.disk.SharedPrefsHelper;
import com.example.androidapp.storage.memory.ExpiringLruCache;

import java.util.List;

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
    ExpiringLruCache<String, List<Developer>> provideExpiringCache() {
        return new ExpiringLruCache<>(15, 1000 * 60 * 10);
    }

    @Provides
    @Singleton
    DevRepository provideDevRepository(StackOverflowApiProvider stackOverflowApiProvider,
                                       SharedPrefsHelper sharedPrefs,
                                       ExpiringLruCache<String, List<Developer>> expiringLruCache) {
        return new DevRepository(expiringLruCache, sharedPrefs, stackOverflowApiProvider);
    }

}

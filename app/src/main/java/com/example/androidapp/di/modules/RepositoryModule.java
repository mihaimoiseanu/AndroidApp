package com.example.androidapp.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.androidapp.api.DevRepository;
import com.example.androidapp.api.caching.disk.DevDatabase;
import com.example.androidapp.api.caching.memory.ExpiringLruCache;
import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;

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
    DevDatabase provideDiskCaching(Context context) {
        return Room.databaseBuilder(context,
                DevDatabase.class, "dev-db").build();
    }

    @Provides
    @Singleton
    ExpiringLruCache provideExpiringCache() {
        return new ExpiringLruCache<String, List<Developer>>(15, 1000 * 60 * 10);
    }

    @Provides
    @Singleton
    DevRepository provideDevRepository(StackOverflowApiProvider stackOverflowApiProvider,
                                       DevDatabase devDatabase,
                                       ExpiringLruCache<String, List<Developer>> expiringLruCache) {
        return new DevRepository(expiringLruCache, devDatabase, stackOverflowApiProvider);
    }

}

package com.example.androidapp.di.modules;


import android.content.Context;

import com.example.androidapp.storage.disk.SharedPrefsHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    SharedPrefsHelper getSharedPrefs(Context context) {
        return new SharedPrefsHelper(context);
    }

}

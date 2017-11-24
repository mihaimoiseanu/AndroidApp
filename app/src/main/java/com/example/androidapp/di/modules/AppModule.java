package com.example.androidapp.di.modules;


import android.app.Application;
import android.content.Context;

import com.example.androidapp.di.components.MainComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        subcomponents = {
                MainComponent.class
        }
)
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

}

package com.example.androidapp;

import android.app.Application;

import com.example.androidapp.di.components.AppComponent;
import com.example.androidapp.di.components.DaggerAppComponent;
import com.example.androidapp.di.modules.AppModule;
import com.example.androidapp.di.modules.RepositoryModule;


public class AndroidApplication extends Application {

    private static AndroidApplication mInstance;
    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initComponent();
    }

    private void initComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .repositoryModule(new RepositoryModule())
                .build();
    }

    public static AndroidApplication getInstance() {
        return mInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

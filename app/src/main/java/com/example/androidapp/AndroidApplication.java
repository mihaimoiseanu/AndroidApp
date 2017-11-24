package com.example.androidapp;

import android.app.Activity;
import android.app.Application;

import com.example.androidapp.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class AndroidApplication extends Application implements HasActivityInjector {

    private static AndroidApplication mInstance;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        initComponent();
    }

    private void initComponent() {
        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    public static AndroidApplication getInstance() {
        return mInstance;
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}

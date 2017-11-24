package com.example.androidapp.di.components;

import com.example.androidapp.di.modules.MainModule;
import com.example.androidapp.di.scope.ActivityScope;
import com.example.androidapp.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity bindMainActivity();

}

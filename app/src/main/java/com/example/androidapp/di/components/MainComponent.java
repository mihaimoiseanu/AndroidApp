package com.example.androidapp.di.components;

import com.example.androidapp.di.modules.MainModule;
import com.example.androidapp.di.scope.ActivityScope;
import com.example.androidapp.ui.main.MainActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {
    }

}

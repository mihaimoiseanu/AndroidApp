package com.example.androidapp.di.components;

import com.example.androidapp.di.modules.MainModule;
import com.example.androidapp.di.scope.ActivityScope;
import com.example.androidapp.ui.main.MainActivity;
import com.example.androidapp.ui.main.MainPresenter;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {

    MainPresenter getPresenter();

    void inject(MainActivity mainActivity);
}

package com.example.androidapp.di.modules;

import com.example.androidapp.storage.DevRepository;
import com.example.androidapp.ui.main.MainContract;
import com.example.androidapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @Provides
    MainPresenter providePresenter(DevRepository devRepository) {
        return new MainPresenter(view, devRepository);
    }

}

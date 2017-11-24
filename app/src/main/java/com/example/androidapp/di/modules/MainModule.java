package com.example.androidapp.di.modules;

import com.example.androidapp.api.storage.DevRepository;
import com.example.androidapp.ui.main.MainActivity;
import com.example.androidapp.ui.main.MainContract;
import com.example.androidapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    @Provides
    MainContract.View provideMainView(MainActivity mainActivity) {
        return mainActivity;
    }

    @Provides
    MainPresenter providePresenter(MainContract.View view, DevRepository devRepository) {
        return new MainPresenter(view, devRepository);
    }

}

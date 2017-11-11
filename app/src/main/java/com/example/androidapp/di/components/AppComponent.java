package com.example.androidapp.di.components;

import android.content.Context;

import com.example.androidapp.di.modules.AppModule;
import com.example.androidapp.di.modules.RepositoryModule;
import com.example.androidapp.storage.DevRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface AppComponent {

    Context getContext();

    DevRepository getDevRepo();

}

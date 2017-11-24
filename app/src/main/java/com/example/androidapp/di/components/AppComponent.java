package com.example.androidapp.di.components;

import android.app.Application;

import com.example.androidapp.AndroidApplication;
import com.example.androidapp.di.modules.AppModule;
import com.example.androidapp.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        RepositoryModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(AndroidApplication app);

}

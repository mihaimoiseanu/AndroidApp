package com.example.androidapp.api;

import android.os.SystemClock;

import com.example.androidapp.api.caching.disk.DevDatabase;
import com.example.androidapp.api.caching.memory.ExpiringLruCache;
import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.commons.models.DeveloperEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DevRepository {

    private final static String MEMORY_KEY = ".key.devs";
    private final static long EXPIRATION_TIME = 1000 * 60 * 10;

    private final ExpiringLruCache<String, List<Developer>> memory;
    private final DevDatabase devDatabase;
    private final StackOverflowApiProvider stackOverflowApiProvider;

    public DevRepository(ExpiringLruCache<String, List<Developer>> memory, DevDatabase devDatabase, StackOverflowApiProvider stackOverflowApiProvider) {
        this.memory = memory;
        this.devDatabase = devDatabase;
        this.stackOverflowApiProvider = stackOverflowApiProvider;
    }

    public void getDevs(final GetUsersCallback getUsersCallback) {
        List<Developer> developers;
        developers = memory.get(MEMORY_KEY);
        if (developers != null) {
            getUsersCallback.getDevelopers(developers);
            return;
        }

        devDatabase.developersDao().fetchDevelopers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DeveloperEntity>() {
                    @Override
                    public void onSuccess(DeveloperEntity developerEntity) {
                        if (elapsedRealtime() >= developerEntity.getExpirationTime()) {
                            devDatabase.developersDao().deleteDevs(developerEntity);
                            getDevsFromNetwork(getUsersCallback);
                            return;
                        }
                        getUsersCallback.getDevelopers(developerEntity.getDevelopers());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getDevsFromNetwork(getUsersCallback);
                    }
                });
    }

    private void getDevsFromNetwork(final GetUsersCallback getUsersCallback) {
        Map<String, String> queries = new HashMap<>();
        stackOverflowApiProvider.getApi().getDevelopers(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Developer>>() {
                    @Override
                    public void onSuccess(List<Developer> developer) {
                        saveResponse(developer);
                        getUsersCallback.getDevelopers(developer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getUsersCallback.getDevelopers(null);
                    }
                });
    }

    private void saveResponse(List<Developer> devs) {
        memory.put(MEMORY_KEY, devs);
        DeveloperEntity developerEntity = new DeveloperEntity();
        developerEntity.setDevelopers(devs);
        developerEntity.setExpirationDate(elapsedRealtime() + EXPIRATION_TIME);
        devDatabase.developersDao().insertDevs(developerEntity);
    }

    private long elapsedRealtime() { // With Bill Maher
        return SystemClock.elapsedRealtime();
    }
}

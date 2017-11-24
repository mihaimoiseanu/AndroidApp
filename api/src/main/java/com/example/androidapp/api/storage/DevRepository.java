package com.example.androidapp.api.storage;

import android.os.SystemClock;

import com.example.androidapp.api.retrofit.StackOverflowApiProvider;
import com.example.androidapp.api.storage.disk.SharedPrefsHelper;
import com.example.androidapp.api.storage.memory.ExpiringLruCache;
import com.example.androidapp.commons.models.DevResponse;
import com.example.androidapp.commons.models.Developer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class DevRepository {

    private final static String MEMORY_KEY = ".key.devs";
    private final static long EXPIRATION_TIME = 1000 * 60 * 10;

    private final ExpiringLruCache<String, List<Developer>> memory;
    private final StackOverflowApiProvider stackOverflowApiProvider;
    private final SharedPrefsHelper sharedPrefsHelper;

    @Inject
    public DevRepository(ExpiringLruCache<String, List<Developer>> memory, SharedPrefsHelper sharedPrefsHelper, StackOverflowApiProvider stackOverflowApiProvider) {
        this.memory = memory;
        this.sharedPrefsHelper = sharedPrefsHelper;
        this.stackOverflowApiProvider = stackOverflowApiProvider;
    }

    public void getDevs(final GetUsersCallback getUsersCallback) {
        List<Developer> developers;
        //try to get devs from memory cache
        developers = memory.get(MEMORY_KEY);
        if (developers != null) {
            getUsersCallback.onUserFetched(developers);
            return;
        }
        //try to get devs from disk cache
        DevResponse devs = sharedPrefsHelper.getDevelopers();
        if (devs != null) {
            if (elapsedRealtime() <= devs.getExpirationTime()) {
                getUsersCallback.onUserFetched(devs.getItems());
                return;
            }
        }
        getDevsFromNetwork(getUsersCallback);
    }

    private void getDevsFromNetwork(final GetUsersCallback getUsersCallback) {
        Map<String, String> queries = new HashMap<>();
        queries.put("pagesize", "10");
        queries.put("order", "desc");
        queries.put("sort", "reputation");
        queries.put("site", "stackoverflow");
        stackOverflowApiProvider.getApi().getDevelopers(queries)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<DevResponse>() {
                    @Override
                    public void onSuccess(DevResponse response) {
                        saveResponse(response);
                        getUsersCallback.onUserFetched(response.getItems());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getUsersCallback.onUserFetched(null);
                    }
                });
    }

    private void saveResponse(DevResponse devResponse) {
        memory.put(MEMORY_KEY, devResponse.getItems());
        devResponse.setExpirationDate(elapsedRealtime() + EXPIRATION_TIME);
        sharedPrefsHelper.putDevs(devResponse);
    }

    private long elapsedRealtime() { // With Bill Maher
        return SystemClock.elapsedRealtime();
    }
}

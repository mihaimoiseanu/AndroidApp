package com.example.androidapp.api.storage.disk;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androidapp.commons.models.DevResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.example.androidapp.api.storage.disk.Preferences.PrefKeys.KEY_DEVS;

@Singleton
public class SharedPrefsHelper {

    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Inject
    public SharedPrefsHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(Preferences.PrefNames.PREFS_NAME_TAG, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void putDevs(DevResponse devResponse) {
        String devs = gson.toJson(devResponse);
        sharedPreferences.edit().putString(KEY_DEVS.name(), devs).apply();
    }

    public DevResponse getDevelopers() {
        String devs = sharedPreferences.getString(KEY_DEVS.name(), null);
        if (devs == null) {
            return null;
        }
        Type listType = new TypeToken<DevResponse>() {
        }.getType();
        return new Gson().fromJson(devs, listType);
    }

}

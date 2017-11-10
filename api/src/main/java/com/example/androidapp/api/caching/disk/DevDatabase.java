package com.example.androidapp.api.caching.disk;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.androidapp.commons.models.DeveloperEntity;

@Database(entities = {DeveloperEntity.class}, version = 1)
public abstract class DevDatabase extends RoomDatabase {
    public abstract DevelopersDao developersDao();
}

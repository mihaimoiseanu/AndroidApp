package com.example.androidapp.api.caching.disk;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.androidapp.commons.converters.Converters;
import com.example.androidapp.commons.models.DeveloperEntity;

@Database(entities = {DeveloperEntity.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class DevDatabase extends RoomDatabase {
    public abstract DevelopersDao developersDao();
}

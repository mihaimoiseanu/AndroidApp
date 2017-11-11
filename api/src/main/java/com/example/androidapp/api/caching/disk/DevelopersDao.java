package com.example.androidapp.api.caching.disk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.androidapp.commons.models.DeveloperEntity;

import io.reactivex.Single;

@Dao
public interface DevelopersDao {

    @Insert
    void insertDevs(DeveloperEntity devs);

    @Delete
    void deleteDevs(DeveloperEntity devs);

    @Query("SELECT * FROM DeveloperEntity")
    Single<DeveloperEntity> fetchDevelopers();


}

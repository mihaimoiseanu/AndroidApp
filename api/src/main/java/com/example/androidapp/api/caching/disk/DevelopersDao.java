package com.example.androidapp.api.caching.disk;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.androidapp.commons.models.DeveloperEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface DevelopersDao {

    @Insert
    void insertDevs(List<DeveloperEntity> developerEntities);

    @Insert
    void insertDev(DeveloperEntity developer);

    @Query("SELECT * FROM DeveloperEntity")
    Single<List<DeveloperEntity>> fetchDevelopers();

}

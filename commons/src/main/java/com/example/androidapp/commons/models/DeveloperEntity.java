package com.example.androidapp.commons.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class DeveloperEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private long expirationDate;
    private List<Developer> developers = new ArrayList<>();

    public int getId() {
        return id;
    }

    public DeveloperEntity setId(int id) {
        this.id = id;
        return this;
    }

    public long getExpirationTime() {
        return expirationDate;
    }

    public DeveloperEntity setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public DeveloperEntity setDevelopers(List<Developer> developers) {
        this.developers = developers;
        return this;
    }
}

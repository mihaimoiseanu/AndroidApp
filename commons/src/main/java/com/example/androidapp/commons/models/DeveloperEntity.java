package com.example.androidapp.commons.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class DeveloperEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Developer developer;
    private long expirationDate;

}

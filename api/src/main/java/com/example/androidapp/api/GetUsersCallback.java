package com.example.androidapp.api;

import com.example.androidapp.commons.models.Developer;

import java.util.List;

public interface GetUsersCallback {
    void getDevelopers(List<Developer> developers);
}

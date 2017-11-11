package com.example.androidapp.storage;

import com.example.androidapp.commons.models.Developer;

import java.util.List;

public interface GetUsersCallback {
    void onUserFetched(List<Developer> developers);
}

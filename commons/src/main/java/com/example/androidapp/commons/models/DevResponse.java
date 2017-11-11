package com.example.androidapp.commons.models;

import java.util.ArrayList;
import java.util.List;

public class DevResponse {

    private long expirationDate;
    private List<Developer> items = new ArrayList<>();

    public long getExpirationTime() {
        return expirationDate;
    }

    public DevResponse setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public List<Developer> getItems() {
        return items;
    }

    public DevResponse setItems(List<Developer> items) {
        this.items = items;
        return this;
    }
}

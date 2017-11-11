package com.example.androidapp.commons.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.androidapp.commons.models.Developer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {

    @TypeConverter
    public static ArrayList<Developer> fromString(String value) {
        Type listType = new TypeToken<ArrayList<Developer>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Developer> developers) {
        Gson gson = new Gson();
        return gson.toJson(developers);
    }

}

package com.example.androidapp.ui.main;

import com.example.androidapp.api.storage.GetUsersCallback;
import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.utils.ActionHandler;

import java.util.List;

public class MainContract {

    public interface View {
        void loadDevelopers(List<Developer> developerList);

        void showDev(Developer item);
    }

    interface Presenter extends ActionHandler<Developer>, GetUsersCallback {
        void getDevelopers();
    }

}

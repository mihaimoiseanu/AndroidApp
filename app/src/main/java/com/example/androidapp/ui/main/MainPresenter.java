package com.example.androidapp.ui.main;

import com.example.androidapp.commons.models.Developer;
import com.example.androidapp.storage.DevRepository;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    private final DevRepository devRepository;
    private final MainContract.View view;

    public MainPresenter(MainContract.View view, DevRepository devRepository) {
        this.view = view;
        this.devRepository = devRepository;
    }

    @Override
    public void onItemClick(Developer item) {
        view.showDev(item);
    }

    @Override
    public void onUserFetched(List<Developer> developers) {
        view.loadDevelopers(developers);
    }

    public void getDevelopers() {
        devRepository.getDevs(this);
    }
}

package com.example.androidapp.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.androidapp.commons.models.Developer;

public class BindingAdapters {


    @BindingAdapter("bind:imgRes")
    public static void loadProfileImage(ImageView imageView, Developer developer) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(developer.getProfileImage())
                .apply(new RequestOptions()
                        .centerCrop())
                .into(imageView);
    }

}

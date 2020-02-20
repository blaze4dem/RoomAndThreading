package com.example.threadingservice;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;

import com.example.threadingservice.DataViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PlaceViewModelFactory implements ViewModelProvider.Factory {

    public Application mApplication;

    public PlaceViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(DataViewModel.class)){
            return (T) new DataViewModel(mApplication);
        }

        throw new IllegalArgumentException("The argument or parameters u used is illegal/////////////////////////////////////////");
    }
}

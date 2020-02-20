package com.example.threadingservice;

import android.app.Application;
import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class PlaceRepository {

    private PlaceDao mDao;
    private LiveData<List<Place>> allPlaces;
    private String mName;
    private Place mPlace;

    public PlaceRepository(Application app){
        PlacesDatabase mDatabase = PlacesDatabase.getInstance(app);
        mDao = mDatabase.getDao();
    }

    public LiveData<List<Place>> getAllPlaces(){

        allPlaces =  mDao.getAllPlaces();

        return allPlaces;
    }

    public void insertPlace(final Place p){
        PlacesDatabase.mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.addPlace(p);
            }
        });
    }

    public void updatePlace(final Place p){
        PlacesDatabase.mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                mDao.updatePlace(p);
            }
        });
    }

    public Place getPlace(String name){

        mName = name;

        PlacesDatabase.mExecutorService.execute(new Runnable() {

            @Override
            public void run() {
                mPlace = mDao.getPlace(mName);
            }
        });

        return mPlace;
    }
}

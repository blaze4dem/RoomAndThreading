package com.example.threadingservice;

import android.app.Application;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PlaceRepository {

    private PlaceDao mDao;
    private LiveData<List<Place>> allPlaces;
    private int mId;
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

    public Place getPlace(int id){

        mId = id;

        PlacesDatabase.mExecutorService.execute(new Runnable() {

            @Override
            public void run() {
                mPlace = mDao.getPlace(mId);
            }
        });

        return mPlace;
    }
}

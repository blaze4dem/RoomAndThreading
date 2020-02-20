package com.example.threadingservice;

import android.app.Application;
import android.util.Log;

import java.util.List;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;



public class DataViewModel extends AndroidViewModel {

    private LiveData<List<Place>> allPlaces;
    private PlaceRepository repo;

    public DataViewModel(Application app) {
        super(app);

        repo = new PlaceRepository(app);
        allPlaces = repo.getAllPlaces();
    }

    public LiveData<List<Place>> getAllPlaces(){
        return allPlaces;
    }

    public void insert(Place p){
        repo.insertPlace(p);
    }

    public void update(Place p){
        repo.updatePlace(p);
    }

    public Place getPlace(String name){

        Place place = repo.getPlace(name);

        Log.v("LOG TAG","/////////////////////////// In the Model Id is := " + place + " ////////////////////////////");

        return place;
    }
}

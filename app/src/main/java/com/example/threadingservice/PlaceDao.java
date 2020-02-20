package com.example.threadingservice;


import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


@Dao
public interface PlaceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addPlace(Place place);

    @Update
    void updatePlace(Place place);

    @Delete
    void deleteOne(Place place);

    @Query("DELETE FROM places_table")
    void deleteAll();

    @Query("SELECT * FROM places_table")
    LiveData<List<Place>> getAllPlaces();

    @Query("SELECT * FROM places_table WHERE place_name = :name LIMIT 1")
    public abstract Place getPlace(String name);

}

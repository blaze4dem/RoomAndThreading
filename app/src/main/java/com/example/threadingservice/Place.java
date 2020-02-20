package com.example.threadingservice;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "places_table")
public class Place {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "place_name")
    @NonNull
    public String mPlaceName;

    @ColumnInfo(name = "place_description")
    @NonNull
    public String mPlaceDescription;

    @ColumnInfo(name = "place_image_url")
    @NonNull
    public String mPlaceImageUrl;

    public Place(int id, @NonNull String placeName, @NonNull String placeDescription, @NonNull String placeImageUrl) {
        this.id = id;
        mPlaceName = placeName;
        mPlaceDescription = placeDescription;
        mPlaceImageUrl = placeImageUrl;
    }

    @NonNull
    public String getPlaceName() {
        return mPlaceName;
    }

    public void setPlaceName(@NonNull String placeName) {
        mPlaceName = placeName;
    }

    @NonNull
    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    public void setPlaceDescription(@NonNull String placeDescription) {
        mPlaceDescription = placeDescription;
    }

    @NonNull
    public String getPlaceImageUrl() {
        return mPlaceImageUrl;
    }

    public void setPlaceImageUrl(@NonNull String placeImageUrl) {
        mPlaceImageUrl = placeImageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

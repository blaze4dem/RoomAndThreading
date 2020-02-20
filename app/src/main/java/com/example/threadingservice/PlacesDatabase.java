package com.example.threadingservice;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Place.class}, version = 1, exportSchema = false)
public abstract class PlacesDatabase extends RoomDatabase {

    public abstract PlaceDao getDao();


    public static PlacesDatabase instance = null;
    private static final int NUMBER_OF_THREADS = 4;
    public static ExecutorService mExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PlacesDatabase getInstance(final Context context){

        if(instance == null){
            synchronized (PlacesDatabase.class){
                instance = Room.databaseBuilder(context.getApplicationContext(), PlacesDatabase.class, "travel_db").addCallback(addData).build();
            }
        }
        return instance;
    }

    public static RoomDatabase.Callback addData = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            final Place p = new Place(0,"Yenagoa","Correct place to be in as at for ever. expecially my village", "https://Image.com");

            mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    PlaceDao dao = instance.getDao();
                    dao.deleteAll();

                    dao.addPlace(p);
                }
            });
        }
    };
}

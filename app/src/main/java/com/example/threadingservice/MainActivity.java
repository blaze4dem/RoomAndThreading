package com.example.threadingservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = "Main Activity Log Tag";
    public DataViewModel mDatamodel;

    public RecyclerView rec_view;
    private PlaceRecycleViewAdapter mAdapter;
    public int NEW_PLACE_INTENT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rec_view = findViewById(R.id.rec_view);

        mAdapter = new PlaceRecycleViewAdapter(this);
        rec_view.setAdapter(mAdapter);
        rec_view.setLayoutManager(new LinearLayoutManager(this));


//        mDatamodel = new ViewModelProvider(this).get(DataViewModel.class);

        PlaceViewModelFactory factory = new PlaceViewModelFactory(getApplication());

        mDatamodel = new ViewModelProvider(this, factory).get(DataViewModel.class);

        mDatamodel.getAllPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                mAdapter.setAllPlaces(places);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, InsertPlaceActivity.class);
                startActivityForResult(intent, NEW_PLACE_INTENT_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_PLACE_INTENT_CODE && resultCode == RESULT_OK){

            Place newPlace = new Place(0, data.getStringExtra("place_name"), data.getStringExtra("place_desc"), data.getStringExtra("place_image"));
            mDatamodel.insert(newPlace);

            Log.i(LOG_TAG," The on Activity Result was called ,,,,,,,,,,,,,,,,,,ooop........epop......................................alkfjalkfj");
            Toast.makeText(this,"Place Added " + data.getStringExtra("place_name"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

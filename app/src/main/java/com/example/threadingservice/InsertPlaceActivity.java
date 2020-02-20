package com.example.threadingservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class InsertPlaceActivity extends AppCompatActivity {

    public static final String LOG_TAG = "InsertPlace Activity Log Tag";

    public EditText edit_place_name;
    public EditText edit_place_desc;
    public EditText edit_place_image;
    public Button add_place;

    public Boolean savedPlace = false;
    public Boolean isNewPlace = true;
    private Place mPlace;

    private DataViewModel mDataViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_place);

        edit_place_name = findViewById(R.id.edit_place_name);
        edit_place_desc = findViewById(R.id.edit_place_desc);
        edit_place_image = findViewById(R.id.edit_image);
        add_place = findViewById(R.id.btn_add);

        PlaceViewModelFactory factory = new PlaceViewModelFactory(getApplication());

        mDataViewModel = new ViewModelProvider(this, factory).get(DataViewModel.class);

        getSavedPlaceData();

        add_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isNewPlace){
                    savedPlace = true;
                    Intent n = new Intent();
                    n.putExtra("place_name", edit_place_name.getText().toString());
                    n.putExtra("place_desc", edit_place_desc.getText().toString());
                    n.putExtra("place_image", edit_place_image.getText().toString());

                    setResult(RESULT_OK, n);
                }else{

                    Place placeEdit = new Place(0,
                                                edit_place_name.getText().toString(),
                                                edit_place_desc.getText().toString(),
                                                edit_place_image.getText().toString());

                    mDataViewModel.update(placeEdit);
                }

                finish();
            }
        });
    }

    private void getSavedPlaceData() {

        Intent intent = getIntent();
        String placeName = intent.getStringExtra("Name");

        PlaceRepository repo = new PlaceRepository(getApplication());
        mPlace = repo.getPlace(placeName);

        Log.v(LOG_TAG, "////////////////////////// " + mPlace + " ///////////////////////////////" );
//            edit_place_name.setText(mPlace.getPlaceName());
//            edit_place_desc.setText(mPlace.getPlaceDescription());
//            edit_place_image.setText(mPlace.getPlaceImageUrl());

    }
}

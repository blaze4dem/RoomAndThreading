package com.example.threadingservice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

public class PlaceRecycleViewAdapter extends RecyclerView.Adapter<PlaceRecycleViewAdapter.MyHolder> {

    public Context context;
    public List<Place> allPlaces;

    public PlaceRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent,false);

        return new MyHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        if(allPlaces != null){
            final Place p = allPlaces.get(position);

            holder.bindView(p.getPlaceName(),p.getPlaceDescription());

            View.OnClickListener click = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), InsertPlaceActivity.class);
                    intent.putExtra("Name", p.getPlaceName());

                    v.getContext().startActivity(intent);
                }
            };

            holder.itemView.setOnClickListener(click);

        }else{
            holder.bindView("No Title", "No data");
        }
    }

    @Override
    public int getItemCount() {
        if(allPlaces != null)
            return allPlaces.size();

        return 0;
    }

    public void setAllPlaces(List<Place> places){
        this.allPlaces = places;

        notifyDataSetChanged();
    }


    public class MyHolder extends RecyclerView.ViewHolder {

        private final TextView mTxt1;
        private final TextView mTxt2;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mTxt1 = itemView.findViewById(android.R.id.text1);
            mTxt2 = itemView.findViewById(android.R.id.text2);

            itemView.setTag(this);
        }

        public void bindView(String txt1, String txt2){
            mTxt1.setText(txt1);
            mTxt2.setText(txt2);
        }


    }
}


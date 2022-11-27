package com.example.cuisie;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    // adding global variables for the info and setting them private
    private Context context;
    Activity activity;
    private ArrayList tourID, tourPoint, tourLocation, tourPreferredSeason, tourMinPocketPinch, tourMinCoveredTime;



    // adding a parameterized constructor that will feed the info to the global variables
    public CustomAdapter(Activity activity,
                  Context context,
                  ArrayList tourID,
                  ArrayList tourPoint,
                  ArrayList tourLocation,
                  ArrayList tourPreferredSeason,
                  ArrayList tourMinPocketPinch,
                  ArrayList tourMinCoveredTime) {

        this.activity = activity;
        this.context = context;
        this.tourID = tourID;
        this.tourPoint = tourPoint;
        this.tourLocation = tourLocation;
        this.tourPreferredSeason = tourPreferredSeason;
        this.tourMinPocketPinch = tourMinPocketPinch;
        this.tourMinCoveredTime = tourMinCoveredTime;

    }



    // inflater to inflate the context and returning to the view-holder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_tour, parent, false);
        return new MyViewHolder(view);
    }



    // setting the values / information / data to the text elements in the view
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tour_id_txt.setText(String.valueOf(tourID.get(position)));
        holder.tour_spot_name_txt.setText(String.valueOf(tourPoint.get(position)));
        holder.tour_location_txt.setText(String.valueOf(tourLocation.get(position)));
        holder.tour_season_txt.setText(String.valueOf(tourPreferredSeason.get(position)));
        holder.tour_cost_txt.setText(String.valueOf(tourMinPocketPinch.get(position)));
        holder.tour_stay_time_txt.setText(String.valueOf(tourMinCoveredTime.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(tourID.get(position)));
                intent.putExtra("spot", String.valueOf(tourPoint.get(position)));
                intent.putExtra("location", String.valueOf(tourLocation.get(position)));
                intent.putExtra("season", String.valueOf(tourPreferredSeason.get(position)));
                intent.putExtra("cost", String.valueOf(tourMinPocketPinch.get(position)));
                intent.putExtra("time", String.valueOf(tourMinCoveredTime.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }



    // returning the size of the array count -> total number of elements
    @Override
    public int getItemCount() {
        return tourID.size();
    }



    // connecting the variables to the elements by the id property
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // elements from the app
        TextView tour_id_txt, tour_spot_name_txt, tour_location_txt, tour_season_txt, tour_cost_txt, tour_stay_time_txt;

        // declaring layout needs to match the class of ConstraintLayout
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // connecting with the app elements
            tour_id_txt = itemView.findViewById(R.id.tour_id_txt);
            tour_spot_name_txt = itemView.findViewById(R.id.tour_spot_name_txt);
            tour_location_txt = itemView.findViewById(R.id.tour_location_txt);
            tour_season_txt = itemView.findViewById(R.id.tour_season_txt);
            tour_stay_time_txt = itemView.findViewById(R.id.tour_stay_time_txt);
            tour_cost_txt = itemView.findViewById(R.id.tour_cost_txt);
            mainLayout = itemView.findViewById(R.id.mainLayoutTour);

        }
    }
}

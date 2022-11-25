package com.example.cuisie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Elements of the main page
    RecyclerView recyclerView;
    FloatingActionButton add_button;

    // database variable
    SqLiteHelper myDb;

    // lists of the information
    ArrayList<String> tourID, tourPoint, tourLocation, tourPreferredSeason, tourMinPocketPinch, tourMinCoveredTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        // initializing the database helper
        myDb = new SqLiteHelper(MainActivity.this);

        // initializing the arraylists
        tourID = new ArrayList<>();
        tourPoint = new ArrayList<>();
        tourLocation = new ArrayList<>();
        tourPreferredSeason = new ArrayList<>();
        tourMinPocketPinch = new ArrayList<>();
        tourMinCoveredTime = new ArrayList<>();

        storeDataInArrays();

    }



    // this function will be displaying all the data from the database
    void storeDataInArrays() {
        // getting the response from the function in the sqliteHelper class
        Cursor cursor = myDb.readAllData();

        // the cursor will be having the results / the lists of all the stuff from the db
        // if the count is 0 --> no data
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No data found!", Toast.LENGTH_SHORT).show();
        } else {
            // we have to iterate through the list and moveToNext() will be doing all that
            while (cursor.moveToNext()) {
                tourID.add(cursor.getString(0));
                tourPoint.add(cursor.getString(1));
                tourLocation.add(cursor.getString(2));
                tourPreferredSeason.add(cursor.getString(3));
                tourMinPocketPinch.add(cursor.getString(4));
                tourMinCoveredTime.add(cursor.getString(5));
            }
        }
    }
}
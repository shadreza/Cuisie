package com.example.cuisie;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    // elements from the app
    EditText tour_spot_input_update, location_input_update, preferred_season_input_update, pocket_pinch_min_input_update, coverage_time_min_input_update;
    Button update_tour_point_button, delete_tour_point_button;

    // variables that will be containing the elements values
    String idTxt, spotTxt, locationTxt, seasonTxt, costTxt, coverageTimeTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // connecting with the elements
        tour_spot_input_update = findViewById(R.id.tour_spot_input_update);
        location_input_update = findViewById(R.id.location_input_update);
        preferred_season_input_update = findViewById(R.id.preferred_season_input_update);
        pocket_pinch_min_input_update = findViewById(R.id.pocket_pinch_min_input_update);
        coverage_time_min_input_update = findViewById(R.id.coverage_time_min_input_update);
        update_tour_point_button = findViewById(R.id.update_tour_point_button);
        delete_tour_point_button = findViewById(R.id.delete_tour_point_button);

        // calling the function to get and set the data from the individual tour items to the update module
        // first we call the get and set data
        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle(spotTxt);
        }

        // when update button clicked
        update_tour_point_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // then we attach teh database connection from the SqLiteHelper class
                SqLiteHelper myDb = new SqLiteHelper(UpdateActivity.this);
                // lastly we can use the update database function
                myDb.updateData(idTxt, spotTxt, locationTxt, seasonTxt, costTxt, coverageTimeTxt);
            }
        });

        // when delete button clicked
        delete_tour_point_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // then we attach teh database connection from the SqLiteHelper class
                SqLiteHelper myDb = new SqLiteHelper(UpdateActivity.this);
                // lastly we can use the update database function
                myDb.deleteData(idTxt);
            }
        });

    }



    // this function will be getting the data from the individual element and setting to the update module
    void getAndSetIntentData() {
        if( getIntent().hasExtra("id") && getIntent().hasExtra("spot") &&
            getIntent().hasExtra("location") && getIntent().hasExtra("season") &&
            getIntent().hasExtra("cost") && getIntent().hasExtra("time") ) {

            // Getting data from intent
            idTxt = getIntent().getStringExtra("id");
            spotTxt = getIntent().getStringExtra("spot");
            locationTxt = getIntent().getStringExtra("location");
            seasonTxt = getIntent().getStringExtra("season");
            costTxt = getIntent().getStringExtra("cost");
            coverageTimeTxt = getIntent().getStringExtra("time");

            // setting the intent data to app
            tour_spot_input_update.setText(spotTxt);
            location_input_update.setText(locationTxt);
            preferred_season_input_update.setText(seasonTxt);
            pocket_pinch_min_input_update.setText(costTxt);
            coverage_time_min_input_update.setText(coverageTimeTxt);

        } else {
            // if not working then having  an err toast
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        }
    }
}
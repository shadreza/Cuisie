package com.example.cuisie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    // declaring names for the EditText components and the Button component
    EditText tour_spot_input, location_input, preferred_season_input, pocket_pinch_min_input, coverage_time_min_input;
    Button add_tour_point_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        // setting the elements by the id and connecting them with the app
        tour_spot_input = findViewById(R.id.tour_spot_input);
        location_input = findViewById(R.id.location_input);
        preferred_season_input = findViewById(R.id.preferred_season_input);
        pocket_pinch_min_input = findViewById(R.id.pocket_pinch_min_input);
        coverage_time_min_input = findViewById(R.id.coverage_time_min_input);
        add_tour_point_button = findViewById(R.id.add_tour_point_button);

        // when the add button is clicked we will be trying to add the info to the database by using the function form the SqLiteHelper class
        add_tour_point_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Have to initialize the helper class by passing the context of this activity class
                SqLiteHelper myDb = new SqLiteHelper(AddActivity.this);

                // now adding the info from the form to the db
                // passing all the info as the proper type
                // using trim for extra spaces
                myDb.addTourPlan(
                        tour_spot_input.getText().toString().trim(),
                        location_input.getText().toString().trim(),
                        preferred_season_input.getText().toString().trim(),
                        Double.valueOf(pocket_pinch_min_input.getText().toString().trim()),
                        Integer.valueOf(coverage_time_min_input.getText().toString().trim())
                );
            }
        });

    }
}
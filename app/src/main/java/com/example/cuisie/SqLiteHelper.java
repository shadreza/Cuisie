package com.example.cuisie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SqLiteHelper extends SQLiteOpenHelper {

    private Context context;

    // setting the initials for the database (name, version)
    private static final String databaseName = "TourMate.db";
    private static final int databaseVersion = 1;

    // setting the initials for the table info (table name and other column / features)
    // for the features we are having id, spot name, location, season, cost, time about that particular tour spot
    private static final String tableName = "mytour";
    private static final String tourID = "_id";
    private static final String tourPoint = "Tour_Name";
    private static final String tourLocation = "Tour_Location";
    private static final String tourPreferredSeason = "Tour_Preferred_Season";
    private static final String tourMinPocketPinch = "Tour_Min_Pocket_Pinch";
    private static final String tourMinCoveredTime = "Tour_Min_Covered_Time";



    // setting up the database with sending the info
    public SqLiteHelper(@Nullable Context context) {
        super(context, databaseName, null, databaseVersion);
        this.context = context;
    }



    // sql command for creating a table with the features / columns having the names from the variable
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =  "CREATE TABLE " + tableName +
                        " (" + tourID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        tourPoint + " TEXT, " +
                        tourLocation + " TEXT, " +
                        tourPreferredSeason + " TEXT, " +
                        tourMinPocketPinch + " REAL, " +
                        tourMinCoveredTime + " INTEGER);";
        db.execSQL(query);
    }



    // if we want to upgrade the database / the table then we will be deleting [drop] the previous table if there is any and then creating a new one with the onCreate function
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }



    // here this function will be trying to add a tour plan with all the necessary values / options and adding to the existing database
    void addTourPlan(String nameOfTourPoint, String locationOfTourPoint,
                     String preferredSeasonForTourPoint, Double minPocketPinchForTourPoint,
                     Integer minTimeToCoverTourPoint) {

        // getting the database and all the values within that
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // adding the new values that are passed through the parameters
        contentValues.put(tourPoint, nameOfTourPoint);
        contentValues.put(tourLocation, locationOfTourPoint);
        contentValues.put(tourPreferredSeason, preferredSeasonForTourPoint);
        contentValues.put(tourMinPocketPinch, minPocketPinchForTourPoint);
        contentValues.put(tourMinCoveredTime, minTimeToCoverTourPoint);

        // if the addition was not done then result will be -1 else sth else
        long result = db.insert(tableName, null, contentValues);

        // We will be showing a toast message if there is any issue / success
        if (result == -1) {
            Toast.makeText(context, "Failed to insert into Database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Alhamdulillah!", Toast.LENGTH_SHORT).show();
        }
    }



    // Here we will be returning the elements from the sqlite database
    // this method will be returning a Cursor object
    // A Cursor represents the result of a query and basically points to one row of the query result.
    // This way Android can buffer the query results efficiently; as it does not have to load all data into memory.
    Cursor readAllData() {
        // selecting all the rows from the table
        String query = "SELECT * FROM " + tableName;

        // getting the database and all the values within that
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = null;

        // if we see the database is not nul only then we will be executing the query and setting it to cursor
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        // returning the result
        return cursor;
    }



    // function for updating data
    void updateData(String tour_id, String updatedNameOfTourPoint, String updatedLocationOfTourPoint,
                    String updatedPreferredSeasonForTourPoint, String updatedMinPocketPinchForTourPoint,
                    String updatedMinTimeToCoverTourPoint) {

        // getting the database and all the values within that
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        // adding the new values that are passed through the parameters
        contentValues.put(tourPoint, updatedNameOfTourPoint);
        contentValues.put(tourLocation, updatedLocationOfTourPoint);
        contentValues.put(tourPreferredSeason, updatedPreferredSeasonForTourPoint);
        contentValues.put(tourMinPocketPinch, updatedMinPocketPinchForTourPoint);
        contentValues.put(tourMinCoveredTime, updatedMinTimeToCoverTourPoint);

        // updating the database
        // if the addition was not done then result will be -1 else sth else
        long result = db.update(tableName, contentValues, "_id = ?", new String[]{tour_id});

        // We will be showing a toast message if there is any issue / success
        if (result == -1) {
            Toast.makeText(context, "Failed to update the Database", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Alhamdulillah! Database Updated", Toast.LENGTH_SHORT).show();
        }
    }



    // function for updating data
    void deleteData(String tour_id) {

        // getting the database and all the values within that
        SQLiteDatabase db = this.getWritableDatabase();

        // deleting the database
        // if the addition was not done then result will be -1 else sth else
        long result = db.delete(tableName, "_id = ?", new String[]{tour_id});

        // We will be showing a toast message if there is any issue / success
        if (result == -1) {
            Toast.makeText(context, "Failed to delete the element", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Alhamdulillah! element deleted", Toast.LENGTH_SHORT).show();
        }
    }
}

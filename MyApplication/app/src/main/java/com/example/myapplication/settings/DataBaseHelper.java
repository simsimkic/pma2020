package com.example.myapplication.settings;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Goal;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String GOALS_TABLE = "GOALS_TABLE";
    public static final String COLUMN_GOALS_NAME = "COLUMN_GOALS_NAME";
    public static final String COLUMN_GOALS_DISTANCE = "COLUMN_GOALS_DISTANCE";
    public static final String COLUMN_GOALS_DURATION = "COLUMN_GOALS_DURATION";
    public static final String COLUMN_GOALS_ARCHIVED = "COLUMN_GOALS_ARCHIVED";
    public static final String COLUMN_GOALS_END_TIME = "COLUMN_GOALS_END_TIME";
    public static final String COLUMN_GOALS_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "running.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + GOALS_TABLE + " (" + COLUMN_GOALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GOALS_NAME + " TEXT, "+ COLUMN_GOALS_DISTANCE +" REAL, " +
                COLUMN_GOALS_DURATION +" REAL, " + COLUMN_GOALS_ARCHIVED +" INTEGER, " + COLUMN_GOALS_END_TIME + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addGoal(Goal goal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_GOALS_NAME, goal.getName());
        cv.put(COLUMN_GOALS_DISTANCE, goal.getDistance());
        cv.put(COLUMN_GOALS_DURATION, goal.getDuration());
        if(goal.getArchived()){
            cv.put(COLUMN_GOALS_ARCHIVED, 1);
        }else{
            cv.put(COLUMN_GOALS_ARCHIVED, 0);
        }

        cv.put(COLUMN_GOALS_END_TIME, goal.getEnd_time().toString());

        db.insert(GOALS_TABLE,null,cv);

        return true;
    }
}

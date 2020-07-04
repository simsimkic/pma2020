package com.example.myapplication.settings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.model.Goal;

import java.io.StringBufferInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String GOALS_TABLE = "GOALS_TABLE";
    public static final String COLUMN_GOALS_NAME = "COLUMN_GOALS_NAME";
    public static final String COLUMN_GOALS_DISTANCE = "COLUMN_GOALS_DISTANCE";
    public static final String COLUMN_GOALS_DURATION = "COLUMN_GOALS_DURATION";
    public static final String COLUMN_GOALS_ARCHIVED = "COLUMN_GOALS_ARCHIVED";
    public static final String COLUMN_GOALS_END_TIME = "COLUMN_GOALS_END_TIME";
    public static final String COLUMN_GOALS_ID = "COLUMN_GOALS_ID";
    public static final String COLUMN_GOALS_USERNAME = "COLUMN_GOALS_USERNAME";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "running.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + GOALS_TABLE + " (" + COLUMN_GOALS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_GOALS_NAME + " TEXT, "+ COLUMN_GOALS_DISTANCE +" REAL, " +
                COLUMN_GOALS_DURATION +" REAL, " + COLUMN_GOALS_ARCHIVED +" INTEGER, " + COLUMN_GOALS_END_TIME + " TEXT, " + COLUMN_GOALS_USERNAME + " INTEGER)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addGoal(Goal goal, String username){
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
        cv.put(COLUMN_GOALS_USERNAME, username);

        db.insert(GOALS_TABLE,null,cv);

        return true;
    }

    public void clear(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("DROP TABLE IF EXISTS GOALS_TABLE");
        onCreate(db);
    }

    public List<Goal> getGoalsByUser(String id){
        List<Goal> returnGoal = new ArrayList<Goal>();

        Log.e("LOG","usao da ispisem :" + id);
        String queryString = "SELECT * FROM " + GOALS_TABLE + " WHERE column_goals_username='" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.rawQuery(queryString,null);
        Log.e("LOG","usao da ispisem :" + id);
        if(cursor.moveToFirst()){
            do {
                String name = cursor.getString(1);
                Double distance = cursor.getDouble(2);
                Double duration = cursor.getDouble(3);
                int archived = cursor.getInt(4);
                Log.e("LOG","Ispisujem boolean ... : :  :" + archived);
                boolean arch;
                if(archived == 1){
                    arch = true;
                }else{
                    arch = false;
                }
                String time = cursor.getString(5);
                Log.e("LOG","usao da ispisem ime :" + name);
                Log.e("LOG","usao da ispisem ime :" + distance);
                Goal goalTemp = new Goal(name,distance,duration,arch,time);
                returnGoal.add(goalTemp);
            } while (cursor.moveToNext());
        }else{
            //fail case
        }
        cursor.close();
        db.close();
        return returnGoal;
    }
}

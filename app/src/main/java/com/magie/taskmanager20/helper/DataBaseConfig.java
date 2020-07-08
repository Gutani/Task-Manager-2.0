package com.magie.taskmanager20.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseConfig extends SQLiteOpenHelper {

    public static int VERSION = 2;
    public static String NAME_DB = "DB_TASKS_MANAGER";
    public static String TABLE_TASKS = "tasks_manager";

    public DataBaseConfig(@Nullable Context context) {
        super(context, NAME_DB, null, (int) VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Used on the first time to create the database
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_TASKS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL);";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Success to create table");
        }catch (Exception e ){
            Log.i("INFO DB", "Error while creating table" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Used to create more data

    }
}

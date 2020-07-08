package com.magie.taskmanager20.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.magie.taskmanager20.model.Tasks;

import java.util.ArrayList;
import java.util.List;

public class DAOTask implements IDAOTask {
    private SQLiteDatabase write, read;
    public DAOTask(Context context) {
        DataBaseConfig dataBaseConfig = new DataBaseConfig(context);
        write = dataBaseConfig.getWritableDatabase();
        read = dataBaseConfig.getReadableDatabase();
    }

    @Override
    public boolean save(Tasks tasks) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", tasks.getNameTask());
        try{
            write.insert(DataBaseConfig.TABLE_TASKS, null, contentValues);
        }catch (Exception e){
            Log.i("Error", " - While Saving - " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Tasks tasks) {

        //Not Used Method BECAUSE UNABLE TO IMPLEMENT SELECTION OF TASK
        String[] args = {tasks.getId().toString()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", tasks.getNameTask());
        try{
            write.update(DataBaseConfig.TABLE_TASKS, contentValues, "id=?", args );
        }catch (Exception e){
            Log.i("Error", " - While Updating - " + e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public boolean delete(Tasks tasks) {
        String[] args = {tasks.getId().toString()};
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", tasks.getNameTask());
        try{
            write.delete(DataBaseConfig.TABLE_TASKS, "id=?", args );
            Log.i("Success", " - While Deleting");
        }catch (Exception e){
            Log.i("Error", " - While Delete - " + e.getMessage());
            return false;
        }
        return true;

    }

    @Override
    public List<Tasks> list() {
        List<Tasks> listTasks = new ArrayList<>();

        String sql = "SELECT * FROM " + DataBaseConfig.TABLE_TASKS;
        @SuppressLint("Recycle") Cursor cursor = read.rawQuery(sql, null );

        while (cursor.moveToNext()){
            Tasks tasks1 = new Tasks();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));

            tasks1.setId(id);
            tasks1.setNameTask(name);

            listTasks.add(tasks1);
        }
        return listTasks;
    }
}

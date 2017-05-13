package com.example.sayefreyadh.austhub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Asif Imtiaz Shaafi on February, 2017.
 * Email: a15shaafi.209@gmail.com
 */

public class RoutineDatabaseCRUD {

    public static final String TABLE_NAME = "routinetable";
    public static final String ID = "id";
    public static final String SEMESTER = "semester";
    public static final String ROUTINE = "routine";
    public static String[] ALL_COLUMNS = {
            SEMESTER , ROUTINE
    };


    public static String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SEMESTER + " TEXT, " +
            ROUTINE + " TEXT " +
            "); ";

    private SQLiteDatabase mSQLiteDatabase;
    private CustomDatabaseHelper mCustomDatabaseHelper;

    public RoutineDatabaseCRUD(Context context)
    {
        mCustomDatabaseHelper = new CustomDatabaseHelper(context);
        mSQLiteDatabase = mCustomDatabaseHelper.getWritableDatabase();
    }



    public void addRoutine(String semester , String routine)
    {
        ContentValues values = new ContentValues();
        values.put(SEMESTER , semester);
        values.put(ROUTINE , routine);

        SQLiteDatabase sqLiteDatabase = mCustomDatabaseHelper.getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME , null , values);
        sqLiteDatabase.close();
    }


    public String getRoutineData(String semester)
    {
        String data = "";
        mSQLiteDatabase = mCustomDatabaseHelper.getWritableDatabase();

        Cursor cursor = mSQLiteDatabase.query(
                TABLE_NAME,
                ALL_COLUMNS,
                SEMESTER+"=?",
                new String[]{semester},
                null,
                null,
                null
        );

        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex(SEMESTER))!= null)
            {
                //id = cursor.getInt(cursor.getColumnIndex(ID));
                //semesterTemp = cursor.getString(cursor.getColumnIndex(SEMESTER));
                data = cursor.getString(cursor.getColumnIndex(ROUTINE));
            }
        }
        cursor.close();
        mSQLiteDatabase.close();
        Log.d("test routine", data);
        return data;
    }

    public boolean isDatabaseEmpty()
    {
        mSQLiteDatabase = mCustomDatabaseHelper.getWritableDatabase();
        return DatabaseUtils.queryNumEntries(mSQLiteDatabase, TABLE_NAME)==0;

    }


}

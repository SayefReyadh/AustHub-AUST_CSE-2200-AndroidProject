package com.example.sayefreyadh.austhub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.sayefreyadh.austhub.database.RoutineDatabaseCRUD.CREATE_TABLE;
import static com.example.sayefreyadh.austhub.database.RoutineDatabaseCRUD.TABLE_NAME;
import static com.example.sayefreyadh.austhub.model.coursemodel.CourseTableConstants.CREATE_TABLE_COURSE;
import static com.example.sayefreyadh.austhub.model.coursemodel.CourseTableConstants.DROP_TABLE_COURSE;

/**
 * Created by SayefReyadh on 1/31/2017.
 */

public class CustomDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "austhub2.db";

    public CustomDatabaseHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        ///creating routine table
        sqLiteDatabase.execSQL(CREATE_TABLE);

        ///creating course table
        sqLiteDatabase.execSQL(CREATE_TABLE_COURSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL(DROP_TABLE_COURSE);
        onCreate(sqLiteDatabase);
    }
//
//    public void addRoutine(String semester , String routine)
//    {
//        ContentValues values = new ContentValues();
//        values.put(SEMESTER , semester);
//        values.put(ROUTINE , routine);
//
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        sqLiteDatabase.insert(TABLE_NAME , null , values);
//        sqLiteDatabase.close();
//    }
//
//
//    public String getRoutineData(String semester)
//    {
//        String data = new String();
//        mSQLiteDatabase = getWritableDatabase();
//
//        Cursor cursor = mSQLiteDatabase.query(
//                TABLE_NAME,
//                ALL_COLUMNS,
//                SEMESTER+"=?",
//                new String[]{semester},
//                null,
//                null,
//                null
//        );
//
//        while(cursor.moveToNext())
//        {
//            if(cursor.getString(cursor.getColumnIndex(SEMESTER))!= null)
//            {
//                //id = cursor.getInt(cursor.getColumnIndex(ID));
//                //semesterTemp = cursor.getString(cursor.getColumnIndex(SEMESTER));
//                data = cursor.getString(cursor.getColumnIndex(ROUTINE));
//            }
//        }
//        cursor.close();
//        mSQLiteDatabase.close();
//        Log.d("test routine", data);
//        return data;
//    }
//
//    public boolean isDatabaseEmpty()
//    {
//        mSQLiteDatabase = getWritableDatabase();
//        return DatabaseUtils.queryNumEntries(mSQLiteDatabase, TABLE_NAME)==0;
//    }

}

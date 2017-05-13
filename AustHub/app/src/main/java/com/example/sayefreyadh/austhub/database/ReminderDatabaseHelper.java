package com.example.sayefreyadh.austhub.database;

/**
 * Created by SayefReyadh on 1/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sayefreyadh.austhub.model.remindermodel.ReminderItem;

import java.util.ArrayList;
import java.util.List;

public class ReminderDatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "austhub.db";
    private static final String TABLE_NAME = "reminder";
    private static final String ID = "id";
    private static final String SUBJECT = "subject";
    private static final String DETAILS = "details";
    private static final String DATE = "date";
    private static final String TIME = "time";

    private SQLiteOpenHelper mSQLiteOpenHelper;
    private SQLiteDatabase mSQLiteDatabase;

    //shaafi

    //end

    public static String[] ALL_COLUMNS = {
            ID , SUBJECT , DETAILS , DATE , TIME
    };



    public ReminderDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME , factory, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SUBJECT + " TEXT, " +
                DETAILS + " TEXT, " +
                DATE + " TEXT, " +
                TIME + " TEXT " +
                "); ";

        sqLiteDatabase.execSQL(query);
        mSQLiteDatabase = sqLiteDatabase;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

//    public void addReminder(ReminderItem reminder)
//    {
//        ContentValues values = new ContentValues();
//        values.put(SUBJECT , reminder.getSubject());
//        values.put(DETAILS , reminder.getDetails());
//        values.put(DATE , reminder.getDate());
//        values.put(TIME , reminder.getTime());
//
//        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
//        sqLiteDatabase.insert(TABLE_NAME , null , values);
//        sqLiteDatabase.close();
//    }

    public int addReminder(ReminderItem reminder)
    {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put(SUBJECT , reminder.getSubject());
        values.put(DETAILS , reminder.getDetails());
        values.put(DATE , reminder.getDate());
        values.put(TIME , reminder.getTime());

        int id = (int) mSQLiteDatabase.insert(TABLE_NAME , null , values);
        closeDatabase();

        return id;
    }

    public void deleteReminder(int id)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ID + "=\"" + id +"\";");
        sqLiteDatabase.close();
    }

    public void deleteReminder(String subject)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + SUBJECT + "=\"" + subject +"\";");
        sqLiteDatabase.close();
    }

    public List<ReminderItem> getReminderData()
    {
        List<ReminderItem> data = new ArrayList<>();
        mSQLiteDatabase = getWritableDatabase();
        ///String query = "SELECT * FROM " + TABLE_NAME + " WHERE 1";
        ///Cursor cursor = sqLiteDatabase.rawQuery(query , null);

        Cursor cursor = mSQLiteDatabase.query(
                TABLE_NAME,
                ALL_COLUMNS,
                null,
                null,
                null,
                null,
                null

        );
        int id;
        String subject , details , date , time;

        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex(SUBJECT))!= null)
            {
                id = cursor.getInt(cursor.getColumnIndex(ID));
                subject = cursor.getString(cursor.getColumnIndex(SUBJECT));
                details = cursor.getString(cursor.getColumnIndex(DETAILS));
                date = cursor.getString(cursor.getColumnIndex(DATE));
                time = cursor.getString(cursor.getColumnIndex(TIME));
                ReminderItem item = new ReminderItem(id , subject , details , date , time);
                data.add(item);
            }
        }
        cursor.close();
        mSQLiteDatabase.close();
        return data;
    }

    public ReminderItem getReminderDataById(int id)
    {
        openDatabase();

        Cursor cursor = mSQLiteDatabase.query(
                TABLE_NAME,
                ALL_COLUMNS,
                ID + "=?",
                new String[] {String.valueOf(id)},
                null,
                null,
                ID + " DESC"

        );
        String subject , details , date , time;
        ReminderItem item = null;
        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndex(SUBJECT))!= null)
            {
                id = cursor.getInt(cursor.getColumnIndex(ID));
                subject = cursor.getString(cursor.getColumnIndex(SUBJECT));
                details = cursor.getString(cursor.getColumnIndex(DETAILS));
                date = cursor.getString(cursor.getColumnIndex(DATE));
                time = cursor.getString(cursor.getColumnIndex(TIME));
                item = new ReminderItem(id , subject , details , date , time);

            }
        }
        cursor.close();
        closeDatabase();
        return item;
    }

    public boolean isDatabaseEmpty()
    {
        mSQLiteDatabase = getWritableDatabase();
        return DatabaseUtils.queryNumEntries(mSQLiteDatabase, TABLE_NAME)==0;
    }

    private void openDatabase() {
        mSQLiteDatabase = this.getWritableDatabase();
    }

    private void closeDatabase() {
        mSQLiteDatabase.close();
    }

}

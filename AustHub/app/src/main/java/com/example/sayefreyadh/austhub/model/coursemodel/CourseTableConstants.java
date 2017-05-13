package com.example.sayefreyadh.austhub.model.coursemodel;

/**
 * Created by Asif Imtiaz Shaafi on 12/14/2016.
 * Email: a15shaafi.209@gmail.com
 */

public class CourseTableConstants {

    public static final String TABLE_NAME = "courses";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SEMESTER = "semester";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_COURSE = "courses";
    public static final String COLUMN_CREDIT = "course_credits";

    public static String[] ALL_COLUMNS = {
        COLUMN_YEAR, COLUMN_SEMESTER, COLUMN_COURSE, COLUMN_CREDIT
    };


    public static final String CREATE_TABLE_COURSE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " ( " +
            COLUMN_ID + " INTEGER PRIMARY KEY, " +
            COLUMN_YEAR + " INTEGER, " +
            COLUMN_SEMESTER + " INTEGER, " +
            COLUMN_COURSE + " TEXT, " +
            COLUMN_CREDIT + " TEXT ); ";

    public static final String DROP_TABLE_COURSE = "DROP TABLE IF EXISTS " + TABLE_NAME;
}

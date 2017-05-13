package com.example.sayefreyadh.austhub.user;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.model.routinemodel.RoutineModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by SayefReyadh on 1/7/2017.
 */

public class UserData {
    private static String userName = "AustHub";
    private static String userSemesterAndSection = "22C";
    private static String userSemesterRoutine;
    private static HashMap<String, List<RoutineModel>> daysRoutine;
    private static final String LAB_RESULT_BASE_URL = "http://www.aust.edu/result/cse_s_";
    private static final String THEORY_RESULT_BASE_URL = "http://www.aust.edu/result/cse_t_";
    private static final String CARRY_RESULT_BASE_URL = "http://www.aust.edu/result/cse_c_";
    private static final String PHP = ".php";


    private static String userYear = "2";
    private static String userSemester = "2";
    private static String userSection = "C";

    public static HashMap<String, List<RoutineModel>> getDaysRoutine() {
        return daysRoutine;
    }

    public static void setDaysRoutine(HashMap<String, List<RoutineModel>> daysRoutine) {
        UserData.daysRoutine = daysRoutine;
    }

    public static String getUserSemesterAndSection(Context context) {
        updateUserAcademicStatus(context);
        userSemesterAndSection = userYear+userSemester+userSection;
        return userSemesterAndSection;
    }

    public static void setUserSemesterAndSection(String userSemesterAndSection) {
        UserData.userSemesterAndSection = userSemesterAndSection;
    }

    public static String getUserSemesterRoutine() {
        return userSemesterRoutine;
    }

    public static void setUserSemesterRoutine(String userSemesterRoutine) {
        UserData.userSemesterRoutine = userSemesterRoutine;
    }

    public static String getUserName(Context context, Activity activity) {
        if (userName == null)
        {
            SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
            userName = sharedPref.getString(context.getResources().getString(R.string.std_name), "AustHub");
        }
        return userName;
    }

    public static void setUserName(String userName) {
        UserData.userName = userName;
    }

    public static String getLabResultBaseUrl(Context context) {
        updateUserAcademicStatus(context);
        return LAB_RESULT_BASE_URL + userYear + "_" + userSemester + PHP;
    }

    public static String getTheoryResultBaseUrl(Context context) {
        updateUserAcademicStatus(context);
        return THEORY_RESULT_BASE_URL+ userYear + "_" + userSemester + PHP;
    }

    public static String getCarryResultBaseUrl(Context context) {
        updateUserAcademicStatus(context);
        return CARRY_RESULT_BASE_URL+ userYear + "_" + userSemester + PHP;
    }



    public static String getUserYear(Context context) {
        updateUserAcademicStatus(context);
        return userYear;
    }
    public static String getUserSemester(Context context) {
        updateUserAcademicStatus(context);

        return userSemester;
    }

    public static void updateUserAcademicStatus(Context context)
    {
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(context);

        UserData.userYear =
                preferences.getString(context.getResources().getString(R.string.std_year), "0");

        UserData.userSemester =
                preferences.getString(context.getResources().getString(R.string.std_sem), "0");

        UserData.userSection =
                preferences.getString(context.getResources().getString(R.string.std_sec), "a");
    }

}

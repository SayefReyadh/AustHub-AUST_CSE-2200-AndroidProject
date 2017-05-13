package com.example.sayefreyadh.austhub.model.remindermodel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SayefReyadh on 1/20/2017.
 */

public class ReminderData {

    ///Dummy Data Source

    public static String[] subjects = {"NM" , "CA"};
    public static String[] details = {"Numerical" , "Computer"};
    public static String[] date = {"1/1/17" , "2/2/17"};
    public static String[] time = {"9:00" , "8:00"};

    public static List<ReminderItem> reminderData = new ArrayList<>();
    public static boolean isNotSet = false;
    public static List<ReminderItem> getData()
    {
        if(!isNotSet)
        {
            List<ReminderItem> data = new ArrayList<>();
            for(int j = 0 ; j < subjects.length ; j++)
            {
                ReminderItem ob = new ReminderItem(0 , subjects[j] , details[j] , date[j] , time[j]);
                ///ReminderActivity.REMINDER_DATABASE_HELPER.addReminder(ob);
                data.add(ob);
            }
            isNotSet = true;
            reminderData = data;
            return data;
        }
        else
            return reminderData;
    }

    public static void addData(ReminderItem item)
    {
        reminderData.add(item);
    }

}

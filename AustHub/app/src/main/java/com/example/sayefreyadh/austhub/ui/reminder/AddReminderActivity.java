package com.example.sayefreyadh.austhub.ui.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.database.ReminderDatabaseHelper;
import com.example.sayefreyadh.austhub.model.remindermodel.ReminderItem;
import com.example.sayefreyadh.austhub.utilities.AlarmReceiver;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddReminderActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener, View.OnClickListener { ///implements View.OnClickListener

    public static final String REMINDER = "id";
    public static final String REMINDER_ID = "reminder_id";
    public static final int ADD_REQUEST = 1110;
    AlarmManager mAlarmManager;


    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_SUBJECT = "EXTRA_SUBJECT";
    private static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_TIME = "EXTRA_TIME";

    private Uri soundUri;

    private NotificationCompat.Builder notificationBuilder;
    private static int uniqueID = 123123;

    private Calendar mCalendar;
    private int mYear, mMonth, mHour, mMinute, mDay;
    String givenDateString;// = "010704120856+0600";
    String mYearString, mMonthString, mHourString, mMinuteString, mDayString;

    private String mSubject;
    private String mDetails;
    private String mTime;
    private String mDate;

    private EditText reminderSubject , reminderDetails;
    private TextView reminderDate , reminderTime;
    private Button addReminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_reminder);
        mAlarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        ///createReminderNotificationAlarm();
        soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        mCalendar = Calendar.getInstance();
        mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = mCalendar.get(Calendar.MINUTE);
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DATE);

        mDate = mDay + "/" + mMonth + "/" + mYear;
        mTime = mHour + ":" + mMinute;

        addReminderButton = (Button) findViewById(R.id.reminder_add_button);
        reminderSubject = (EditText) findViewById(R.id.reminder_subject);
        reminderDetails = (EditText) findViewById(R.id.reminder_details);
        reminderDate = (TextView) findViewById(R.id.reminder_date);
        reminderTime = (TextView) findViewById(R.id.reminder_time);

        reminderDate.setText(mDate);
        reminderTime.setText(mTime);
        ///addReminderButton.setOnClickListener(addReminderButtonListiner);
        addReminderButton.setOnClickListener(AddReminderActivity.this);
    }

    //THe Project Crush When U Add this listener??

    @Override
    public void onClick(View view) {

        String subject = reminderSubject.getText().toString();
        String details = reminderDetails.getText().toString();
        String date = reminderDate.getText().toString();
        String time = reminderTime.getText().toString();


        //adding to database
        ReminderDatabaseHelper database = new ReminderDatabaseHelper(this , null , null , 0);



        if(subject.compareTo("")!=0 && details.compareTo("") !=0 ) {
            ReminderItem item = new ReminderItem(0, subject, details, date, time);
            int id = database.addReminder(item);
            ///ReminderActivity.REMINDER_DATABASE_HELPER.addReminder(item);
            ///ReminderData.addData(item);

            Toast.makeText(this, "Added", Toast.LENGTH_LONG).show();
//            Long t = System.currentTimeMillis() + 10 * 1000;
            Long t = System.currentTimeMillis() + timeToLongVariableMethod();

            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra(REMINDER_ID, id);

            PendingIntent pendingIntent =
                    PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            mAlarmManager.set(AlarmManager.RTC_WAKEUP, t, pendingIntent);


            intent = new Intent();
            item.setId(id);
            //intent.putExtra(REMINDER, item);
            setResult(ADD_REQUEST, intent);

            Toast.makeText(this, "Reminder Added at " + id, Toast.LENGTH_LONG).show();

            ///createReminderNotificationAlarm(timeToLongVariableMethod() , subject , details , date , time);

            finish();
        }
        else
        {
            Toast.makeText(this, "Please Complete All The Box " , Toast.LENGTH_LONG).show();


        }

    }


    // On clicking Time picker
    public void setTime(View v){

        Calendar now = Calendar.getInstance();
        TimePickerDialog tpd = TimePickerDialog.newInstance(
                this,
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                false
        );
        tpd.setThemeDark(false);
        tpd.show(getFragmentManager(), "Timepickerdialog");
    }

    // On clicking Date picker
    public void setDate(View v){
        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

    // Obtain time from time picker
    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        Toast.makeText(this, mHour +" " + mMinute, Toast.LENGTH_LONG).show();
        if (minute < 10) {
            mTime = hourOfDay + ":" + "0" + minute;
        } else {
            mTime = hourOfDay + ":" + minute;
        }
        reminderTime.setText(mTime);
    }

    // Obtain date from date picker
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear ++;
        mDay = dayOfMonth;
        mMonth = monthOfYear;
        mYear = year;
        mDate = dayOfMonth + "/" + monthOfYear + "/" + year;
        Toast.makeText(this, mDay +" " + mMonth + " "+ mYear, Toast.LENGTH_LONG).show();
        reminderDate.setText(mDate);
    }


    public String formatTimeInString(int time)
    {
        String str = new String();
        if(time % 100 >= 10)
        {
            str = ""+ time %100;
        }
        else
        {
            str = "0" + time %100;
        }
        return str;
    }


//    public void createReminderNotificationAlarm(long time , String subject , String details , String date , String timeString)
//    {
//        notificationBuilder = new NotificationCompat.Builder(this);
//        notificationBuilder.setAutoCancel(true);
//        notificationBuilder.setSmallIcon(R.drawable.logo);
//        notificationBuilder.setTicker("Reminder");
//        notificationBuilder.setWhen(time);
//        notificationBuilder.setContentTitle(subject);
//        notificationBuilder.setContentText(details);
//        notificationBuilder.setSound(soundUri);
//        Intent intent = new Intent(this, ReminderActivity.class);
//
//        Bundle extras = new Bundle();
//        extras.putString(EXTRA_SUBJECT, subject);
//        extras.putString(EXTRA_DETAILS, details);
//        extras.putString(EXTRA_DATE , date);
//        extras.putString(EXTRA_TIME , timeString);
//        intent.putExtra(BUNDLE_EXTRAS, extras);
//
//        //setAlarm(this , intent , new Random().nextInt() , time);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        notificationBuilder.setContentIntent(pendingIntent);
//
//        Toast.makeText(this, "" + System.currentTimeMillis() , Toast.LENGTH_LONG).show();
//
//        //Builds notification and issues it
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(uniqueID, notificationBuilder.build());
//    }


    public long timeToLongVariableMethod()
    {
        mYearString = formatTimeInString(mYear);
        mMonthString = formatTimeInString(mMonth);
        mDayString = formatTimeInString(mDay);
        mHourString = formatTimeInString(mHour);
        mMinuteString = formatTimeInString(mMinute);

        Toast.makeText(this, mMinuteString + " : " + mHourString + " : " + mDayString + " : " + mMonthString + " : " + mYearString, Toast.LENGTH_LONG).show();

        givenDateString = mYearString+mMonthString+mDayString+mHourString+mMinuteString+"00"+"+0600";
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssZ");
        try {
            Date reminderDate = sdf.parse(givenDateString);
            long timeInMilliseconds = reminderDate.getTime();
            ///System.out.println("Date in milli :: " + timeInMilliseconds);
            Toast.makeText(this, "Returned In Long" + timeInMilliseconds, Toast.LENGTH_LONG).show();
            return timeInMilliseconds;
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(this, "Not Returned in long", Toast.LENGTH_LONG).show();

        }
        return 0;
    }


    public static void setAlarm(Context context, Intent intent, int notificationId, long time) {
        intent.putExtra("NOTIFICATION_ID", notificationId);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
    }

}

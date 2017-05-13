package com.example.sayefreyadh.austhub.utilities;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.WakefulBroadcastReceiver;


import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.database.ReminderDatabaseHelper;
import com.example.sayefreyadh.austhub.model.remindermodel.ReminderItem;
import com.example.sayefreyadh.austhub.ui.reminder.ReminderDetailActivity;



public class AlarmReceiver extends WakefulBroadcastReceiver {

    public static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    public static final String EXTRA_SUBJECT = "EXTRA_SUBJECT";
    public static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_TIME = "EXTRA_TIME";
    public static final String REMINDER_ID = "reminder_id";

    private ReminderDatabaseHelper mReminderDatabaseHelper;
    private Uri soundUri;

    @Override
    public void onReceive(Context context, Intent intent) {

        mReminderDatabaseHelper = new ReminderDatabaseHelper(context, null , null , 0);

        int id = intent.getIntExtra(REMINDER_ID, -1);

        ReminderItem model = mReminderDatabaseHelper.getReminderDataById(id);

        if (model != null)
            createNotificationWithAlarm(context, id);
        else {
            createNotificationWithAlarm(context, 1);
        }


    }

    private void createNotificationWithAlarm(Context context, int reminderID) {

        Intent reminderDetailIntent =
                new Intent(context, ReminderDetailActivity.class);

        ReminderItem model = mReminderDatabaseHelper.getReminderDataById(reminderID);

        ///if model is null, means the reminder was deleted, so don't need to display the notification
        if (model == null) return;

        ///putting data for displaying the reminder in details
        Bundle extras = new Bundle();
        extras.putString(EXTRA_SUBJECT, model.getSubject());
        extras.putString(EXTRA_DETAILS, model.getDetails());
        extras.putString(EXTRA_DATE, model.getDate());
        extras.putString(EXTRA_TIME, model.getTime());
        reminderDetailIntent.putExtra(BUNDLE_EXTRAS, extras);

        soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(context, reminderID, reminderDetailIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setTicker("AustHub Reminder")
                        .setContentTitle(model.getSubject())
                        .setContentText(model.getDetails())
                        .setSound(soundUri)
                        .setSmallIcon(R.drawable.logo)
                        .setAutoCancel(true);

        builder.setContentIntent(pendingIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(reminderID, builder.build());
    }
}

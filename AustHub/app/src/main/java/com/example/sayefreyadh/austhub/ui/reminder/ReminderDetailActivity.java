package com.example.sayefreyadh.austhub.ui.reminder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;

public class ReminderDetailActivity extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_SUBJECT = "EXTRA_SUBJECT";
    private static final String EXTRA_DETAILS = "EXTRA_DETAILS";
    private static final String EXTRA_DATE = "EXTRA_DATE";
    private static final String EXTRA_TIME = "EXTRA_TIME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);

        Bundle extras = getIntent().getBundleExtra(BUNDLE_EXTRAS);

        ((TextView)findViewById(R.id.reminder_subject_detail_activity)).setText(extras.getString(EXTRA_SUBJECT));
        ((TextView)findViewById(R.id.reminder_details_detail_activity)).setText(extras.getString(EXTRA_DETAILS));
        ((TextView)findViewById(R.id.reminder_date_detail_activity)).setText(extras.getString(EXTRA_DATE));
        ((TextView)findViewById(R.id.reminder_time_detail_activity)).setText(extras.getString(EXTRA_TIME));


    }
}
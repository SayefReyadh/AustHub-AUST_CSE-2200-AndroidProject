package com.example.sayefreyadh.austhub.ui.routine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.database.CustomDatabaseHelper;
import com.example.sayefreyadh.austhub.database.RoutineDatabaseCRUD;
import com.example.sayefreyadh.austhub.user.UserData;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static android.R.layout.simple_list_item_1;

public class RoutineActivity extends AppCompatActivity {
    private static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    private static final String EXTRA_DAY = "EXTRA_DAY";
    private RoutineDatabaseCRUD mRoutineDatabaseCRUD;
    public ListView listView;
    private String[] days = {"Saturday" , "Sunday" , "Monday" , "Tuesday" ,"Wednesday" , "Thursday"};

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);
        ((TextView) findViewById(R.id.user_year_semester_section)).setText("Semester : " + UserData.getUserSemesterAndSection(getBaseContext()));
        //setTimeInRoutine(); // comment out at 1/ 30 / 17
        ///This object routine data can be created in the settings menu when the settings for semester is updated
        ///What do u think shaafi bae :* :P ??

        mRoutineDatabaseCRUD = new RoutineDatabaseCRUD(this);

        /*if(mCustomDatabaseHelper.isDatabaseEmpty()) {
            mCustomDatabaseHelper.addRoutine("22C", RoutineProvider.getCSE22C());
            ///set all semester routine here
        }
        */

        String routineDataString = mRoutineDatabaseCRUD.getRoutineData(UserData.getUserSemesterAndSection(getBaseContext()));
        UserData.setUserSemesterRoutine(routineDataString);

        ListAdapter daysListAdapter = new ArrayAdapter<String>(this , simple_list_item_1 , days);
        listView = (ListView) findViewById(R.id.routine_list_item_days);
        listView.setAdapter(daysListAdapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String day = String.valueOf(adapterView.getItemAtPosition(position));
                        Toast.makeText(RoutineActivity.this , day , Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(RoutineActivity.this , RoutineMainActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString(EXTRA_DAY, day);
                        i.putExtra(BUNDLE_EXTRAS, extras);
                        RoutineActivity.this.startActivity(i);
                    }
                }
        );
    }


    @Override
    protected void onResume() {
        super.onResume();
        String routineDataString = mRoutineDatabaseCRUD.getRoutineData(UserData.getUserSemesterAndSection(getBaseContext()));
        UserData.setUserSemesterRoutine(routineDataString);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setTimeInRoutine()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MMMM/d/E" , Locale.US);
        String date = simpleDateFormat.format(cal.getTime());
        String values[] = date.split("/" , 0);

        ((TextView) findViewById(R.id.activity_routine_clock_year)).setText(values[0]);
        ((TextView) findViewById(R.id.activity_routine_clock_month)).setText(values[1]);
        ((TextView) findViewById(R.id.activity_routine_clock_date)).setText(values[2]);
        ((TextView) findViewById(R.id.activity_routine_clock_day)).setText(values[3]);
    }
}

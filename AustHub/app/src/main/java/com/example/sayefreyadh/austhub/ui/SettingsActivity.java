package com.example.sayefreyadh.austhub.ui;

import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.user.UserData;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ///if semester changes then do call this method

        //String userSemester;
        //userSemester = "22C";
        //UserData.setUserSemesterAndSection(userSemester);


        //adding the settings fragment
        getFragmentManager()
                .beginTransaction()
                .add(R.id.container_settings, new SettingsFragment())
                .commit();

    }

    public static class SettingsFragment extends PreferenceFragment{

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.settings);
        }
    }
}

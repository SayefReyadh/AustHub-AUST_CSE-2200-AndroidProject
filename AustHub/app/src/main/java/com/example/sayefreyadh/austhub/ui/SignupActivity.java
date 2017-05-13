package com.example.sayefreyadh.austhub.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.sayefreyadh.austhub.R;
import com.example.sayefreyadh.austhub.ui.homemenu.NavActivity;
import com.example.sayefreyadh.austhub.user.UserData;

import java.util.Arrays;
import java.util.List;

public class SignupActivity extends AppCompatActivity {

    public static final String USER_DETAILS = "user_details";

    //user name
    private TextInputLayout mUserNameLayout;
    private TextView mUserName;

    //user year
    private Spinner mYearSpinner;
    private int year = 0;

    //user semester
    private Spinner mSemesterSpinner;
    private int sem = 0;
    private ImageView mUplodedImage;

    //user section
    private Spinner mSectionSpinner;
    private String sec = "section_a";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUpYearSpinner();
        setUpSemesterSpinner();
        setSectionSpinner();
        setUpUserName_N_Email();

    }

    private void setUpYearSpinner() {
        mYearSpinner = (Spinner) findViewById(R.id.sign_up_user_year);


        List<String> years = Arrays.asList(getResources().getStringArray(R.array.std_year_title));

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mYearSpinner.setAdapter(arrayAdapter);

        mYearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                year = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setUpSemesterSpinner() {

        mSemesterSpinner = (Spinner) findViewById(R.id.sign_up_user_semester);

        List<String> years = Arrays.asList(getResources().getStringArray(R.array.std_sem_title));

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSemesterSpinner.setAdapter(arrayAdapter);

        mSemesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sem = i + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSectionSpinner() {
        mSectionSpinner = (Spinner) findViewById(R.id.sign_up_user_section);

        final List<String> sections = Arrays.asList(getResources().getStringArray(R.array.std_section_title));
        final List<String> sectionValues = Arrays.asList(getResources().getStringArray(R.array.std_section_value));

        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sections);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSectionSpinner.setAdapter(arrayAdapter);

        mSectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sec = sectionValues.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setUpUserName_N_Email() {
        mUserName = (TextView) findViewById(R.id.sign_up_user_name);

        mUserNameLayout = (TextInputLayout) findViewById(R.id.sign_up_user_name_layout);

    }

    public void performSignUp(View view) {

        String name = mUserName.getText().toString().trim();

        if (validateInputs(name) && year != 0 && sem != 0) {

            UserData.setUserName(name);

            PreferenceManager.setDefaultValues(this, R.xml.settings, true);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

            preferences.edit()
                    .putString(getString(R.string.std_year), String.valueOf(year))
                    .putString(getString(R.string.std_sem), String.valueOf(sem))
                    .putString(getString(R.string.std_sec), sec)
                    .apply();

            //saving user name and email

            SharedPreferences.Editor editor = getSharedPreferences(USER_DETAILS, MODE_PRIVATE).edit();
            editor.putString(getString(R.string.std_name), name)
                    .apply();

            startActivity(new Intent(this, NavActivity.class));
            finish();
        }

    }

    private boolean validateInputs(String name) {

//        boolean emailOk;
        boolean nameOk;

        //checkin if the name and email is not emplty and valid

        if (TextUtils.isEmpty(name)) {
            mUserNameLayout.setError("Please Enter Your Name!");
            nameOk = false;
        } else {
            nameOk = true;
            mUserNameLayout.setError(null);
        }
//
//        if (TextUtils.isEmpty(email) || !email.contains("@") || !email.contains(".com")) {
//            mUserEmailLayout.setError("Please Enter Your Valid Email!");
//            emailOk = false;
//        } else {
//            emailOk = true;
//            mUserEmailLayout.setError(null);
//        }

        return nameOk;
    }

    public void finishActivity(View view) {
        finish();
    }

}
